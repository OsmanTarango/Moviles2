package com.example.rep_2_asyntask_banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int[] imagenes = {R.drawable.f1, R.drawable.f2, R.drawable.f3};
    int indice = 0;
    ImageView imgVwBanner;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imgVwBanner.setImageResource(imagenes[indice]);
            if(indice>1) {
                indice = 0;
            } else {
                indice++;
            }
        }
    };

    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            int i = 0, j = 100;

            while(i<j){
                try {
                    Thread.sleep(1000);
                    runOnUiThread(runnable);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                i++;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);
        thread.start();
    }
}