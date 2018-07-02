package com.mzbcacak.kele.cityinfosrbija;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.Manifest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView dDosli;
    private Spinner spinner;
    private static int por = 0;
    private String grad;
    private IzaberiGrad ig;
    private int izbor;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor prefEditor;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ig= new IzaberiGrad(this);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "quatt.ttf");
        ig.setFont(1);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefEditor = sharedPref.edit();
        izbor = sharedPref.getInt("spiner",0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE};
            boolean flag = false;
            for (int i = 0; i < permissions.length; i++) {
                if (checkSelfPermission(permissions[i]) == PackageManager.PERMISSION_DENIED) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                requestPermissions(permissions, 1);
            }
        }else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1);
        }
        setContentView(R.layout.activity_main);
        final Intent i = new Intent(this,GlavniMeni.class);
        dDosli = (ImageView) findViewById(R.id.napredDugme);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.grad, R.layout.spiner_layout);
        adapter.setDropDownViewResource(R.layout.spiner_layout);
        spinner.setAdapter(adapter);

        spinner.setSelection(izbor,false);
        grad = spinner.getSelectedItem().toString();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grad = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dDosli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (grad){
                    case "Čačak":ig.promenaGrada("CACAK,","Čačak");break;
                    case "Kraljevo":ig.promenaGrada("KRALJEVO,","Kraljevo");break;
                    case "Priboj":ig.promenaGrada("PRIBOJ,","Priboj");break;
                    case "Loznica":ig.promenaGrada("LOZNICA,","Loznica");break;
                    case "Zlatibor":ig.promenaGrada("ZLATIBOR,","Zlatibor");break;
                    case "Vrnjačka Banja":ig.promenaGrada("VRNJACKABANJA,","Vrnjačka Banja");break;
                    case "Užice":ig.promenaGrada("UZICE,","Užice");break;
                    case "Požega":ig.promenaGrada("POZEGA,","Požega");break;
                    case "Lučani":ig.promenaGrada("LUCANI,","Lučani");break;
                    case "Kragujevac":ig.promenaGrada("KRAGUJEVAC,","Kragujevac");break;
                    case "Pirot":ig.promenaGrada("PIROT,","Pirot");break;
                    case "Bela Crkva":ig.promenaGrada("BELA CRKVA,","Bela Crkva");break;
                    case "Crna Trava":ig.promenaGrada("CRNA TRAVA,","Crna Trava");break;
                    case "Dimitrovgrad":ig.promenaGrada("DIMITROVGRAD,","Dimitrovgrad");break;
                    case "Gornji Milanovac":ig.promenaGrada("GORNJIMILANOVAC,","Gornji Milanovac");break;
                    case "Trgoviste":ig.promenaGrada("TRGOVISTE,","Trgoviste");break;
                    case "Vranje":ig.promenaGrada("VRANJE,","Vranje");break;
                    case "Jagodina":ig.promenaGrada("JAGODINA,","Jagodina");break;
                    case "Arandjelovac":ig.promenaGrada("ARANDJELOVAC,","Arandjelovac");break;
                    case "Zajecar":ig.promenaGrada("ZAJECAR,","Zajecar");break;
                    case "Pancevo":ig.promenaGrada("PANCEVO,","Pancevo");break;
                    case "Topola":ig.promenaGrada("TOPOLA,","Topola");break;
                    case "Smederevo":ig.promenaGrada("SMEDEREVO,","Smederevo");break;
                    case "Paracin":ig.promenaGrada("PARACIN,","Paracin");break;
                    case "Bujanovac":ig.promenaGrada("BUJANOVAC,","Bujanovac");break;
                    case "Sombor":ig.promenaGrada("SOMBOR,","Sombor");break;
                    case "Leskovac":ig.promenaGrada("LESKOVAC,","Leskovac");break;
                    case "Mladenovac":ig.promenaGrada("MLADENOVAC,","Mladenovac");break;
                    default:ig.promenaGrada("CACAK,","Čačak");break;
                }
                //Spiner poslednji izabrani
                izbor = spinner.getSelectedItemPosition();
                prefEditor.putInt("spiner",izbor);
                prefEditor.commit();

                startActivity(i);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

        }else{
            if(Build.VERSION.SDK_INT>23){
                if(por==0) {
                    int porukaMain;
                    SharedPreferences lg = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    final SharedPreferences.Editor editor = lg.edit();
                    porukaMain = lg.getInt("porukaMain",0);
                    if(porukaMain==0) {
                        AlertDialog.Builder poruka = new AlertDialog.Builder(this);
                        poruka.setMessage("Za rad aplikacije su potrebne dozvole za pozive,poruke i lokaciju. Ako se nisu pojavili prozori za sve 3 dozvole, morate odobriti u podešavanjima \n" +
                                "podesavanja-> apps-> City info Srbija -> permissions");
                        poruka.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor.putInt("porukaMain", 1);
                                editor.apply();
                            }
                        });
                        poruka.create().show();
                        por++;
                    }
                }
            }
        }
    }
    @Override
    protected void onPostResume() {
        ig.reset();
        super.onPostResume();
    }

    /*public void podesiGrad(String tabela, String gradIme){
        ig.setUrlPoNazivu("http://cityinfosrbija.tk/poNazivu.php?pretraga="+tabela);
        ig.setUrlPoVrstama("http://cityinfosrbija.tk/poVrstama.php?pretraga="+tabela);
        ig.setUrlBanke("http://cityinfosrbija.tk/banke.php?pretraga="+tabela);
        ig.setUrlBankomati("http://cityinfosrbija.tk/bankomati.php?pretraga="+tabela);
        ig.setIzabraniGrad(gradIme);
    }*/
}
