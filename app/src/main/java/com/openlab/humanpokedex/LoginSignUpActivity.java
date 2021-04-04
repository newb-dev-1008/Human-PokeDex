package com.openlab.humanpokedex;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class LoginSignUpActivity extends AppCompatActivity {

    private MaterialButton signIn, signUp, recFace, regFace;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.login_activity);

        signIn = findViewById(R.id.signInButton);
        signUp = findViewById(R.id.signUpButton);
        recFace = findViewById(R.id.recognizeFaceButton);
        regFace = findViewById(R.id.registerFaceButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        recFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizeFace();
            }
        });

        regFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFace();
            }
        });
    }

    private void signIn() {
        Intent intent = new Intent(LoginSignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void signUp() {

    }

    private void recognizeFace() {

    }

    private void registerFace() {
        Intent intent = new Intent(LoginSignUpActivity.this, RegisterFaceActivity.class);
        startActivity(intent);
    }
}
