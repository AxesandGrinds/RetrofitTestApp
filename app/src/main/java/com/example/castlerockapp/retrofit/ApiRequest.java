package com.example.castlerockapp.retrofit;

import com.example.castlerockapp.models.Sign;
import com.example.castlerockapp.response.SignResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("signs_v1/api/signs")
    Call<List<Sign>> getCarSigns();

//    Call<SignResponse> getCarSigns(
//            //@Query("q") String query,
//            //@Query("apikey") String apiKey
//    );

}
