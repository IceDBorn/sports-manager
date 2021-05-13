package com.icedborn.sportsmanager.databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//The Sport.class reason of creation is for creating a table in the database.

//The annotation @Entity is used for telling our application (through the RoomAPI libraries), that this is a
//table in our RoomAPI database
@Entity
public class Sport {

    //Each private variable in the class represents a column in the table

    //With the annotation @PrimaryKey we set the primary key of the table "Sport" as the ID of the sport
    //With the annotation @ColumnInfo we set the title of the column that is being made in the next line
    @PrimaryKey
    @ColumnInfo(name = "sport_id")
    private long id;

    private String name;

    private String type;

    private String sex;



    //We are making the two basic constructors in our class
    public Sport( String name, String type, String sex) {

        this.name = name;
        this.type = type;
        this.sex = sex;
    }

    @Ignore
    public Sport(long id, String name, String type, String sex) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sex = sex;
    }
    public Sport() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    //A simple toString in case we need it somewhere
    @Override
    public String toString() {
        return name;
    }
}
