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

import com.icedborn.sportsmanager.HideShowIconInterface;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.controllers.DateController;

import java.util.Calendar;

public class EditMatchFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private TextView date;
    private Spinner host, guest, sport;
    public MatchModel match;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_match, container, false);

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

        // TODO: Δημιούργησε μέθοδο για να γεμίζει ο πίνακας με τα αθλήματα, ομάδες
        String[] sports = {"Sport One", "Sport Two", "Sport Three"};
        String[] teams = {"Team One", "Team Two", "Team Three"};

        // TODO: Initialize fields with values

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<String> hostAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teams);
        hostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> guestAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teams);
        guestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> sportAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sports);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        host.setAdapter(hostAdapter);
        guest.setAdapter(guestAdapter);
        sport.setAdapter(sportAdapter);

        // TODO: Add button on click events

        btnAdd.setOnClickListener(v -> {
            if (host.getCount() < 2) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Add two teams before adding a match");
                toast.show();
            } else if (guest.getSelectedItem() == host.getSelectedItem()) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Team " + host.getSelectedItem().toString() + " can't be host and guest at the same match");
                toast.show();
            } else if (sport.getSelectedItem() == null) {
                Toast toast = new Toast(this.getContext());
                toast.setText("Add a sport before adding a match");
                toast.show();
            } else {
                MatchesFragment Matches = new MatchesFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, Matches);
                transaction.commit();
                ((HideShowIconInterface) requireActivity()).showBurger();
            }
        });

        btnRemove.setOnClickListener(v -> {
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
}