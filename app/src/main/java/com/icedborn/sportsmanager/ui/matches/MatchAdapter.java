package com.icedborn.sportsmanager.ui.matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder>{
    // Δημιουργία νέου ArrayList
    private final ArrayList<MatchModel> matchesList;
    private final OnMatchListener mOnMatchListener;

    public MatchAdapter(ArrayList<MatchModel> matches, OnMatchListener onMatchListener) {
        // Θέσε το matchesList με το ArrayList απο τις παραμέτρους
        this.matchesList = matches;
        this.mOnMatchListener = onMatchListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView code;
        private final TextView host;
        private final TextView guest;
        private final TextView sport;
        private final TextView date;
        private final OnMatchListener onMatchListener;

        public MyViewHolder(final View view, OnMatchListener onMatchListener) {
            super(view);
            this.onMatchListener = onMatchListener;
            // Σύνδεσε τa textview με τα textview απο το list_sports
            code = view.findViewById(R.id.matchCode);
            host = view.findViewById(R.id.matchHost);
            guest = view.findViewById(R.id.matchGuest);
            sport = view.findViewById(R.id.matchSportCode);
            date = view.findViewById(R.id.matchDate);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMatchListener.onMatchClick(getAdapterPosition());
        }
    }

    public interface OnMatchListener{
        void onMatchClick(int position);
    }

    @NonNull
    @NotNull
    @Override
    public MatchAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_matches, parent, false);
        return new MatchAdapter.MyViewHolder(itemView, mOnMatchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MatchAdapter.MyViewHolder holder, int position) {
        // Μετατροπή των int σε String
        String code = String.valueOf(matchesList.get(position).code);
        String host = String.valueOf(matchesList.get(position).host);
        String guest = String.valueOf(matchesList.get(position).guest);
        String sport = String.valueOf(matchesList.get(position).sport);

        // Θέσε τα field του holder με τις τιμές απο το matchesList
        holder.code.setText(code);
        holder.host.setText(host);
        holder.guest.setText(guest);
        holder.sport.setText(sport);
        holder.date.setText(matchesList.get(position).date);
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
