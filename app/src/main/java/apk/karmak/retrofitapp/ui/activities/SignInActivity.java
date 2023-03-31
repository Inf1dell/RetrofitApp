package apk.karmak.retrofitapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

import apk.karmak.retrofitapp.model.DataModel;
import apk.karmak.retrofitapp.retrofit.MyAPI;
import apk.karmak.retrofitapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    TextView textView6;
    ImageButton back;
    EditText e1, e2, e3, e4;
    String email;
    CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");


        textView6 = findViewById(R.id.textView6);
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        e1 = findViewById(R.id.ECode1);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(e1.getText().length()>0){
                    e2.requestFocus();
                }
            }
        });
        e2 = findViewById(R.id.ECode2);
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(e2.getText().length()>0){
                    e3.requestFocus();
                }else {
                    e1.requestFocus();
                }
            }
        });
        e3 = findViewById(R.id.ECode3);
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(e3.getText().length()>0){
                    e4.requestFocus();
                }else {
                    e2.requestFocus();
                }
            }
        });
        e4 = findViewById(R.id.ECode4);
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(e4.getText().length()>0){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://medic.madskill.ru/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MyAPI getWeatherApi = retrofit.create(MyAPI.class);
                    Call<DataModel> call = getWeatherApi.getCode(email, e1.getText().toString()+e2.getText().toString()+e3.getText().toString()+e4.getText().toString());
                    call.enqueue(new Callback<DataModel>() {
                        @Override
                        public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                            if(!response.isSuccessful()) {
                                Log.e("MSG", response.code()+"");
                                Log.e("MSG", new Gson().toJson(response.body())+"");
//                                Log.e("MSG", response.body().getToken()+"");

                                return;
                            }

                            SharedPreferences preferences = getSharedPreferences("token_pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("token", response.body().getToken());
                            editor.commit();


                            Intent pin = new Intent(SignInActivity.this, PinCodeActivity.class);
                            pin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(pin);
//                            finish();
//                            ActivityCompat.finishAffinity(YourActivity.this);
//                            finishAffinity();
                        }

                        @Override
                        public void onFailure(Call<DataModel> call, Throwable t) {
                            Log.e("MSG", t.getMessage());
                        }
                    });
                }else {
                    e3.requestFocus();
                }
            }
        });



        mCountDownTimer = new CountDownTimer(59*1000, 1*1000) {

            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            public void onTick(long millisUntilFinished) {
                textView6.setText("Отправить код повторно можно \nбудет через "+ (millisUntilFinished+1000)/1000 +" секунд");
            }
            //Задаем действия после завершения отсчета
            public void onFinish() {
                sendCode();
            }
        }.start();


    }

    private void sendCode() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medic.madskill.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI getWeatherApi = retrofit.create(MyAPI.class);
        Call<DataModel> call = getWeatherApi.getMess(email);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                    mCountDownTimer.start();

            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e("Error get code", t.getMessage());
                finish();
            }
        });
    }

}