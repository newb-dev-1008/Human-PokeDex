package com.openlab.humanpokedex.TFLiteFaceRecognition;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.openlab.humanpokedex.TFLiteFaceRecognition.tflite.SimilarityClassifier;

import java.util.ArrayList;
import java.util.List;

public class RecognizedStudentAdapter extends RecyclerView.Adapter<> {

    private List<SimilarityClassifier.Recognition> recognitionArrayList;
    private String date, time, offence, complaintNo;
    private Context context;
    private FirebaseFirestore db;

    public RecognizedStudentAdapter(List<SimilarityClassifier.Recognition> recognizedStudents) {
        recognitionArrayList = recognizedStudents;
    }
}
