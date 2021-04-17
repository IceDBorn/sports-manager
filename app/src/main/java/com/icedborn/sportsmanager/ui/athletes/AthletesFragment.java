package com.icedborn.sportsmanager.ui.athletes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.icedborn.sportsmanager.R;

public class AthletesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AthletesViewModel athletesViewModel = new ViewModelProvider(this).get(AthletesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_athletes, container, false);
        final TextView textView = root.findViewById(R.id.text_athletes);
        athletesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}