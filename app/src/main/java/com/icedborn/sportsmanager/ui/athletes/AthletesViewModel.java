package com.icedborn.sportsmanager.ui.athletes;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.icedborn.sportsmanager.databases.AppDatabase;
import com.icedborn.sportsmanager.databases.Athlete;
import com.icedborn.sportsmanager.databases.AthleteDAO;
import com.icedborn.sportsmanager.databases.Connections;

import java.util.ArrayList;

public class AthletesViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Athlete> athletesList;
    private Context context;
    AthleteDAO athleteDAO;

    public AthletesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No athletes");
        athletesList = new ArrayList<>();


    }
    public void setContext(Context context){
        this.context=context;
    }
    // Πρόσθεσε στοιχεία στη λίστα των αθλητών
    public void SetAthletesInfo(ArrayList<Athlete> l) {
//        AppDatabase database= Room.databaseBuilder(context,AppDatabase.class,"db_sportmanager").allowMainThreadQueries().fallbackToDestructiveMigration().build();
          athletesList = l;
//
//
//        System.out.println(athleteDAO.getAllAthletes().get(0));
       // athletesList = (ArrayList) athleteDAO.getAllAthletes();
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Επέστρεψε την λίστα των αθλητών
    public ArrayList<Athlete> getAthletes() {
        return athletesList;
    }
}