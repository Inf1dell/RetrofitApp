package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2*1000);
                    Intent i=new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
}