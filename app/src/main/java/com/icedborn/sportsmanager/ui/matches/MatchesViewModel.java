package com.icedborn.sportsmanager.ui.matches;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MatchesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<MatchModel> matchesList;

    public MatchesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No matches");

        matchesList = new ArrayList<>();

        SetMatchesInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αγώνων
    private void SetMatchesInfo() {
        /*matchesList.add(new MatchModel(1,4, 5, 6,"15/06/2021"));*/
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των ομάδων
    public ArrayList<MatchModel> getMatches() {
        return matchesList;
    }
}