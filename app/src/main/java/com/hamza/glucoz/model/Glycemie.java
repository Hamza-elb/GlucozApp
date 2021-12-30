package com.hamza.glucoz.model;

import java.sql.Time;
import java.util.Date;

public class Glycemie {
    Float concentration;
    String date;
    String time;

    public Glycemie() {
    }

    public Glycemie(Float concentration, String date, String time) {
        this.concentration = concentration;
        this.date = date;
        this.time = time;
    }
    public Glycemie(Float concentration) {
        this.concentration = concentration;
    }

    public Glycemie(String time) {

        this.time = time;
    }
    public Float getConcentration() {
        return concentration;
    }

    public void setConcentration(Float concentration) {
        this.concentration = concentration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
