package businessLogic.validators;

import java.util.List;
import java.util.NoSuchElementException;


import businessLogic.validators.StocVerificator;
import businessLogic.validators.Validator;
import dao.ProdusDAO;
import model.Produs;

public class ProdusBL {
    private Validator<Produs> v;
    private ProdusDAO pDAO;

    public ProdusBL()
    {
        v = new StocVerificator();
        pDAO = new ProdusDAO();
    }

    public Produs findProdusById(int id)
    {
        Produs p = pDAO.findById(id);
        if(p == null)
        {
            throw new NoSuchElementException("Produsul cu id =" + id + " nu a fost gasit");
        }
        return p;
    }

    public List<Produs> findAll()
    {
        List<Produs> p = pDAO.findAll();
        if (p == null) {
            throw new NoSuchElementException("Nu s-a putut afisa tabela");
        }
        return p;

    }

    public void insert(Produs p)
    {
        v.validate(p);
        pDAO.insert(p);
    }

    public void update(Produs p, int id, String fld)
    {
        if(findProdusById(id) != null)
        {
            try {
                pDAO.updateByID(p, id, fld);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        else
            throw new NoSuchElementException("Produsul cu id =" + id + " nu exista");
    }

    public void delete(int id, String fld)
    {
        if(findProdusById(id) != null)
        {
            pDAO.delete(id, fld);
        }
        else
            throw new NoSuchElementException("Produsul cu id =" + id + " nu exista");
    }

}