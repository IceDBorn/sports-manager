package com.icedborn.sportsmanager.ui.athletes;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.icedborn.sportsmanager.R;
import com.icedborn.sportsmanager.controllers.DateController;

import java.util.Calendar;

public class AddAthleteFragment extends Fragment {
    private DatePickerDialog datePickerDialog;
    TextView dateButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.add_athlete, container, false);

        InitDatePicker();

        dateButton = root.findViewById(R.id.athleteBirthDate);

        dateButton.setText(DateController.getToday());

        dateButton.setOnClickListener(v -> datePickerDialog.show());

        return root;
    }

    private void InitDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1;
            String date = DateController.makeDateString(dayOfMonth, month, year);
            dateButton.setText(date);
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this.getContext(), style, dateSetListener, year, month, day);
    }
}
