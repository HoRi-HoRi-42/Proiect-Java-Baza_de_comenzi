package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Produs;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    /**
     * pentru aceasta clasa sunt generate instructiunile intr-un tip abstract, astfel se trimite prin parametru de tip T (type)
     * clasa care este trimisa de catre BLL (care se ocupa de catre validarea datelor)
     */
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        StringBuilder sb = new StringBuilder();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sb.toString());
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void inser_help(T t, StringBuilder sb) throws IllegalAccessException {
        for (Field field : t.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            Object value;

                value = field.get(t);
                sb.append(value + "' , '");
        }
    }

  public void insert(T t)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES ('" );

        try {
            inser_help(t,sb);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        sb.delete(sb.length() - 3, sb.length());
        sb.append(")");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void updateHelp(T t, StringBuilder s) throws IllegalAccessException {
        for (Field field : t.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            Object value;

                value = field.get(t);
                s.append(field.getName() + " = '" + value + "' , ");

        }
    }

    public void updateByID(T t, int id, String fld) throws IllegalAccessException {
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        updateHelp(t,sb);
        int auz= sb.length();
        int auz2= auz -2;
        sb.delete(auz, auz);
        sb.append("WHERE " + fld + " =?");
        System.out.println(sb.toString());
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


    public void delAjut(Connection conju, StringBuilder ssw, PreparedStatement stntm, int id) throws SQLException {
        conju = ConnectionFactory.getConnection();
        stntm = conju.prepareStatement(ssw.toString());
        stntm.setInt(1, id);
        stntm.executeUpdate();
    }

    public void delete(int id, String field)
    {
        Connection con = null;
        PreparedStatement st = null;

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");

        try {
           delAjut(con,sb,st,id);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(st);
            ConnectionFactory.close(con);
        }


    }

}
