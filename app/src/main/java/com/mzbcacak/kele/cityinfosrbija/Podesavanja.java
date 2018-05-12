package com.mzbcacak.kele.cityinfosrbija;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Podesavanja extends AppCompatActivity {
    private Button izbrisi;
    private Button kontaktPod;
    private Button oApp;
    private Button odgovornost;
    SharedPreferences loginData;
    SharedPreferences.Editor editor;
    private String zaJezik = "sr";
    private AlertDialog.Builder ad;
    private HeaderFragment hf;
    private Button uputstva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanja);

        hf = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.meniHeader2);
        izbrisi = (Button) findViewById(R.id.izbrisi);
        uputstva = (Button) findViewById(R.id.uputstva);
        kontaktPod = (Button) findViewById(R.id.kontaktPod);
        oApp = (Button) findViewById(R.id.oApl);
        odgovornost = (Button) findViewById(R.id.odgovornost);

        ad = new AlertDialog.Builder(this);
        izbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences lg = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = lg.edit();
                editor.putString("omiljeno", "");
                editor.apply();
                Toast.makeText(getApplicationContext(),"izbrisano",Toast.LENGTH_LONG).show();
                recreate();
            }
        });

        kontaktPod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ina = new Intent(getApplicationContext(),Kontak.class);
                startActivity(ina);

            }
        });

        uputstva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Uputstva.class);
                startActivity(i);
            }
        });

        oApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder poruka = new AlertDialog.Builder(Podesavanja.this);
                poruka.setMessage("City Info Srbija je interaktivna aplikacija koja omogućava individualni pristup velikoj bazi podataka i servisa u gradovima Srbije. Korišćenje aplikacije omogućava brz i jednostavan pristup velikom broju ustanova i servisa u gradovima Srbije koji su potpuno besplatni za sve naše korisnike. \n" +
                        "Naš tim svojim svakodnevnim radom nastoji da obogati sadrzaj ove aplikacije i ponudi unikatne servise na ovim prostorima. \n" +
                        "Nadamo se da ćete uzivati!\n" +
                        "\n\n" +
                        "myB Srbija team");
                poruka.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                poruka.create().show();
            }
        });

        odgovornost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder poruka = new AlertDialog.Builder(Podesavanja.this);
                poruka.setMessage("Odricanje od odgovornosti\n\n" +
                        "City Info Srbija je aplikacija koja u svom sadržaju objavljuje iskljucivo informacije koje su u trenutku objave bile dostupne na internet sajtovima i raznim pretraživačima. Stoga City Info Srbija se odriče bilo kakve odgovornosti za tačnost podataka i potencijalne štete nanete korisniku zbog korišćenja istih. Svaki brend i logo korišćen u aplikaciji je jedino vlasništvo svog vlasnika i nesme biti korišćen ni u kakve druge svrhe bez saglasnosti vlasnika. \n" +
                        "U slučaju da odredjeni entitet ne želi da svoje informacije ustupi putem aplikacije, isti će biti uklonjen na zahtev vlasnika.");
                poruka.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                poruka.create().show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment17);
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
