package com.hamza.glucoz.model;

public class Cetones {
    float cetones;
    String date;
    String time;

    public Cetones(){

    }
    public Cetones(float cetones, String date, String time) {
        this.cetones = cetones;
        this.date = date;
        this.time = time;
    }

    public void setCetones(float hba1c) {
        this.cetones = hba1c;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getCetones() {
        return cetones;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
