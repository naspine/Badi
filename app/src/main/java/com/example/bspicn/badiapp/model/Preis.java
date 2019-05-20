package com.example.bspicn.badiapp.model;

public class Preis {
     private String preis;

    @Override
    public String toString() {
        if (preis == null){
            return "Leider ist kein Preis vorhanden";
        }
        else{
            return this.preis;
        }

    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {

        this.preis = preis;
    }
}
