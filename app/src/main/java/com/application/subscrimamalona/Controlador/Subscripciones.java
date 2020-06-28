package com.application.subscrimamalona.Controlador;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application.subscrimamalona.DB.Conexion;
import com.application.subscrimamalona.DB.Data;
import com.application.subscrimamalona.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subscripciones extends Fragment {
    private ArrayList<CasilleroContent> casillerosList2;
    private RecyclerView mRecyclerView;
    private CasilleroAdapter2 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Conexion conexion;

    public Subscripciones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2 = inflater.inflate(R.layout.fragment_subscripciones, container, false);

        conexion = new Conexion(this.getContext());

        casillerosList2 = new ArrayList<>();

        mRecyclerView = view2.findViewById(R.id.recyclerViewSubs);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CasilleroAdapter2(casillerosList2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CasilleroAdapter2.OnItemClickListener(){
            /*@Override
            public void onItemClick(int position) {
                ;
            }*/

            @Override
            public void onDeleteCLick(int position) {
                removeItem(position);
            }
        });

        insertItem();

        return view2;
    }

    public void insertItem(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] campos = {Data.CAMPO_NOMBRE, Data.CAMPO_MONTO, Data.CAMPO_TIPO};

        Cursor cursor = db.query(Data.TABLA_DATA, campos, null, null, null, null, null);
        while (cursor.moveToNext()){
            String nombre = cursor.getString(0);
            String monto = cursor.getString(1);
            String tipo = cursor.getString(2);
            if (tipo.equals("Subscripción")) {
                casillerosList2.add(new CasilleroContent(nombre, monto));
            }
        }
    }

    public void removeItem(int position){
        SQLiteDatabase db = conexion.getWritableDatabase();

        Cursor cursor = db.query(Data.TABLA_DATA, null, null, null, null, null, null);

        if (cursor.moveToPosition(position)) {
            String rowID = cursor.getString(cursor.getColumnIndex(Data.CAMPO_NOMBRE));
            db.delete(Data.TABLA_DATA, Data.CAMPO_NOMBRE + "=?", new String[]{rowID});
            Toast.makeText(this.getContext(), "Se eliminó la subscripción", Toast.LENGTH_SHORT).show();
            db.close();
        }
        casillerosList2.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
