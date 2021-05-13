package com.icedborn.sportsmanager.databases;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

//We are using the singleton technique that makes only one instance of the database.
//If an instance exists then it cannot make another instance of the database.
//For that we builded the Connections Class that can check the connections to the database.
public class Connections {

    private SportDAO sportDAO;

    private static Connections instance;
    private AppDatabase database;   //The connection to our database (AppDatabase.class).

    private Connections(Context context){   //Private constructor

        //Setting up our database
        database = Room.databaseBuilder(context, AppDatabase.class, "db_sportmanager")
                .allowMainThreadQueries()
                .build();   //Builds the database
    }

    public static Connections getInstance(Context context){

        synchronized (Connections.class){   //This connection clause can be accessed from one thread
            // at a time
            //So we will be sure that we will not have many threads
            //trying to change the same things at the database at
            // the same time

            if (instance == null){          //Testing if it is the first time running
                instance = new Connections(context);    //We create a new Connections object which will
                //have the database field (Calls the private constructor)
            }
            return instance;    //So in the end if instance=null it creates the database, if it is not
            //null then it returns the database that was made before
        }
    }

    public AppDatabase getDatabase(){
        return database;
    }
}