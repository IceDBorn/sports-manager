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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.icedborn.sportsmanager.HideShowIconInterface;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.controllers.DateController;
import com.icedborn.sportsmanager.databases.Athlete;
import com.icedborn.sportsmanager.databases.AthleteDAO;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Match;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;
import com.icedborn.sportsmanager.databases.Team;
import com.icedborn.sportsmanager.databases.TeamDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMatchFragment extends Fragment {

    private DatePickerDialog datePickerDialog;
    private TextView date;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Spinner hostSpinner, guestSpinner, sportSpinner;
    private final CollectionReference matchRef = db.collection("Matches");
    private List<Sport> sportsList;
    private List<Team> teamsList;
    private List<Athlete> addAthletesList;
    private ArrayList<String> removeAthletesList = new ArrayList<>();
    private ArrayAdapter<Athlete> addParticipantAdapter;


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
        Spinner addParticipantSpinner = root.findViewById(R.id.addMatchAdd);
        Spinner removeParticipantSpinner = root.findViewById(R.id.addMatchRemove);
        FloatingActionButton addParticipant = root.findViewById(R.id.addMatchAddParticipant);
        FloatingActionButton removeParticipant = root.findViewById(R.id.addMatchRemoveParticipant);

        Connections c = Connections.getInstance(getContext());
        SportDAO sportDAO = c.getDatabase().getSportDAO();
        sportsList = sportDAO.getAllSports();
        TeamDAO teamDAO = c.getDatabase().getTeamDAO();
        teamsList = teamDAO.getAllTeams();
        AthleteDAO athleteDAO = c.getDatabase().getAthleteDAO();
        addAthletesList = athleteDAO.getAllAthletes();

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<Team> hostAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teamsList);
        hostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Team> guestAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teamsList);
        guestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Sport> sportAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sportsList);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addParticipantAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, addAthletesList);
        addParticipantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hostSpinner.setAdapter(hostAdapter);
        guestSpinner.setAdapter(guestAdapter);
        sportSpinner.setAdapter(sportAdapter);
        addParticipantSpinner.setAdapter(addParticipantAdapter);

        addParticipant.setOnClickListener(v -> {
            if (addParticipantSpinner.getSelectedItem() != null) {
                removeAthletesList.add(addParticipantSpinner.getSelectedItem().toString());
                addAthletesList.remove(addParticipantSpinner.getSelectedItemPosition());
                addParticipantAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, addAthletesList);
                addParticipantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addParticipantSpinner.setAdapter(addParticipantAdapter);
                ArrayAdapter<String> removeParticipantAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, removeAthletesList);
                removeParticipantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                removeParticipantSpinner.setAdapter(removeParticipantAdapter);
            }
            else {
                Toast toast = new Toast(this.getContext());
                toast.setText("No more athletes to add");
                toast.show();
            }
        });

        removeParticipant.setOnClickListener(v -> {
            if (removeParticipantSpinner.getSelectedItem() != null) {
                addAthletesList.add(new Athlete(removeParticipantSpinner.getSelectedItem().toString()));
                removeAthletesList.remove(removeParticipantSpinner.getSelectedItemPosition());
                addParticipantAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, addAthletesList);
                addParticipantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addParticipantSpinner.setAdapter(addParticipantAdapter);
                ArrayAdapter<String> removeParticipantAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, removeAthletesList);
                removeParticipantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                removeParticipantSpinner.setAdapter(removeParticipantAdapter);
            }
            else {
                Toast toast = new Toast(this.getContext());
                toast.setText("No more athletes to remove");
                toast.show();
            }
        });

        btnAdd.setOnClickListener(v -> {
            if (hostSpinner.getCount() < 2) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Add two teams before adding a match");
                toast.show();
            } else if (hostSpinner.getSelectedItem() == guestSpinner.getSelectedItem()) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Team " + hostSpinner.getSelectedItem().toString() + " can't be the host and the guest at the same match");
                toast.show();
            } else if (sportSpinner.getSelectedItem() == null) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Add a sport before adding a match");
                toast.show();
            } else if (date.getText().equals("")) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Date is empty");
                toast.show();
            } else {
                String sportId = 0 + "";
                String country = "";
                String city = "";

                for (int i = 0; i < sportsList.size(); i++) {
                    if (sportsList.get(i).getName().equals(sportSpinner.getSelectedItem().toString())) {
                        sportId = sportsList.get(i).getId() + "";
                        break;
                    }
                }

                for (int i = 0; i < teamsList.size(); i++) {
                    if (teamsList.get(i).getName().equals(hostSpinner.getSelectedItem().toString())) {
                        country = teamsList.get(i).getCountry();
                        city = teamsList.get(i).getCity();
                        break;
                    }
                }
                Match match = new Match();
                match.setTeam2(guestSpinner.getSelectedItem().toString());
                match.setTeam1(hostSpinner.getSelectedItem().toString());
                match.setSportName(sportSpinner.getSelectedItem().toString());
                match.setDate(date.getText().toString());
                match.setCountry(country);
                match.setCityName(city);
                match.setSportId(sportId);

                matchRef.add(match);

                MatchesFragment Matches = new MatchesFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, Matches);
                transaction.commit();
                ((HideShowIconInterface) requireActivity()).showBurger();
            }
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
