package com.example.kele.test_1;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kele on 7/22/2017.
 */

public class IzaberiGrad extends Application {
    private static String urlPoNazivu;
    private static String urlPoVrstama;
    private static String urlBanke;
    private static String urlBankomati;
    public static Map<String,Integer> ikonice = createMap();
    private static Map<String, Integer> createMap()
    {
        Map<String,Integer> myMap = new HashMap<String,Integer>();
        myMap.put("Opština", R.drawable.aa);
        myMap.put("Gradske ustanove", R.drawable.ab);
        myMap.put("Javna preduzeća", R.drawable.ac);
        myMap.put("Javne sluzbe", R.drawable.ad);
        myMap.put("Kulturne ustanove", R.drawable.ae);
        myMap.put("Pošte", R.drawable.af);
        myMap.put("Banke", R.drawable.ag);
        myMap.put("Crkve", R.drawable.ah);
        myMap.put("Bolnice", R.drawable.ba);
        myMap.put("Zdravstveni centar", R.drawable.bb);
        myMap.put("Apoteke", R.drawable.bc);
        myMap.put("Privatne ordinacije", R.drawable.bd);
        myMap.put("Zubari", R.drawable.be);
        myMap.put("Očne klinike", R.drawable.bf);
        myMap.put("Sportski klubovi", R.drawable.da);
        myMap.put("Stadioni", R.drawable.db);
        myMap.put("Tereni", R.drawable.dc);
        myMap.put("Sale", R.drawable.dd);
        myMap.put("Teretane", R.drawable.de);
        myMap.put("Bazeni", R.drawable.df);
        myMap.put("Osnovne škole", R.drawable.ca);
        myMap.put("Srednje škole", R.drawable.cb);
        myMap.put("Više škole", R.drawable.cc);
        myMap.put("Fakulteti", R.drawable.cd);
        myMap.put("Muzičke škole", R.drawable.ce);
        myMap.put("Vrtići", R.drawable.cg);
        myMap.put("Predškolske ustanove", R.drawable.cf);
        myMap.put("Škole za decu sa SP", R.drawable.ch);
        myMap.put("Hipermarketi", R.drawable.ga);
        myMap.put("Pijace", R.drawable.gb);
        myMap.put("Restorani", R.drawable.gc);
        myMap.put("Dostava hrane", R.drawable.gd);
        myMap.put("Pekare", R.drawable.ge);
        myMap.put("Pravljenje torti", R.drawable.gf);
        myMap.put("Kafići", R.drawable.gg);
        myMap.put("Parking", R.drawable.ea);
        myMap.put("Benzinske pumpe", R.drawable.eb);
        myMap.put("Tehnički pregledi", R.drawable.ec);
        myMap.put("Auto servisi", R.drawable.ed);
        myMap.put("Saloni automobila", R.drawable.ee);
        myMap.put("Auto placevi", R.drawable.ef);
        myMap.put("Auto otpadi", R.drawable.eg);
        myMap.put("Policija", R.drawable.ha);
        myMap.put("Vatrogasci", R.drawable.hb);
        myMap.put("Hitna pomoć", R.drawable.hc);
        myMap.put("Distribucija", R.drawable.hd);
        myMap.put("Vodovod", R.drawable.he);
        myMap.put("Bolnica", R.drawable.hf);
        myMap.put("Dežurne apoteke", R.drawable.hg);
        myMap.put("Amss", R.drawable.hk);
        myMap.put("Voz", R.drawable.fa);
        myMap.put("Autobus", R.drawable.fb);
        myMap.put("Gradski prevoz", R.drawable.fc);
        myMap.put("Taxi udruženja", R.drawable.fd);
        myMap.put("Prevoz robe", R.drawable.fe);
        return myMap;
    }

    public IzaberiGrad() {
    }

    public String getUrlPoNazivu() {
        return urlPoNazivu;
    }

    public String getUrlPoVrstama() {
        return urlPoVrstama;
    }

    public String getUrlBanke() {
        return urlBanke;
    }

    public String getUrlBankomati() {
        return urlBankomati;
    }

    public void setUrlPoNazivu(String urlPoNazivu) {
        this.urlPoNazivu = urlPoNazivu;
    }

    public void setUrlPoVrstama(String urlPoVrstama) {
        this.urlPoVrstama = urlPoVrstama;
    }

    public void setUrlBanke(String urlBanke) {
        this.urlBanke = urlBanke;
    }

    public void setUrlBankomati(String urlBankomati) {
        this.urlBankomati = urlBankomati;
    }
}
