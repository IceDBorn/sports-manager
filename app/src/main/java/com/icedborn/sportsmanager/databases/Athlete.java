package com.icedborn.sportsmanager.databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//The Athlete.class reason of creation is for creating a table in the database.

//The annotation @Entity is used for telling our application (through the RoomAPI libraries), that this is a
//table in our RoomAPI database
@Entity
public class Athlete {

    //Each private variable in the class represents a column in the table

    //With the annotation @PrimaryKey we set the primary key of the table "Athlete" as the ID of the athlete
    //With the annotation @ColumnInfo we set the title of the column that is being made in the next line

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "athlete_id")
    private long id;

    private String name;

    private String surname;

    @ColumnInfo(name = "home_ground")
    private String city;

    private String country;

    private long sport_id;

    @ColumnInfo(name = "date_of_birth")
    private String year;



    //We are making the two basic constructors in our class
    @Ignore
    public Athlete(long id, String name, String surname, String city, String country, long sport_id, String year) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.sport_id = sport_id;
        this.year = year;
    }
    public Athlete( String name, String surname, String city, String country, long sport_id, String year) {

        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.sport_id = sport_id;
        this.year = year;
    }

    public Athlete() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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



    //A simple toString in case we need it somewhere
    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", sport_id=" + sport_id +
                ", year='" + year + '\'' +
                '}';
    }
}
