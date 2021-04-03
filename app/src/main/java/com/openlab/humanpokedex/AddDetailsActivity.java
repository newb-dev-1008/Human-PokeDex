package com.openlab.humanpokedex;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddDetailsActivity extends AppCompatActivity {

    private TextInputEditText nameET, classET, phoneET, deptET, yearET, emailET;
    private MaterialButton registerBtn;
    private ProgressBar registerProgress;
    private TextView registerTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra_details);

        nameET = findViewById(R.id.extraNameET);
        classET = findViewById(R.id.extraClassET);
        phoneET = findViewById(R.id.extraPhoneET);
        deptET = findViewById(R.id.extraDeptET);
        yearET = findViewById(R.id.extraYearET);
        emailET = findViewById(R.id.extraEmailET);
        registerBtn = findViewById(R.id.extraRegisterBtn);
        registerProgress = findViewById(R.id.extraProgress);
        registerTV = findViewById(R.id.extraTV);
    }
}
