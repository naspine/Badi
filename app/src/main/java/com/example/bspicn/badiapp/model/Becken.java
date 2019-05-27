package com.example.bspicn.badiapp.model;

import java.util.Objects;


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

    @Override
    public boolean equals(Object obj) {
        Becken becken = (Becken) obj;

        return Objects.equals(name, becken.name) &&
                Objects.equals(temperature, becken.temperature);
    }

}
