package apk.karmak.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class PatientCardCreate extends AppCompatActivity {
    Button btnCreateAccount;
    EditText eName, eSurname, eFamilyName, eBirthday;
    Spinner eSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_card_create);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        eName = findViewById(R.id.eName);
        eSurname = findViewById(R.id.eSurname);
        eFamilyName = findViewById(R.id.eFamilyName);
        eBirthday = findViewById(R.id.eBirthday);

        eSex = findViewById(R.id.eSex);

        eName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (eName.getEditableText().length() > 0) {
                    btnCreateAccount.setEnabled(true);
                    eName.setBackgroundResource(R.drawable.input_active);
                } else {
                    if (eSurname.length() > 0 | eBirthday.length() > 0 | eFamilyName.length() > 0){
                        btnCreateAccount.setEnabled(true);
                    } else {
                        btnCreateAccount.setEnabled(false);
                    }
                    eName.setBackgroundResource(R.drawable.input);
                }
            }
        });
        eSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (eSurname.getEditableText().length() > 0) {
                    btnCreateAccount.setEnabled(true);
                    eSurname.setBackgroundResource(R.drawable.input_active);
                } else {
                    if (eName.length() > 0 | eBirthday.length() > 0 | eFamilyName.length() > 0){
                        btnCreateAccount.setEnabled(true);
                    } else {
                        btnCreateAccount.setEnabled(false);
                    }
                    eSurname.setBackgroundResource(R.drawable.input);
                }
            }
        });
        eFamilyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (eFamilyName.getEditableText().length() > 0) {
                    btnCreateAccount.setEnabled(true);
                    eFamilyName.setBackgroundResource(R.drawable.input_active);
                } else {
                    if (eSurname.length() > 0 | eBirthday.length() > 0 | eName.length() > 0){
                        btnCreateAccount.setEnabled(true);
                    } else {
                        btnCreateAccount.setEnabled(false);
                    }
                    eFamilyName.setBackgroundResource(R.drawable.input);
                }
            }
        });
        eBirthday.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (eBirthday.getEditableText().length() > 0) {
                    btnCreateAccount.setEnabled(true);
                    eBirthday.setBackgroundResource(R.drawable.input_active);
                } else {
                    if (eSurname.length() > 0 | eName.length() > 0 | eFamilyName.length() > 0){
                        btnCreateAccount.setEnabled(true);
                    } else {
                        btnCreateAccount.setEnabled(false);
                    }
                    eBirthday.setBackgroundResource(R.drawable.input);
                }
            }
        });
////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.patientSex, android.R.layout.simple_spinner_item);
////        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
////        eSex.setAdapter(adapter);
////        eSex.setOnItemSelectedListener(this);
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
    }
}