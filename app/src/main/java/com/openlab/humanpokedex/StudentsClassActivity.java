package com.openlab.humanpokedex;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StudentsClassActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView classnameTV;
    private ArrayList<ClassStudents> classStudents;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_students);

        db = FirebaseFirestore.getInstance();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshClassStudents);
        classnameTV = findViewById(R.id.classNameTV);
        recyclerView = findViewById(R.id.class_studentsRecycler);
        classStudents = new ArrayList<ClassStudents>();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showStudents();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
