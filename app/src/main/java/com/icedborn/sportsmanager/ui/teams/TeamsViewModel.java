package com.icedborn.sportsmanager.ui.teams;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.icedborn.sportsmanager.databases.Athlete;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Team;
import com.icedborn.sportsmanager.databases.TeamDAO;

import java.util.ArrayList;

public class TeamsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Team> teamsList;
    private Context context;
    TeamDAO teamDAO;

    public TeamsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No teams");

        teamsList = new ArrayList<>();


    }

    // Πρόσθεσε στοιχεία στη λίστα των ομάδων
    public void SetTeamsInfo(Context context) {
        Connections connections = Connections.getInstance(context);
        TeamDAO teamDAO = connections.getDatabase().getTeamDAO();
        teamDAO.insert(new Team("fag","gag","gahs","gahsd",124,"1832"));
        teamsList = (ArrayList)teamDAO.getAllTeams();
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των ομάδων
    public ArrayList<Team> getTeams() {
        return teamsList;
    }
}
