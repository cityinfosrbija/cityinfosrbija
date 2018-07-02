package com.mzbcacak.kele.cityinfosrbija;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class Uputstva extends AppCompatActivity {
    private ImageSwitcher pasaNadSubasama;
    private ImageView nap;
    private ImageView naz;
    private int[] sliciceU = {
            R.drawable.uputstvaa,
            R.drawable.uputstvab,
            R.drawable.uputstvac};
    private int pozicija = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uputstva);

        pasaNadSubasama = (ImageSwitcher) findViewById(R.id.pasa);
        nap = (ImageView) findViewById(R.id.napUp);
        naz = (ImageView) findViewById(R.id.nazUp);

        pasaNadSubasama.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView im = new ImageView(getApplicationContext());
                im.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return im;
            }
        });
        nap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pozicija<2){
                    pozicija++;
                    pasaNadSubasama.setImageResource(sliciceU[pozicija]);
                }
            }
        });
        naz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pozicija>0){
                    pozicija--;
                    pasaNadSubasama.setImageResource(sliciceU[pozicija]);
                }
            }
        });
        pasaNadSubasama.setImageResource(sliciceU[0]);
    }
}
