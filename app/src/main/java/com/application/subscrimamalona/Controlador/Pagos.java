package com.application.subscrimamalona.Controlador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.application.subscrimamalona.MainActivity;
import com.application.subscrimamalona.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pagos extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public Pagos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagos, container, false);

        ArrayList<CasilleroContent> casillerosList1 = new ArrayList<>();
        casillerosList1.add(new CasilleroContent("hola p","546"));
        casillerosList1.add(new CasilleroContent("no waaaay","65.4"));

        mRecyclerView = view.findViewById(R.id.recyclerViewPagos);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CasilleroAdapter(casillerosList1);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
