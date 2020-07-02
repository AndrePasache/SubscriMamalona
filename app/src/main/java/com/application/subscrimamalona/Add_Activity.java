package com.application.subscrimamalona;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.application.subscrimamalona.DB.Conexion;
import com.application.subscrimamalona.DB.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Add_Activity extends AppCompatActivity {
    Spinner spinner, spinner2;
    Button bfecha, bhora, close, agregar;
    EditText efecha, Subsname, Monto, MetodoPago, ehora;
    String Periodo;
    Long tiempoInicial;
    TextView dias_faltan;
    CountDownTimer countDownTimer;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    Date fechaYhora = new Date();
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    private int dia, mes, ano, minutos, hora;

    Random r = new Random();
    int randomNo = r.nextInt(500+1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
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

        fechaYhora = calendar.getTime();

        close = (Button)findViewById(R.id.buttoncross);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver(v);
            }
        });

        agregar = (Button)findViewById(R.id.buttonAdd);
        dias_faltan = (TextView)findViewById(R.id.dias_faltan);
        agregar.setOnClickListener(new View.OnClickListener() {
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


                    /*tiempoInicial = AlertTime;
                    countDownTimer = new CountDownTimer(tiempoInicial,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int dias = (int)((millisUntilFinished / 1000) / 86400);
                            String countDownText = String.format("%2d", dias);
                            ksjfhkajfh.setText(countDownText);
                        }

                        @Override
                        public void onFinish() {
                            askjhksfha.setText(DateUtils.formatElapsedTime(0));

                        }
                    }

                     */







                    Toast.makeText(Add_Activity.this, "Recordatorio guardado.", Toast.LENGTH_SHORT).show();
                }
                sendInfo(v);
            }
        });

        Subsname = findViewById(R.id.editText3);
        Monto = findViewById(R.id.editText4);
        MetodoPago = findViewById(R.id.editText5);

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

    public void sendInfo(View view){
        String inputSubsname = Subsname.getText().toString();
        String inputMonto = Monto.getText().toString();
        String inputTipo = spinner.getSelectedItem().toString();
        String inputMetodo = MetodoPago.getText().toString();
        String inputMoneda = spinner2.getSelectedItem().toString();
        String inputFechaPago = efecha.getText().toString();
        String inputRecordatorio = ehora.getText().toString();


        if (inputSubsname.equals("")|| inputMonto.equals("")|| inputMetodo.equals("")|| inputFechaPago.equals("")|| inputRecordatorio.equals("") ){
            Toast.makeText(this, "Ningún campo debe quedar vacío", Toast.LENGTH_SHORT).show();
        } else {
            Conexion conexion = new Conexion(this);
            SQLiteDatabase db = conexion.getWritableDatabase();

            /*if (Periodo.equals("1")){
                Periodo = Periodo + " día";
            } else if (Periodo.equals("0")){
                Periodo = "ES HOY!";
            } else {
                Periodo = Periodo + " días";
            }*/

            ContentValues values = new ContentValues();
            values.put(Data.CAMPO_ID,randomNo);
            values.put(Data.CAMPO_NOMBRE, inputSubsname);
            values.put(Data.CAMPO_MONTO, inputMonto);
            values.put(Data.CAMPO_TIPO, inputTipo);
            values.put(Data.CAMPO_METODO_PAGO, inputMetodo);
            values.put(Data.CAMPO_MONEDA, inputMoneda);
            values.put(Data.CAMPO_DIAS_FALTAN, Periodo);
            values.put(Data.CAMPO_FECHA_PAGO, inputFechaPago);
            values.put(Data.CAMPO_RECORDATORIO, inputRecordatorio);

            Long id = db.insert(Data.TABLA_DATA, Data.CAMPO_ID, values);
            db.close();

            if (inputTipo.equals("Pago")) {
                Toast.makeText(this, "Se añadió un nuevo pago", Toast.LENGTH_SHORT).show();
            } else if (inputTipo.equals("Subscripción")) {
                Toast.makeText(this, "Se añadió una nueva subscripción", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void volver(View view){
        Intent volver = new Intent(this,MainActivity.class);
        startActivity(volver);
    }
}
