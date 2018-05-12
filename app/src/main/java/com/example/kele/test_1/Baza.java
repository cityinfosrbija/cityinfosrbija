package com.example.kele.test_1;

import java.io.Serializable;

public class Baza implements Serializable{
    private int id;
    private String vrsta;
    private String adresa;
    private String fiksni;
    private String mobilni;
    private String email;
    private String website;
    private String googleMap;
    private String ime;

    public Baza() {
    }

    public Baza(String vrsta, String adresa, String fiksni, String mobilni, String email, String website, String googleMap, String ime) {
        this.vrsta = vrsta;
        this.adresa = adresa;
        this.fiksni = fiksni;
        this.mobilni = mobilni;
        this.email = email;
        this.website = website;
        this.googleMap = googleMap;
        this.ime = ime;
    }

    public Baza(int id, String vrsta, String adresa, String fiksni, String mobilni, String email, String website, String googleMap, String ime) {
        this.id = id;
        this.vrsta = vrsta;
        this.adresa = adresa;
        this.fiksni = fiksni;
        this.mobilni = mobilni;
        this.email = email;
        this.website = website;
        this.googleMap = googleMap;
        this.ime = ime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrsta() {
        return vrsta;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getFiksni() {
        return fiksni;
    }

    public String getMobilni() {
        return mobilni;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getGoogleMap() {
        return googleMap;
    }

    public String getIme() {
        return ime;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setFiksni(String fiksni) {
        this.fiksni = fiksni;
    }

    public void setMobilni(String mobilni) {
        this.mobilni = mobilni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setGoogleMap(String googleMap) {
        this.googleMap = googleMap;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
}
