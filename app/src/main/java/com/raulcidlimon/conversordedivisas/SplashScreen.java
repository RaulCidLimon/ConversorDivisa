package com.raulcidlimon.conversordedivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Type;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#2369C4"))
                .withBeforeLogoText("Currency converter").withLogo(R.drawable.logo_prueba_pix);

        Typeface font = Typeface.create("proxima", Typeface.NORMAL);
        easySplashScreen.getBeforeLogoTextView().setTypeface(font);

        easySplashScreen.getBeforeLogoTextView().setTextColor(Color.WHITE);
        View view = easySplashScreen.create();
        setContentView(view);
    }
}
