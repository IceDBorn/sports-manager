package com.icedborn.sportsmanager.ui.athletes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AthletesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AthletesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is athletes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}