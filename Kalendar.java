package com.mzbcacak.kele.cityinfosrbija;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.Date;

public class Kalendar extends AppCompatActivity {
    CompactCalendarView kalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendar);

        kalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        kalendar.setUseThreeLetterAbbreviation(true);

        kalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Nema podataka",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
        AlertDialog.Builder poruka = new AlertDialog.Builder(this);
        poruka.setMessage("Uskoro");
        poruka.create().show();
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment14);
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
