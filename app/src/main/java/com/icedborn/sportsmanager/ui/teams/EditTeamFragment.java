package com.icedborn.sportsmanager.ui.teams;

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
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;
import com.icedborn.sportsmanager.databases.Team;
import com.icedborn.sportsmanager.databases.TeamDAO;

import java.util.Calendar;
import java.util.List;

public class EditTeamFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    private TextView date;
    private EditText etName, etCountry, etCity, etStadium;
    private long sportId;
    public Team team;
    private Spinner spinner;
    private String sportName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.edit_team, container, false);

        ((HideShowIconInterface) requireActivity()).hideBurger();

        etName = root.findViewById(R.id.editTeamName);
        etCountry = root.findViewById(R.id.editTeamCountry);
        etCity = root.findViewById(R.id.editTeamCity);
        etStadium = root.findViewById(R.id.editTeamStadium);
        date = root.findViewById(R.id.editTeamCreationDate);
        Button btnAdd = root.findViewById(R.id.editTeamSave);
        Button btnRemove = root.findViewById(R.id.editTeamRemove);
        spinner = root.findViewById(R.id.editTeamSports);

        etName.setText(team.getName());
        etCountry.setText(team.getCountry());
        etCity.setText(team.getCity());
        etStadium.setText(team.getCourt_name());
        date.setText(team.getYear());

        //Team ID
        long id = team.getId();

        // Δημιουργία της επιλογής ημερομηνίας
        InitializeDatePicker();

        // Δείξε την επιλογή ημερομηνίας όταν πατάς click στην ημερομηνία
        date.setOnClickListener(v -> datePickerDialog.show());

        Connections c = Connections.getInstance(getContext());
        SportDAO sportDAO = c.getDatabase().getSportDAO();
        List<Sport> sportList = sportDAO.getAllSports();

        // Δημιουργία νέου ArrayAdapter για το spinner
        ArrayAdapter<Sport> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, sportList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sport sport = (Sport) parent.getSelectedItem();
                sportId = sport.getId();
                sportName = sport.getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sportId = -1;
            }
        });

        SetSpinnerSelectedItem(sportList);

        btnAdd.setOnClickListener(v -> {

            try {
                Connections c1 = Connections.getInstance(getContext());
                TeamDAO teamDAO = c1.getDatabase().getTeamDAO();
                Team team = teamDAO.getTeamById(id);


                if (team == null) {
                    System.out.println("Team does not exist");
                } else {
                    if (etName.getText().toString().trim().equals("")) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("Name is empty");
                        toast.show();
                    } else if (etCity.getText().toString().trim().equals("")) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("City is empty");
                        toast.show();
                    } else if (etCountry.getText().toString().trim().equals("")) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("Country is empty");
                        toast.show();
                    } else if (etStadium.getText().toString().trim().equals("")) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("Stadium is empty");
                        toast.show();
                    } else if (spinner.getSelectedItem() == null) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("Add a sport before editing a team");
                        toast.show();
                    } else if (date.getText().toString().trim().equals("")) {
                        Toast toast = new Toast(this.getContext());
                        toast.setText("Date is empty");
                        toast.show();
                    } else {
                        team.setName(etName.getText().toString().trim());
                        team.setCountry(etCountry.getText().toString().trim());
                        team.setCity(etCity.getText().toString().trim());
                        team.setCourt_name(etStadium.getText().toString().trim());
                        team.setYear(date.getText().toString());
                        team.setSport_id(sportId);
                        team.setSport_name(sportName);

                        teamDAO.update(team);

                        TeamsFragment Teams = new TeamsFragment();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, Teams);
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
                TeamDAO teamDAO = c1.getDatabase().getTeamDAO();
                Team team = teamDAO.getTeamById(id);


                if (team == null) {
                    System.out.println("Team does not exist");
                } else {

                    teamDAO.delete(team);

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            TeamsFragment Teams = new TeamsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, Teams);
            transaction.commit();
            ((HideShowIconInterface) requireActivity()).showBurger();
        });

        ImageButton imageButton = root.findViewById(R.id.editTeamBackButton);

        imageButton.setOnClickListener(v -> {
            TeamsFragment Teams = new TeamsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.nav_host_fragment, Teams);
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

    private void SetSpinnerSelectedItem(List<Sport> sportsList) {
        for (int i = 0; i < sportsList.size(); i++) {
            if (team.getSport_id() == sportsList.get(i).getId()) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}