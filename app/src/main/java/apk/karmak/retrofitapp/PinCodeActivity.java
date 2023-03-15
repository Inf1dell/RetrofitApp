package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PinCodeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView scip, resultTextView;
    ImageView pin1,pin2,pin3,pin4;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    ImageButton del;


    String pinCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);

        scip=findViewById(R.id.scip2);
        scip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(PinCodeActivity.this, PatientCardCreate.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main);

            }
        });
        resultTextView = findViewById(R.id.resultTextView);
        pin1=findViewById(R.id.pin1);
        pin2=findViewById(R.id.pin2);
        pin3=findViewById(R.id.pin3);
        pin4=findViewById(R.id.pin4);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
        btn0=findViewById(R.id.btn0);

        del=findViewById(R.id.btnDel);


    }

    @Override
    public void onClick(View v) {
        if (pinCode.length() <= 3) {
            switch (v.getId()) {
                case R.id.btn1:
                    pinCode = pinCode + 1;
                    // pin1.setBackgroundResource(R.drawable.btn_yandex_blue);
                    break;
                case R.id.btn2:
                    pinCode = pinCode + 2;
                    break;
                case R.id.btn3:
                    pinCode = pinCode + 3;
                    break;
                case R.id.btn4:
                    pinCode = pinCode + 6;
                    break;
                case R.id.btn5:
                    pinCode = pinCode + 5;
                    break;
                case R.id.btn6:
                    pinCode = pinCode + 4;
                    break;
                case R.id.btn7:
                    pinCode = pinCode + 8;
                    break;
                case R.id.btn8:
                    pinCode = pinCode + 7;
                    break;
                case R.id.btn9:
                    pinCode = pinCode + 9;
                    break;
                case R.id.btn0:
                    pinCode = pinCode + 0;
                    break;
                case R.id.btnDel:
                //    pinCode.substring(0, pinCode.length() - 1); // не работает
                    break;
            }
        }
        resultTextView.setText(pinCode);
        if (pinCode.length() == 1) {
            pin1.setBackgroundResource(R.drawable.btn_yandex_blue);
        } else if (pinCode.length() == 2) {
            pin2.setBackgroundResource(R.drawable.btn_yandex_blue);
        } else if (pinCode.length() == 3) {
            pin3.setBackgroundResource(R.drawable.btn_yandex_blue);
        } else if (pinCode.length() == 4) {
            pin4.setBackgroundResource(R.drawable.btn_yandex_blue);
        }
    }
}