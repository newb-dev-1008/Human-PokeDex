package com.openlab.humanpokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StudentTrackerLogActivity extends AppCompatActivity {

    private RecyclerView.Adapter trackerAdapter;
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private String regNo;
    private ArrayList<ArrayList<String>> trackerLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracker_log);
        regNo = getIntent().getStringExtra("regNo");
        trackerLog = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.trackerLogProgress);
        showTrackerLog();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showTrackerLog();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void showTrackerLog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                backgroundTracker();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);
                        runTrackerUI();
                    }
                });
            }
        }).start();
    }

    private void backgroundTracker() {
        db.collection("Users").document("Username " + regNo).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        trackerLog = (ArrayList<ArrayList<String>>) documentSnapshot.get("trackerLog");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StudentTrackerLogActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        for (ArrayList<String> log : trackerLog) {

        }
    }

    private void runTrackerUI() {
        if (classStudents.size() == 0) {
            classnameTV.setText("Empty Class.");
        } else {
            GridLayoutManager classStudentsLayoutManager = new GridLayoutManager(StudentsClassActivity.this, 3);
            classStudentsAdapter = new StudentsClassAdapter(classStudents);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(classStudentsLayoutManager);
            recyclerView.setAdapter(classStudentsAdapter);
            // swipeDownRefreshTV.setVisibility(View.GONE);
        }
    }
}
