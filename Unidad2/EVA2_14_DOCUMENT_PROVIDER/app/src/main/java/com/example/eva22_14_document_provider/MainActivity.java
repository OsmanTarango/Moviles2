package com.example.eva22_14_document_provider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtTxtdatos;
    final int abrirArchivo = 10;
    final int guardarArchivo = 20;
    Uri uriResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxtdatos = findViewById(R.id.edtTxtDatos);
    }

    public void abrir(View v) {
        Intent intAbrir = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intAbrir.addCategory(Intent.CATEGORY_OPENABLE);
        intAbrir.setType("text/plain"); //Archivos Mimetype
        intAbrir.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriResult); //Opcional. Null = Ruta por defaulta del sistema
        startActivityForResult(intAbrir, abrirArchivo);
    }

    public void cerrar(View v) {
        Intent intGuardar = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intGuardar.addCategory(Intent.CATEGORY_OPENABLE);
        intGuardar.setType("text/plain"); //Archivos Mimetype
        intGuardar.putExtra(Intent.EXTRA_TITLE, "prueba.txt");
        intGuardar.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriResult); //Opcional. Null = Ruta por defaulta del sistema
        startActivityForResult(intGuardar, guardarArchivo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case abrirArchivo:
                if(resultCode == Activity.RESULT_OK) {
                    uriResult = data.getData();
                    Log.wtf("URI", data.getData().toString());
                    String sCade;
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uriResult);
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        while ((sCade = bufferedReader.readLine()) != null) {
                            edtTxtdatos.append(sCade);
                            edtTxtdatos.append(" \n");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case guardarArchivo:
                if(resultCode == Activity.RESULT_OK) {
                    uriResult = data.getData();
                    Log.wtf("URI", data.getData().toString());
                    try {
                        String[] aCade = edtTxtdatos.getText().toString().split("\n");
                        OutputStream outputStream = getContentResolver().openOutputStream(uriResult);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                        for(String cade : aCade) {
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
                break;
        }
    }
}