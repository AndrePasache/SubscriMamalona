package com.application.subscrimamalona.Controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.subscrimamalona.MainActivity;
import com.application.subscrimamalona.R;

import java.util.ArrayList;

import javax.xml.namespace.QName;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pagos extends Fragment {
    private ArrayList<CasilleroContent> casillerosList1;
    private RecyclerView mRecyclerView;
    private CasilleroAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public Pagos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagos, container, false);

        casillerosList1 = new ArrayList<>();

        insertItem(casillerosList1.size());

        mRecyclerView = view.findViewById(R.id.recyclerViewPagos);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CasilleroAdapter(casillerosList1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CasilleroAdapter.OnItemClickListener(){
            /*@Override
            public void onItemClick(int position) {
                ;
            }*/

            @Override
            public void onDeleteCLick(int position) {
                removeItem(position);
            }
        });
        return view;
    }


    /*public void createContentList(){
        casillerosList1 = new ArrayList<>();
        casillerosList1.add(new CasilleroContent("hola p","546"));
        casillerosList1.add(new CasilleroContent("no waaaay","65.4"));
        //insertItem();
        //k++;
    }*/

    public void insertItem(int position){
        if(getActivity().getIntent().getExtras()!=null) {
            String name = getActivity().getIntent().getStringExtra("nombre");
            String amount = getActivity().getIntent().getStringExtra("monto");
            String type = getActivity().getIntent().getStringExtra("tipo");

            Data datos = new Data(getContext().getSharedPreferences("SubscriMamalona", Context.MODE_PRIVATE));
            //datos.leerData();
            for(int i=0; i<datos.cantidadData();i++){
                    casillerosList1.add(position,new CasilleroContent(name, amount));
            }
            /*if(datos.validarData(name,amount,type)){
                casillerosList1.add(position,new CasilleroContent(name, amount));
                //casillerosList1.add(position,new CasilleroContent("prueba","llegan datos"));
            }*/
        }else {
            Toast.makeText(getContext(), "No pasó la data", Toast.LENGTH_SHORT).show();
        }
    }


    public void removeItem(int position){
        casillerosList1.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

}
