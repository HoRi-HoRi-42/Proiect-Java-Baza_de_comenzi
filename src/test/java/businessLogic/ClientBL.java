package businessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import model.Client;
import businessLogic.validators.EmailVerificator;
import businessLogic.validators.Validator;

public class ClientBL {
    private List<Validator<Client>> validatori;
    private ClientDAO clntDAO;

    public ClientBL()
    {
        validatori = new ArrayList<Validator<Client>>();
        validatori.add(new EmailVerificator());

        clntDAO = new ClientDAO();
    }

    public List<Client> findAll()
    {
        List<Client> cl = clntDAO.findAll();

        return cl;
    }

    public void insert(Client cl)
    {
        for(Validator<Client> vl : validatori)
        {
            vl.validate(cl);
        }
        clntDAO.insert(cl);
    }


}