package com.example.castlerockapp.models;

import java.util.List;
import java.util.Map;

public class Sign {

    private String status;
    private String agencyId;
    private Long   lastUpdated;
    private String agencyName;
    private String idForDisplay;
    private String name;
    private String id;

    private Location location;
    private Properties properties;

    // Nullable
    Map<String, List<Pages>> display;

    public Sign(String status, String agencyId, Long lastUpdated, String agencyName,
                String idForDisplay, String name, String id, Location location,
                Properties properties, Map<String, List<Pages>> display)
    {

        this.status = status;
        this.agencyId = agencyId;
        this.lastUpdated = lastUpdated;
        this.agencyName = agencyName;
        this.idForDisplay = idForDisplay;
        this.name = name;
        this.id = id;
        this.location = location;
        this.properties = properties;
        this.display = display;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getIdForDisplay() {
        return idForDisplay;
    }

    public void setIdForDisplay(String idForDisplay) {
        this.idForDisplay = idForDisplay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<String, List<Pages>> getDisplay() {
        return display;
    }

    public void setDisplay(Map<String, List<Pages>> display) {
        this.display = display;
    }

}
