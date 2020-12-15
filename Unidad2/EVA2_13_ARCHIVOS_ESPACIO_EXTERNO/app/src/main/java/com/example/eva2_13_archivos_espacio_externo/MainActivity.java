package com.example.eva2_13_archivos_espacio_externo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtTxtDatos;
    String rutaSDApp, rutaSD;
    boolean bLeerEscArch = false;
    final static int PERMISO_ESCRITURA = 200;
    final static String nombre = "prueba.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtDatos = findViewById(R.id.edtTxtDatos);
        rutaSD = Environment.getExternalStorageDirectory().getAbsolutePath();
        rutaSDApp = getExternalFilesDir(null).getAbsolutePath();
        //  Log.wtf("SD", rutaSD);
        //  Log.wtf("App", rutaSDApp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Ver version de Android que tienes
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_ESCRITURA);
            } else {
                bLeerEscArch = true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISO_ESCRITURA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bLeerEscArch = true;
            }
        }
    }

    public void guardar(View v) {
        if (bLeerEscArch) {
            String[] aCadenas = edtTxtDatos.getText().toString().split("\n");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(rutaSDApp + "/" + nombre);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                for (String cade : aCadenas) {
                    bufferedWriter.append(cade);
                    bufferedWriter.append("\n");
                }
                bufferedWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void leer(View v) {
        if (bLeerEscArch) {
            String sCade;
            try {
                FileInputStream fileInputStream = new FileInputStream(rutaSDApp + "/" + nombre);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                    while ((sCade = bufferedReader.readLine()) != null) {
                        edtTxtDatos.append(sCade);
                        edtTxtDatos.append("\n");

                    }
                    bufferedReader.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}