package com.orzechowski.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.LinkedList;
import java.util.List;

public class Recycler extends AppCompatActivity {
    private List<ModelOceny> listaOcen;
    protected RecyclerView recycler;
    protected RecyclerView.LayoutManager manager;
    public int liczba;
    protected ListAdapter adapter;

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler);
        Button przycisk = findViewById(R.id.przycisk_po_ankiecie);
        listaOcen = new LinkedList<>();
        super.onCreate(savedInstanceState);
        Bundle pakunek = getIntent().getExtras();
        liczba = pakunek.getInt("liczba");
        String[] przedmioty = getResources().getStringArray(R.array.przedmioty);
        for(int i = 0; i < liczba; i++){
            listaOcen.add(new ModelOceny(przedmioty[i]));
        }
        adapter = new ListAdapter(this, listaOcen);
        manager = new LinearLayoutManager(this);
        recycler = findViewById(R.id.lista_ocen_rv);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);

        przycisk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent toCalculate = new Intent(getApplicationContext(), MainActivity.class);
                List<Integer> idZaznaczonych = adapter.getIdZaznaczonych();
                View finder = findViewById(R.id.lista_ocen_rv);
                RadioButton checkedButton;
                for(int i = 0; i < liczba; i++){
                    checkedButton = finder.findViewById(idZaznaczonych.get(i));
                    listaOcen.get(i).setOcena(Integer.parseInt((String)checkedButton.getText()));
                    toCalculate.putExtra("ocena"+i, listaOcen.get(i).getOcena());
                }
                toCalculate.putExtra("liczba", liczba);
                startActivity(toCalculate);
                finish();
            }
        });
    }
}