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

        //insertItem(k);

        return view;
    }


    public void createContentList(){
        casillerosList1 = new ArrayList<>();
        casillerosList1.add(new CasilleroContent("Universidad de Piura","1700"));
        casillerosList1.add(new CasilleroContent("Trio Hogar","180.5"));
        casillerosList1.add(new CasilleroContent("Luz","150"));
        casillerosList1.add(new CasilleroContent("Sedapal","80"));
        casillerosList1.add(new CasilleroContent("Plan Postpago","76.5"));
        casillerosList1.add(new CasilleroContent("Alquiler local","220.5"));

    }

    //int k = casillerosList1.size();

    /*public void insertItem(int k){

        //Bundle bundle = getIntent().getExtras();
        String name = getArguments().getString("titulo");
        String amount = getArguments().getString("monto");
        casillerosList1.add(k, new CasilleroContent(name, amount));
        mAdapter.notifyItemInserted(k);
        k++;
    }*/

    public void removeItem(int position){
        casillerosList1.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

}
