package com.example.kele.test_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
    private ImageButton navigacija;
    private LocationManager lManager;

    private double x = 43.893285;
    private double y = 20.349426;
    private double x1,y1;
    private GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);


        final Baza prosledjena = (Baza)getIntent().getSerializableExtra("prebaci");

        HeaderFragment hfc = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment5);
        hfc.promeniNaslov(prosledjena.getIme());
        hfc.promeniLogo(prosledjena.getVrsta());

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
        //navigacija = (ImageButton) findViewById(R.id.navigacija);

        adr.setText(prosledjena.getAdresa());
        tel.setText(prosledjena.getFiksni());
        mob.setText(prosledjena.getMobilni());
        email.setText(prosledjena.getEmail());
        web.setText(prosledjena.getWebsite());

        String[] koo = prosledjena.getGoogleMap().split(",");
        x1 = Double.parseDouble(koo[0]);
        y1 = Double.parseDouble(koo[1]);


        pozovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provera.setMessage("Pozivate "+prosledjena.getFiksni().trim());
                provera.setCancelable(false);
                provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+prosledjena.getFiksni().trim()));
                        startActivity(callIntent);
                    }
                });
                provera.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                provera.create().show();
            }
        });

        webOtvori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provera.setMessage("Otvarate website "+prosledjena.getWebsite());
                provera.setCancelable(false);
                provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse("http://"+prosledjena.getWebsite());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                provera.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                provera.create().show();
            }
        });

        posaljiMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provera.setMessage("Posalji mail na "+prosledjena.getEmail());
                provera.setCancelable(false);
                provera.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mail = new Intent(Intent.ACTION_SEND);
                        String[] TO = {prosledjena.getEmail()};
                        mail.setData(Uri.parse("mailto:"));
                        mail.setType("text/plain");

                        mail.putExtra(Intent.EXTRA_EMAIL, TO);
                        //startActivity(Intent.createChooser(mail, "Send mail..."));
                        startActivity(mail);
                        finish();
                    }
                });
                provera.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                provera.create().show();
            }
        });

        /*navigacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+x+","+y+"&daddr="+x1+","+y1));
                startActivity(intent);
            }
        });*/

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
}
