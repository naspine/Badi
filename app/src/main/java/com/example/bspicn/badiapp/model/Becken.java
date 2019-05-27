package com.example.bspicn.badiapp.model;

public class Becken {
    private String name;
    private double temperature;

    public double getTemperatur() {
        return temperature;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name + " " + this.temperature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemperature(double temperatur) {
        temperature = temperatur;
    }

    public double getTemperature() {
        return temperature;
    }



}
