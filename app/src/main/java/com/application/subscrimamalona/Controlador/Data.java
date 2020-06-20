package com.application.subscrimamalona.Controlador;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class Data {
    //Primera Versión: Guardamos solo el nombre, tipo de subscripción/pago y el monto
    private Set<String> datosCasilleros;
    private SharedPreferences preferences;

    public Data(SharedPreferences preferences){
        this.preferences=preferences;
        leerData();
    }
    public void leerData(){
        datosCasilleros = preferences.getStringSet("data", new HashSet<String>());
    }
    public void guardarData(String nombre, String monto, String tipo){
        datosCasilleros.add(nombre+":"+monto+":"+tipo);
    }
    public void escribirData(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("data",datosCasilleros);
        editor.commit();
    }
    public int cantidadData(){
        return datosCasilleros.size();
    }
    public boolean validarData(String nombre, String monto, String tipo) {
        for (String datos:datosCasilleros) {
            if (datos.equals(nombre+":"+monto+":"+tipo)) {
                return true;
            }
        }
        return false;
    }
    public boolean verificarData(){
        if(datosCasilleros.isEmpty()){
            return false;
        }
        return true;
    }
}
