package com.icedborn.sportsmanager.databases;

public class Match {

    private String id;
    private String team1;
    private String team2;
    private String cityName;
    private String country;
    private String date;
    private String sport;

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

    public Match(String team1, String team2, String cityName, String country, String date, String sport) {
        this.team1 = team1;
        this.team2 = team2;
        this.cityName = cityName;
        this.country = country;
        this.date = date;
        this.sport = sport;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }


}
