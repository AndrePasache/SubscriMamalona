package com.application.subscrimamalona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.subscrimamalona.DB.Conexion;
import com.application.subscrimamalona.DB.Usuarios;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conexion = new Conexion(this);

        botonIngresar = (Button)findViewById(R.id.buttonIngresar);
        botonRegistrarse = (Button)findViewById(R.id.buttonRegistrate);
        eUsuario = (EditText)findViewById(R.id.eUsuario);
        eContrasena = (EditText)findViewById(R.id.eContrasena);

        botonIngresar.setOnClickListener(this);
        botonRegistrarse.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            Usuarios usuario = (Usuarios)bundle.getSerializable("usuario");
            eUsuario.setText(usuario.getUser());
            eContrasena.setText(usuario.getPassword());
        }
    }
    Button botonIngresar, botonRegistrarse;
    EditText eUsuario, eContrasena;
    Conexion conexion;

    @Override
    public void onClick(View v) {

        SQLiteDatabase db = conexion.getReadableDatabase();

        if (v.getId() == R.id.buttonIngresar){
            String inputUsuario = eUsuario.getText().toString();
            String inputContrasena = eContrasena.getText().toString();

            if (inputUsuario.equals("")){
                Toast.makeText(this,getString(R.string.alertaUsuario),Toast.LENGTH_SHORT).show();
            } else if (inputContrasena.equals("")) {
                Toast.makeText(this,getString(R.string.alertaContrasena),Toast.LENGTH_SHORT).show();
            }else {
                String[] campos = {Usuarios.CAMPO_USER, Usuarios.CAMPO_PASSWORD};

                Cursor cursor = db.query(Usuarios.TABLA_USUARIOS, campos, null, null, null, null, null);
                while (cursor.moveToNext()){
                    String user = cursor.getString(0);
                    String password = cursor.getString(1);
                    if (user.equals(inputUsuario) && password.equals(inputContrasena)){
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this,getString(R.string.incorrectos),Toast.LENGTH_LONG).show();
                    }
                }

            }

        }

        else if (v.getId()== R.id.buttonRegistrate){
            Intent intent = new Intent(this, Registrarse.class);
            startActivity(intent);
        }
    }
}
