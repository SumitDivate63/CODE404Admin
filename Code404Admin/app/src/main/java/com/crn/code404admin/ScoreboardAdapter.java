package com.crn.code404admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.ScoreViewHolder> {

    private List<ParticipantModel> participantList;

    public ScoreboardAdapter(List<ParticipantModel> participantList) {
        this.participantList = participantList;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participant, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ParticipantModel participant = participantList.get(position);
        holder.tvName.setText(participant.getName());
        holder.tvMobile.setText(participant.getMobile());
        holder.tvScore.setText(String.valueOf(participant.getScore()));
        holder.tvLevel.setText(participant.getLevel());
    }

    @Override
    public int getItemCount() {
        return participantList.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvMobile, tvScore,tvLevel;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvLevel=itemView.findViewById(R.id.tvLevel);
        }
    }
}

