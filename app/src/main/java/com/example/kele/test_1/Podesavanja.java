package com.example.kele.test_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mzbcacak.kele.cityinfosrbija.R;

public class Podesavanja extends AppCompatActivity {
    private Button izbrisi;
    private Button kontaktPod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podesavanja);

        izbrisi = (Button) findViewById(R.id.izbrisi);
        kontaktPod = (Button) findViewById(R.id.kontaktPod);
        izbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences loginData = getSharedPreferences("omiljeno", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = loginData.edit();
                editor.putString("omiljeno", "");
                editor.apply();
                Toast.makeText(getApplicationContext(),"izbrisano",Toast.LENGTH_LONG).show();
                izbrisi.setText("oce li");
            }
        });
        kontaktPod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dalje = new Intent(getApplicationContext(),BankeMapaLista.class);
                dalje.putExtra("nazivBanke","Halkbank");
                dalje.putExtra("logo",R.drawable.halkbank);
                dalje.putExtra("vrstaB",1);
                startActivity(dalje);
            }
        });
        /*kontaktPod = (Button) findViewById(R.id.kontaktPod);
        kontaktPod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("nazivBanke","intesa");
                startActivity(i);
            }
        });*/
    }
}
