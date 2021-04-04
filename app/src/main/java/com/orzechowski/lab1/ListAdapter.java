package com.orzechowski.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.OcenyViewHolder> {
    private final List<ModelOceny> mListaOcen;
    private List<Integer> idZaznaczonych;
    private final LayoutInflater mPompka;
    private int numerWiersza = 0;

    public ListAdapter(Activity kontekst, List<ModelOceny> listaOcen) {
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    public List<Integer> getIdZaznaczonych() {
        return idZaznaczonych;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View wiersz = mPompka.inflate(R.layout.wiersz_oceny, null);
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza) {
        TextView label = ocenyViewHolder.itemView.findViewById(R.id.textViewer);
        label.setText(mListaOcen.get(numerWiersza).getNazwa());
        RadioGroup group = ocenyViewHolder.itemView.findViewById(R.id.grupa);
        group.setTag(this.numerWiersza);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(idZaznaczonych==null){
                    idZaznaczonych = new LinkedList<>();
                    for(int i = 0; i < mListaOcen.size(); i++) idZaznaczonych.add(null);
                }
                idZaznaczonych.set((Integer)group.getTag(), checkedId);
                System.out.println(group.getTag());
                System.out.println(checkedId);
            }
        });
        this.numerWiersza++;
    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup
            .OnCheckedChangeListener {
         public OcenyViewHolder(@NonNull View glownyElementWiersza){
            super(glownyElementWiersza);
        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {}
    }
}
