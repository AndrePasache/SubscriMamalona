package com.application.subscrimamalona.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.subscrimamalona.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pagos extends Fragment {

    public Pagos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pagos, container, false);
    }
}
