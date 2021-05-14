package com.icedborn.sportsmanager.databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//The Team.class reason of creation is for creating a table in the database.

//The annotation @Entity is used for telling our application (through the RoomAPI libraries), that this is a
//table in our RoomAPI database
@Entity
public class Team {

    //Each private variable in the class represents a column in the table

    //With the annotation @PrimaryKey we set the primary key of the table "Team" as the ID of the team
    //With the annotation @ColumnInfo we set the title of the column that is being made in the next line
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "team_id")
    private long id;

    private String name;

    private String court_name;

    @ColumnInfo(name = "home_ground")
    private String city;

    private String country;

    private long sport_id;

    private String sport_name;

    @ColumnInfo(name = "founded")
    private String year;



    public Team( String name, String court_name, String city, String country, long sport_id, String year) {
        this.name = name;
        this.court_name = court_name;
        this.city = city;
        this.country = country;
        this.sport_id = sport_id;
        this.year = year;
    }

    @Ignore
    public Team(long id, String name, String court_name, String city, String country, long sport_id, String year) {
        this.id = id;
        this.name = name;
        this.court_name = court_name;
        this.city = city;
        this.country = country;
        this.sport_id = sport_id;
        this.year = year;
    }

    public Team() {
    }



    //Our Setters and Getters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getSport_id() {
        return sport_id;
    }

    public void setSport_id(long sport_id) {
        this.sport_id = sport_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSport_name(String name) {this.sport_name = name;}

    public String getSport_name() {return this.sport_name;}



    //A simple toString in case we need it somewhere
    @Override
    public String toString() {
        return name;
    }
}
