package com.example.kele.test_1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private ImageView dDosli;
    private Spinner spinner;
    private String grad;
    private IzaberiGrad ig= new IzaberiGrad();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "quatt.ttf");
        setContentView(R.layout.activity_main);
        final Intent i = new Intent(this,GlavniMeni.class);

        dDosli = (ImageView) findViewById(R.id.napredDugme);
        spinner = (Spinner) findViewById(R.id.spinner);
        grad = (spinner.getSelectedItem()).toString();

        ig.setUrlPoNazivu("https://cacakandroid.000webhostapp.com/poNazivu.php?pretraga=");
        ig.setUrlPoVrstama("https://cacakandroid.000webhostapp.com/poVrstama.php?pretraga=");
        dDosli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grad.equals("Čačak")) {
                    ig.setUrlPoNazivu(ig.getUrlPoNazivu() + "CACAK,");
                    ig.setUrlPoVrstama(ig.getUrlPoVrstama() + "CACAK,");
                }
                startActivity(i);
            }
        });

    }


    @Override
    protected void onPostResume() {
        ig.setUrlPoNazivu("https://cacakandroid.000webhostapp.com/poNazivu.php?pretraga=");
        ig.setUrlPoVrstama("https://cacakandroid.000webhostapp.com/poVrstama.php?pretraga=");
        super.onPostResume();
    }
}
