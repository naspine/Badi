package com.example.bspicn.badiapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Badi {
    private int id;
    private String name;
    private List<Becken> becken = new ArrayList<>();
    private List<Oeffnungszeit> oeffnungszeit = new ArrayList<>();
    private List<Preis> preis = new ArrayList<>();
    private String adresses;
    private String kanton;
    private String ort;


    public Badi(int id, String Name, String ort, String kanton, String adresse1, String adresse2) {
        setId(id);
        setKanton(kanton);
        setName(Name);
        setOrt(ort);
        setAdresses(ort + " " + kanton + " " + adresse1 + " " + adresse2);
    }

    public Badi() {

    }

    public int getId() {
        return id;
    }

    public String getOrt() {
        return ort;
    }

    public  String getKanton() {
        return kanton;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.ort + " (" + this.kanton + ")";
    }

    public List<Becken> getBecken() {
        return becken;
    }

    public void setBecken(List<Becken> becken) {
        this.becken = becken;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKanton(String kanton) {
        this.kanton = kanton;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void addBecken(Becken beck) {
        becken.add(beck);
    }

    public void setOeffnungszeit(List<Oeffnungszeit> oeffnungszeit) {
        this.oeffnungszeit = oeffnungszeit;
    }

    public List<Oeffnungszeit> getOeffnungszeit() {
        return oeffnungszeit;
    }

    public void addOeffnungszeit(Oeffnungszeit zeit) {
        oeffnungszeit.add(zeit);
    }

    public void setPreis(List<Preis> preis) {
        this.preis = preis;
    }

    public List<Preis> getPreis() {
        return preis;
    }

    public void addPreis(Preis preise) {
        preis.add(preise);
    }

    public String getAdresses() {
        return adresses;
    }

    public void addAdress(Adresse adresse) {
        adresses = adresse.toString();
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Badi badi = (Badi) o;
        return id == badi.id &&
                Objects.equals(name, badi.name) &&
                Objects.equals(becken, badi.becken) &&
                Objects.equals(oeffnungszeit, badi.oeffnungszeit) &&
                Objects.equals(preis, badi.preis) &&
                Objects.equals(adresses, badi.adresses) &&
                Objects.equals(kanton, badi.kanton) &&
                Objects.equals(ort, badi.ort);
    }
}

