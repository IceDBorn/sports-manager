package com.icedborn.sportsmanager.ui.matches;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Match;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;
import com.icedborn.sportsmanager.databases.Team;
import com.icedborn.sportsmanager.databases.TeamDAO;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MatchesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<Match> matchesList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchRef = db.collection("Matches");
    TeamDAO teamDAO;
    private Context context;
    SportDAO sportDAO;

    public MatchesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No matches");

        matchesList = new ArrayList<>();

        SetMatchesInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αγώνων
    private void SetMatchesInfo() {

//        Connections connections = Connections.getInstance(context);
//        TeamDAO teamDAO = connections.getDatabase().getTeamDAO();
//        SportDAO sportDAO = connections.getDatabase().getSportDAO();
//        ArrayList<Team> teams = (ArrayList<Team>) teamDAO.getAllTeams();
//        ArrayList<Sport> sports = (ArrayList<Sport>) sportDAO.getAllSports();
//
//


    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των ομάδων
    public ArrayList<Match> getMatches() {
        return matchesList;
    }
}