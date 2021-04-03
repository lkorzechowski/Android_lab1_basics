package com.orzechowski.lab1;

public class ModelOceny {
    private String nazwa;
    private int ocena;

    public String getNazwa() { return nazwa; }

    public void setNazwa(String nazwa) { this.nazwa = nazwa; }

    public int getOcena() { return ocena; }

    public void setOcena(int ocena) { this.ocena = ocena; }

    public ModelOceny(String nazwa) {
        this.nazwa = nazwa;
    }
}
