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
public interface TeamDAO {

    //The special annotation @Insert is used to insert something in the database.
    @Insert
    void insert (Team team);

    //The special annotation @Delete is used to delete something from the database.
    @Delete
    void delete (Team team);

    //The special annotation @Update is used to update something in the database.
    @Update
    void update (Team team);

    //The annotation @Query requires that you provide a SQL query as a string parameter to the annotation.
    //Its often used for selecting many items in the table.
    @Query("DELETE FROM Team WHERE team_id = :id")
    void delete (long id);

    @Query("SELECT * FROM Team ORDER BY name")
    List<Team> getAllTeams();

    @Query("SELECT * FROM Team WHERE team_id = :id")
    Team getTeamById(long id);
}