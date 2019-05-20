package com.example.bspicn.badiapp.model;

public class Adresse {
    private String adresse1;
    private String adresse2;
    private String ort;
    private String kanton;
    private String adresse;

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setKanton(String kanton) {
        this.kanton = kanton;
    }

    public void setAdresse1(String adresse1) {
        this.adresse1 = adresse1;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getKanton() {
        return kanton;
    }

    public String getOrt() {
        return ort;
    }

    public String getAdresse1() {
        return adresse1;
    }

    public String getAdresse2() {
        return adresse2;
    }

    @Override
    public String toString() {
        return ort + " "+kanton + " "+adresse1+ " "+ adresse2;
    }

    public String getAdresse() {
        return ort + " "+kanton + " "+adresse1+ " "+ adresse2 ;
    }
}
