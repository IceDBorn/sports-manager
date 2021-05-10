package com.icedborn.sportsmanager.ui.teams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.icedborn.sportsmanager.R;

import java.util.ArrayList;

public class TeamsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<TeamModel> teamsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TeamsViewModel teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_athletes, container, false);
        teamsList = teamsViewModel.getTeams();

        if (teamsList.size() == 0) {
            final TextView textView = root.findViewById(R.id.text_teams);
            teamsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        // Δημιουργία adapter για το Recycler View
        SetAdapter();

        return root;
    }

    private void SetAdapter() {
        // Δημιουργία νέου adapter με την λίστα ομάδων
        TeamAdapter adapter = new TeamAdapter(teamsList);
        // Δημιουργία νέου Layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Θέσε το layoutManager ως το Layout Manager του Recycler View
        recyclerView.setLayoutManager(layoutManager);
        // Θέσε τον Animator του Recycler View ως το DefaultItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Θέσε τον Adapter toy Recycler View με το adapter
        recyclerView.setAdapter(adapter);
    }
}
