package com.icedborn.sportsmanager.ui.athletes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.Athlete;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.MyViewHolder> {
    // Δημιουργία νέου ArrayList
    private final ArrayList<Athlete> athletesList;
    private final OnAthleteListener mOnAthleteListener;

    public AthleteAdapter(ArrayList<Athlete> athletes, OnAthleteListener onAthleteListener) {
        // Θέσε το athleteList με το ArrayList απο τις παραμέτρους
        this.athletesList = athletes;
        this.mOnAthleteListener = onAthleteListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView name;
        private final TextView surname;
        private final TextView city;
        private final TextView code;
        private final TextView country;
        private final TextView date;
        private final TextView sport;
        private final OnAthleteListener onAthleteListener;

        public MyViewHolder(final View view, OnAthleteListener onAthleteListener) {
            super(view);
            this.onAthleteListener = onAthleteListener;
            // Σύνδεσε τa textview με τα textview απο το list_athletes
            name = view.findViewById(R.id.athleteName);
            surname = view.findViewById(R.id.athleteSurname);
            city = view.findViewById(R.id.athleteCity);
            code = view.findViewById(R.id.athleteCode);
            country = view.findViewById(R.id.athleteCountry);
            date = view.findViewById(R.id.athleteDate);
            sport = view.findViewById(R.id.athleteSportCode);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAthleteListener.onAthleteClick(getAdapterPosition());
        }
    }

    public interface OnAthleteListener{
        void onAthleteClick(int position);
    }

    @NonNull
    @NotNull
    @Override
    public AthleteAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_athletes, parent, false);
        return new MyViewHolder(itemView, mOnAthleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AthleteAdapter.MyViewHolder holder, int position) {
        // Μετατροπή του int code σε String
        String code = String.valueOf(athletesList.get(position).getId());

        // Μετατροπή του int sport σε String
        String sport = String.valueOf(athletesList.get(position).getSport_id());

        // Θέσε τα field του holder με τις τιμές απο το athletesList
        holder.name.setText(athletesList.get(position).getName());
        holder.surname.setText(athletesList.get(position).getSurname());
        holder.city.setText(athletesList.get(position).getCity());
        holder.code.setText(code);
        holder.country.setText(athletesList.get(position).getCountry());
        holder.date.setText(athletesList.get(position).getYear());
        holder.sport.setText(sport);
    }

    @Override
    public int getItemCount() {
        return athletesList.size();
    }
}
