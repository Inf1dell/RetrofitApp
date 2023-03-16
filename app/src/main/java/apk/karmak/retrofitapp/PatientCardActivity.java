package apk.karmak.retrofitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientCardActivity extends AppCompatActivity {

    String token;
    EditText name,middlename,surname,date;
    Spinner gender;
    Button createCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card);

        SharedPreferences preferences = getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        token = preferences.getString("token","");


        name=findViewById(R.id.PatientName);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(name.getText().length()>0) {
                    name.setBackgroundResource(R.drawable.input_active);
                } else{
                    name.setBackgroundResource(R.drawable.input_inactive);
                }
                if(name.getText().length()>0
                        &&middlename.getText().length()>0
                        &&surname.getText().length()>0
                        &&date.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    createCard.setEnabled(true);
                }
            }
        });
        middlename=findViewById(R.id.PatientPatron);
        middlename.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(middlename.getText().length()>0) {
                    middlename.setBackgroundResource(R.drawable.input_active);
                } else{
                    middlename.setBackgroundResource(R.drawable.input_inactive);
                }
                if(name.getText().length()>0
                        &&middlename.getText().length()>0
                        &&surname.getText().length()>0
                        &&date.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    createCard.setEnabled(true);
                }
            }
        });
        surname=findViewById(R.id.PatientSurname);
        surname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(surname.getText().length()>0) {
                    surname.setBackgroundResource(R.drawable.input_active);
                } else{
                    surname.setBackgroundResource(R.drawable.input_inactive);
                }
                if(name.getText().length()>0
                        &&middlename.getText().length()>0
                        &&surname.getText().length()>0
                        &&date.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    createCard.setEnabled(true);
                }
            }
        });
        date=findViewById(R.id.PatientDate);
        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(date.getText().length()>0) {
                    date.setBackgroundResource(R.drawable.input_active);
                } else{
                    date.setBackgroundResource(R.drawable.input_inactive);
                }
                if(name.getText().length()>0
                        &&middlename.getText().length()>0
                        &&surname.getText().length()>0
                        &&date.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    createCard.setEnabled(true);
                }
            }
        });

        gender=findViewById(R.id.PatientGender);
//        gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i==0){
////                    adapterView.getChildAt(0).setTextColor(Color.parseColor("#fff"));
//                }
//            }
//        });

        createCard=findViewById(R.id.createCard);
        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://medic.madskill.ru/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MyAPI myAPI = retrofit.create(MyAPI.class);

                try {
                    JSONObject paramObject = new JSONObject();
                    paramObject.put("id", 1);
                    paramObject.put("firstname", "fdg");
                    paramObject.put("lastname", "fdg");
                    paramObject.put("middlename", "fdg");
                    paramObject.put("bith", "fdg");
                    paramObject.put("pol", "Мужской");
                    paramObject.put("image", "fdg");


                    Call<DataModel> call = myAPI.create("Bearer "+token,
                            paramObject.toString());
                    call.enqueue(new Callback<DataModel>() {
                        @Override
                        public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                            if(!response.isSuccessful()) {
                                Log.e("MSG", response.code()+"");
                                Log.e("MSG", new Gson().toJson(response.body())+"");

                                return;
                            }
//                        Intent pin = new Intent(PatientCardActivity.this, PinCodeActivity.class);
//                        pin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(pin);

                        }

                        @Override
                        public void onFailure(Call<DataModel> call, Throwable t) {
                            Log.e("MSG", t.getMessage());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });





        List<String> plantsList = new ArrayList<String>();
        plantsList.add("Пол");
        plantsList.add("Мужской");
        plantsList.add("Женский");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, plantsList){

            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,@NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if(position == 0){
                    textView.setTextColor(Color.GRAY);
                }
                else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        String selectedItemText = (String) parent.getItemAtPosition(position);
                        TextView textView = (TextView) view;
                        if(position > 0){
                            textView.setTextColor(Color.parseColor("#000000"));
                        }else {
                            textView.setTextColor(Color.parseColor("#939396"));
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        gender.setAdapter(spinnerArrayAdapter);

    }
}