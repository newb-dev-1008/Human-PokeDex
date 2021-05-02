package com.openlab.humanpokedex;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

public class ComplaintLogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_log);

        recyclerView = findViewById(R.id.trackerLogRecyclerView);
        progressBar = findViewById(R.id.trackerLogProgress);
        db = FirebaseFirestore.getInstance();
    }
}
