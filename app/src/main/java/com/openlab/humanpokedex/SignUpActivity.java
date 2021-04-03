package com.openlab.humanpokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText nameET, regNoET, classET, yearET, deptET, emailET, phoneET, passwordET;
    private MaterialButton signUpBtn;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private ProgressBar signupProgress;
    private TextView signupTV;

    private String emailID, password, name, regNo, className, year, dept, phone;

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
        signUpBtn = findViewById(R.id.signupSignUpBtn);

        signupProgress = findViewById(R.id.signupProgress);
        signupTV = findViewById(R.id.signupTV);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        retrieveData();
        firebaseAuth.createUserWithEmailAndPassword(emailID, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {

    }

    private void retrieveData() {
        name = nameET.getText().toString().trim();
        regNo = regNoET.getText().toString().trim();
        className = classET.getText().toString().trim();
        year = yearET.getText().toString().trim();
        emailID = emailET.getText().toString().trim();
        dept = deptET.getText().toString().trim();
        phone = phoneET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
    }
}
