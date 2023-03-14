package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SignInActivity extends AppCompatActivity {
    TextView textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        textView6 = findViewById(R.id.textView6);



        new CountDownTimer(60000, 1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                textView6.setText("Отправить код повторно можно \nбудет через "+ millisUntilFinished/1000 +" секунд");
            }
            //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
            public void onFinish() {
                textView6.setText("Бабах!");

            }
        }
                .start();
    }

}