package com.application.subscrimamalona.Controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.subscrimamalona.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CasilleroAdapter extends RecyclerView.Adapter<CasilleroAdapter.CasilleroViewHolder>{
    private ArrayList<CasilleroContent> casillerosList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteCLick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class CasilleroViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2, textView3, textView4, textView5;
        public ImageButton fbEditar, fbEliminar;

        public CasilleroViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.linea1);
            textView2 = itemView.findViewById(R.id.linea2);
            textView3 = itemView.findViewById(R.id.linea3);
            textView4 = itemView.findViewById(R.id.linea4);
            textView5 = itemView.findViewById(R.id.dias_faltan);
            fbEliminar = itemView.findViewById(R.id.eliminar);
            fbEditar = itemView.findViewById(R.id.editar);

            fbEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onDeleteCLick(position);
                        }
                    }
                }
            });
            fbEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CasilleroAdapter(ArrayList<CasilleroContent> casillerosList){
        this.casillerosList=casillerosList;
    }

    @NonNull
    @Override
    public CasilleroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.casillero_contenido,parent,false);
        CasilleroViewHolder cvh = new CasilleroViewHolder(v,mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CasilleroViewHolder holder, int position) {
        CasilleroContent currentCasillero = casillerosList.get(position);

        holder.textView1.setText(currentCasillero.getTitulo());
        holder.textView2.setText(currentCasillero.getMonto());
        holder.textView3.setText(currentCasillero.getMetodo_pago());
        holder.textView4.setText(currentCasillero.getMoneda());
        holder.textView5.setText(currentCasillero.getDias_restantes());
    }

    @Override
    public int getItemCount() {
        return casillerosList.size();
    }
}
