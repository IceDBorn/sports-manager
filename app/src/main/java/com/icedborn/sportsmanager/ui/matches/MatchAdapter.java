package com.icedborn.sportsmanager.ui.matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.Match;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MyViewHolder>{
    // Δημιουργία νέου ArrayList
    private final ArrayList<Match> matchesList;
    private final OnMatchListener mOnMatchListener;

    public MatchAdapter(ArrayList<Match> matches, OnMatchListener onMatchListener) {
        // Θέσε το matchesList με το ArrayList απο τις παραμέτρους
        this.matchesList = matches;
        this.mOnMatchListener = onMatchListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView host;
        private final TextView guest;
        private final TextView sport;
        private final TextView date;
        private final TextView city;
        private final TextView country;
        private final OnMatchListener onMatchListener;

        public MyViewHolder(final View view, OnMatchListener onMatchListener) {
            super(view);
            this.onMatchListener = onMatchListener;
            // Σύνδεσε τa textview με τα textview απο το list_sports
            host = view.findViewById(R.id.matchHost);
            guest = view.findViewById(R.id.matchGuest);
            sport = view.findViewById(R.id.matchSportCode);
            date = view.findViewById(R.id.matchDate);
            city = view.findViewById(R.id.matchCity);
            country = view.findViewById(R.id.matchCountry);

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
        // Θέσε τα field του holder με τις τιμές απο το matchesList
        holder.host.setText(matchesList.get(position).getTeam1());
        holder.guest.setText(matchesList.get(position).getTeam2());
        holder.sport.setText(matchesList.get(position).getSportName());
        holder.date.setText(matchesList.get(position).getDate());
        holder.city.setText(matchesList.get(position).getCityName());
        holder.country.setText(matchesList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
