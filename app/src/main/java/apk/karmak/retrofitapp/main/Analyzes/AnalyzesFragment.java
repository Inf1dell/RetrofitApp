package apk.karmak.retrofitapp.main.Analyzes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import apk.karmak.retrofitapp.PinCodeActivity;
import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.auth.SignInActivity;
import apk.karmak.retrofitapp.auth.modal.DataModel;
import apk.karmak.retrofitapp.main.Analyzes.modal.NewsModal;
import apk.karmak.retrofitapp.webservice.MyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AnalyzesFragment extends Fragment {

    RecyclerView hor_news;
    private static CustomAdapter adapter;
    private ArrayList<NewsModal> modals;

    ArrayList<NewsModal> newsModals = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_analyzes, container, false);

        hor_news = v.findViewById(R.id.hor_news);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medic.madskill.ru/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI myAPI = retrofit.create(MyAPI.class);
        Call<List<NewsModal>> call = myAPI.getNews();
        call.enqueue(new Callback<List<NewsModal>>() {
            @Override
            public void onResponse(Call<List<NewsModal>> call, Response<List<NewsModal>> response) {
                if(!response.isSuccessful()) {
                    Log.e("MSG", response.code()+"");
                    Log.e("MSG", new Gson().toJson(response.body())+"");
//                                Log.e("MSG", response.body().getToken()+"");
                    return;
                }
                List<NewsModal> List = response.body();
//                String[] oneHeroes = new String[myheroList.size()];



                for (int i = 0; i < List.size(); i++) {
                    newsModals.add(new NewsModal(List.get(i).getId(), List.get(i).getName(),
                            List.get(i).getDescription(), List.get(i).getPrice(), List.get(i).getImage() ));


                    Log.e("Name:", List.get(i).getName());
                    Log.e("Description", List.get(i).getDescription());
                    Log.e("Image", List.get(i).getImage());
                    Log.e("id", List.get(i).getId()+"");
                    Log.e("edn", "---------");
                }
                CustomAdapter adapter=new CustomAdapter(newsModals,getActivity());

                hor_news.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<NewsModal>> call, Throwable t) {
                Log.e("MSG", t.getMessage());

            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://medic.madskill.ru/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        MyAPI myAPI = retrofit.create(MyAPI.class);
//        Call<NewsModal> call = myAPI.getNews();
//        call.enqueue(new Callback<NewsModal>() {
//            @Override
//            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
//                Log.e("t", response+"");
//                if(!response.isSuccessful()) {
//                    return;
//                }
//                Log.e("t", call+"");
//            }
//
//            @Override
//            public void onFailure(Call<NewsModal> call, Throwable t) {
//                Log.e("t", "Error");
//            }
//        });

        return v;
    }
}