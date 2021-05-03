package com.openlab.humanpokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.firestore.FirebaseFirestore;

public class StudentTrackerLogActivity extends AppCompatActivity {

    private RecyclerView.Adapter trackerAdapter;
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracker_log);
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
}
