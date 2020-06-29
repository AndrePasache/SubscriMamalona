package com.application.subscrimamalona.Controlador;

public class CasilleroContent {
    private String titulo, monto;

    public CasilleroContent(String titulo, String monto){
        this.titulo = titulo;
        this.monto = monto;
    }


    public String getTitulo(){
        return this.titulo;
    }
    public String getMonto(){
        return this.monto;
    }
}
