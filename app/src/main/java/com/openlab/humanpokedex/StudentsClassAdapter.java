package com.openlab.humanpokedex;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentsClassAdapter extends RecyclerView.Adapter<StudentsClassAdapter.StudentsClassViewHolder> {

    private ArrayList<ClassStudents> ClassStudents;
    private String name, className, regNo, photosURL;
    private Context context;

    public StudentsClassAdapter(ArrayList<ClassStudents> classStudents) {
        ClassStudents = classStudents;
    }

    @NonNull
    @Override
    public StudentsClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_cardview, parent, false);
        context = v.getContext();

        StudentsClassViewHolder studentsClassViewHolder = new StudentsClassViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StudentOpenCardActivity.class);
                String transitionName = view.getResources().getString(R.string.card_open_transition);
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
    public void onBindViewHolder(@NonNull StudentsClassViewHolder holder, int position) {
        ClassStudents classStudent = ClassStudents.get(position);

        name = classStudent.getStudentName();
        photosURL = classStudent.getPhotosURL();

        holder.nameTV.setText(name);
        holder.photo.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class StudentsClassViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTV;
        public ImageView photo;

        public StudentsClassViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameCardViewStudent);
            photo = itemView.findViewById(R.id.studentPhoto);
        }
    }
}
