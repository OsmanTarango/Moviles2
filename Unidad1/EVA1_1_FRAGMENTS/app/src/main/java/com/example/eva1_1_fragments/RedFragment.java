package com.example.eva1_1_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class RedFragment extends Fragment {

    Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText( context, "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText( context, "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText( context, "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText( context, "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText( context, "", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_red, container, false);

    }
}