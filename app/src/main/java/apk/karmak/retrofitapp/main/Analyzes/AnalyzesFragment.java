package apk.karmak.retrofitapp.main.Analyzes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import apk.karmak.retrofitapp.PinCodeActivity;
import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.auth.SignInActivity;
import apk.karmak.retrofitapp.auth.modal.DataModel;
import apk.karmak.retrofitapp.main.Analyzes.modal.CatalogModel;
import apk.karmak.retrofitapp.main.Analyzes.modal.NewsModal;
import apk.karmak.retrofitapp.webservice.MyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AnalyzesFragment extends Fragment {

    RecyclerView hor_news, catalogList;

    CustomAdapter adapter;
    CatalogAdapter adapterCat;

    ArrayList<NewsModal> newsModals = new ArrayList<>();
    ArrayList<CatalogModel> catalogModels = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_analyzes, container, false);

        catalogList = v.findViewById(R.id.catalogList);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        catalogList.setLayoutManager(layoutManager1);


        hor_news = v.findViewById(R.id.hor_news);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        hor_news.setLayoutManager(layoutManager);

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
                    return;
                }
                List<NewsModal> List = response.body();
//


                for (int i = 0; i < List.size(); i++) {
                    newsModals.add(new NewsModal(List.get(i).getId(), List.get(i).getName(),
                            List.get(i).getDescription(), List.get(i).getPrice(), List.get(i).getImage() ));

                    adapter=new CustomAdapter(newsModals,getActivity());
                    hor_news.setAdapter(adapter);
                }


            }

            @Override
            public void onFailure(Call<List<NewsModal>> call, Throwable t) {
                Log.e("MSG", t.getMessage());
            }

        });


        Call<List<CatalogModel>> catalogCall = myAPI.getCatalog();
        catalogCall.enqueue(new Callback<List<CatalogModel>>() {
            @Override
            public void onResponse(Call<List<CatalogModel>> call, Response<List<CatalogModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<CatalogModel> CatList = response.body();
                for(int i=0; i<CatList.size(); i++) {
                    catalogModels.add(new CatalogModel(CatList.get(i).getId(), CatList.get(i).getName(),
                            CatList.get(i).getDescription(), CatList.get(i).getPrice(), CatList.get(i).getImage(),
                            CatList.get(i).getCategory(), CatList.get(i).getTime_result(), CatList.get(i).getPreparation(), CatList.get(i).getBio()));

                    adapterCat=new CatalogAdapter(catalogModels,getActivity());
                    catalogList.setAdapter(adapterCat);
                }
            }
            @Override
            public void onFailure(Call<List<CatalogModel>> call, Throwable t) {
                Log.e("CatalogResponseError", t.getMessage());
            }
        });



        return v;
    }


}