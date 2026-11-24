package com.example.classproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    Button buttonRegistration, buttonLibrary, buttonHostel,buttonPayment,buttonReport;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegistration = findViewById(R.id.buttonRegistration);
        buttonLibrary = findViewById(R.id.buttonLibrary);
        buttonHostel = findViewById(R.id.buttonHostel);
        buttonPayment = findViewById(R.id.buttonPayment);
        buttonReport = findViewById(R.id.buttonReports);

        firestore =FirebaseFirestore.getInstance();


        buttonRegistration.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, StudentRegistrationActivity.class);
                    startActivity(intent);
                });

        buttonHostel.setOnClickListener( view -> {
             Intent intent= new Intent(MainActivity.this, HostelActivity.class);
            startActivity(intent);
            });

}}