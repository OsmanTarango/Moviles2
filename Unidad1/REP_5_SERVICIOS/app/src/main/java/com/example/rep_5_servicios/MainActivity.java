package com.example.rep_5_servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwDatos;
    Intent inServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inServicio = new Intent(this, MyService.class);
        BroadcastReceiver breceiver = new MiBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("MI_SERVICIO");
        registerReceiver(breceiver, intentFilter);


    }

    public void iniciar (View v) {
startService(inServicio);
    }

    public void detener (View v) {
stopService(inServicio);

    }


    class MiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Procesamos el mensaje
            Log.wtf("MENSAJE", "THREAD");
        }
    }

}