package com.example.classproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistrationActivity extends AppCompatActivity {

    EditText textFullNames, textIDNumber, textRegistrationNumber;
    RadioGroup radioGender;
    Spinner spinnerCourse, spinnerDepartment;
    Button buttonRegister;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        firestore =FirebaseFirestore.getInstance();

        textFullNames = findViewById(R.id.textFullNames);
        textIDNumber = findViewById(R.id.textIDNumber);
        textRegistrationNumber = findViewById(R.id.textRegistrationNumber);

        radioGender = findViewById(R.id.radioGender);

        spinnerCourse = findViewById(R.id.spinnerCourse);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);

        buttonRegister = findViewById(R.id.buttonRegister);


        String[] courses = {
                "Select Course",
                "BSc Software Engineering",
                "BSc Computer Science",
                "BSc Information Systems",
                "BSc Applied Computer Technology",
                "BSc Accounting",
                "BSc Pharmacy"

        };

        String[] departments = {
                "Select Department",
                "School of Science and Technology",
                "Chandaria School of Business",
                "School of Humanities and Social Sciences",
                "School of Pharmacy and Health Science"
        };


        ArrayAdapter<String> courseAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, courses);

        courseAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerCourse.setAdapter(courseAdapter);

        ArrayAdapter<String> departmentAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, departments);

        departmentAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerDepartment.setAdapter(departmentAdapter);


        buttonRegister.setOnClickListener(v -> {

            String fullNames = textFullNames.getText().toString().trim();
            String idNumber = textIDNumber.getText().toString().trim();
            String regNumber = textRegistrationNumber.getText().toString().trim();

            int selectedGenderId = radioGender.getCheckedRadioButtonId();

            RadioButton selectedRadioButton = findViewById(selectedGenderId);
            String gender = selectedRadioButton.getText().toString();

            String course = spinnerCourse.getSelectedItem().toString();
            String department = spinnerDepartment.getSelectedItem().toString();

            Map<String, Object> student = new HashMap<>();

            student.put("fullNames", fullNames);
            student.put("idNumber", idNumber);
            student.put("regNumber", regNumber);
            student.put("gender", gender);
            student.put("course", course);
            student.put("department", department);
            student.put("paymentStatus", "PENDING");

            buttonRegister.setEnabled(false);

            firestore.collection("StudentRegistration")
                    .add(student)
                    .addOnSuccessListener(documentReference -> {

                        Toast.makeText(this,
                                "Student details saved successfully!",
                                Toast.LENGTH_LONG).show();
                    })
                    .addOnFailureListener(error -> {

                        buttonRegister.setEnabled(true);

                        Toast.makeText(this,
                                "Saving failed: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();

                    });
        });
    }}
