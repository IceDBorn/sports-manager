package com.icedborn.sportsmanager.ui.athletes;

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
import com.icedborn.sportsmanager.databases.Athlete;
import com.icedborn.sportsmanager.databases.AthleteDAO;
import com.icedborn.sportsmanager.databases.Connections;

import java.util.ArrayList;

public class AthletesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Athlete> athletesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AthletesViewModel athletesViewModel = new ViewModelProvider(this).get(AthletesViewModel.class);
        athletesViewModel.setContext(getContext());
        Connections c= Connections.getInstance(getContext());
        AthleteDAO athleteDAO=c.getDatabase().getAthleteDAO();
        athleteDAO.insert(new Athlete("1231","takhs","mlks","cuiity",2,"3532"));
        athletesList=(ArrayList)athleteDAO.getAllAthletes();
        View root = inflater.inflate(R.layout.fragment_recycleview, container, false);
        athletesViewModel.SetAthletesInfo(athletesList);
//        athletesList = athletesViewModel.getAthletes();

        // Αν η λίστα με τους αθλητές είναι άδεια, τότε εμφάνισε το textview
        if (athletesList.size() == 0) {
            final TextView textView = root.findViewById(R.id.addText);
            athletesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        }

        // Δημιουργία νέου Recycler View
        recyclerView = root.findViewById(R.id.itemsView);

        // Δημιουργία adapter για το Recycler View
        SetAdapter();

        return root;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
    private void SetAdapter() {
        // Δημιουργία νέου adapter με την λίστα αθλητών
        AthleteAdapter adapter = new AthleteAdapter(athletesList);
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