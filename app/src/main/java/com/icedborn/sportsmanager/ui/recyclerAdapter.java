package com.icedborn.sportsmanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.ui.athletes.AthletesModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    // Δημιουργία νέου ArrayList
    private final ArrayList<AthletesModel> athletesList;

    public recyclerAdapter(ArrayList<AthletesModel> athletes) {
        // Θέσε το athleteList με το ArrayList απο τις παραμέτρους
        this.athletesList = athletes;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView surname;
        private final TextView city;
        private final TextView code;
        private final TextView country;
        private final TextView date;
        private final TextView sport;

        public MyViewHolder(final View view) {
            super(view);
            // Σύνδεσε τa textview με τα textview απο το list_athletes
            name = view.findViewById(R.id.athleteName);
            surname = view.findViewById(R.id.athleteSurname);
            city = view.findViewById(R.id.athleteCity);
            code = view.findViewById(R.id.athleteCode);
            country = view.findViewById(R.id.athleteCountry);
            date = view.findViewById(R.id.athleteDate);
            sport = view.findViewById(R.id.athleteSportCode);
        }
    }

    @NonNull
    @NotNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_athletes, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull recyclerAdapter.MyViewHolder holder, int position) {
        // Θέσε τα field του holder με τις τιμές απο το athletesList
        holder.name.setText(athletesList.get(position).name);
        holder.surname.setText(athletesList.get(position).surname);
        holder.city.setText(athletesList.get(position).city);
        holder.code.setText(athletesList.get(position).code);
        holder.country.setText(athletesList.get(position).country);
        holder.date.setText(athletesList.get(position).date);
        holder.sport.setText(athletesList.get(position).sport);
    }

    @Override
    public int getItemCount() {
        return athletesList.size();
    }
}
