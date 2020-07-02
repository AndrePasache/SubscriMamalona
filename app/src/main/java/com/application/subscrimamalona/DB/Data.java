package com.application.subscrimamalona.DB;

import android.widget.SpinnerAdapter;

import java.io.Serializable;

public class Data implements Serializable{

    private String nombre;
    private String monto;
    private String tipo;
    private String dias_faltan;
    private String metodo_pago;
    private String moneda;

    public Data(String nombre, String monto, String tipo, String dias_faltan, String metodo_pago, String moneda) {
        this.nombre = nombre;
        this.monto = monto;
        this.tipo = tipo;
        this.dias_faltan = dias_faltan;
        this.metodo_pago = metodo_pago;
        this.moneda = moneda;
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

    public String getDias_faltan() {
        return dias_faltan;
    }

    public void setDias_faltan(String dias_faltan) {
        this.dias_faltan = dias_faltan;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public static final String CREATE_TABLA_DATA = "CREATE TABLE data (nombre TEXT, monto TEXT, tipo TEXT, dias_faltan TEXT, metodo_pago TEXT, moneda TEXT)";
    public static final String DROP_TABLA_DATA = "DROP TABLE IF EXISTS data";

    public static final String TABLA_DATA = "data";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_MONTO = "monto";
    public static final String CAMPO_TIPO = "tipo";
    public static final String CAMPO_METODO_PAGO = "metodo_pago";
    public static final String CAMPO_MONEDA = "moneda";
    public static final String CAMPO_DIAS_FALTAN = "dias_faltan";
}