package com.icedborn.sportsmanager.ui.matches;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.icedborn.sportsmanager.databases.Match;

import java.util.ArrayList;

public class MatchesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<Match> matchesList;

    public MatchesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No matches");

        matchesList = new ArrayList<>();

        SetMatchesInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αγώνων
    private void SetMatchesInfo() {
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των ομάδων
    public ArrayList<Match> getMatches() {
        return matchesList;
    }
}