package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medic.madskill.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI getWeatherApi = retrofit.create(MyAPI.class);
        Call<DataModel> call = getWeatherApi.getMess("its@worked.yes");
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                Log.e("MSG", response.code()+"");
                Log.e("MSG", response.body().getMessage()+"");
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                Log.e("MSG", t.getMessage());
            }
        });

//        postData();
    }

//    private void postData() {
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://medic.madskill.ru/api/")
//
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MyAPI retrofitAPI = retrofit.create(MyAPI.class);
//
//        DataModel modal = new DataModel("ivan@iii@ru");
//        Call<DataModel> call = retrofitAPI.getMes(modal);
//
//        call.enqueue(new Callback<DataModel>() {
//            @Override
//            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
//                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
//
//
//                Log.e("MSG", response.code()+"");
////                Log.e("MSG", responseFromAPI.getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<DataModel> call, Throwable t) {
//                Log.e("MSG", "Error found is : " + t.getMessage());
//            }
//        });
//    }
}