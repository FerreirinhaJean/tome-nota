package com.takenote.tomenota.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.takenote.tomenota.R;
import com.takenote.tomenota.model.util.Preference;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Preference preference = new Preference(this);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preference.recuperarPreferencia()) {
                    startActivity(new Intent(SplashScreenActivity.this, PrincipalActivity.class));
                    finish();
                } else {
                    preference.salvarPreferencia(true);
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }
        }, 2000);


    }
}
