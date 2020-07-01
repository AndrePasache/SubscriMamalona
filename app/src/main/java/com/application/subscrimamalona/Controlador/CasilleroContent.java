package com.application.subscrimamalona.Controlador;

public class CasilleroContent {
    private String titulo, monto, metodo_pago, dias_restantes, moneda;

    public CasilleroContent(String titulo, String monto, String dias_restantes, String metodo_pago, String moneda){
        this.titulo = titulo;
        this.monto = monto;
        this.metodo_pago = metodo_pago;
        this.dias_restantes = dias_restantes;
        this.moneda = moneda;
    }


    public String getTitulo(){
        return this.titulo;
    }
    public String getMonto(){
        return this.monto;
    }
    public String getMetodo_pago(){
        return this.metodo_pago;
    }
    public String getDias_restantes(){
        return this.dias_restantes;
    }
    public String getMoneda(){
        return this.moneda;
    }
}
