package com.application.subscrimamalona;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Editar extends AppCompatActivity {
    Spinner spinner, spinner2;
    Button bfecha, bhora, close, guardar;
    EditText efecha, Subsname, Monto, MetodoPago, ehora;
    String Periodo;
    Long tiempoInicial;
    CountDownTimer countDownTimer;
    String countDownt, TAG;


    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int dia, mes, ano, minutos, hora, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Bundle extras = getIntent().getExtras();
        final Data data = (Data)extras.getSerializable("dataEdit");

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

        if(data.getTipo().equals("Subscripción")) {
            int spinnerPosition = 0;
            spinner.setSelection(spinnerPosition);
        } else if (data.getTipo().equals("Pago")) {
            int spinnerPosition = 1;
            spinner.setSelection(spinnerPosition);
        }

        String[] moneda = {"PEN","USD"};
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(moneda));
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this,R.layout.spinner_est,arrayList2);
        spinner2.setAdapter(arrayAdapter2);

        if(data.getMoneda().equals("PEN")) {
            int spinnerPosition = 0;
            spinner2.setSelection(spinnerPosition);
        } else if (data.getMoneda().equals("USD")) {
            int spinnerPosition = 1;
            spinner2.setSelection(spinnerPosition);
        }

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
        final Conexion conexion = new Conexion(this);
        guardar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override

            public void onClick(View v) {

                String fecha = data.getFecha_pago();
                Date fechaActual = new Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
                Date fechaElegida = null;
                try {
                    fechaElegida = formato.parse(fecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long resta = fechaElegida.getTime() - fechaActual.getTime();

                if (!fechaActual.equals(fechaElegida)) {
                    String tag = generateKey();
                    TAG = tag;
                    int random = (int) (Math.random() * 50 + 1);
                    androidx.work.Data data = GuardarData("Susbscrimanager", "¡Hoy es la fecha de tu PAGO/SUSCRIPCIÓN!", random);
                    WorkManagernoti.GuardarNoti(resta, data, tag);
                    tiempoInicial = resta;
                    countDownTimer = new CountDownTimer(tiempoInicial, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int dias = (int) ((millisUntilFinished / 1000) / 86400);
                            countDownt = String.format("%2d", dias);
                            //ksjfhkajfh.setText(countDownText);
                        }

                        @Override
                        public void onFinish() {
                            //askjhksfha.setText(DateUtils.formatElapsedTime(0));

                        }
                    }.start();

                } else{

                    String tag = generateKey();
                    TAG = tag;
                    Long AlertTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                    int random = (int) (Math.random() * 50 + 1);
                    androidx.work.Data data = GuardarData("Susbscrimanager", "¡Hoy es la fecha de tu PAGO/SUSCRIPCIÓN!", random);
                    WorkManagernoti.GuardarNoti(AlertTime, data, tag);

                    Periodo = Long.toString(TimeUnit.MILLISECONDS.toDays(AlertTime));

                    tiempoInicial = AlertTime;
                    countDownTimer = new CountDownTimer(tiempoInicial, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int dias = (int) ((millisUntilFinished / 1000) / 86400);
                            countDownt = String.format("%2d", dias);
                            //ksjfhkajfh.setText(countDownText);
                        }

                        @Override
                        public void onFinish() {
                            //askjhksfha.setText(DateUtils.formatElapsedTime(0));

                        }
                    };
                }
                    editInfo(v);
                }




        });

        Subsname = findViewById(R.id.editText3);
        Monto = findViewById(R.id.editText4);
        MetodoPago = findViewById(R.id.editText5);
        efecha = findViewById(R.id.fecha2);
        ehora = findViewById(R.id.eHora22);

        Subsname.setText(data.getNombre());
        Monto.setText(data.getMonto());
        MetodoPago.setText(data.getMetodo_pago());

        efecha.setText(data.getFecha_pago());
        ehora.setText(data.getRecordatorio());
        id = data.getId();

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

    public void editInfo(View view){
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

            if (Periodo.equals("1")){
                Periodo = Periodo + " día";
            } else if (Periodo.equals("0")){
                Periodo = "ES HOY!";
            } else {
                Periodo = Periodo + " días";
            }

            ContentValues values = new ContentValues();
            values.put(Data.CAMPO_NOMBRE, inputSubsname);
            values.put(Data.CAMPO_MONTO, inputMonto);
            values.put(Data.CAMPO_TIPO, inputTipo);
            values.put(Data.CAMPO_METODO_PAGO, inputMetodo);
            values.put(Data.CAMPO_MONEDA, inputMoneda);
            values.put(Data.CAMPO_DIAS_FALTAN, Periodo);
            values.put(Data.CAMPO_FECHA_PAGO, inputFechaPago);
            values.put(Data.CAMPO_RECORDATORIO, inputRecordatorio);
            values.put(Data.CAMPO_TAG, TAG );
            values.put(Data.CAMPO_COUNT_DOWN, countDownt );


            String[] datitos = {id+""};

            db.update(Data.TABLA_DATA,values, Data.CAMPO_ID + "=?", datitos);
            db.close();

            if (inputTipo.equals("Pago")) {
                Toast.makeText(this, "Se actualizó el pago", Toast.LENGTH_SHORT).show();
            } else if (inputTipo.equals("Subscripción")) {
                Toast.makeText(this, "Se actualizó la subscripción", Toast.LENGTH_SHORT).show();
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