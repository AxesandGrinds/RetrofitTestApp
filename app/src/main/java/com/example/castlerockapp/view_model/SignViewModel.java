package com.example.castlerockapp.view_model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.castlerockapp.models.Sign;
import com.example.castlerockapp.repository.SignRepository;

import java.util.List;

public class SignViewModel extends AndroidViewModel implements LifecycleObserver {

    private static final String TAG = SignViewModel.class.getSimpleName();
    private boolean enabled = false;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {
        Log.i(TAG, "ATTENTION ATTENTION: SignViewModel resumed");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disconnectListener() {
        Log.i(TAG, "ATTENTION ATTENTION: LiveData Working from MainActivity");
    }

    //    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    public void start() {
//        if (enabled) {
//            // connect
//        }
//    }
//
//    public void enable() {
//        enabled = true;
//        if (lifecycle.getCurrentState().isAtLeast(STARTED)) {
//            // connect if not connected
//        }
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    public void stop() {
//        // disconnect if connected
//    }

    private SignRepository              signRepository;
    private MutableLiveData<List<Sign>> signResponseLiveData;
    private List<Sign>                  signResponseList;

    public SignViewModel(@NonNull Application application) {
        super(application);

        this.signRepository       = new SignRepository();
        //this.signResponseLiveData = signRepository.getCarSigns();
        this.signResponseList     = signRepository.getCarSigns(); // SIGN_QUERY, API_KEY
    }

    public List<Sign> getCarSigns() {
        return signResponseList;
    }

    public MutableLiveData<List<Sign>> getSignResponseList() {
        return signResponseLiveData;
    }

}
