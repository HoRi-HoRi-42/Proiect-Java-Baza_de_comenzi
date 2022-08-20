package model;

public class Produs {
    private int id;


    private float pret;
    private int cantitate;
    private String nume_produs;

    public Produs() {
    }

    ;

    public Produs(int id, float pret, int cantitate, String nume_produs) {
        this.id = id;
        this.pret = pret;
        this.cantitate = cantitate;
        this.nume_produs = nume_produs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }


    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getNume_produs() {
        return nume_produs;
    }

    public void setNume_produs(String nume_produs) {
        this.nume_produs = nume_produs;
    }
}
