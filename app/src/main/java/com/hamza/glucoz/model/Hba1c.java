package com.hamza.glucoz.model;

public class Hba1c {
    float hba1c;
    String date;
    String time;

    public Hba1c(){

    }
    public Hba1c(float hba1c, String date, String time) {
        this.hba1c = hba1c;
        this.date = date;
        this.time = time;
    }

    public void setHba1c(float hba1c) {
        this.hba1c = hba1c;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getHba1c() {
        return hba1c;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
