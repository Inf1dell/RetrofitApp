package apk.karmak.retrofitapp.main.Profile;

import android.app.DatePickerDialog;
import android.app.ZygotePreload;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import apk.karmak.retrofitapp.R;

public class ProfileFragment extends Fragment {
    EditText name,surname,middlename;
    Spinner gender;
    Button updateBtn, dateBtn;
    ImageView imageButton;

    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_profile, container, false);


        preferences = getActivity().getSharedPreferences("patient_pref", Context.MODE_PRIVATE);


        initElemetnts(v);

        dateBtn.setOnClickListener(new View.OnClickListener() {
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
                        getActivity(),
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
                                    dateBtn.setText(dateTime);
                                    dateBtn.setTextColor(Color.parseColor("#000000"));
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


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

        List<String> plantsList = new ArrayList<String>();
        plantsList.add("Мужской");
        plantsList.add("Женский");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_dropdown_item_1line, plantsList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender.setSelection(preferences.getInt("gender",0));
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
                        &&dateBtn.getText().length()>0
                        &&gender.getSelectedItemPosition()!=0) {
                    updateBtn.setEnabled(true);
                }else {
                    updateBtn.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        gender.setAdapter(spinnerArrayAdapter);



        return v;
    }

    private void initElemetnts(View v) {
        dateBtn=v.findViewById(R.id.CardDate);
        imageButton = v.findViewById(R.id.imageButton);
        gender=v.findViewById(R.id.CardGender);
        updateBtn=v.findViewById(R.id.btnAdd);

        name=v.findViewById(R.id.CardName);
        middlename=v.findViewById(R.id.CardMiddlename);
        surname=v.findViewById(R.id.CardSurname);




        if(preferences.getBoolean("patient",false)){
            name.setText(preferences.getString("name",""));
            middlename.setText(preferences.getString("middlename",""));
            surname.setText(preferences.getString("surname",""));
            dateBtn.setText(preferences.getString("date",""));
            dateBtn.setTextColor(getResources().getColor(R.color.black));

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Uri selectedImageUri = data.getData();
            imageButton.setImageURI(selectedImageUri);
        }
    }
}