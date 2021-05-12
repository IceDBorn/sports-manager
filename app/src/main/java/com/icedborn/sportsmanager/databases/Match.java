package com.icedborn.sportsmanager.databases;

public class Match {

    private String id;
    private String cityName;
    private String country;
    private String date;
    private Sport sport;

    public Match(String id, String cityName, String country, String date, Sport sport) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
        this.date = date;
        this.sport = sport;
    }

    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }
}
