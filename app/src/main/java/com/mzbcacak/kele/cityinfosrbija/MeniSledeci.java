package com.mzbcacak.kele.cityinfosrbija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MeniSledeci extends AppCompatActivity {
    private static final String TAG = "proba";
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
    private String[] strings = {"Opština","Gradske ustanove","Javna preduzeća","Javne sluzbe","Kulturne ustanove","Pošte","Banke","Verske ustanove"
            ,"Bolnice","Zdravstveni centar","Apoteke","Privatne ordinacije","Zubari","Očne klinike"
            ,"Sportski klubovi","Stadioni","Tereni","Sale","Teretane","Bazeni"
            ,"Osnovne škole","Srednje škole","Više škole","Fakulteti","Muzičke škole","Vrtići","Predškolske ustanove","Škole za decu sa SP"
            ,"Hipermarketi","Pijace","Restorani","Dostava hrane","Pekare","Pravljenje torti","Kafići"
            ,"Parking","Benzinske pumpe","Tehnički pregledi","Auto servisi","Saloni automobila","Auto placevi","Auto otpadi"
            ,"Policija","Vatrogasci","Hitna pomoć","Distribucija","Vodovod","Bolnica","Dežurne apoteke","Amss"
            ,"Vozovi","Autobus","Gradski prevoz","Prevoz robe"};

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
        izaberiGrad = new IzaberiGrad(this);
        String[] izStringa = getResources().getStringArray(R.array.sledeci_ikonice);

        final HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.meniHeader1);

        switch (v) {
            case "Ustanove":
                for(int i = 0;i<8;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[0]);
                }break;


            case "Zdravstvo":
                for(int i = 8;i<14;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[1]);
                }break;

            case "Sport":
                for(int i = 14;i<20;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[3]);
                }break;

            case "Školstvo":
                for(int i = 20;i<28;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[2]);
                }break;

            case "Ishrana":
                for(int i = 28;i<35;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[6]);
                }break;

            case "Saobraćaj":
                for(int i = 35;i<42;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[4]);
                }break;

            case "Transport":
                for(int i = 50;i<54;i++){
                    textIspod.add(izStringa[i]);
                    ikoniceL.add(izaberiGrad.ikonice.get(strings[i]));
                    hfc.promeniNaslov(v);
                    hfc.grb.setImageResource(ikoniceNizA[5]);
                }break;
            default:break;
        }

        glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);

        menuAdapter = new MenuSledeciAdapter(this,textIspod,ikoniceL);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.meniFooter1);
        if(ff.otvorena==1){
            ff.popupWindow.dismiss();
            ff.otvorena = 0;
        }else if(ff.otvorenaP==1){
            ff.popupWindowP.dismiss();
            ff.otvorenaP = 0;
        }else {
            super.onBackPressed();
        }
    }

}
