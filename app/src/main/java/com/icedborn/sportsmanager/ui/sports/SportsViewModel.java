package com.icedborn.sportsmanager.ui.sports;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.icedborn.sportsmanager.databases.Connections;
import com.icedborn.sportsmanager.databases.Sport;
import com.icedborn.sportsmanager.databases.SportDAO;
import com.icedborn.sportsmanager.ui.athletes.AthleteModel;

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
        sportsList=(ArrayList)sportDAO.getAllSports();
        /*sportsList.add(new SportModel(1,"Tennis","Individual", "Men/Women"));
        sportsList.add(new SportModel(2,"Basketball","Group", "Men"));
        sportsList.add(new SportModel(3,"Volleyball","Group", "Women"));
        sportsList.add(new SportModel(4,"Golf","Individual", "Men/Women"));
        sportsList.add(new SportModel(5,"Football","Group", "Men"));
        sportsList.add(new SportModel(6,"Soccer","Group", "Men"));
        sportsList.add(new SportModel(7,"Swimming","Group", "Men/Women"));
        sportsList.add(new SportModel(8,"Beach Volleyball","Group", "Women"));*/
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των αθλητών
    public ArrayList<Sport> getSports() {
        return sportsList;
    }
}