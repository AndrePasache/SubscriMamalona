package com.application.subscrimamalona.Controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.subscrimamalona.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subscripciones extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public Subscripciones() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2 = inflater.inflate(R.layout.fragment_subscripciones, container, false);

        ArrayList<CasilleroContent> casillerosList2 = new ArrayList<>();
        casillerosList2.add(new CasilleroContent("Spotify familiar","34"));
        casillerosList2.add(new CasilleroContent("Smartfit","220"));

        mRecyclerView = view2.findViewById(R.id.recyclerViewSubs);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CasilleroAdapter2(casillerosList2);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view2;
    }
}
