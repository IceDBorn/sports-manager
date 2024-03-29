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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.Match;

import java.util.ArrayList;
import java.util.Objects;

public class MatchesFragment extends Fragment implements MatchAdapter.OnMatchListener {
    private RecyclerView recyclerView;
    private ArrayList<Match> matchesList;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference matchRef = db.collection("Matches");
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MatchesViewModel matchesViewModel = new ViewModelProvider(this).get(MatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recycleview, container, false);
        matchesList = matchesViewModel.getMatches();

        if (matchesList.size() == 0) {
            textView = root.findViewById(R.id.addText);
            matchesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }

        FloatingActionButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(v -> {
            AddMatchFragment addMatch = new AddMatchFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, addMatch);
            transaction.commit();
        });

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        matchRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {

                    String[] matchData = documentSnapshot.getData().values().toArray(new String[0]);
                    matchesList.add(new Match(documentSnapshot.getId(), matchData[4], matchData[5], matchData[3], matchData[1], matchData[0], matchData[6], matchData[2]));
                }
            }

            SetAdapter();
            if (matchesList.size() > 0) {
                textView.setText("");
            }

        });

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
        transaction.replace(R.id.nav_host_fragment, editMatch);
        transaction.commit();
    }
}