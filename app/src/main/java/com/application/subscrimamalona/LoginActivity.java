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

public class LoginActivity<bundle> extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botonIngresar = (Button)findViewById(R.id.buttonIngresar);
        botonRegistrarse = (Button)findViewById(R.id.buttonRegistrate);
        eUsuario = (EditText)findViewById(R.id.eUsuario);
        eContrasena = (EditText)findViewById(R.id.eContrasena);

        botonIngresar.setOnClickListener(this);
        botonRegistrarse.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            String usuario = (String) bundle.get("usuario");
            eUsuario.setText(usuario);
        }
    }
    Button botonIngresar, botonRegistrarse;
    EditText eUsuario, eContrasena;


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonIngresar){
            String inputUsuario = eUsuario.getText().toString();
            String inputContrasena = eContrasena.getText().toString();

            if (inputUsuario.equals("")){
                Toast.makeText(this,getString(R.string.alertaUsuario),Toast.LENGTH_SHORT).show();
            } else if (inputContrasena.equals("")) {
                Toast.makeText(this,getString(R.string.alertaContrasena),Toast.LENGTH_SHORT).show();
            }else {
                Usuarios usuarios = new Usuarios(this.getSharedPreferences("subscrimanager", Context.MODE_PRIVATE));
                if (usuarios.validar(inputUsuario,inputContrasena)){
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this,getString(R.string.incorrectos),Toast.LENGTH_LONG).show();
                }

            }

        }

        else if (v.getId()== R.id.buttonRegistrate){
            Intent intent = new Intent(this, Registrarse.class);
            startActivity(intent);
        }
    }
}
