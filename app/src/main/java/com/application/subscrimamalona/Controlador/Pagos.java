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

import com.application.subscrimamalona.Add_Activity;
import com.application.subscrimamalona.MainActivity;
import com.application.subscrimamalona.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        createContentList();

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

        insertItem(casillerosList1.size());

        return view;
    }


    public void createContentList(){
        casillerosList1 = new ArrayList<>();
        /*casillerosList1.add(new CasilleroContent("hola p","546"));
        casillerosList1.add(new CasilleroContent("no waaaay","65.4"));*/

    }

    public void insertItem(int position){
        Data datos = new Data(getContext().getSharedPreferences("SubscriMamalona", Context.MODE_PRIVATE));
        String name, amount;
        if(getActivity().getIntent().getExtras()!=null && getActivity().getIntent().getStringExtra("tipo").equals("Pago")) {
            name = getActivity().getIntent().getStringExtra("nombre");
            amount = getActivity().getIntent().getStringExtra("monto");

            datos.guardarData(name,amount);
            datos.escribirData();

        }/*else {
            Toast.makeText(getContext(), "No pasó la data", Toast.LENGTH_SHORT).show();
        }*/

        Set<String> lista;
        lista = datos.returnData();

        for(String datitos: lista){
            String[] partes = datitos.split(":");
            casillerosList1.add(new CasilleroContent(partes[0], partes[1]));
        }

        /*List<String> lista = new ArrayList<>();
        lista.addAll(datos.returnData());
        java.util.Collections.sort(lista);*/
        /*for(String datitos: lista) {
            String[] partes = datitos.split(":");
            casillerosList1.add(new CasilleroContent(partes[0], partes[1]));
        }*/
        /*for(int i=0; i<lista.size(); i++){
            String[] partes = lista.get(i).split(":");
            casillerosList1.add(new CasilleroContent(partes[0], partes[1]));
        }*/

        /*ArrayList<String> lista = new ArrayList<>();
        //lista.addAll(datos.returnData());
        lista.addAll(position, datos.returnData());

        for(String datitos: lista){
            String[] partes = datitos.split(":");
            casillerosList1.add(new CasilleroContent(partes[0], partes[1]));*/
        }
    }

    public void removeItem(int position){
        Data datos = new Data(getContext().getSharedPreferences("SubscriMamalona", Context.MODE_PRIVATE));
        datos.deleteData();
        casillerosList1.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

}
