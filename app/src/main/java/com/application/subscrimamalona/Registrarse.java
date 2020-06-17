package com.application.subscrimamalona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.application.subscrimamalona.Controlador.Usuarios;

public class Registrarse extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        botonRegistrarse = (Button)findViewById(R.id.buttonRegistrarse);
        botonRegistrarse.setOnClickListener(this);

        eUsuario = (EditText)findViewById(R.id.regUsuario);
        eContrasena = (EditText)findViewById(R.id.regcontrasena);
        eContrasena2 = (EditText)findViewById(R.id.regcontrasena2);

    }
    Button botonRegistrarse;
    EditText eUsuario, eContrasena, eContrasena2;

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonRegistrarse) {
            String inputUsuario = eUsuario.getText().toString();
            String inputContrasena = eContrasena.getText().toString();
            String inputContrasena2 = eContrasena2.getText().toString();

            if (inputUsuario.equals("")) {
                Toast.makeText(this, getString(R.string.alertaUsuario), Toast.LENGTH_SHORT).show();
            } else if (inputContrasena.equals("") || inputContrasena2.equals("")) {
                Toast.makeText(this, getString(R.string.alertaContrasena), Toast.LENGTH_SHORT).show();
            } else if (!inputContrasena.equals(inputContrasena2)) {
                Toast.makeText(this, getString(R.string.alertaNoCoinciden), Toast.LENGTH_SHORT).show();

            } else {
                Usuarios usuarios = new Usuarios(this.getSharedPreferences("subscrimanager", Context.MODE_PRIVATE));
                usuarios.guardar(inputUsuario, inputContrasena);
                usuarios.escribirUsuarios();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("usuario", inputUsuario);
                startActivity(intent);

            }

        }
    }
}
