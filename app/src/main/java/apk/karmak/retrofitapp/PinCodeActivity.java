package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apk.karmak.retrofitapp.patient.PatientCardActivity;
import apk.karmak.retrofitapp.main.MainActivity;

public class PinCodeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView scip, title;
    ImageView pin1,pin2,pin3,pin4;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    ImageButton del;

    ArrayList<String> numbers_list = new ArrayList<>();

    String passCode = "";
    String num1,num2,num3,num4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_code);


        title=findViewById(R.id.textView8);


        scip=findViewById(R.id.scip2);
        scip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getPatientStatus()==true){
                    Intent main = new Intent(PinCodeActivity.this, MainActivity.class);
                    main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(main);
                }else {
                    Intent main = new Intent(PinCodeActivity.this, PatientCardActivity.class);
                    main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(main);
                }

            }
        });


        if(!getPassCode().equals("")){
            scip.setVisibility(View.INVISIBLE);
            title.setText("Введите пароль");
        }



        pin1=findViewById(R.id.pin1);
        pin2=findViewById(R.id.pin2);
        pin3=findViewById(R.id.pin3);
        pin4=findViewById(R.id.pin4);

        btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3=findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4=findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5=findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6=findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7=findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        btn8=findViewById(R.id.btn8);
        btn8.setOnClickListener(this);
        btn9=findViewById(R.id.btn9);
        btn9.setOnClickListener(this);
        btn0=findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        del=findViewById(R.id.btnDel);
        del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                numbers_list.add("1");
                passNumber(numbers_list);
                break;
            case R.id.btn2:
                numbers_list.add("2");
                passNumber(numbers_list);
                break;
            case R.id.btn3:
                numbers_list.add("3");
                passNumber(numbers_list);
                break;
            case R.id.btn4:
                numbers_list.add("4");
                passNumber(numbers_list);
                break;
            case R.id.btn5:
                numbers_list.add("5");
                passNumber(numbers_list);
                break;
            case R.id.btn6:
                numbers_list.add("6");
                passNumber(numbers_list);
                break;
            case R.id.btn7:
                numbers_list.add("7");
                passNumber(numbers_list);
                break;
            case R.id.btn8:
                numbers_list.add("8");
                passNumber(numbers_list);
                break;
            case R.id.btn9:
                numbers_list.add("9");
                passNumber(numbers_list);
                break;
            case R.id.btn0:
                numbers_list.add("0");
                passNumber(numbers_list);
                break;
            case R.id.btnDel:
                numbers_list.clear();
                passNumber(numbers_list);
                break;
        }
    }

    private void passNumber(ArrayList<String> numbers_list) {
        if(numbers_list.size() == 0){
            pin1.setBackgroundResource(R.drawable.pin_dot_inactive);
            pin2.setBackgroundResource(R.drawable.pin_dot_inactive);
            pin3.setBackgroundResource(R.drawable.pin_dot_inactive);
            pin4.setBackgroundResource(R.drawable.pin_dot_inactive);
        } else {
            switch (numbers_list.size()) {
                case 1:
                    num1=numbers_list.get(0);
                    pin1.setBackgroundResource(R.drawable.button_login_active);
                    break;
                case 2:
                    num2=numbers_list.get(1);
                    pin2.setBackgroundResource(R.drawable.button_login_active);
                    break;
                case 3:
                    num3=numbers_list.get(2);
                    pin3.setBackgroundResource(R.drawable.button_login_active);
                    break;
                case 4:
                    num4=numbers_list.get(3);
                    pin4.setBackgroundResource(R.drawable.button_login_active);
                    passCode = num1+num2+num3+num4;
                    if(getPassCode().length()==0 || getPassCode().equals("") || getPassCode()==null){
                        savePassCode(passCode);
                        Intent patient = new Intent(PinCodeActivity.this, PatientCardActivity.class);
                        patient.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(patient);
                    }else {
                        matchPassCode();
                    }
                    break;
            }
        }
    }

    private void matchPassCode() {
        if(getPassCode().equals(passCode)) {
            if(getPatientStatus()==true){
                Intent main = new Intent(PinCodeActivity.this, MainActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main);
            }else {
                Intent main = new Intent(PinCodeActivity.this, PatientCardActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main);
            }
        } else {
//            Toast.makeText(this, "Pass error", Toast.LENGTH_SHORT).show();
            numbers_list.clear();
            passNumber(numbers_list);
            pin1.setBackgroundResource(R.drawable.pin_dot_inactive);
            pin2.setBackgroundResource(R.drawable.pin_dot_inactive);
            pin3.setBackgroundResource(R.drawable.pin_dot_inactive);
            pin4.setBackgroundResource(R.drawable.pin_dot_inactive);
        }
    }

    private SharedPreferences.Editor savePassCode(String passCode){
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", passCode);
        editor.commit();

        return editor;
    }

    private String getPassCode() {
        SharedPreferences preferences = getSharedPreferences("passcode_pref",Context.MODE_PRIVATE);
        return preferences.getString("passcode","");
    }

    private Boolean getPatientStatus() {
        SharedPreferences preferences = getSharedPreferences("patient_pref",Context.MODE_PRIVATE);
        return preferences.getBoolean("patient",false);
    }
}