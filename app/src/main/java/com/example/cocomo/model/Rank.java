package com.example.cocomo.model;

import java.io.Serializable;

public class Rank implements Serializable {
    private double value;
    private String title;

    public Rank(double value, String title) {
        this.value = value;
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
