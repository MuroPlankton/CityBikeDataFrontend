package com.miroplanting.citybikedatafrontend.station;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Station {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nimi")
    @Expose
    private String nimi;

    @SerializedName("osoite")
    @Expose
    private String osoite;

    @SerializedName("kaupunki")
    @Expose
    private String kaupunki;

    @SerializedName("namn")
    @Expose
    private String namn;

    @SerializedName("adress")
    @Expose
    private String adress;

    @SerializedName("stad")
    @Expose
    private String stad;

    @SerializedName("operator")
    @Expose
    private String operator;

    @SerializedName("capacity")
    @Expose
    private int capacity;

    @SerializedName("x")
    @Expose
    private double x;

    @SerializedName("y")
    @Expose
    private double y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
