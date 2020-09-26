package com.example.rep_5_servicios;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    Intent intento;
    Thread thread;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.wtf("MI SERVICIO", "onCreate");
    }

    @Override
    public void onStart(final Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.wtf("MI_SERVICIO", "onStart");
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        thread.sleep(1000);
                      //  Log.wtf("MI_SERVICIO","THREAD");
                        intento = new Intent("MI_SERVICIO");
                        sendBroadcast(intento);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                }
            }
        };
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.wtf("MI_SERVICIO", "onDestroy");
        thread.interrupt();
    }
}
