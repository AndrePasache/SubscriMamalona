package com.application.subscrimamalona.Controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.subscrimamalona.R;

import java.util.ArrayList;

public class CasilleroAdapter2 extends RecyclerView.Adapter<CasilleroAdapter2.CasilleroViewHolder>{
    private ArrayList<CasilleroContent> casillerosList;

    public static class CasilleroViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2;

        public CasilleroViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.linea1);
            textView2 = itemView.findViewById(R.id.linea2);
        }
    }

    public CasilleroAdapter2(ArrayList<CasilleroContent> casillerosList){
        this.casillerosList=casillerosList;
    }

    @NonNull
    @Override
    public CasilleroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.casillero_contenido,parent,false);
        CasilleroViewHolder cvh2 = new CasilleroViewHolder(v);
        return cvh2;
    }

    @Override
    public void onBindViewHolder(@NonNull CasilleroViewHolder holder, int position) {
        CasilleroContent currentCasillero = casillerosList.get(position);

        holder.textView1.setText(currentCasillero.getTitulo());
        holder.textView2.setText(currentCasillero.getMonto());
    }

    @Override
    public int getItemCount() {
        return casillerosList.size();
    }
}
