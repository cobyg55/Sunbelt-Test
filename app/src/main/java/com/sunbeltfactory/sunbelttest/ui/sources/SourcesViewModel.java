package com.sunbeltfactory.sunbelttest.ui.sources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SourcesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SourcesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}