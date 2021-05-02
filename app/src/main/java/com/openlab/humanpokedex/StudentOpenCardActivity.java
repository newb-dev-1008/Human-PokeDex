package com.openlab.humanpokedex;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentOpenCardActivity extends AppCompatActivity {

    private ImageView studentImage;
    private TextView nameET, regNoET, classET, yearET, deptET, phoneNoET, emailIDET;
    private MaterialButton trackerLog, complaintLog;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_opencard);

        db = FirebaseFirestore.getInstance();
        nameET = ;
        regNoET = ;
        classET = ;
        yearET = ;
        deptET = ;
        phoneNoET = ;
        emailIDET = ;
        trackerLog = ;
        complaintLog = ;
        studentImage = ;

    }
}
