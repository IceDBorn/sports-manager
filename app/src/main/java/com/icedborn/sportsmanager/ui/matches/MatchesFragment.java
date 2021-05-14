package com.icedborn.sportsmanager.ui.matches;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Match;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;
import com.icedborn.sportsmanager.databases.Team;
import com.icedborn.sportsmanager.databases.TeamDAO;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class MatchesFragment extends Fragment implements MatchAdapter.OnMatchListener {
    private RecyclerView recyclerView;
    private ArrayList<Match> matchesList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchRef = db.collection("Matches");
    SportDAO sportDAO;
    TeamDAO teamDAO;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MatchesViewModel matchesViewModel = new ViewModelProvider(this).get(MatchesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recycleview, container, false);
        matchesList = matchesViewModel.getMatches();

        if (matchesList.size() == 0) {
            final TextView textView = root.findViewById(R.id.addText);
            matchesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }
//////
        Connections connections = Connections.getInstance(context);
        TeamDAO teamDAO = connections.getDatabase().getTeamDAO();
        SportDAO sportDAO = connections.getDatabase().getSportDAO();
        ArrayList<Team> teams = (ArrayList<Team>) teamDAO.getAllTeams();
        ArrayList<Sport> sports = (ArrayList<Sport>) sportDAO.getAllSports();





/////
        FloatingActionButton add = root.findViewById(R.id.addButton);

        add.setOnClickListener(v -> {
            AddMatchFragment addMatch = new AddMatchFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment,addMatch);
            transaction.commit();
        });

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        matchRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                Team host = new Team();
                Sport sport = new Sport();
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){



                        String[] matchData = documentSnapshot.getData().values().toArray(new String[0]);
                        for (int i=0; i<teams.size();i++){
                            if (teams.get(i).getName().equals(matchData[4]) ){
                                host = teams.get(i);
                                break;
                            }
                        }

                        for (int i=0; i<sports.size();i++){
                            if (sports.get(i).getName().equals(matchData[6]) ){
                                sport = sports.get(i);
                                break;
                            }
                        }

                        teams.contains(matchData[4]);

                        matchesList.add(new Match(host,matchData[3],matchData[2],matchData[1],matchData[0],sport));

                    }
                }

                SetAdapter();
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
        transaction.replace(R.id.nav_host_fragment,editMatch);
        transaction.commit();
    }
}