package com.example.castlerockapp.models;

public class Properties {

    private int maxSignPhases;
    private int phaseDwellTime;
    private int phaseBlankTime;
    private int maxLinesPerPage;
    private int maxCharactersPerLine;
    private boolean sizeKnown;


    public Properties(int maxSignPhases, int phaseDwellTime, int phaseBlankTime,
                      int maxLinesPerPage, int maxCharactersPerLine, boolean sizeKnown)
    {
        this.maxSignPhases = maxSignPhases;
        this.phaseDwellTime = phaseDwellTime;
        this.phaseBlankTime = phaseBlankTime;
        this.maxLinesPerPage = maxLinesPerPage;
        this.maxCharactersPerLine = maxCharactersPerLine;
        this.sizeKnown = sizeKnown;
    }

    public int getMaxSignPhases() {
        return maxSignPhases;
    }

    public void setMaxSignPhases(int maxSignPhases) {
        this.maxSignPhases = maxSignPhases;
    }

    public int getPhaseDwellTime() {
        return phaseDwellTime;
    }

    public void setPhaseDwellTime(int phaseDwellTime) {
        this.phaseDwellTime = phaseDwellTime;
    }

    public int getPhaseBlankTime() {
        return phaseBlankTime;
    }

    public void setPhaseBlankTime(int phaseBlankTime) {
        this.phaseBlankTime = phaseBlankTime;
    }

    public int getMaxLinesPerPage() {
        return maxLinesPerPage;
    }

    public void setMaxLinesPerPage(int maxLinesPerPage) {
        this.maxLinesPerPage = maxLinesPerPage;
    }

    public int getMaxCharactersPerLine() {
        return maxCharactersPerLine;
    }

    public void setMaxCharactersPerLine(int maxCharactersPerLine) {
        this.maxCharactersPerLine = maxCharactersPerLine;
    }

    public boolean isSizeKnown() {
        return sizeKnown;
    }

    public void setSizeKnown(boolean sizeKnown) {
        this.sizeKnown = sizeKnown;
    }

}
