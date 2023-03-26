package com.gundogan.taskmanager.ui.mycards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyCardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}