package com.icedborn.sportsmanager.ui.matches;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.controllers.DateController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddMatchFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private TextView date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_match, container, false);

        // Δημιουργία της επιλογής ημερομηνίας
        InitializeDatePicker();

        date = root.findViewById(R.id.addMatchDate);
        // Θέσε την ημερομηνία στη τωρινή
        date.setText(DateController.getToday());

        // Δείξε την επιλογή ημερομηνίας όταν πατάς click στην ημερομηνία
        date.setOnClickListener(v -> datePickerDialog.show());

        Spinner hostSpinner = root.findViewById(R.id.addMatchHost);

        Spinner guestSpinner = root.findViewById(R.id.addMatchGuest);

        Spinner sportSpinner = root.findViewById(R.id.addMatchSport);

        // TODO: Δημιούργησε μέθοδο για να γεμίζει ο πίνακας με τα αθλήματα, ομάδες
        String[] sports = {"Sport One", "Sport Two", "Sport Three"};
        String[] teams = {"Team One", "Team Two", "Team Three"};

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<String> hostAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teams);
        hostAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> guestAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, teams);
        guestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> sportAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sports);
        sportAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        hostSpinner.setAdapter(hostAdapter);
        guestSpinner.setAdapter(guestAdapter);
        sportSpinner.setAdapter(sportAdapter);

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
