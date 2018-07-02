package com.mzbcacak.kele.cityinfosrbija;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class Detalji extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,MapFragment.tag {

    private TextView adr;
    private TextView tel;
    private TextView mob;
    private TextView email;
    private TextView web;
    private ImageView pozovi;
    private ImageView webOtvori;
    private ImageView posaljiMail;

    private double x = 43.893285;
    private double y = 20.349426;
    private double x1,y1;
    private GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji2);


        final Baza prosledjena = (Baza)getIntent().getSerializableExtra("prebaci");

        HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment5);
        hfc.promeniNaslov(prosledjena.getIme());
        if(prosledjena.getUrl().equals("")) {
            hfc.promeniLogo(prosledjena.getVrsta());
        }else{
            hfc.logoDetalji(prosledjena.getUrl());
        }

        MapFragment mp = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment8);
        mp.koordinateO(prosledjena.getGoogleMap());

        final AlertDialog.Builder provera = new AlertDialog.Builder(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        adr = (TextView) findViewById(R.id.detaljiAdresa);
        tel = (TextView) findViewById(R.id.detaljiTelefon);
        mob = (TextView) findViewById(R.id.detaljiMobilni);
        email = (TextView) findViewById(R.id.detaljiMail);
        web = (TextView) findViewById(R.id.detaljiWeb);
        pozovi = (ImageView) findViewById(R.id.poziv);
        webOtvori = (ImageView) findViewById(R.id.webOtvori);
        posaljiMail = (ImageView) findViewById(R.id.posaljiMail);

        adr.setText(prosledjena.getAdresa());
        tel.setText(prosledjena.getFiksni());
        mob.setText(prosledjena.getMobilni());
        email.setText(prosledjena.getEmail());
        web.setText(prosledjena.getWebsite());

        pozovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proveriPozive()==false) {
                    provera.setCancelable(true);
                    if (prosledjena.getFiksni().equals("") && !(prosledjena.getMobilni().equals(""))) {
                        provera.setMessage("Pozivate " + prosledjena.getMobilni().trim());
                        provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + prosledjena.getMobilni().trim()));
                                startActivity(callIntent);
                            }
                        });
                    } else if (!(prosledjena.getFiksni().equals("")) && prosledjena.getMobilni().equals("")) {
                        provera.setMessage("Pozivate " + prosledjena.getFiksni());
                        provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + prosledjena.getFiksni().trim()));
                                startActivity(callIntent);
                            }
                        });

                    } else if (prosledjena.getFiksni().equals("") && prosledjena.getMobilni().equals("")) {
                        provera.setMessage("Izabrana lokacija nema broj telefona");
                        provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                    } else {
                        provera.setMessage("Pozivate ");
                        provera.setPositiveButton("Fiksni", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + prosledjena.getFiksni().trim()));
                                startActivity(callIntent);
                            }
                        });
                        provera.setNegativeButton("Mobilni", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + prosledjena.getMobilni().trim()));
                                startActivity(callIntent);
                            }
                        });
                    }
                    provera.create().show();
                }
            }
        });

        webOtvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provera.setCancelable(true);
                if(!prosledjena.getWebsite().equals("")) {
                    provera.setMessage("Otvarate website "+prosledjena.getWebsite());
                    provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Uri uri = Uri.parse("http://" + prosledjena.getWebsite());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                        }
                    });
                    provera.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                }else{
                    provera.setMessage("Odabrana lokacija nema web stranicu");
                }
                provera.create().show();
            }
        });

        posaljiMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provera.setMessage("Posalji mail na "+prosledjena.getEmail());
                provera.setCancelable(true);
                if(!(prosledjena.getEmail().equals(""))) {
                    provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent mail = new Intent(Intent.ACTION_SEND);
                            String[] TO = {prosledjena.getEmail()};
                            mail.setData(Uri.parse("mailto:"));
                            mail.setType("text/plain");
                            mail.putExtra(Intent.EXTRA_EMAIL, TO);
                            startActivity(mail);
                        }
                    });
                    provera.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                }
                else{
                    provera.setMessage("Odabrana lokacija nema web stranicu");
                    provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                }
                provera.create().show();
            }
        });

    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            x=mLastLocation.getLatitude();
            y=mLastLocation.getLongitude();
        }
        else {
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void getTaga(String t) {

    }

    @Override
    public void onBackPressed() {
        FooterFragment ff = (FooterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment6);
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

    public boolean proveriPozive(){
        boolean flag = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{android.Manifest.permission.CALL_PHONE};
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

    @Override
    protected void onResume() {
        IzaberiGrad izaberiGrad = new IzaberiGrad(this);
        if(izaberiGrad.getFont()!=1) {
            FontsOverride.setDefaultFont(this, "MONOSPACE", "quatt.ttf");
            izaberiGrad.setFont(1);
        }
        super.onResume();
    }
}
