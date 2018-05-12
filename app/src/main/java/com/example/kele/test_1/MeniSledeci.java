package com.example.kele.test_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MeniSledeci extends AppCompatActivity {
    private List<String> textIspod;
    private List<Integer> ikoniceL;
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private MenuSledeciAdapter menuAdapter;
    private int[] ikoniceNizA= {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.k
    };
    private String[] strings = {"Opština","Gradske ustanove","Javna preduzeća","Javne sluzbe","Kulturne ustanove","Pošte","Banke","Crkve"
            ,"Bolnice","Zdravstveni centar","Apoteke","Privatne ordinacije","Zubari","Očne klinike"
            ,"Sportski klubovi","Stadioni","Tereni","Sale","Teretane","Bazeni"
            ,"Osnovne škole","Srednje škole","Više škole","Fakulteti","Muzičke škole","Vrtići","Predškolske ustanove","Škole za decu sa SP"
            ,"Hipermarketi","Pijace","Restorani","Dostava hrane","Pekare","Pravljenje torti","Kafići"
            ,"Parking","Benzinske pumpe","Tehnički pregledi","Auto servisi","Saloni automobila","Auto placevi","Auto otpadi"
            ,"Policija","Vatrogasci","Hitna pomoć","Distribucija","Vodovod","Bolnica","Dežurne apoteke","Amss"
            ,"Voz","Autobus","Gradski prevoz","Taxi udruženja","Prevoz robe"};
    Intent prikazi;
    private IzaberiGrad izaberiGrad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meni_sledeci);
        Bundle vrsta = getIntent().getExtras();
        String v = vrsta.getString("dalje");
        textIspod = new ArrayList<String>();
        ikoniceL = new ArrayList<Integer>();
        recyclerView = (RecyclerView) findViewById(R.id.recyView1);
        prikazi = new Intent(this,ListaObjekata.class);
        textIspod.clear();
        ikoniceL.clear();
        izaberiGrad = new IzaberiGrad();

        final HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.meniHeader1);

        switch (v) {
            case "Ustanove":
                for(int i = 0;i<8;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[0]);
                }break;


            case "Zdravstvo":
                for(int i = 8;i<14;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[1]);
                }break;

            case "Sport":
                for(int i = 14;i<20;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[3]);
                }break;

            case "Školstvo":
                for(int i = 20;i<28;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[2]);
                }break;

            case "Ishrana":
                for(int i = 28;i<35;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[6]);
                }break;

            case "Saobraćaj":
                for(int i = 35;i<42;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[4]);
                }break;

            case "Hitne službe":
                for(int i = 42;i<50;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[7]);
                }break;

            case "Transport":
                for(int i = 50;i<55;i++){
                    textIspod.add(strings[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[5]);
                }break;
            case "Kalendar":
                Intent in = new Intent(this,Kalendar.class);
                startActivity(in);
            default:break;
        }

        glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);

        menuAdapter = new MenuSledeciAdapter(this,textIspod,ikoniceL);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }
}
