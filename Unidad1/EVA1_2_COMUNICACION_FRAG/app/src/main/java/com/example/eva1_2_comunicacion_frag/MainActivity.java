package com.example.eva1_2_comunicacion_frag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Recuperar fragmentos
    ListFragment lista;
    DataFragment datos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //Acceder
    //Hay un método que se ejecuta cuando un fragmento se vincula


    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment.getClass() == ListFragment.class) {
            lista = (ListFragment) fragment;
        }else if (fragment.getClass() == DataFragment.class) {
            datos = (DataFragment)fragment;
        }

    }

    public void onMessageFromFragmentToMain(String sender, String param) {
        if (sender.equals("LISTA")) { //Recibimos la informacion
        //Enviar al fragmento data
            datos.MessageFromMainToFragment(param);
        } else if (sender.equals("DATA")) {
            //Imprimir un mensaje
            Toast.makeText(this, param, Toast.LENGTH_SHORT).show();
        }
    }

}