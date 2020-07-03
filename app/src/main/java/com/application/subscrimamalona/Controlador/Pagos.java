package com.application.subscrimamalona.Controlador;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application.subscrimamalona.DB.Conexion;
import com.application.subscrimamalona.DB.Data;
import com.application.subscrimamalona.Editar;
import com.application.subscrimamalona.MainActivity;
import com.application.subscrimamalona.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pagos extends Fragment {
    private ArrayList<CasilleroContent> casillerosList1;
    private RecyclerView mRecyclerView;
    private CasilleroAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Conexion conexion;

    public Pagos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagos, container, false);

        conexion = new Conexion(this.getContext());

        casillerosList1 = new ArrayList<>();

        mRecyclerView = view.findViewById(R.id.recyclerViewPagos);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new CasilleroAdapter(casillerosList1);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CasilleroAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                editItem(position);
            }

            @Override
            public void onDeleteCLick(int position) {
                removeItem(position);
            }
        });

        insertItem();

        return view;
    }

    public void insertItem(){
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] campos = {Data.CAMPO_ID,Data.CAMPO_NOMBRE, Data.CAMPO_MONTO, Data.CAMPO_TIPO, Data.CAMPO_DIAS_FALTAN, Data.CAMPO_METODO_PAGO, Data.CAMPO_MONEDA, Data.CAMPO_FECHA_PAGO, Data.CAMPO_RECORDATORIO,Data.CAMPO_TAG,Data.CAMPO_COUNT_DOWN};

        Cursor cursor = db.query(Data.TABLA_DATA, campos, null, null, null, null, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String monto = cursor.getString(2);
            String tipo = cursor.getString(3);
            String dias_faltan = cursor.getString(4);
            String metodo_pago = cursor.getString(5);
            String moneda = cursor.getString(6);
            String countDown = cursor.getString(7);

            if (tipo.equals("Pago")) {
                casillerosList1.add(new CasilleroContent(id, nombre, monto, dias_faltan,metodo_pago,moneda));
            }
        }
    }

    public void removeItem(int position){
        SQLiteDatabase db = conexion.getWritableDatabase();

        Cursor cursor = db.query(Data.TABLA_DATA, null, null, null, null, null, null);
        int id = casillerosList1.get(position).getId();

        while (cursor.moveToNext()){
            int iD = cursor.getInt(0);
            if (id == iD){
                String rowID = cursor.getString(cursor.getColumnIndex(Data.CAMPO_ID));
                db.delete(Data.TABLA_DATA, Data.CAMPO_ID + "=?", new String[]{rowID});
                Toast.makeText(this.getContext(), "Se eliminó el pago", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }
        casillerosList1.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void editItem(int positon){
        Intent intent = new Intent(this.getContext(), Editar.class);
        SQLiteDatabase db = conexion.getReadableDatabase();
        String[] campos = {Data.CAMPO_ID,Data.CAMPO_NOMBRE, Data.CAMPO_MONTO, Data.CAMPO_TIPO, Data.CAMPO_DIAS_FALTAN, Data.CAMPO_METODO_PAGO, Data.CAMPO_MONEDA, Data.CAMPO_FECHA_PAGO, Data.CAMPO_RECORDATORIO, Data.CAMPO_TAG};

        Cursor cursor = db.query(Data.TABLA_DATA,campos,null,null,null,null,null);
        int iD = casillerosList1.get(positon).getId();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == iD) {
                String nombre = cursor.getString(1);
                String monto = cursor.getString(2);
                String tipo = cursor.getString(3);
                String dias_faltan = cursor.getString(4);
                String metodo_pago = cursor.getString(5);
                String moneda = cursor.getString(6);
                String fecha_pago = cursor.getString(7);
                String recordatorio = cursor.getString(8);
                String tag = cursor.getString(9);

                eliminarNoti(tag);
                Data a = new Data(id,nombre,monto,tipo,dias_faltan,metodo_pago,moneda,fecha_pago,recordatorio,tag);

                Bundle extras = new Bundle();
                extras.putSerializable("dataEdit",a);
                intent.putExtras(extras);
                startActivity(intent);



            }

        }

    }
    public void eliminarNoti(String tag){
        WorkManager.getInstance(this.getContext()).cancelAllWorkByTag(tag);
    }
}
/* public void removeItem(){
   SQLiteDatabase db = conexion.getReadableDatabase();

 */

