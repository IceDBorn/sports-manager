package com.icedborn.sportsmanager.ui.sports;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.icedborn.sportsmanager.ui.athletes.AthleteModel;

import java.util.ArrayList;

public class SportsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ArrayList<SportModel> sportsList;

    public SportsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No sports");
        sportsList = new ArrayList<>();

        SetSportsInfo();
    }

    // Πρόσθεσε στοιχεία στη λίστα των αθλητών
    private void SetSportsInfo() {
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
    public ArrayList<SportModel> getSports() {
        return sportsList;
    }
}