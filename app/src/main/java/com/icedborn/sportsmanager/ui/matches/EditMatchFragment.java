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

import com.google.firebase.firestore.DocumentReference;
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

import java.util.Calendar;
import java.util.List;

public class EditMatchFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private TextView date;
    private Spinner host, guest, sport;
    public Match match;
    private String city;
    private String country;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.edit_match, container, false);

        ((HideShowIconInterface) requireActivity()).hideBurger();

        host = root.findViewById(R.id.editMatchHost);
        guest = root.findViewById(R.id.editMatchGuest);
        sport = root.findViewById(R.id.editMatchSport);
        Button btnAdd = root.findViewById(R.id.editMatchSave);
        Button btnRemove = root.findViewById(R.id.editMatchRemove);
        date = root.findViewById(R.id.editMatchDate);


        // Δημιουργία της επιλογής ημερομηνίας
        InitializeDatePicker();

        // Θέσε την ημερομηνία στη τωρινή
        date.setText(DateController.getToday());

        // Δείξε την επιλογή ημερομηνίας όταν πατάς click στην ημερομηνία
        date.setOnClickListener(v -> datePickerDialog.show());

        Connections c = Connections.getInstance(getContext());

        SportDAO sportDAO = c.getDatabase().getSportDAO();
        List<Sport> sportList = sportDAO.getAllSports();

        TeamDAO teamDAO = c.getDatabase().getTeamDAO();
        List<Team> teamList = teamDAO.getAllTeams();

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<Team> hostAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teamList);
        hostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Team> guestAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teamList);
        guestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<Sport> sportAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sportList);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        host.setAdapter(hostAdapter);
        guest.setAdapter(guestAdapter);
        sport.setAdapter(sportAdapter);

        SetSpinnersSelectedItems(sportList, teamList);

        btnAdd.setOnClickListener(v -> {

            if (host.getCount() < 2) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Add two teams before editing a match");
                toast.show();
            } else if (host.getSelectedItem() == guest.getSelectedItem()) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Team " + host.getSelectedItem().toString() + " can't be the host and  the guest at the same match");
                toast.show();
            } else if (sport.getSelectedItem() == null) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Add a sport before editing a match");
                toast.show();
            } else if (date.getText().equals("")) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Date is empty");
                toast.show();
            } else {

                for (int i = 0; i < teamList.size(); i++) {
                    if (teamList.get(i).getName().equals(host.getSelectedItem().toString())) {
                        city = teamList.get(i).getCity();
                        country = teamList.get(i).getCountry();
                    }
                }


                DocumentReference updateRef = db.document("Matches/" + match.getId());

                updateRef.update("cityName", city);
                updateRef.update("country", country);
                updateRef.update("date", date.getText().toString());
                updateRef.update("sport", sport.getSelectedItem().toString());
                updateRef.update("team1", host.getSelectedItem().toString());
                updateRef.update("team2", guest.getSelectedItem().toString());

                MatchesFragment Matches = new MatchesFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, Matches);
                transaction.commit();
                ((HideShowIconInterface) requireActivity()).showBurger();
            }
        });

        btnRemove.setOnClickListener(v -> {

            DocumentReference deleteRef = db.document("Matches/" + match.getId());
            deleteRef.delete();

            MatchesFragment Matches = new MatchesFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, Matches);
            transaction.commit();
            ((HideShowIconInterface) requireActivity()).showBurger();
        });

        ImageButton imageButton = root.findViewById(R.id.editMatchBackButton);

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

    private void SetSpinnersSelectedItems(List<Sport> sportsList, List<Team> teamsList) {
        for (int i = 0; i < sportsList.size(); i++) {
            if (Integer.parseInt(match.getSportId()) == sportsList.get(i).getId()) {
                sport.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < teamsList.size(); i++) {
            if (match.getTeam1().equals(teamsList.get(i).getName())) {
                host.setSelection(i);
                break;
            }
        }

        for (int i = 0; i < teamsList.size(); i++) {
            if (match.getTeam2().equals(teamsList.get(i).getName())) {
                guest.setSelection(i);
                break;
            }
        }
    }
}