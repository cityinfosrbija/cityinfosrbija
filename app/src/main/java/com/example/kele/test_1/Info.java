package com.example.kele.test_1;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mzbcacak.kele.cityinfosrbija.R;

public class Info extends AppCompatActivity implements Wcallback {

    ViewPager vp;
    Slajder sl;
    private YahooW service;
    private TextView prvi;
    private TextView drugi;
    private TextView treci;
    private TextView cetvrti;
    private TextView peti;
    private ImageView prviS;
    private ImageView drugiS;
    private ImageView treciS;
    private ImageView cetvrtiS;
    private ImageView petiS;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        prvi = (TextView) findViewById(R.id.prviDan);
        drugi = (TextView) findViewById(R.id.drugiDan);
        treci = (TextView) findViewById(R.id.treciDan);
        cetvrti = (TextView) findViewById(R.id.cetvrtiDan);
        peti = (TextView) findViewById(R.id.petiDan);

        prviS = (ImageView) findViewById(R.id.prviSlika);
        drugiS = (ImageView) findViewById(R.id.drugiSlika);
        treciS = (ImageView) findViewById(R.id.treciSlika);
        cetvrtiS = (ImageView) findViewById(R.id.cetvrtiSlika);
        petiS = (ImageView) findViewById(R.id.petiSlika);

        vp = (ViewPager) findViewById(R.id.Vslajder);
        sl = new Slajder(this);
        vp.setAdapter(sl);

        service = new YahooW(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();

        service.refreshL("Cacak, Serbia");
    }

    @Override
    public void serviceSucces(Chanel chanel) {
        progressDialog.hide();
        Items item = chanel.getItems();
        int resursiID = getResources().getIdentifier("drawable/icon_"+item.getCondition().getCode(),null,getPackageName());
        /*@SuppressWarnings("deprecation")
        Drawable drawable = getResources().getDrawable(resursiID);*/
        /*vreme.setImageDrawable(drawable);
        lokacija.setText(item.getCondition().getTemperature()+"\u00B0"+ chanel.getUnits().getTemperature()+"\n"+item.getCondition().getDescription());
        String saa = String.valueOf(item.getForecast().get(0).getHigh());
        String sas = String.valueOf(item.getForecast().get(1).getHigh());*/
        prvi.setText(item.getForecast().get(0).getDay()+"\n"
                +"Max: "+item.getForecast().get(0).getHigh()+"\n"
                +"Min: "+item.getForecast().get(0).getLow());
        drugi.setText(item.getForecast().get(1).getDay()+"\n"
                +"Max: "+item.getForecast().get(1).getHigh()+"\n"
                +"Min: "+item.getForecast().get(1).getLow());
        treci.setText(item.getForecast().get(2).getDay()+"\n"
                +"Max: "+item.getForecast().get(2).getHigh()+"\n"
                +"Min: "+item.getForecast().get(2).getLow());
        cetvrti.setText(item.getForecast().get(3).getDay()+"\n"
                +"Max: "+item.getForecast().get(3).getHigh()+"\n"
                +"Min: "+item.getForecast().get(3).getLow());
        peti.setText(item.getForecast().get(4).getDay()+"\n"
                +"Max: "+item.getForecast().get(4).getHigh()+"\n"
                +"Min: "+item.getForecast().get(4).getLow());
        int resursi[] = new int[5];
        for(int i=0;i<5;i++){
            resursi[i] = getResources().getIdentifier("drawable/icon_"+item.getForecast().get(i).getCode(),null,getPackageName());
        }
        @SuppressWarnings("deprecation")
        Drawable d1 = getResources().getDrawable(resursi[0]);
        @SuppressWarnings("deprecation")
        Drawable d2 = getResources().getDrawable(resursi[1]);
        @SuppressWarnings("deprecation")
        Drawable d3 = getResources().getDrawable(resursi[2]);
        @SuppressWarnings("deprecation")
        Drawable d4 = getResources().getDrawable(resursi[3]);
        @SuppressWarnings("deprecation")
        Drawable d5 = getResources().getDrawable(resursi[4]);
        prviS.setImageDrawable(d1);
        drugiS.setImageDrawable(d2);
        treciS.setImageDrawable(d3);
        cetvrtiS.setImageDrawable(d4);
        petiS.setImageDrawable(d5);

    }

    @Override
    public void serviceE(Exception e) {
        progressDialog.hide();
        Toast.makeText(this,"Nece",Toast.LENGTH_LONG).show();
    }
}