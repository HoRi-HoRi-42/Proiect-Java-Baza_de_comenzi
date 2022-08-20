package app;

import dao.ClientDAO;
import model.Client;
import presentation.Magazin;

public class App {
    public static void main (String args[]){
        Magazin mag = new Magazin();
        ClientDAO dd = new ClientDAO();
    }
}
