package com.openlab.humanpokedex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private MaterialButton signOutButton, trackerLogButton, complaintLogButton, trafficButton, recognizeFaceButton, identifyCriminalButton, findStudentButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        signOutButton = findViewById(R.id.logOutButton);
        trackerLogButton = findViewById(R.id.trackerLogButton);
        complaintLogButton = findViewById(R.id.complaintLogButton);
        trafficButton = findViewById(R.id.trafficButton);
        recognizeFaceButton = findViewById(R.id.recognizeFaceButton);
        identifyCriminalButton = findViewById(R.id.identifyCriminalsButton);
        findStudentButton = findViewById(R.id.findStudentButton);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new MaterialAlertDialogBuilder(HomeActivity.this)
                        .setTitle("Signing out")
                        .setMessage("Are you sure you want to sign out?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseAuth.signOut();
                                Toast.makeText(HomeActivity.this, "Signed out successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(HomeActivity.this, LoginSignUpActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                finish();
                                startActivity(intent);
                            }
                        }).setNegativeButton("Cancel", null)
                        .show();
            }
        });

        trackerLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackerLog();
            }
        });

        complaintLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaintLog();
            }
        });

        recognizeFaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizeFace();
            }
        });

        identifyCriminalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identifyCriminal();
            }
        });

        findStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findStudent();
            }
        });
    }
}
