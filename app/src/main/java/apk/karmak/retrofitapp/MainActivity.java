package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText mail, code;
    Button send, check;
    TextView tSend, tCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail=findViewById(R.id.editMail);
        code=findViewById(R.id.editCode);

        tSend=findViewById(R.id.textMail);
        tCheck=findViewById(R.id.textCode);

        send=findViewById(R.id.btnSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://medic.madskill.ru/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MyAPI getWeatherApi = retrofit.create(MyAPI.class);
                Call<DataModel> call = getWeatherApi.getMess(mail.getText().toString());
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        Log.e("MSG", response.code()+"");
                        Log.e("MSG", response.body().getMessage()+"");
                       tSend.setText(response.body().getMessage()+"");
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        Log.e("MSG", t.getMessage());
                    }
                });
            }
        });
        check=findViewById(R.id.btnCheck);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://medic.madskill.ru/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MyAPI getWeatherApi = retrofit.create(MyAPI.class);
                Call<DataModel> call = getWeatherApi.getCode(mail.getText().toString(), code.getText().toString());
                call.enqueue(new Callback<DataModel>() {
                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                        if (!response.isSuccessful()) {
                            tCheck.setText("Code: " + response.code());
                            return;
                        }
                        Log.e("MSG", response.code()+"");
                        Log.e("MSG", new Gson().toJson(response.body())+"");
                        Log.e("MSG", response.body().getToken()+"");
                        tCheck.setText(response.body().getToken()+"");
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        Log.e("MSG", t.getMessage());
                    }
                });
            }
        });





    }

}