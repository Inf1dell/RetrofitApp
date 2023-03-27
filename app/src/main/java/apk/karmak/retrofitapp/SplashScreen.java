package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
                    if (isOpenAlread())
                    {
                        if(isSignIn().length()>0||isSignIn()!=null||!isSignIn().equals("")){
                            Intent login = new Intent(SplashScreen.this, PatientCardActivity.class);
//                            Intent login = new Intent(SplashScreen.this, PinCodeActivity.class);
                            startActivity(login);
                        }else {
                            Intent login = new Intent(SplashScreen.this, LoginActivity.class);
                            startActivity(login);
                        }
                    }
                    else {
                        Intent board = new Intent(SplashScreen.this, OnBoard.class);
                        startActivity(board);
                        //test
//                        ?t/
                    }
                    finish();

                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }
    private boolean isOpenAlread() {
        SharedPreferences sharedPreferences=getSharedPreferences("board_pref",MODE_PRIVATE);
        return sharedPreferences.getBoolean("board",false);
    }
    private String isSignIn() {
        SharedPreferences sharedPreferences=getSharedPreferences("token_pref",MODE_PRIVATE);
        return sharedPreferences.getString("token","");
    }
}