package com.ltd.fix.the_movie_db;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class Splash extends Activity {
    public static final int time_out = (int) TimeUnit.SECONDS.toMillis(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },time_out);
    }
}
