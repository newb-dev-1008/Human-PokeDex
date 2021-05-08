package com.openlab.humanpokedex;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ComplaintLogActivity extends AppCompatActivity {

    private RecyclerView.Adapter complaintAdapter;
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private String regNo;
    private ArrayList<String> complaintLog;
    private ArrayList<ComplaintLog> trackerLogArray;
    private TextView complaintLogProgressTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_log);

        recyclerView = findViewById(R.id.trackerLogRecyclerView);
        progressBar = findViewById(R.id.trackerLogProgress);
        db = FirebaseFirestore.getInstance();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showComplaints();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void showComplaints() {
         new Thread(new Runnable() {
             @Override
             public void run() {
                 backgroundDataRetrieval();
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {

                     }
                 });
             }
         })
    }
}
