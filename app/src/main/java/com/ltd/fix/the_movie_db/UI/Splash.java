package com.ltd.fix.the_movie_db.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ltd.fix.the_movie_db.MainActivity;
import com.ltd.fix.the_movie_db.R;

public class Splash extends Activity {
    public static final int time_out = 3000;

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
