package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.stream.Collector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button login, yandex;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.emailET);
        login=findViewById(R.id.btnLogin);
        yandex=findViewById(R.id.btnYandex);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(email.getEditableText().length()>0){
                    login.setEnabled(true);
                }else {
                    login.setEnabled(false);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://medic.madskill.ru/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MyAPI getWeatherApi = retrofit.create(MyAPI.class);
                Call<DataModel> call = getWeatherApi.getMess(email.getText().toString());
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        Intent code = new Intent(LoginActivity.this, SignInActivity.class);
                        startActivity(code);
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        Log.e("Error get code", t.getMessage());
                    }
                });
            }
        });
    }
}