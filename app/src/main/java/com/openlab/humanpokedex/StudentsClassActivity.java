package com.openlab.humanpokedex;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class StudentsClassActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView classnameTV;
    private ArrayList<ClassStudents> classStudents;
    private RecyclerView recyclerView;
    private String className, dept;

    // private RecyclerView.LayoutManager classStudentsLayoutManager;
    private RecyclerView.Adapter classStudentsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_students);

        className = getIntent().getStringExtra("className");
        dept = getIntent().getStringExtra("dept");

        db = FirebaseFirestore.getInstance();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshClassStudents);
        classnameTV = findViewById(R.id.classNameTV);
        recyclerView = findViewById(R.id.class_studentsRecycler);
        classStudents = new ArrayList<ClassStudents>();

        classnameTV.setText(className);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showStudents();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void showStudents() {
        db.collection("Campus").document("Department " + dept)
                .collection("Class " + className).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.size() > 0) {
                    ArrayList<ClassStudents> classStudents = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        String name = documentSnapshot.get("Username").toString();
                        String regNo = documentSnapshot.get("RegNo").toString();
                        String photoStoredStr = documentSnapshot.get("photoStored").toString();

                        try {
                            URI uri = new URI(photoStoredStr);
                            URL photoStored = uri.toURL();
                            classStudents.add(new ClassStudents(regNo, name, className, photoStored));
                        } catch (URISyntaxException | MalformedURLException e) {
                            Toast.makeText(StudentsClassActivity.this, "Unable to parse URI.", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                }

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
        })
    }
}
