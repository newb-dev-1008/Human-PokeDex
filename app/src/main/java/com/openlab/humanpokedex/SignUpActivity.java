package com.openlab.humanpokedex;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText nameET, regNoET, classET, yearET, deptET, emailET, phoneET, passwordET;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        nameET = findViewById(R.id.signupNameET);
        regNoET = findViewById(R.id.signupRegNoET);
        classET = findViewById(R.id.signupClassET);
        yearET = findViewById(R.id.signupYearET);
        emailET = findViewById(R.id.signupEmailET);
        deptET = findViewById(R.id.signupDeptET);
        phoneET = findViewById(R.id.signupPhoneET);
        passwordET = findViewById(R.id.signupPasswordET);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }
}
