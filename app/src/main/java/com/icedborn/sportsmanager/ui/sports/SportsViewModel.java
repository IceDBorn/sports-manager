package com.icedborn.sportsmanager.ui.sports;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;

import java.util.ArrayList;

public class SportsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private  ArrayList<Sport> sportsList;

    public SportsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No sports");
        sportsList = new ArrayList<Sport>();


    }

    // Πρόσθεσε στοιχεία στη λίστα των αθλητών
    public void SetSportsInfo(Context context) {

        Connections c= Connections.getInstance(context);
        SportDAO sportDAO=c.getDatabase().getSportDAO();
        sportDAO.insert(new Sport("name","aoishf","qof"));
        sportDAO.insert(new Sport("George","Karellias","Male"));

        sportsList=(ArrayList)sportDAO.getAllSports();

    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των αθλητών
    public ArrayList<Sport> getSports() {
        return sportsList;
    }
}