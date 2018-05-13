package com.example.kele.test_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mzbcacak.kele.cityinfosrbija.R;

public class Pozivi extends AppCompatActivity {
    private Button hitne;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pozivi);

        i = new Intent(this,Hitne_sluzbe.class);
        hitne = (Button) findViewById(R.id.napredDugme);

        hitne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }
}
