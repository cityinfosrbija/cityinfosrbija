package com.example.kele.test_1;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Kalendar extends AppCompatActivity {

    CompactCalendarView kalendar;
    //private SimpleDateFormat danFormat = new SimpleDateFormat("0000 - yyyy", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendar);

        kalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        kalendar.setUseThreeLetterAbbreviation(true);

        Event proba = new Event(Color.BLUE,1496847600,"Krule casti");
        kalendar.addEvent(proba);

        kalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                //if(dateClicked.toString().compareTo("Wed")){}
                Toast.makeText(context,"Nece",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
    }
}
