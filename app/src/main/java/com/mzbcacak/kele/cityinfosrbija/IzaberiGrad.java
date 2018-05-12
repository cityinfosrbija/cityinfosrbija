package com.mzbcacak.kele.cityinfosrbija;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

public class IzaberiGrad extends Application {
    private String urlPoNazivu;
    private String urlPoVrstama;
    private String urlBanke;
    private String urlBankomati;
    private String urlParking;
    private String urlHitne;
    private String izabraniGrad;
    private Context context;
    public static Map<String,Integer> ikonice = createMap();
    private static int font = 0;

    private SharedPreferences upn;
    private SharedPreferences upv;
    private SharedPreferences ube;
    private SharedPreferences ubi;
    private SharedPreferences urp;
    private SharedPreferences urh;
    private SharedPreferences grad;

    private SharedPreferences.Editor upnE;
    private SharedPreferences.Editor upvE;
    private SharedPreferences.Editor ubeE;
    private SharedPreferences.Editor ubiE;
    private SharedPreferences.Editor urpE;
    private SharedPreferences.Editor urhE;
    private SharedPreferences.Editor gradE;

    private static Map<String, Integer> createMap()
    {
        Map<String,Integer> myMap = new HashMap<String,Integer>();
        myMap.put("Opština", R.drawable.aa);
        myMap.put("Gradske ustanove", R.drawable.ab);
        myMap.put("Javna preduzeća", R.drawable.ac);
        myMap.put("Javne službe", R.drawable.ad);
        myMap.put("Kulturne ustanove", R.drawable.ae);
        myMap.put("Pošte", R.drawable.af);
        myMap.put("Banke", R.drawable.ag);
        myMap.put("Verske ustanove", R.drawable.ah);
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
        myMap.put("Vozovi", R.drawable.fa);
        myMap.put("Autobus", R.drawable.fb);
        myMap.put("Gradski prevoz", R.drawable.fc);
        myMap.put("Taxi udruženja", R.drawable.fd);
        myMap.put("Prevoz robe", R.drawable.fe);
        myMap.put("Javne sluzbe", R.drawable.ad);
        return myMap;
    }

    public IzaberiGrad(Context context) {
        this.context = context;
        upn = context.getSharedPreferences("urlPoNazivu", Activity.MODE_PRIVATE);
        upv =  context.getSharedPreferences("urlPoVrstama", Activity.MODE_PRIVATE);
        ubi = context.getSharedPreferences("urlBankomati", Activity.MODE_PRIVATE);
        ube = context.getSharedPreferences("urlBanke", Activity.MODE_PRIVATE);
        urp = context.getSharedPreferences("urlParking", Activity.MODE_PRIVATE);
        urh = context.getSharedPreferences("urlHitne", Activity.MODE_PRIVATE);
        grad = context.getSharedPreferences("izabraniGrad", Activity.MODE_PRIVATE);

        upnE = upn.edit();
        upvE = upv.edit();
        ubeE = ube.edit();
        ubiE = ubi.edit();
        urpE = urp.edit();
        urhE = urh.edit();
        gradE = grad.edit();

        this.urlPoNazivu = upn.getString("urlPoNazivu","http://cityinfosrbija.tk/poNazivu.php?pretraga=");
        this.urlPoVrstama = upv.getString("urlPoVrstama","http://cityinfosrbija.tk/poVrstama.php?pretraga=");
        this.urlBanke = ube.getString("urlBanke","http://cityinfosrbija.tk/banke.php?pretraga=");
        this.urlBankomati = ubi.getString("urlBankomati","http://cityinfosrbija.tk/bankomati.php?pretraga=");
        this.urlParking =  urp.getString("urlParking","http://cityinfosrbija.tk/parking.php?pretraga=");
        this.izabraniGrad = grad.getString("izabraniGrad","Čačak");
    }

    public IzaberiGrad() {
    }

    public void promenaGrada(String iGrad, String iiGrad){
        upnE.putString("urlPoNazivu","http://cityinfosrbija.tk/poNazivu.php?pretraga="+iGrad);
        upnE.commit();
        upvE.putString("urlPoVrstama","http://cityinfosrbija.tk/poVrstama.php?pretraga="+iGrad);
        upvE.commit();
        ubeE.putString("urlBanke","http://cityinfosrbija.tk/banke.php?pretraga="+iGrad);
        ubeE.commit();
        ubiE.putString("urlBankomati","http://cityinfosrbija.tk/bankomati.php?pretraga="+iGrad);
        ubiE.commit();
        urpE.putString("urlParking","http://cityinfosrbija.tk/parking.php?pretraga=");
        urpE.commit();
        gradE.putString("izabraniGrad",iiGrad);
        gradE.commit();
    }

    public void reset(){
        upnE.putString("urlPoNazivu","http://cityinfosrbija.tk/poNazivu.php?pretraga=");
        upvE.putString("urlPoVrstama","http://cityinfosrbija.tk/poVrstama.php?pretraga=");
        ubeE.putString("urlBanke","http://cityinfosrbija.tk/banke.php?pretraga=");
        ubiE.putString("urlBankomati","http://cityinfosrbija.tk/bankomati.php?pretraga=");
        urpE.putString("urlParking","http://cityinfosrbija.tk/parking.php?pretraga=");
        gradE.putString("izabraniGrad","Čačak");
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

    public String getUrlParking() {
        return urlParking;
    }

    public String getUrlHitne() {
        return urlHitne;
    }

    public String getIzabraniGrad() {
        return izabraniGrad;
    }

    public static int getFont() {
        return font;
    }

    public static void setFont(int font) {
        IzaberiGrad.font = font;
    }
}
