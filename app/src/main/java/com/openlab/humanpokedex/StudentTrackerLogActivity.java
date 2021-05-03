package com.openlab.humanpokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Tracker;
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
    private ArrayList<TrackerLog> trackerLogArray;
    private TextView trackerLogProgressTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracker_log);
        regNo = getIntent().getStringExtra("regNo");
        trackerLog = new ArrayList<>();
        trackerLogArray = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.trackerLogProgress);
        showTrackerLog();
        trackerLogProgressTV = findViewById(R.id.trackerLogProgressTV);

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
            trackerLogArray.add(new TrackerLog(log.get(0), log.get(1), log.get(2)));
        }
    }

    private void runTrackerUI() {
        if (trackerLogArray.size() == 0) {
            trackerLogProgressTV.setText("No track records.");
            progressBar.setVisibility(View.GONE);
        } else {
            LinearLayoutManager trackerLayoutManager = new LinearLayoutManager(StudentTrackerLogActivity.this);
            trackerAdapter = new TrackerAdapter(trackerLogArray);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(trackerLayoutManager);
            recyclerView.setAdapter(trackerAdapter);
            // swipeDownRefreshTV.setVisibility(View.GONE);
        }
    }
}
