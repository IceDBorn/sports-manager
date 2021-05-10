package com.icedborn.sportsmanager.ui.teams;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TeamsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    private final ArrayList<TeamModel> teamsList;

    public TeamsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No teams");

        teamsList = new ArrayList<>();

        SetTeamsInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των ομάδων
    private void SetTeamsInfo() {
        /*teamsList.add(new TeamModel(1, "Axileas", "Neoxori", "Arta", "Greece", 8, "02/12/1967"));
        teamsList.add(new TeamModel(2, "Anaggenisi", "Doxa", "Arta", "Greece", 7, "05/03/1982"));
        teamsList.add(new TeamModel(3, "Doxa", "Anaggenisi", "Arta", "Greece", 6, "07/06/1967"));
        teamsList.add(new TeamModel(4, "Panathinaikos", "Oaka", "Athens", "Greece", 6, "02/12/1967"));
        teamsList.add(new TeamModel(5, "Olimpiakos", "Karaiskaki", "Athens", "Greece", 6, "02/12/1967"));
        teamsList.add(new TeamModel(6, "Paok", "Toumpa", "Thessaloniki", "Greece", 3, "02/12/1967"));
        teamsList.add(new TeamModel(7, "Aek", "Oaka", "Athens", "Greece", 2, "02/12/1967"));
        teamsList.add(new TeamModel(8, "Kommeno", "Kommeno", "Arta", "Greece", 1, "02/12/1967"));*/
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των ομάδων
    public ArrayList<TeamModel> getTeams() {
        return teamsList;
    }
}
