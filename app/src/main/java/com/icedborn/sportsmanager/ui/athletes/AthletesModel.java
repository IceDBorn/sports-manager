package com.icedborn.sportsmanager.ui.athletes;

public class AthletesModel {
    public String code;
    public String name;
    public String surname;
    public String city;
    public String country;
    public String sport;
    public String date;

    public AthletesModel(String code, String name, String surname, String city, String country, String sport, String date) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.sport = sport;
        this.date = date;
    }
}
