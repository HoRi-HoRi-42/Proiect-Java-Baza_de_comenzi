package model;

public class Comanda {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_produs() {
        return id_produs;
    }

    public void setId_produs(int id_produs) {
        this.id_produs = id_produs;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    private int id_client;
    private int id_produs;
    private int cantitate;
    private float pret;

    public Comanda(){};
    
    public Comanda(int id, int id_client, int id_produs, int cantitate, float pret) {
        this.id = id;
        this.id_client = id_client;
        this.id_produs = id_produs;
        this.cantitate = cantitate;
        this.pret = pret;
    }


}
