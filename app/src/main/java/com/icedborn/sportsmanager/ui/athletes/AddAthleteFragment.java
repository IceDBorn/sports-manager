package com.icedborn.sportsmanager.ui.athletes;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.controllers.DateController;
import com.icedborn.sportsmanager.databases.Athlete;
import com.icedborn.sportsmanager.databases.AthleteDAO;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Sport;

import java.util.Calendar;

public class AddAthleteFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    TextView date;
    Spinner spinner;
    AthleteDAO athleteDAO;
    EditText etName,etSurname,etCity,etCountry;
    Button btnAdd;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_athlete, container, false);

        etCity = root.findViewById(R.id.addAthleteCity);
        etCountry = root.findViewById(R.id.addAthleteCountry);
        etName = root.findViewById(R.id.addAthleteName);
        etSurname = root.findViewById(R.id.addAthletesSurname);
        btnAdd = root.findViewById(R.id.addAthleteSave);


        // Δημιουργία της επιλογής ημερομηνίας
        InitializeDatePicker();

        date = root.findViewById(R.id.athleteBirthDate);
        // Θέσε την ημερομηνία στη τωρινή
        date.setText(DateController.getToday());

        // Δείξε την επιλογή ημερομηνίας όταν πατάς click στην ημερομηνία
        date.setOnClickListener(v -> datePickerDialog.show());

        spinner = root.findViewById(R.id.addAthleteSports);

        // TODO: Δημιούργησε μέθοδο για να γεμίζει ο πίνακας με τα αθλήματα
        String[] sports = {"One", "Two", "Three"};

        // Δημιουργία νέου ArrayAdapter για το spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sports);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Athlete athlete = new Athlete();
                athlete.setName(etName.getText().toString().trim());
                athlete.setSurname(etSurname.getText().toString().trim());
                athlete.setCountry(etCountry.getText().toString().trim());
                athlete.setCity(etCity.getText().toString().trim());
                athlete.setSport_id(3);
                athlete.setYear(date.getText().toString().trim());





                Connections c= Connections.getInstance(getContext());
                AthleteDAO athleteDAO=c.getDatabase().getAthleteDAO();
                athleteDAO.insert(athlete);
            }
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
