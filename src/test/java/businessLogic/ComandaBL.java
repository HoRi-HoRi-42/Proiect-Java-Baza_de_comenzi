package businessLogic;

import businessLogic.validators.StocVerificator;
import dao.ComandaDAO;
import model.Comanda;
import model.Produs;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ComandaBL {
    private List<StocVerificator> validatori;
    private ComandaDAO comndDAO;

    public ComandaBL()
    {
        validatori = new ArrayList<StocVerificator>();
        validatori.add(new StocVerificator());

        comndDAO = new ComandaDAO();
    }

    public Comanda findClientById(int id) {
        Comanda comnd = comndDAO.findById(id);

        return comnd;

    }

    public List<Comanda> findAll()
    {
        List<Comanda> cl = comndDAO.findAll();

        return cl;

    }




}
