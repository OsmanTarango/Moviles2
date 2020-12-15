package com.example.eva2_16_mysqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstDatos;
    SQLiteDatabase sqlDB;
    final String NOMBRE_DB = "mi_base_datos";
    List <String> nombres = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstDatos = findViewById(R.id.lstDatos);

        sqlDB = openOrCreateDatabase(NOMBRE_DB, MODE_PRIVATE, null);

        try {
            sqlDB.execSQL("create table mitabla(id integer primary key autoincrement," + "nombre text," + "apellido text);");
        }catch (SQLiteException e) {
            e.printStackTrace();
        }

        sqlDB.beginTransaction();

        try {
            sqlDB.execSQL("insert into mitabla(nombre, apellido) values ('Osman', 'Tarango')");
            sqlDB.execSQL("insert into mitabla(nombre, apellido) values ('Janeth', 'Diaz')");
            int i = 5/0;
            sqlDB.execSQL("insert into mitabla(nombre, apellido) values ('Irman', 'Rodriguez')");
            sqlDB.execSQL("insert into mitabla(nombre, apellido) values ('Jonathan', 'Noche')");
            sqlDB.execSQL("insert into mitabla(nombre, apellido) values ('Cynthia', 'Chavez')");
            sqlDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            sqlDB.endTransaction();
        }

        Cursor cursor = sqlDB.rawQuery("select * from mitabla;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")) + " " + cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }
            lstDatos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres));
    }
}