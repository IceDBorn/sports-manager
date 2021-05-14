package com.icedborn.sportsmanager.ui.matches;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.icedborn.sportsmanager.HideShowIconInterface;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.controllers.DateController;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Match;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;
import com.icedborn.sportsmanager.databases.Team;
import com.icedborn.sportsmanager.databases.TeamDAO;
import com.icedborn.sportsmanager.ui.athletes.AthletesFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMatchFragment extends Fragment {

    private DatePickerDialog datePickerDialog;
    private TextView date;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Spinner hostSpinner,guestSpinner,sportSpinner;
    private CollectionReference matchRef = db.collection("Matches");
    private String teamCity,teamCountry;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_match, container, false);

        ((HideShowIconInterface) requireActivity()).hideBurger();

        Button btnAdd = root.findViewById(R.id.addMatchSave);


        // Δημιουργία της επιλογής ημερομηνίας
        InitializeDatePicker();

        date = root.findViewById(R.id.addMatchDate);
        // Θέσε την ημερομηνία στη τωρινή
        date.setText(DateController.getToday());

        // Δείξε την επιλογή ημερομηνίας όταν πατάς click στην ημερομηνία
        date.setOnClickListener(v -> datePickerDialog.show());

        hostSpinner = root.findViewById(R.id.addMatchHost);

        guestSpinner = root.findViewById(R.id.addMatchGuest);

        sportSpinner = root.findViewById(R.id.addMatchSport);

        // TODO: Δημιούργησε μέθοδο για να γεμίζει ο πίνακας με τα αθλήματα, ομάδες
        List<Sport> sportList = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();

        Connections c= Connections.getInstance(getContext());

        SportDAO sportDAO=c.getDatabase().getSportDAO();
        sportList = sportDAO.getAllSports();

        TeamDAO teamDAO=c.getDatabase().getTeamDAO();
        teamList = teamDAO.getAllTeams();

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<Team> hostAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teamList);
        hostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Team> guestAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teamList);
        guestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Sport> sportAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sportList);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hostSpinner.setAdapter(hostAdapter);
        guestSpinner.setAdapter(guestAdapter);
        sportSpinner.setAdapter(sportAdapter);

        for (int i=0; i<teamList.size(); i++){

            if (teamList.get(i).getName().equals(hostSpinner.getSelectedItem().toString())){
                teamCity = teamList.get(i).getCity();
                teamCountry = teamList.get(i).getCountry();
                break;
            }
        }


        btnAdd.setOnClickListener(v -> {

            Match match = new Match();
            match.setTeam2(guestSpinner.getSelectedItem().toString());
            match.setTeam1(guestSpinner.getSelectedItem().toString());
            match.setSport(sportSpinner.getSelectedItem().toString());
            match.setDate(date.getText().toString());
            match.setCountry(teamCountry);
            match.setCityName(teamCity);

            matchRef.add(match);

        });

        ImageButton imageButton = root.findViewById(R.id.addMatchBackButton);

        imageButton.setOnClickListener(v -> {
            MatchesFragment Matches = new MatchesFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, Matches);
            transaction.commit();
            ((HideShowIconInterface) requireActivity()).showBurger();
        });

        return root;
    }

    private void InitializeDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month++;
            String date = DateController.makeDateString(dayOfMonth, month, year);
            // Θέσε την ημερομηνία
            this.date.setText(date);
        };

        // Δημιουργία νέου calendar
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        // Δημιουργία DatePickerDialog με τα στοιχεία απο παραπάνω
        datePickerDialog = new DatePickerDialog(this.getContext(), style, dateSetListener, year, month, day);
    }
}
