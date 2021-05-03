package com.openlab.humanpokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder> {

    private ArrayList<ComplaintLog> ComplaintLogss;
    private String date, time, description;
    private Context context;
    private FirebaseFirestore db;
    // private URL photoStored;

    public ComplaintAdapter(ArrayList<ComplaintLog> complaintLogs) {
        ComplaintLogss = complaintLogs;
        db = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ComplaintAdapter.ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trackerlog_cardview, parent, false);
        context = v.getContext();

        ComplaintAdapter.ComplaintViewHolder complaintViewHolder = new ComplaintAdapter.ComplaintViewHolder(v);
        return complaintViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ComplaintViewHolder holder, int position) {
        ComplaintLog complaintLog = ComplaintLogss.get(position);

        date = complaintLog.getDate();
        time = complaintLog.getTime();
        description = complaintLog.getDescription();

        holder.dateTV.setText(date);
        holder.timeTV.setText(time);
        holder.descriptionTV.setText(description);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ComplaintViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTV, timeTV, descriptionTV;

        public ComplaintViewHolder(@NonNull View itemView) {
            super(itemView);

            dateTV = itemView.findViewById(R.id.complaintlogDateTV);
            timeTV = itemView.findViewById(R.id.complaintlogTimeTV);
            descriptionTV = itemView.findViewById(R.id.complaintlogArea);
        }
    }

}
