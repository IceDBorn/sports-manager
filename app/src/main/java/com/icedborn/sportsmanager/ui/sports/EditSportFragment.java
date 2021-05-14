package com.icedborn.sportsmanager.ui.sports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.icedborn.sportsmanager.HideShowIconInterface;
import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;

public class EditSportFragment extends Fragment {

    private EditText etName;
    private Spinner typeSpinner, genderSpinner;
    public Sport sport;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.edit_sport, container, false);

        ((HideShowIconInterface) requireActivity()).hideBurger();

        typeSpinner = root.findViewById(R.id.editSportTypes);
        genderSpinner = root.findViewById(R.id.editSportGenders);
        etName = root.findViewById(R.id.editSportName);
        Button btnAdd = root.findViewById(R.id.editSportSave);
        Button btnRemove = root.findViewById(R.id.editSportRemove);

        etName.setText(sport.getName());

        //ID του Sport
        long id = sport.getId();

        // Οι τύποι των αθλημάτων
        String[] types = {"Individual", "Team"};

        // Τα γένη των αθλητών για το κάθε άθλημα
        String[] genders = {"Male", "Female", "Both"};

        btnAdd.setOnClickListener(v -> {

            try {
                Connections c1 = Connections.getInstance(getContext());
                SportDAO sportDAO = c1.getDatabase().getSportDAO();
                Sport sport = sportDAO.getSportById(id);


                if (sport == null) {
                    System.out.println("Sport does not exist");
                } else {

                    if (etName.getText().toString().trim().equals("")) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("Name is empty");
                        toast.show();
                    } else {
                        sport.setName(etName.getText().toString().trim());
                        sport.setType(typeSpinner.getSelectedItem().toString());
                        sport.setSex(genderSpinner.getSelectedItem().toString());

                        sportDAO.update(sport);

                        SportsFragment Sports = new SportsFragment();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, Sports);
                        transaction.commit();
                        ((HideShowIconInterface) requireActivity()).showBurger();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        btnRemove.setOnClickListener(v -> {
            try {
                Connections c1 = Connections.getInstance(getContext());
                SportDAO sportDAO = c1.getDatabase().getSportDAO();
                Sport sport = sportDAO.getSportById(id);


                if (sport == null) {
                    System.out.println("Sport does not exist");
                } else {

                    sportDAO.delete(sport);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            SportsFragment Sports = new SportsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, Sports);
            transaction.commit();
            ((HideShowIconInterface) requireActivity()).showBurger();
        });

        // Δημιουργία νέων ArrayAdapter για τα spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        genderSpinner.setAdapter(genderAdapter);

        SetTypeSpinnerSelectedItem();
        SetGenderSpinnerSelectedItem();

        ImageButton imageButton = root.findViewById(R.id.editSportBackButton);

        imageButton.setOnClickListener(v -> {
            SportsFragment Sports = new SportsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, Sports);
            transaction.commit();
            ((HideShowIconInterface) requireActivity()).showBurger();
        });

        return root;
    }

    private void SetTypeSpinnerSelectedItem() {
        if ("Individual".equals(sport.getType())) {
            typeSpinner.setSelection(0);
        } else {
            typeSpinner.setSelection(1);
        }
    }

    private void SetGenderSpinnerSelectedItem() {
        switch (sport.getSex()) {
            case "Male":
                genderSpinner.setSelection(0);
                break;
            case "Female":
                genderSpinner.setSelection(1);
                break;
            default:
                genderSpinner.setSelection(2);
                break;
        }
    }
}
