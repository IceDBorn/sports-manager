package com.icedborn.sportsmanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    // Δημιουργία νέου ArrayList
    private final ArrayList<String> itemList;

    public recyclerAdapter(ArrayList<String> items) {
        // Θέσε το itemlist με το ArrayList απο τις παραμέτρους
        this.itemList = items;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Δημιουργία νέου textview
        private final TextView athleteName;

        public MyViewHolder(final View view) {
            super(view);
            // Θέσε το atheleteName με το athleteNameValue απο το textview στο list_athletes
            athleteName = view.findViewById(R.id.athleteNameValue);
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
        // Θέσε athleteName με το string που βρίσκεται στην θέση position
        String athleteName = itemList.get(position);
        // Θέσε το athleteName του συγκεκριμένου item με το string απο παραπάνω
        holder.athleteName.setText(athleteName);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
