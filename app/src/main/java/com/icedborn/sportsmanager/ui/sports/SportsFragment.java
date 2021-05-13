package com.icedborn.sportsmanager.ui.sports;

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
import com.icedborn.sportsmanager.databases.Sport;

import java.util.ArrayList;

public class SportsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Sport> sportsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SportsViewModel sportsViewModel = new ViewModelProvider(this).get(SportsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recycleview, container, false);
        sportsViewModel.SetSportsInfo(getContext());

//


        sportsList = sportsViewModel.getSports();

        if (sportsList.size() == 0) {
            final TextView textView = root.findViewById(R.id.addText);
            sportsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }

        FloatingActionButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(v -> {
            AddSportFragment addSport = new AddSportFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment,addSport);
            transaction.commit();
        });

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        // Δημιουργία adapter για το Recycler View
        SetAdapter();
        
        return root;
    }

    private void SetAdapter() {
        // Δημιουργία νέου adapter με την λίστα αθλητών
        SportAdapter adapter = new SportAdapter(sportsList);
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