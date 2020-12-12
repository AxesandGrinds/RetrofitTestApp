package com.example.castlerockapp.sort;

import com.example.castlerockapp.models.Sign;

import java.util.Comparator;

public class SortByLastUpdated implements Comparator<Sign> {

    // Used for sorting in ascending order of
    // roll number
    public int compare(Sign a, Sign b)
    {
        return Long.compare(a.getLastUpdated(), b.getLastUpdated());
        //return a.getLastUpdated() - b.getLastUpdated();
    }

}
