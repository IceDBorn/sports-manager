package com.icedborn.sportsmanager.ui.athletes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class AthletesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<AthleteModel> athletesList;

    public AthletesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No athletes");
        athletesList = new ArrayList<>();

        SetAthletesInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αθλητών
    private void SetAthletesInfo() {
        /*athletesList.add(new AthleteModel(1, "Bruno", "Kaloci", "Giannena", "Albania", 8, "22/02/1998"));
        athletesList.add(new AthleteModel(2, "Vasilis", "Kostas", "Arta", "Greece", 7, "23/06/2000"));
        athletesList.add(new AthleteModel(3, "Panagiotis", "Kostas", "Athens", "Italy", 6, "13/12/2005"));
        athletesList.add(new AthleteModel(4, "Joanna", "Petsa", "Thessaloniki", "Germany", 5, "13/07/1995"));
        athletesList.add(new AthleteModel(5, "Stefanos", "Papanatsios", "Larisa", "USA", 4, "21/06/2003"));
        athletesList.add(new AthleteModel(6, "Nikolas", "Pantazis", "Agrinio", "UK", 3, "20/05/2001"));
        athletesList.add(new AthleteModel(7, "Antonis", "Kostas", "Patra", "Sweden", 2, "11/07/1990"));
        athletesList.add(new AthleteModel(8, "George", "Mentis", "Kefalonia", "Turkey", 1, "15/09/1993"));*/
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των αθλητών
    public ArrayList<AthleteModel> getAthletes() {
        return athletesList;
    }
}