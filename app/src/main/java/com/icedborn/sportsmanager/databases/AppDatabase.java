package com.icedborn.sportsmanager.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//AppDatabase takes the classes and brings them all together in a database

//In entities we set the tables of our database and set our version of the database
@Database(entities = {Sport.class, Athlete.class,Team.class}, version =1, exportSchema = false)
public abstract class  AppDatabase extends RoomDatabase {
    //We say that it extends from the RoomDatabase so it can work
    //Its a abstract class, it means it does not create objects


    //We get the DAO we made of the table we inserted
    public abstract SportDAO getSportDAO();
    public abstract AthleteDAO getAthleteDAO();
    public abstract TeamDAO getTeamDAO();

}