package apk.karmak.retrofitapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.model.PatientModal;
import apk.karmak.retrofitapp.retrofit.MyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientCardActivity extends AppCompatActivity {

    String token;
    EditText name,middlename,surname;
    Button date;
    Spinner gender;
    Button createCard;
    TextView scipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card);

        SharedPreferences preferences = getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        token = preferences.getString("token","");


        scipe = findViewById(R.id.scip3);
        scipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("patient_pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("patient", false);
                editor.commit();

                Intent pin = new Intent(PatientCardActivity.this, MainActivity.class);
                pin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(pin);
            }
        });


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
                }else {
                    createCard.setEnabled(false);
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
                }else {
                    createCard.setEnabled(false);
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
                }else {
                    createCard.setEnabled(false);
                }
            }
        });
        date=findViewById(R.id.PatientDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MSG","Date");
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        PatientCardActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                try {
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                                    Date dateT = sdf.parse(dayOfMonth + "." + (monthOfYear + 1) + "." + year);

                                    String dateTime = simpleDateFormat.format(dateT).toString();
                                    date.setText(dateTime);
                                    date.setTextColor(Color.parseColor("#000000"));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }


                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        gender=findViewById(R.id.PatientGender);
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
                Typeface typeface = getResources().getFont(R.font.regular);
                textView.setTypeface(typeface);
                if(position > 0){
                    textView.setTextColor(Color.parseColor("#000000"));
                }else {
                    textView.setTextColor(Color.parseColor("#939396"));
                }

                if(name.getText().length()>0
                        &&middlename.getText().length()>0
                        &&surname.getText().length()>0
                        &&date.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    createCard.setEnabled(true);
                }else {
                    createCard.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        gender.setAdapter(spinnerArrayAdapter);


        createCard=findViewById(R.id.createCard);
        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://medic.madskill.ru/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MyAPI myAPI = retrofit.create(MyAPI.class);




                PatientModal patientModal = new PatientModal(null,
                        name.getText().toString()+"",
                        surname.getText().toString()+"",
                        middlename.getText().toString()+"",
                        date.getText().toString()+"",
                        gender.getSelectedItem().toString()+"",
                        null,null,null,null);
                Call<PatientModal> call = myAPI.create("Bearer "+token, patientModal);
                call.enqueue(new Callback<PatientModal>() {
                    @Override
                    public void onResponse(Call<PatientModal> call, Response<PatientModal> response) {
                        if(!response.isSuccessful()) {
                            Log.e("MSG", response.code()+" || "+new Gson().toJson(response.body()));
                            return;
                        }
                        Log.e("MSG", "JSON: "+new Gson().toJson(response.body()));

                        SharedPreferences preferences = getSharedPreferences("patient_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("patient", true);
                        editor.putString("name", name.getText().toString());
                        editor.putString("middlename", middlename.getText().toString());
                        editor.putString("surname", surname.getText().toString());
                        editor.putString("date", date.getText().toString());
                        editor.putInt("gender", gender.getSelectedItemPosition());
                        editor.commit();

                        Intent pin = new Intent(PatientCardActivity.this, MainActivity.class);
                        pin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(pin);
                    }
                    @Override
                    public void onFailure(Call<PatientModal> call, Throwable t) {
                        Log.e("MSG_ERROR", t.getMessage());
                    }
                });


            }
        });











    }
}