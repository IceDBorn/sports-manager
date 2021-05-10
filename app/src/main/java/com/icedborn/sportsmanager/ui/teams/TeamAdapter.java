package com.icedborn.sportsmanager.ui.teams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.MyViewHolder>{
    // Δημιουργία νέου ArrayList
    private final ArrayList<TeamModel> teamsList;

    public TeamAdapter(ArrayList<TeamModel> teams) {
        // Θέσε το teamsList με το ArrayList απο τις παραμέτρους
        this.teamsList = teams;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView code;
        private final TextView name;
        private final TextView stadium;
        private final TextView city;
        private final TextView country;
        private final TextView sport;
        private final TextView date;

        public MyViewHolder(final View view) {
            super(view);
            // Σύνδεσε τa textview με τα textview απο το list_sports
            code = view.findViewById(R.id.teamCode);
            name = view.findViewById(R.id.teamName);
            stadium = view.findViewById(R.id.teamStadium);
            city = view.findViewById(R.id.teamCity);
            country = view.findViewById(R.id.teamCountry);
            sport = view.findViewById(R.id.teamSportCode);
            date = view.findViewById(R.id.teamDate);
        }
    }

    @NonNull
    @NotNull
    @Override
    public TeamAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teams, parent, false);
        return new TeamAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TeamAdapter.MyViewHolder holder, int position) {
        // Μετατροπή των int σε String
        String code = String.valueOf(teamsList.get(position).code);
        String sportCode = String.valueOf(teamsList.get(position).sport);

        // Θέσε τα field του holder με τις τιμές απο το teamsList
        holder.code.setText(code);
        holder.name.setText(teamsList.get(position).name);
        holder.stadium.setText(teamsList.get(position).stadium);
        holder.city.setText(teamsList.get(position).city);
        holder.country.setText(teamsList.get(position).country);
        holder.sport.setText(sportCode);
        holder.date.setText(teamsList.get(position).date);
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }
}
