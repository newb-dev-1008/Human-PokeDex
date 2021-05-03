package com.openlab.humanpokedex;

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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerAdapter.TrackerViewHolder> {

    private ArrayList<TrackerLog> TrackerLogss;
    private String date, time, description;
    private Context context;
    private FirebaseFirestore db;
    // private URL photoStored;

    public TrackerAdapter(ArrayList<TrackerLog> trackerLogs) {
        TrackerLogss = trackerLogs;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public TrackerAdapter.TrackerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trackerlog_cardview, parent, false);
        context = v.getContext();

        TrackerAdapter.TrackerViewHolder trackerViewHolder = new TrackerAdapter.TrackerViewHolder(v);
        return trackerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrackerAdapter.TrackerViewHolder holder, int position) {
        TrackerLog trackerLog = TrackerLogss.get(position);

        date = trackerLog.getDate();
        time = trackerLog.getTime();
        description = trackerLog.getDescription();

        holder.dateTV.setText(date);
        holder.timeTV.setText(time);
        holder.descriptionTV.setText(description);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TrackerViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTV, timeTV, descriptionTV;

        public TrackerViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.trackerlogDateTV);
            timeTV = itemView.findViewById(R.id.trackerlogTimeTV);
            descriptionTV = itemView.findViewById(R.id.trackerlogArea);
        }
    }
}
