package com.icedborn.sportsmanager.ui.teams;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TeamsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is teams fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}