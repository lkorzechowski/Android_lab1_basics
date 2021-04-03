package com.orzechowski.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.OcenyViewHolder> {
    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    private int numerWiersza = 0;

    public ListAdapter(Activity kontekst, List<ModelOceny> listaOcen) {
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View wiersz = mPompka.inflate(R.layout.wiersz_oceny, null);
        TextView label = wiersz.findViewById(R.id.textViewer);
        label.setText(mListaOcen.get(numerWiersza).getNazwa());
        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder ocenyViewHolder, int numerWiersza) {
        RadioGroup group = ocenyViewHolder.itemView.findViewById(R.id.grupa);
        group.setTag(this.numerWiersza);
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
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    }
}
