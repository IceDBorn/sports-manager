package com.icedborn.sportsmanager.ui.athletes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class AthletesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<String> athletesList;

    public AthletesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add athlete");
        athletesList = new ArrayList<>();

        SetAthletesInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αθλητών
    private void SetAthletesInfo() {
        athletesList.add("Bruno");
        athletesList.add("John");
        athletesList.add("Alex");
        athletesList.add("Bruno");
        athletesList.add("John");
        athletesList.add("Alex");
        athletesList.add("Bruno");
        athletesList.add("John");
        athletesList.add("Alex");
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των αθλητών
    public ArrayList<String> getAthletes() {return athletesList;}
}