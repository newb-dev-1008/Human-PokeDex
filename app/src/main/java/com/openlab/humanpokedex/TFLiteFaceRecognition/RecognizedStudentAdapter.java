package com.openlab.humanpokedex.TFLiteFaceRecognition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.openlab.humanpokedex.R;
import com.openlab.humanpokedex.StudentOpenCardActivity;
import com.openlab.humanpokedex.StudentsClassAdapter;
import com.openlab.humanpokedex.TFLiteFaceRecognition.tflite.SimilarityClassifier;

import java.util.ArrayList;
import java.util.List;

public class RecognizedStudentAdapter extends RecyclerView.Adapter<RecognizedStudentAdapter.RecognizedStudentsViewHolder> {

    private List<SimilarityClassifier.Recognition> recognitionArrayList;
    private String regNo, className;
    private Context context;
    private FirebaseFirestore db;

    public RecognizedStudentAdapter(List<SimilarityClassifier.Recognition> recognizedStudents) {
        recognitionArrayList = recognizedStudents;
    }

    @NonNull
    @Override
    public RecognizedStudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recognized_face_cardview, parent, false);
        context = v.getContext();

        RecognizedStudentAdapter.RecognizedStudentsViewHolder recognizedStudentsViewHolder = new RecognizedStudentAdapter.RecognizedStudentsViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StudentOpenCardActivity.class);
                String transitionName = view.getResources().getString(R.string.transitionAnimation);
                View viewStart = view.findViewById(R.id.student_cardview);

                intent.putExtra("regNo", regNo);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(), viewStart, transitionName);

                ActivityCompat.startActivity(view.getContext(), intent, options.toBundle());
            }
        });

        return studentsClassViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecognizedStudentAdapter.RecognizedStudentsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class RecognizedStudentsViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV, classTV;
        public ImageView photo;

        public RecognizedStudentsViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameCardViewStudent);
            classTV = itemView.findViewById(R.id.classCardViewStudent);
            photo = itemView.findViewById(R.id.studentPhoto);
        }
    }
}
