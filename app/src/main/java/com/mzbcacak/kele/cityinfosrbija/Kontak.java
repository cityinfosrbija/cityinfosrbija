package com.mzbcacak.kele.cityinfosrbija;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Kontak extends AppCompatActivity {
    private ImageView fb;
    private ImageView mail;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        fb = (ImageView) findViewById(R.id.facebookK);
        mail = (ImageView) findViewById(R.id.mailK);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/City-Info-Srbija-200003853869386");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                String[] TO = {"cityinfosrbija@gmail.com"};
                mail.setData(Uri.parse("mailto:"));
                mail.setType("text/plain");
                mail.putExtra(Intent.EXTRA_EMAIL, TO);
                startActivity(mail);
            }
        });
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment9);
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
