package com.application.subscrimamalona;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class Add_Activity extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner, spinner2, spinner3;
    Button bfecha;
    EditText efecha;
    private int dia, mes, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        String[] tipo = {"suscripción","pago"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(tipo));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList);
        spinner.setAdapter(arrayAdapter);

        String[] moneda = {"PEN","USD"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(moneda));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList2);
        spinner2.setAdapter(arrayAdapter2);

        String[] ciclo = {"mensual","trimestral","semestral","anual"};
        ArrayList<String> arrayList3 = new ArrayList<>(Arrays.asList(ciclo));
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList3);
        spinner3.setAdapter(arrayAdapter3);

        bfecha = (Button)findViewById(R.id.bfecha);
        efecha = (EditText)findViewById(R.id.efecha);
        bfecha.setOnClickListener(this);
        efecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==bfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    efecha.setText(dayOfMonth+ " / "+(month+1)+" / "+year);
                }
            }
            ,ano,mes,dia);
            datePickerDialog.show();
        }

    }
}
