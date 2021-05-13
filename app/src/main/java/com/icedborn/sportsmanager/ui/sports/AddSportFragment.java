package com.icedborn.sportsmanager.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.AthleteDAO;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;

public class AddSportFragment extends Fragment {

    private EditText etName;
    private Button btnAdd;
    private Spinner typeSpinner,genderSpinner;
    private SportDAO sportDAO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_sport, container, false);

        typeSpinner = root.findViewById(R.id.addSportTypes);
        genderSpinner = root.findViewById(R.id.addSportGenders);

        etName = root.findViewById(R.id.addSportName);
        btnAdd = root.findViewById(R.id.addSportSave);

        // Οι τύποι των αθλημάτων
        String[] types = {"Individual", "Team"};

        // Τα γένη των αθλητών για το κάθε άθλημα
        String[] genders = {"Male", "Female", "Both"};

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sport sport = new Sport();
                sport.setName(etName.getText().toString().trim());
                sport.setType(typeSpinner.getSelectedItem().toString());
                sport.setSex(genderSpinner.getSelectedItem().toString());


                Connections c= Connections.getInstance(getContext());
                SportDAO sportDAO = c.getDatabase().getSportDAO();
                sportDAO.insert(sport);
            }
        });

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        genderSpinner.setAdapter(genderAdapter);

        return root;
    }
}
