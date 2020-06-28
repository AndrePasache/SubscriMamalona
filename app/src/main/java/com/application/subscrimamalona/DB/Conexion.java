package com.application.subscrimamalona.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion extends SQLiteOpenHelper {

    public static final String DB_MYDATA = "bd_mydata";

    public Conexion(@Nullable Context context) {
        super(context, DB_MYDATA, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Data.CREATE_TABLA_DATA);
        db.execSQL(Usuarios.CREATE_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Data.DROP_TABLA_DATA);
        db.execSQL(Usuarios.DROP_TABLA_USUARIO);
        onCreate(db);
    }
}
