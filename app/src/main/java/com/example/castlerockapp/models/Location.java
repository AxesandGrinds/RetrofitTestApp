package com.example.castlerockapp.models;

public class Location {

    private String locationDescription;
    private String cityReference;
    private float  latitude;
    private float  longitude;
    private String routeId;
    private float  linearReference;
    private int    fips;
    private float  perpendicularRadiansForDirectionOfTravel;
    private String signFacingDirection;

    public Location(String locationDescription, String cityReference, float latitude,
                    float longitude, String routeId, float linearReference, int fips,
                    float perpendicularRadiansForDirectionOfTravel, String signFacingDirection)
    {

        this.locationDescription = locationDescription;
        this.cityReference = cityReference;
        this.latitude = latitude;
        this.longitude = longitude;
        this.routeId = routeId;
        this.linearReference = linearReference;
        this.fips = fips;
        this.perpendicularRadiansForDirectionOfTravel = perpendicularRadiansForDirectionOfTravel;
        this.signFacingDirection = signFacingDirection;

    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getCityReference() {
        return cityReference;
    }

    public void setCityReference(String cityReference) {
        this.cityReference = cityReference;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public float getLinearReference() {
        return linearReference;
    }

    public void setLinearReference(float linearReference) {
        this.linearReference = linearReference;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public float getPerpendicularRadiansForDirectionOfTravel() {
        return perpendicularRadiansForDirectionOfTravel;
    }

    public void setPerpendicularRadiansForDirectionOfTravel(float perpendicularRadiansForDirectionOfTravel) {
        this.perpendicularRadiansForDirectionOfTravel = perpendicularRadiansForDirectionOfTravel;
    }

    public String getSignFacingDirection() {
        return signFacingDirection;
    }

    public void setSignFacingDirection(String signFacingDirection) {
        this.signFacingDirection = signFacingDirection;
    }

}
