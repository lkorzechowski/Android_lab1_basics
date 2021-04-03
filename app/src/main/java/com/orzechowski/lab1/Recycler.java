package com.orzechowski.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class Recycler extends AppCompatActivity {
    private List<ModelOceny> listaOcen;
    protected RecyclerView recycler;
    protected RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button przycisk = findViewById(R.id.przycisk_po_ankiecie);
        listaOcen = new LinkedList<ModelOceny>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Bundle pakunek = getIntent().getExtras();
        int liczba = pakunek.getInt("liczba");
        String[] przedmioty = getResources().getStringArray(R.array.przedmioty);
        for(int i = 0; i < liczba; i++){
            listaOcen.add(new ModelOceny(przedmioty[i]));
        }
        ListAdapter adapter = new ListAdapter(this, listaOcen);
        manager = new LinearLayoutManager(this);
        recycler = (RecyclerView)findViewById(R.id.lista_ocen_rv);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);

        przycisk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent toCalculate = new Intent(getApplicationContext(), MainActivity.class);
                for(int i = 0; i < liczba; i++){

                }
                startActivity(toCalculate);
            }
        });
    }
}