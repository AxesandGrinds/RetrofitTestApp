package com.example.castlerockapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.castlerockapp.models.Sign;
import com.example.castlerockapp.response.SignResponse;
import com.example.castlerockapp.retrofit.ApiRequest;
import com.example.castlerockapp.retrofit.RetrofitRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// UNUSED

public class SignRepository {

    private static final String TAG = SignRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    private List<Sign> signList = null;

    public List<Sign> getSignList() {
        return signList;
    }

    public void setSignList(List<Sign> signList) {
        this.signList = signList;
    }

    public SignRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    List<Sign> data = null;

    public List<Sign> getCarSigns() { // String query, String key

        apiRequest.getCarSigns() // query, key
                .enqueue(new Callback<List<Sign>>() {

                    @Override
                    public void onResponse(Call<List<Sign>> call, Response<List<Sign>> response) {

                        Log.d(TAG, "onResponse: " + response);

                        if (response.body() != null) {

                            signList = response.body();
                            data = signList;

                            //data.setValue(response.body());
                            Log.i(TAG, "signs size: "       + signList.size());
                            Log.i(TAG, "signs name pos 0: " + signList.get(0).getName());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Sign>> call, Throwable t) {
                        //data.setValue(null);
                    }

                });

        return data;

    }

    /*public MutableLiveData<List<Sign>> getCarSignsFailed() { // String query, String key

        final MutableLiveData<List<Sign>> data = new MutableLiveData<>();

        apiRequest.getCarSigns() // query, key
                .enqueue(new Callback<List<Sign>>() {

                    @Override
                    public void onResponse(Call<List<Sign>> call, Response<List<Sign>> response) {

                        Log.d(TAG, "onResponse: " + response);

                        if (response.body() != null) {

                            signList = response.body();

                            //data.setValue(response.body());
                            Log.i(TAG, "signs size: "       + signList.size());
                            Log.i(TAG, "signs name pos 0: " + signList.get(0).getName());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Sign>> call, Throwable t) {
                        data.setValue(null);
                    }

                });

        return data;

    }*/

}
