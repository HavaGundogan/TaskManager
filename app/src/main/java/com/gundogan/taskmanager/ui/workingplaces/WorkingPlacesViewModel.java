package com.gundogan.taskmanager.ui.workingplaces;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorkingPlacesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WorkingPlacesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}