package com.mzbcacak.kele.cityinfosrbija;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    tag zaTag;
    GoogleMap gMap;
    MapView vMap;
    View mView;
    boolean zaBanke=true;
    int pozicija = 0;
    int pozicijaP = 0;
    private double x=43.871263;
    private double y=20.365104;
    ArrayList<Marker> markeri = new ArrayList<Marker>();
    private BankeMapaLista bml;

    private int[] mIconNiz = {
            R.drawable.markera,
            R.drawable.markerb,
            R.drawable.markerc,
            R.drawable.markerd,
            R.drawable.markere,
            R.drawable.markerf,
            R.drawable.markerg,
            R.drawable.markerh
    };

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        zaTag = (tag) getContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //zaBanke = true;
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        bml = new BankeMapaLista();
        return mView;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vMap = (MapView) mView.findViewById(R.id.fMapa);
        if(vMap != null){
            vMap.onCreate(null);
            vMap.onResume();
            vMap.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        gMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.setMyLocationEnabled(true);
        gMap.getUiSettings().setMapToolbarEnabled(true);
        gMap.getUiSettings().setCompassEnabled(true);
        if(zaBanke==true && !(getActivity() instanceof zaParking) && !(getActivity() instanceof NajbliziObjekti)) {
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    zaTag.getTaga(marker.getTag().toString());
                    //bml.promeni();
                    return true;
                }
            });

            CameraPosition cp = CameraPosition.builder().target(new LatLng(43.890130, 20.348738)).zoom(14).bearing(0).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        }else if(getActivity() instanceof zaParking) {
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    ((zaParking) getActivity()).markerClick((Integer) marker.getTag());
                    return true;
                }
            });
        }else if(getActivity() instanceof NajbliziObjekti){
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    return false;
                }
            });
            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    ((NajbliziObjekti) getActivity()).otvoriDetalje(marker.getTag().toString());
                }
            });
        }
        else if(getActivity() instanceof Detalji){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(x,y)));

            CameraPosition cp = CameraPosition.builder().target(new LatLng(x, y)).zoom(16).bearing(0).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        }
    }


    public void koordinate(String m){
        String[] koo = m.split(",");
        Marker marker;
        double x1 = Double.parseDouble(koo[0]);
        double y1 = Double.parseDouble(koo[1]);
        marker = gMap.addMarker(new MarkerOptions().position(new LatLng(x1,y1)));
        marker.setTag(pozicija);
        markeri.add(marker);
        pozicija++;
    }

    public void koordinateNaj(String m, String naziv, String ulica){
        String[] koo = m.split(",");
        Marker marker;
        double x1 = Double.parseDouble(koo[0]);
        double y1 = Double.parseDouble(koo[1]);
        if(gMap != null) {
            marker = gMap.addMarker(new MarkerOptions().position(new LatLng(x1, y1)));
            marker.setTag(pozicija);
            marker.setTitle(naziv);
            marker.setSnippet(ulica);
            markeri.add(marker);
            pozicija++;
        }
    }

    public void dodajLiniju (String prva, String druga, String ime){
        String p[] = prva.split(",");
        String d[] = druga.split(",");
        LatLng pp = new LatLng(Double.parseDouble(p[0]),Double.parseDouble(p[1]));
        LatLng dd = new LatLng(Double.parseDouble(d[0]),Double.parseDouble(d[1]));
        PolylineOptions polyOptions = new PolylineOptions();
        if(ime.contains("plava")) {
            polyOptions.color(Color.BLUE);
        }else if(ime.contains("žuta")){
            polyOptions.color(Color.YELLOW);
        }else if(ime.contains("crvena")){
            polyOptions.color(Color.RED);
        }else{
            polyOptions.color(Color.GREEN);
        }
        polyOptions.width(10);
        polyOptions.add(pp, dd);
        gMap.addPolyline(polyOptions);
    }

    public void pozicjaKamereP(String kk, int zoom){
        String k[] = kk.split(",");
        CameraPosition cp = CameraPosition.builder().target(new LatLng(Double.parseDouble(k[0]), Double.parseDouble(k[1]))).zoom(zoom).bearing(0).build();
        gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
    }


    public void promeniMarker(int poz){
        for(int i=0;i<poz;i++) {
            markeri.get(i).setIcon(BitmapDescriptorFactory.fromResource(mIconNiz[i]));
        }
    }

    public void koordinateO(String m){
        zaBanke = false;
        String[] koo = m.split(",");
        x = Double.parseDouble(koo[0]);
        y = Double.parseDouble(koo[1]);
    }

    public void pozicijaKamere (int poz){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i=0;i<poz;i++) {
            builder.include(markeri.get(i).getPosition());
        }
        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (getResources().getDisplayMetrics().heightPixels)/2;
        int padding;
        if(poz==1){
            CameraPosition cp = CameraPosition.builder().target(new LatLng(markeri.get(0).getPosition().latitude,markeri.get(0).getPosition().longitude)).zoom(16).bearing(0).build();
            gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        }else{
            padding = (int) (width * 0.30); // offset from edges of the map 10% of screen
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            gMap.animateCamera(cu);
        }
    }
    public void pozicijaKamereCeoEkran (int poz){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i=0;i<poz;i++) {
            builder.include(markeri.get(i).getPosition());
        }
        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = (getResources().getDisplayMetrics().heightPixels)/2;
        int padding;
        if(poz==1){
            CameraPosition cp = CameraPosition.builder().target(new LatLng(markeri.get(0).getPosition().latitude,markeri.get(0).getPosition().longitude)).zoom(16).bearing(0).build();
            gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        }else{
            padding = (int) (width * 0.10); // offset from edges of the map 10% of screen
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            gMap.animateCamera(cu);
        }
    }

    public interface tag{
        void getTaga(String t);
    }

}
