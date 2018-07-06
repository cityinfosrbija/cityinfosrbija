package com.mzbcacak.kele.cityinfosrbija;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView povrsina;
    private TextView stanovnistvo;
    private IzaberiGrad izaberiGrad;
    private String grad;

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        izaberiGrad = new IzaberiGrad(this);

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

        povrsina = (TextView) findViewById(R.id.povrsina);
        stanovnistvo = (TextView) findViewById(R.id.stanovnistvo);

        grad = izaberiGrad.getIzabraniGrad();
        if(grad.equals("Kraljevo")){
            povrsina.setText("Povrsina: 1,530 km²");
            stanovnistvo.setText("Stanovnistvo: 67,536");
        }else if(grad.equals("Čačak")){
            povrsina.setText("Povrsina: 636 km²");
            stanovnistvo.setText("Stanovnistvo: 72,489");
        }else if (grad.equals("Priboj")){
            povrsina.setText("Povrsina: 553 km²");
            stanovnistvo.setText("Stanovnistvo: 14,920");
        }else if (grad.equals("Loznica")){
            povrsina.setText("Povrsina: 612 km²");
            stanovnistvo.setText("Stanovnistvo: 79,327");
        }else if (grad.equals("Arandjelovac")){
            povrsina.setText("Povrsina: 376 km²");
            stanovnistvo.setText("Stanovnistvo: 24,797");
        }else if (grad.equals("Bela Crkva")){
            povrsina.setText("Povrsina: 353 km²");
            stanovnistvo.setText("Stanovnistvo: 9,080  ");
        }else if (grad.equals("Bor")){
            povrsina.setText("Povrsina: 856  km²");
            stanovnistvo.setText("Stanovnistvo: 34,160");
        }else if (grad.equals("Bujanovac")){
            povrsina.setText("Povrsina: 384 km²");
            stanovnistvo.setText("Stanovnistvo: 12,011");
        }else if (grad.equals("Crna Trava")){
            povrsina.setText("Povrsina: 312 km²");
            stanovnistvo.setText("Stanovnistvo: 1,663");
        }else if (grad.equals("Dimitrovgrad")){
            povrsina.setText("Povrsina: 483 km²");
            stanovnistvo.setText("Stanovnistvo: 10,118");
        }else if (grad.equals("Gornji Milanovac")){
            povrsina.setText("Povrsina: 836 km²");
            stanovnistvo.setText("Stanovnistvo: 42,730");
        }else if (grad.equals("Jagodina")){
            povrsina.setText("Povrsina: 470 km²");
            stanovnistvo.setText("Stanovnistvo: 36,092");
        }else if (grad.equals("Kragujevac")){
            povrsina.setText("Povrsina: 865 km²");
            stanovnistvo.setText("Stanovnistvo: 179,417");
        }else if (grad.equals("Krusevac")){
            povrsina.setText("Povrsina: 854 km²");
            stanovnistvo.setText("Stanovnistvo: 73,316");
        }else if (grad.equals("Leskovac")){
            povrsina.setText("Povrsina: 1,025 km²");
            stanovnistvo.setText("Stanovnistvo: 60,288");
        }else if (grad.equals("Lučani")){
            povrsina.setText("Povrsina: 454 km²");
            stanovnistvo.setText("Stanovnistvo: 20,897");
        }else if (grad.equals("Mladenovac")){
            povrsina.setText("Povrsina: 339 km²");
            stanovnistvo.setText("Stanovnistvo: 53,904");
        }else if (grad.equals("Nova Varos")){
            povrsina.setText("Povrsina: 584 km²");
            stanovnistvo.setText("Stanovnistvo: 16,638");
        }else if (grad.equals("Novi Pazar")){
            povrsina.setText("Povrsina: 742 km²");
            stanovnistvo.setText("Stanovnistvo: 66,527");
        }else if (grad.equals("Pancevo")){
            povrsina.setText("Povrsina: 148 km²");
            stanovnistvo.setText("Stanovnistvo: 76,203");
        }else if (grad.equals("Paracin")){
            povrsina.setText("Povrsina: 542 km²");
            stanovnistvo.setText("Stanovnistvo: 25,104");
        }else if (grad.equals("Pirot")){
            povrsina.setText("Povrsina: 1,232 km²");
            stanovnistvo.setText("Stanovnistvo: 38,785");
        }else if (grad.equals("Požega")){
            povrsina.setText("Povrsina: 426 km²");
            stanovnistvo.setText("Stanovnistvo: 13,153");
        }else if (grad.equals("Smederevo")){
            povrsina.setText("Povrsina: 484  km²");
            stanovnistvo.setText("Stanovnistvo: 64,175");
        }else if (grad.equals("Sabac")){
            povrsina.setText("Povrsina: 795 km²");
            stanovnistvo.setText("Stanovnistvo: 53,336");
        }else if (grad.equals("Sombor")){
            povrsina.setText("Povrsina: 1,178 km²");
            stanovnistvo.setText("Stanovnistvo: 47,623");
        }else if (grad.equals("Subotica")){
            povrsina.setText("Povrsina: 290 km²");
            stanovnistvo.setText("Stanovnistvo: 97,910");
        }else if (grad.equals("Topola")){
            povrsina.setText("Povrsina: 356  km²");
            stanovnistvo.setText("Stanovnistvo: 4,793");
        }else if (grad.equals("Trgoviste")){
            povrsina.setText("Povrsina: 370 km²");
            stanovnistvo.setText("Stanovnistvo: 5,091");
        }else if (grad.equals("Užice")){
            povrsina.setText("Povrsina: 667 km²");
            stanovnistvo.setText("Stanovnistvo: 52,646");
        }else if (grad.equals("Vranje")){
            povrsina.setText("Povrsina: 860 km²");
            stanovnistvo.setText("Stanovnistvo: 55,052");
        }else if (grad.equals("Vrnjačka Banja")){
            povrsina.setText("Povrsina: 239 km²");
            stanovnistvo.setText("Stanovnistvo: 27,527");
        }else if (grad.equals("Vrsac")){
            povrsina.setText("Povrsina: 800 km²");
            stanovnistvo.setText("Stanovnistvo: 52,026");
        }else if (grad.equals("Zajecar")){
            povrsina.setText("Povrsina: 1068 km²");
            stanovnistvo.setText("Stanovnistvo: 59,461");
        }else if (grad.equals("Zlatibor")){
            povrsina.setText("Povrsina: 1000 km²");
            stanovnistvo.setText("Stanovnistvo: /");
        }else if (grad.equals("Zrenjanin")){
            povrsina.setText("Povrsina: 191 km²");
            stanovnistvo.setText("Stanovnistvo: 76,511");
        }
        vp = (ViewPager) findViewById(R.id.Vslajder);
        sl = new Slajder(this);
        vp.setAdapter(sl);

        service = new YahooW(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ucitava");
        progressDialog.show();

        service.refreshL(izaberiGrad.getIzabraniGrad()+", Serbia");
    }

    @Override
    public void serviceSucces(Chanel chanel) {
        progressDialog.hide();
        Items item = chanel.getItems();
        int resursiID = getResources().getIdentifier("drawable/icon_"+item.getCondition().getCode(),null,getPackageName());
        prvi.setText(dani(item.getForecast().get(0).getDay())+"\n"
                +"Max: "+item.getForecast().get(0).getHigh()+"\n"
                +"Min: "+item.getForecast().get(0).getLow());
        drugi.setText(dani(item.getForecast().get(1).getDay())+"\n"
                +"Max: "+item.getForecast().get(1).getHigh()+"\n"
                +"Min: "+item.getForecast().get(1).getLow());
        treci.setText(dani(item.getForecast().get(2).getDay())+"\n"
                +"Max: "+item.getForecast().get(2).getHigh()+"\n"
                +"Min: "+item.getForecast().get(2).getLow());
        cetvrti.setText(dani(item.getForecast().get(3).getDay())+"\n"
                +"Max: "+item.getForecast().get(3).getHigh()+"\n"
                +"Min: "+item.getForecast().get(3).getLow());
        peti.setText(dani(item.getForecast().get(4).getDay())+"\n"
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
        Toast.makeText(this,"Nema podataka",Toast.LENGTH_LONG).show();
    }

    public String dani(String dan){
        String danR;
        switch (dan){
            case "Mon": danR = "Pon";
            break;
            case "Tue": danR = "Uto";break;
            case "Wed": danR = "Sre";break;
            case "Thu": danR = "Čet";break;
            case "Fri": danR = "Pet";break;
            case "Sat": danR = "Sub";break;
            case "Sun": danR = "Ned";break;
            default: danR = "Pon";break;
        }
        return danR;
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentF);
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