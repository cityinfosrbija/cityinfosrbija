package com.example.kele.test_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GlavniMeni extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager glm;
    private MenuAdapter menuAdapter;
    private List<String> vrste;
    private int[] ikoniceNiz= {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.k
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni_meni);

        recyclerView = (RecyclerView) findViewById(R.id.recyView);

        vrste = new ArrayList<String>();
        vrste.add("Ustanove");
        vrste.add("Zdravstvo");
        vrste.add("Školstvo");
        vrste.add("Sport");
        vrste.add("Saobraćaj");
        vrste.add("Transport");
        vrste.add("Ishrana");
        vrste.add("Hitne službe");
        vrste.add("Kalendar");
        glm = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(glm);

        menuAdapter = new MenuAdapter(this,vrste,ikoniceNiz);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }
}
