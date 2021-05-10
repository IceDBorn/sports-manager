package com.icedborn.sportsmanager.ui.sports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.MyViewHolder>{
    // Δημιουργία νέου ArrayList
    private final ArrayList<SportModel> sportsList;

    public SportAdapter(ArrayList<SportModel> sports) {
        // Θέσε το sportsList με το ArrayList απο τις παραμέτρους
        this.sportsList = sports;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView code;
        private final TextView name;
        private final TextView type;
        private final TextView gender;

        public MyViewHolder(final View view) {
            super(view);
            // Σύνδεσε τa textview με τα textview απο το list_sports
            code = view.findViewById(R.id.sportCode);
            name = view.findViewById(R.id.sportName);
            type = view.findViewById(R.id.sportType);
            gender = view.findViewById(R.id.sportGender);
        }
    }

    @NonNull
    @NotNull
    @Override
    public SportAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sports, parent, false);
        return new SportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SportAdapter.MyViewHolder holder, int position) {
        // Μετατροπή του int code σε String
        String code = String.valueOf(sportsList.get(position).code);

        // Θέσε τα field του holder με τις τιμές απο το sportsList
        holder.code.setText(code);
        holder.name.setText(sportsList.get(position).name);
        holder.type.setText(sportsList.get(position).type);
        holder.gender.setText(sportsList.get(position).gender);
    }

    @Override
    public int getItemCount() {
        return sportsList.size();
    }
}
