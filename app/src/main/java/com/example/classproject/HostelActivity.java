package com.example.classproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class HostelActivity extends AppCompatActivity {

    EditText textIDNumber;
    Spinner spinnerHostelLocation, spinnerRoomType;
    RadioGroup radioSharable;
    Button btnSave, btnNext;

    FirebaseFirestore firestore;
    private Object choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);


        textIDNumber = findViewById(R.id.textIDNumber);
        radioSharable= findViewById(R.id.radioSharable);
        spinnerHostelLocation = findViewById(R.id.spinnerHostelLocation);
        spinnerRoomType = findViewById(R.id.spinnerRoomType);
        firestore =FirebaseFirestore.getInstance();
        btnSave=findViewById(R.id.btnSaveHostel);

        String[] hostellocation = {
                "Select Hostel Location",
                "On Campus",
                "Off Campus",
        };


        String[] roomtype = {
                "Select Room Type",
                "Bedsitter",
                "One-bedroom",
                "Studio",
        };


        ArrayAdapter<String> hostellocationAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, hostellocation);

        hostellocationAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerHostelLocation.setAdapter(hostellocationAdapter);

        ArrayAdapter<String> roomtypeAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, roomtype);

        roomtypeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerRoomType.setAdapter(roomtypeAdapter);


        btnSave.setOnClickListener(v -> {

            String idNumber = textIDNumber.getText().toString().trim();


            int selectedSharableId = radioSharable.getCheckedRadioButtonId();

            RadioButton selectedRadioButton = findViewById(selectedSharableId);
            String choice = selectedRadioButton.getText().toString();

            String location = spinnerHostelLocation.getSelectedItem().toString();
            String type = spinnerRoomType.getSelectedItem().toString();

            Map<String, Object> student = new HashMap<>();

            student.put("idNumber", idNumber);;
            student.put("choice", choice);
            student.put("hostel location", location);
            student.put("roomtype", type);

            btnSave.setEnabled(false);

            firestore.collection("Hostel")
                    .add(student)
                    .addOnSuccessListener(documentReference -> {

                        Toast.makeText(this,
                                "Hostel details saved successfully!",
                                Toast.LENGTH_LONG).show();
                    })
                    .addOnFailureListener(error -> {

                        btnSave.setEnabled(true);

                        Toast.makeText(this,
                                "Saving failed: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();

                    });
        });
    }
}