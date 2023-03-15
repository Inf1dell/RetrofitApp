package apk.karmak.retrofitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientCardActivity extends AppCompatActivity {

    EditText name,patron,surname,date;
    Spinner gender;
    Button createCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card);

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
                        &&patron.getText().length()>0
                        &&surname.getText().length()>0
                        &&date.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    createCard.setEnabled(true);
                }
            }
        });
        patron=findViewById(R.id.PatientPatron);
        patron.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(patron.getText().length()>0) {
                    patron.setBackgroundResource(R.drawable.input_active);
                } else{
                    patron.setBackgroundResource(R.drawable.input_inactive);
                }
                if(name.getText().length()>0
                        &&patron.getText().length()>0
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
                        &&patron.getText().length()>0
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
                        &&patron.getText().length()>0
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