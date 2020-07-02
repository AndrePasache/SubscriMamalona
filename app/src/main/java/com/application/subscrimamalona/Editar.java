package com.application.subscrimamalona;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.application.subscrimamalona.DB.Conexion;
import com.application.subscrimamalona.DB.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Editar extends AppCompatActivity {
    Spinner spinner, spinner2;
    Button bfecha, bhora, close, guardar;
    EditText efecha, Subsname, Monto, MetodoPago, ehora;
    String Periodo;

    //Conexion conexion;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int dia, mes, ano, minutos, hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Bundle extras = getIntent().getExtras();
        Data data = (Data)extras.getSerializable("dataEdit");

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);

        bfecha = (Button)findViewById(R.id.bfecha);
        bhora = (Button)findViewById(R.id.bhora);
        efecha = (EditText)findViewById(R.id.efecha);
        ehora = (EditText)findViewById(R.id.eHora);


        String[] tipo = {"Subscripción","Pago"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(tipo));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList);
        spinner.setAdapter(arrayAdapter);
        /*if (data.getTipo().equals("Subscripción")&&data.getTipo().equals(arrayAdapter)) {
            spinner.setAdapter(arrayAdapter);
        }else if(data.getTipo().equals("Pago")&&data.getTipo().equals(arrayAdapter)){
            spinner.setAdapter(arrayAdapter);
        }*/
        //spinner.autofill(data.getTipo());

        /*if(data.getTipo().equals("Subscripción")) {
            spinner.setTag(data.getTipo());
        } else if (data.getTipo().equals("Pago")) {
            spinner.setTag(data.getTipo());
        }*/

        String[] moneda = {"PEN","USD"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(moneda));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList2);
        spinner2.setAdapter(arrayAdapter2);

        bfecha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ano = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        calendar.set(Calendar.DAY_OF_MONTH, d);
                        calendar.set(Calendar.MONTH, m);
                        calendar.set(Calendar.YEAR, y);

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = format.format(calendar.getTime());
                        efecha.setText(strDate);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });
        bhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY,h);
                        calendar.set(Calendar.MINUTE,m);

                        ehora.setText(String.format("%02d:%02d", h, m));
                    }
                },hora,minutos,true);
                timePickerDialog.show();
            }
        });

        close = (Button)findViewById(R.id.buttoncross);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver(v);
            }
        });

        guardar = (Button)findViewById(R.id.buttonAdd);
        guardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (!ehora.getText().toString().equals("")||!efecha.getText().toString().equals("")) {

                    String tag = generateKey();
                    Long AlertTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                    int random = (int) (Math.random() * 50 + 1);

                    androidx.work.Data data = GuardarData("Susbscrimanager", "¡Hoy es la fecha de tu PAGO/SUSCRIPCIÓN!", random);
                    WorkManagernoti.GuardarNoti(AlertTime, data, tag);

                    Periodo = Long.toString(TimeUnit.MILLISECONDS.toDays(AlertTime));

                    Toast.makeText(Editar.this, "Recordatorio guardado.", Toast.LENGTH_SHORT).show();
                }
                //sendInfo(v);
            }
        });

        Subsname = findViewById(R.id.editText3);
        Monto = findViewById(R.id.editText4);
        MetodoPago = findViewById(R.id.editText5);

        Subsname.setText(data.getNombre());
        Monto.setText(data.getMonto());
        MetodoPago.setText(data.getMetodo_pago());
        //spinner.setSelected(data.getTipo());
        //spinner2.setAdapter(data.getMoneda());


    }
    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    private androidx.work.Data GuardarData(String titulo, String detalle, int id_noti){ //Guardar info para llevarla al WorManagernoti
        return new androidx.work.Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle",detalle)
                .putInt("id_noti",id_noti).build();
    }

    public void volver(View view){
        Intent volver = new Intent(this,MainActivity.class);
        startActivity(volver);
    }
}