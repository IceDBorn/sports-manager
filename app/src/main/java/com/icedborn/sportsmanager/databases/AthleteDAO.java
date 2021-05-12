package com.icedborn.sportsmanager.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//A DAO (data access object) validates your SQL at compile-time and associates it with a method.
//The annotation @Dao sets the class as an DAO
@Dao
public interface AthleteDAO {

    //The special annotation @Insert is used to insert something in the database.
    @Insert
    void insert (Athlete athlete);

    //The special annotation @Delete is used to delete something from the database.
    @Delete
    void delete (Athlete athlete);

    //The special annotation @Update is used to update something in the database.
    @Update
    void update (Athlete athlete);

    //The annotation @Query requires that you provide a SQL query as a string parameter to the annotation.
    //Its often used for selecting many items in the table.
    @Query("DELETE FROM Athlete WHERE athlete_id = :id")
    void delete (long id);

    @Query("SELECT * FROM Athlete ORDER BY name")
    List<Athlete> getAllAthletes();

    @Query("SELECT * FROM Athlete WHERE athlete_id = :id")
    Athlete getAthleteById(long id);
}