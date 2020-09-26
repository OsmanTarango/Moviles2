package com.example.eva1_4_frag_parametros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void crearFrag(View v) {
        //Transaccion
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //Crear fragmento
        ParamFragment paramFragment = ParamFragment.newInstance("Hola mundo.", "Valores asignados al fragmento correctamente");
        //Reemplazar nuestro layout con el fragmento
        ft.replace(R.id.frmLayout, paramFragment);
        //Commit
        ft.commit();
    }

}