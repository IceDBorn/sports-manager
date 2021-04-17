package com.icedborn.sportsmanager.ui.teams;

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

public class TeamsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TeamsViewModel teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_teams, container, false);
        final TextView textView = root.findViewById(R.id.text_teams);
        teamsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}
