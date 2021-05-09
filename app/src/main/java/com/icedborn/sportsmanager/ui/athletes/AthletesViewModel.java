package com.icedborn.sportsmanager.ui.athletes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class AthletesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<AthletesModel> athletesList;

    public AthletesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add athlete");
        athletesList = new ArrayList<>();

        SetAthletesInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αθλητών
    private void SetAthletesInfo() {
        athletesList.add(new AthletesModel("1", "Bruno", "Kaloci", "Giannena", "Albania", "Football", "22/02/1998"));
        athletesList.add(new AthletesModel("2", "Vasilis", "Kostas", "Arta", "Greece", "Tennis", "23/06/2000"));
        athletesList.add(new AthletesModel("3", "Panagiotis", "Kostas", "Athens", "Italy", "Soccer", "13/12/2005"));
        athletesList.add(new AthletesModel("4", "Joanna", "Petsa", "Thessaloniki", "Germany", "Basketball", "13/07/1995"));
        athletesList.add(new AthletesModel("5", "Stefanos", "Papanatsios", "Larisa", "USA", "Volley", "21/06/2003"));
        athletesList.add(new AthletesModel("6", "Nikolas", "Pantazis", "Agrinio", "UK", "Beach Volley", "20/05/2001"));
        athletesList.add(new AthletesModel("7", "Antonis", "Kostas", "Patra", "Sweden", "Golf", "11/07/1990"));
        athletesList.add(new AthletesModel("8", "George", "Mentis", "Kefalonia", "Turkey", "Swimming", "15/09/1993"));
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των αθλητών
    public ArrayList<AthletesModel> getAthletes() {
        return athletesList;
    }
}