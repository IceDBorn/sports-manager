package com.icedborn.sportsmanager.ui.teams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.Team;

import java.util.ArrayList;

public class TeamsFragment extends Fragment implements TeamAdapter.OnTeamListener {
    private RecyclerView recyclerView;
    private ArrayList<Team> teamsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TeamsViewModel teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_recycleview, container, false);
        teamsViewModel.SetTeamsInfo(getContext());
        teamsList = teamsViewModel.getTeams();

        if (teamsList.size() == 0) {
            final TextView textView = root.findViewById(R.id.addText);
            teamsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }

        FloatingActionButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(v -> {
            AddTeamFragment addTeam = new AddTeamFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment,addTeam);
            transaction.commit();
        });

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        // Δημιουργία adapter για το Recycler View
        SetAdapter();

        return root;
    }

    private void SetAdapter() {
        // Δημιουργία νέου adapter με την λίστα ομάδων
        TeamAdapter adapter = new TeamAdapter(teamsList, this);
        // Δημιουργία νέου Layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Θέσε το layoutManager ως το Layout Manager του Recycler View
        recyclerView.setLayoutManager(layoutManager);
        // Θέσε τον Animator του Recycler View ως το DefaultItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Θέσε τον Adapter toy Recycler View με το adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTeamClick(int position) {
        EditTeamFragment editTeam = new EditTeamFragment();
        editTeam.team = teamsList.get(position);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment,editTeam);
        transaction.commit();
    }
}
