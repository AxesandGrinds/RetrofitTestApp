package com.example.castlerockapp.models;

import java.util.List;

public class Pages {

    private String justification;
    private List<String> lines;

    public Pages(String justification, List<String> lines) {
        this.justification = justification;
        this.lines = lines;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

}
