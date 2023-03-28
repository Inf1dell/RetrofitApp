package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import apk.karmak.retrofitapp.auth.SendCodeActivity;
import apk.karmak.retrofitapp.onBoard.OnBoard;

public class LaunchScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch_screen);



        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2*1000);
                    if (isOpenAlread())
                    {
                        if(!isSignIn().equals("")){
                            Intent login = new Intent(LaunchScreen.this, PinCodeActivity.class);
                            startActivity(login);
                        }else {
                            Intent login = new Intent(LaunchScreen.this, SendCodeActivity.class);
                            startActivity(login);
                        }
                    }
                    else {
                        Intent board = new Intent(LaunchScreen.this, OnBoard.class);
                        startActivity(board);
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