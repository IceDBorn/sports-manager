package com.icedborn.sportsmanager.databases;

public class Match {

    private String id;
    private Team team1;
    private String team2;
    private String cityName;
    private String country;
    private String date;
    private Sport sport;

    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return team1.getCity();
    }

    public void setCityName(String cityName) {
        this.team1.setCity(cityName);
    }

    public String getCountry() {

        return team1.getCountry();
    }

    public void setCountry(String country) {
        this.team1.setCountry(country);
    }

    public String getDate() {
        return date;
    }

    public Match(Team team1, String team2, String cityName, String country, String date, Sport sport) {
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }


}
