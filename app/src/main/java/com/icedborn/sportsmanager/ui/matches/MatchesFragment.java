package com.icedborn.sportsmanager.ui.matches;

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

import java.util.ArrayList;

public class MatchesFragment extends Fragment implements MatchAdapter.OnMatchListener {
    private RecyclerView recyclerView;
    private ArrayList<MatchModel> matchesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MatchesViewModel matchesViewModel = new ViewModelProvider(this).get(MatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recycleview, container, false);
        matchesList = matchesViewModel.getMatches();

        if (matchesList.size() == 0) {
            final TextView textView = root.findViewById(R.id.addText);
            matchesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }

        FloatingActionButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(v -> {
            AddMatchFragment addMatch = new AddMatchFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment,addMatch);
            transaction.commit();
        });

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        // Δημιουργία adapter για το Recycler View
        SetAdapter();

        return root;
    }

    private void SetAdapter() {
        // Δημιουργία νέου adapter με την λίστα αγώνων
        MatchAdapter adapter = new MatchAdapter(matchesList, this);
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
    public void onMatchClick(int position) {
        EditMatchFragment editMatch = new EditMatchFragment();
        editMatch.match = matchesList.get(position);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment,editMatch);
        transaction.commit();
    }
}