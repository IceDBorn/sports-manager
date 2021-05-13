package com.icedborn.sportsmanager.ui.athletes;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.icedborn.sportsmanager.databases.SportDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddAthleteFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    TextView date;
    Spinner spinner;
    AthleteDAO athleteDAO;
    EditText etName,etSurname,etCity,etCountry;
    Button btnAdd;
    private long spoid;
    private  ArrayList<Sport> sportArrayList;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_athlete, container, false);

        etCity = root.findViewById(R.id.addAthleteCity);
        etCountry = root.findViewById(R.id.addAthleteCountry);
        etName = root.findViewById(R.id.addAthleteName);
        etSurname = root.findViewById(R.id.addAthleteSurname);
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
        List<Sport> sportList = new ArrayList<>();

        Connections c= Connections.getInstance(getContext());
        SportDAO sportDAO=c.getDatabase().getSportDAO();
        sportList = sportDAO.getAllSports();

        // Δημιουργία νέου ArrayAdapter για το spinner
        ArrayAdapter<Sport> adapter = new ArrayAdapter<Sport>(this.getContext(), android.R.layout.simple_spinner_item, sportList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sport sport = (Sport) parent.getSelectedItem();
                spoid = sport.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spoid = -1;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Athlete athlete = new Athlete();
                athlete.setName(etName.getText().toString().trim());
                athlete.setSurname(etSurname.getText().toString().trim());
                athlete.setCountry(etCountry.getText().toString().trim());
                athlete.setCity(etCity.getText().toString().trim());
                athlete.setSport_id(spoid);
                athlete.setYear(date.getText().toString().trim());


                Connections c= Connections.getInstance(getContext());
                AthleteDAO athleteDAO=c.getDatabase().getAthleteDAO();
                athleteDAO.insert(athlete);
            }
        });

        return root;
    }

    public void getSelectedAthlete(View v) {
        Athlete athlete = (Athlete) spinner.getSelectedItem();
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
