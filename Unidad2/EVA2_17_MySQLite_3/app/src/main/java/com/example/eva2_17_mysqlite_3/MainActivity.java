package com.example.eva2_17_mysqlite_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstDatos;
    SQLiteDatabase sqlDB;
    final String NOMBRE_DB = "mi_base_datos";
    List<String> nombres = new ArrayList<String>();

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

        //Insertar valores
        ContentValues cvDatos = new ContentValues();
        cvDatos.put("nombre", "Osman");
        cvDatos.put("apellido", "Tarango");
        sqlDB.insert("mitabla", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Janeth");
        cvDatos.put("apellido", "Diaz");
        sqlDB.insert("mitabla", null, cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Cynthia");
        cvDatos.put("apellido", "Chavez");
        sqlDB.insert("mitabla", null, cvDatos);
        cvDatos.clear();
        long iClave;
        cvDatos.put("nombre", "Juan");
        cvDatos.put("apellido", "Perez");
        iClave = sqlDB.insert("mitabla", null, cvDatos);
        Toast.makeText(this, iClave + "", Toast.LENGTH_LONG).show();

        //Update
        cvDatos.clear();
        cvDatos.put("nombre", "Juanito");
        String[] args = {iClave + ""};
        sqlDB.update("mitabla", cvDatos, "id = ?", args);   //? = Comodin que se va a sustituir por un argumento

        //Delete
        String[] args2 = {"Cynthia"};
        sqlDB.delete("mitabla", "nombre = ?", args2);

        String[] columns = {"id", "nombre", "apellido"};
        String[] args3 = {"Juan"};
        Cursor cursor = sqlDB.query("mitabla", columns, "nombre like ?", args3, null, null, "apellido");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")) + " " + cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }
        lstDatos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres));


    }
}