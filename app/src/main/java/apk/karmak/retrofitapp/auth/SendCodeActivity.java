package apk.karmak.retrofitapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.auth.modal.DataModel;
import apk.karmak.retrofitapp.webservice.MyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendCodeActivity extends AppCompatActivity {

    Button login, yandex;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);

        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Отправка кода...");


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
                pd.show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://medic.madskill.ru/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MyAPI getWeatherApi = retrofit.create(MyAPI.class);
                Call<DataModel> call = getWeatherApi.getMess(email.getText().toString());
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        if (!response.isSuccessful()) {
                            return;
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        pd.dismiss();
                        Log.e("Error get code", t.getMessage());
                        finish();
                    }
                });

                Intent code = new Intent(SendCodeActivity.this, SignInActivity.class);
                code.putExtra("email", email.getText().toString());
                startActivity(code);

            }
        });
    }
}