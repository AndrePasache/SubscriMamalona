package com.application.subscrimamalona;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.subscrimamalona.Controlador.Data;
import com.application.subscrimamalona.Controlador.Pagos;
import com.application.subscrimamalona.Controlador.Subscripciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Add_Activity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner, spinner2, spinner3;
    Button bfecha, close, agregar;
    EditText efecha, Subsname, Monto, MetodoPago;
    private int dia, mes, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        String[] tipo = {"Suscripción","Pago"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(tipo));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList);
        spinner.setAdapter(arrayAdapter);

        String[] moneda = {"PEN","USD"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(moneda));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList2);
        spinner2.setAdapter(arrayAdapter2);

        String[] ciclo = {"Mensual","Trimestral","Semestral","Anual"};
        ArrayList<String> arrayList3 = new ArrayList<>(Arrays.asList(ciclo));
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList3);
        spinner3.setAdapter(arrayAdapter3);

        bfecha = (Button)findViewById(R.id.bfecha);
        efecha = (EditText)findViewById(R.id.efecha);
        bfecha.setOnClickListener(this);
        efecha.setOnClickListener(this);

        close = (Button)findViewById(R.id.buttoncross);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Volver(v);
            }
        });

        agregar = (Button)findViewById(R.id.buttonAdd);

        Subsname = findViewById(R.id.editText3);
        Monto = findViewById(R.id.editText4);

        MetodoPago = findViewById(R.id.editText5);
    }

    @Override
    public void onClick(View v) {
        if (v == bfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha.setText(dayOfMonth + " / " + (month + 1) + " / " + year);
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }

        /*if (v == agregar) {
            //Primera Versión: Guardamos solo el nombre, tipo de subscripción/pago y el monto
            String inputSubsname = Subsname.getText().toString();
            String inputMonto = Monto.getText().toString();
            String inputTipo = spinner.getAdapter().toString();

            if (inputSubsname.equals("") || inputMonto.equals("") || inputTipo.equals("")) {
                Toast.makeText(this, "Ningún campo puede quedar vacío", Toast.LENGTH_SHORT).show();
            }
            else {
                Data datosCasilleros = new Data(this.getSharedPreferences("SubscriMamalona", Context.MODE_PRIVATE));*/
                //SharedPreferences.Editor editor = this.getSharedPreferences("SubscriMamalona", Context.MODE_PRIVATE).edit();
                //datosCasilleros.guardarData(inputSubsname,inputMonto,inputTipo);
                //datosCasilleros.validarData(inputSubsname,inputMonto,inputTipo);
                //editor.putString("titulo", inputSubsname);
                //editor.putString("monto", inputMonto);
               // datosCasilleros.escribirData();
                //editor.commit();

                /*if (inputTipo.equals("Pago")) {
                    Intent intent = new Intent(this, Pagos.class);
                    intent.putExtra("titulo", inputSubsname);
                    intent.putExtra("monto", inputMonto);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(this, Subscripciones.class);
                    intent.putExtra("titulo", inputSubsname);
                    intent.putExtra("monto", inputMonto);
                    startActivity(intent);
                }*/

                /*Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("titulo",inputSubsname);
                intent.putExtra("monto",inputMonto);
                intent.putExtra("tipo", inputTipo);
                startActivity(intent);
            }
        }*/
    }
    public void Volver(View view){
        Intent volver = new Intent(this,MainActivity.class);
        startActivity(volver);
    }
}
