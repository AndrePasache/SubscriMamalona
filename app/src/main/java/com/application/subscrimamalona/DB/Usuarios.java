package com.application.subscrimamalona.DB;

import java.io.Serializable;

public class Usuarios implements Serializable {

    private String user, password;

    public Usuarios(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static final String CREATE_TABLA_USUARIO = "CREATE TABLE usuarios (user TEXT, password TEXT)";
    public static final String DROP_TABLA_USUARIO = "DROP TABLE IF EXISTS usuarios ";

    public static final String TABLA_USUARIOS = "usuarios";
    public static final String CAMPO_USER = "user";
    public static final String CAMPO_PASSWORD = "password";
}
