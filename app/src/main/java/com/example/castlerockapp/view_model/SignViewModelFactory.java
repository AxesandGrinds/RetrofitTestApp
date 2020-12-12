package com.example.castlerockapp.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SignViewModelFactory implements ViewModelProvider.Factory   {

    @NonNull
    private final Application mApplication;

    public SignViewModelFactory(@NonNull Application application) {
        this.mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SignViewModel.class)) {
            return (T) new SignViewModel(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");

    }

}
