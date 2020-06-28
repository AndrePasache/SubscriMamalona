package com.application.subscrimamalona.DB;

import java.io.Serializable;

public class Data implements Serializable{

    private String nombre, monto, tipo;

    public Data(String nombre, String monto, String tipo) {
        this.nombre = nombre;
        this.monto = monto;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static final String CREATE_TABLA_DATA = "CREATE TABLE data (nombre TEXT, monto TEXT, tipo TEXT)";
    public static final String DROP_TABLA_DATA = "DROP TABLE IF EXISTS data";

    public static final String TABLA_DATA = "data";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_MONTO = "monto";
    public static final String CAMPO_TIPO = "tipo";
}