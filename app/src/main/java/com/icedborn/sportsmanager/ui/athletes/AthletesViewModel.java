package com.icedborn.sportsmanager.ui.athletes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AthletesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AthletesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("No athletes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}