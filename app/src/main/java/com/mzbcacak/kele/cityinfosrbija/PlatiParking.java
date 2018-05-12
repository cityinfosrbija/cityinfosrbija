package com.mzbcacak.kele.cityinfosrbija;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PlatiParking extends AppCompatActivity {
    private TextView tablice;
    private Spinner zone;
    private Button zoneMapa;
    private CheckBox zapamti;
    private Button plati;
    private String izZona = "Crvena zona";
    private SharedPreferences Preference;
    private SharedPreferences tabliceP;
    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editorT;
    private boolean cekirano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plati_parking);

        tablice = (TextView) findViewById(R.id.tablice);
        zone = (Spinner) findViewById(R.id.spinerZona);
        zoneMapa = (Button) findViewById(R.id.otvoriZaPar);
        zapamti = (CheckBox) findViewById(R.id.zapamti);
        plati = (Button) findViewById(R.id.plati);

        Preference = getSharedPreferences("zapamti", PlatiParking.MODE_PRIVATE);
        tabliceP = getSharedPreferences("tablice", PlatiParking.MODE_PRIVATE);
        editor = Preference.edit();
        editorT = tabliceP.edit();


        cekirano = Preference.getBoolean("zapamti",false);
        zapamti.setChecked(cekirano);

        if(cekirano){
            tablice.setText(tabliceP.getString("tablice","Broj tabli"));
        }

        zapamti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putBoolean("zapamti",true);
                    editor.commit();
                    editorT.putString("tablice",tablice.getText().toString());
                    editorT.commit();
                }
                else{
                    editor.putBoolean("zapamti",false);
                    editor.commit();
                }
            }
        });

        zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                izZona = zone.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        zoneMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),zaParking.class);
                startActivity(i);
            }
        });

        plati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proveriSMS()==false) {
                    AlertDialog.Builder poruka = new AlertDialog.Builder(PlatiParking.this);
                    poruka.setMessage("Cena za jedan sat:\nCrvrna zona - 65 din. \nŽuta zona - 50 din. \nZelena zona - 30 din. \nPlava zona - 19 din.");
                    poruka.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + brojTelefona(izZona)));
                            i.putExtra("sms_body", tablice.getText().toString());
                            startActivity(i);
                        }
                    });
                    poruka.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    poruka.create().show();
                }
            }
        });
    }
    public String brojTelefona (String zona){
        String bt;
        switch (zona){
            case "Crvena zona": bt = "9320";break;
            case "Žuta zona":bt = "9321";break;
            case "Zelena zona":bt = "9322";break;
            case "Plava zona":bt = "9323";break;
            default:bt="greska";break;
        }
        return bt;
    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment11);
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
    public boolean proveriSMS(){
        boolean flag = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.SEND_SMS};
            for (int i = 0; i < permissions.length; i++) {
                if (this.checkSelfPermission(permissions[i]) == PackageManager.PERMISSION_DENIED) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
        return flag;
    }
}
