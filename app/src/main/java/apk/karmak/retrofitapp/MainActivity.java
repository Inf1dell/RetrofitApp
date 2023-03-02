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

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://medic.madskill.ru/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        MyAPI myAPI = retrofit.create(MyAPI.class);
//
//        Call<DataModel> call = myAPI.getMessage();
//
//        call.enqueue(new Callback<DataModel>() {
//            @Override
//            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
//                if(response.code() != 200) {
//                    Log.e("", "Check");
//                    return;
//                }
//
//                Log.e("MSG", response.body().toString());
//                Log.e("MSG", response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<DataModel> call, Throwable t) {
//
//            }
//        });

        postData("Ivan@iiii@re");
    }

    private void postData(String name) {

        // below line is for displaying our progress bar.

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medic.madskill.ru/api/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPI retrofitAPI = retrofit.create(MyAPI.class);

        // passing data from our text fields to our modal class.
        DataModel modal = new DataModel("ivan@iii@ru");

        // calling a method to create a post and passing our modal class.
        Call<DataModel> call = retrofitAPI.getMes(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();


                Log.e("MSG", response.code()+"");
//                Log.e("MSG", responseFromAPI.getMessage());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Log.e("MSG", "Error found is : " + t.getMessage());
            }
        });
    }
}