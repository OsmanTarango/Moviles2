package com.example.eva1_5_cambio_orientacion;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class MasterFragment extends Fragment {

    MainActivity main;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_master, container, false);
        Button btnDetail = linearLayout.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dependemos de la orientación
                //Portrait - Lanzar una actividad
                //Landscape - Crear dinamicamente un fragmento
                main.onMessageFromFragmentToMain();

            }
        });
        return linearLayout;
    }
}