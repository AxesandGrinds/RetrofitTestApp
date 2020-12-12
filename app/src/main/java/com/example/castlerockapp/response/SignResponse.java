package com.example.castlerockapp.response;

import com.example.castlerockapp.models.Sign;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// UNUSED

public class SignResponse {

    @SerializedName("signs")
    @Expose
    private List<Sign> signs = null;

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

}
