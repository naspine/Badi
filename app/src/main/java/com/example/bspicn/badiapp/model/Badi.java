package com.example.bspicn.badiapp.model;

import java.util.ArrayList;
import java.util.List;

public class Badi {
    private int id;
    private String name;
    private List<Becken> becken = new ArrayList<>();
    private String kanton;
    private String ort;

    public Badi(int id, String Name, String ort, String kanton) {
        setId(id);
        setKanton(kanton);
        setName(Name);
        setOrt(ort);
    }

    public Badi() {

    }

    public int getId() {
        return id;
    }

    public String getOrt() {
        return ort;
    }

    public String getKanton() {
        return kanton;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name + ", " +this.ort +" (" + this.kanton +")";
    }

    public List<Becken> getBecken() {
        return becken;
    }

    public void setBecken(List<Becken> becken) {
        becken = becken;
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
}
