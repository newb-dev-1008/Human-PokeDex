package com.openlab.humanpokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
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
    private ArrayList<ComplaintLog> complaintLogArray;
    private TextView complaintLogProgressTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_log);

        regNo = getIntent().getStringExtra("regNo");

        recyclerView = findViewById(R.id.complaintLogRecyclerView);
        progressBar = findViewById(R.id.complaintProgressBar);
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
                         progressBar.setVisibility(View.VISIBLE);
                     }
                 });
             }
         }).start();
    }

    private void backgroundDataRetrieval() {
        db.collection("Users").document("Username " + regNo).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        complaintLog = (ArrayList<String>) documentSnapshot.get("ComplaintLog");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ComplaintLogActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        for (String s : complaintLog) {
            db.collection()
        }
    }
}
