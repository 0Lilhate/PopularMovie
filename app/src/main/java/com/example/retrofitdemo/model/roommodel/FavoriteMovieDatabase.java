package com.example.retrofitdemo.model.roommodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.retrofitdemo.model.Result;

@Database(entities = Result.class, version = 3, exportSchema = false)
public abstract class FavoriteMovieDatabase extends RoomDatabase {

    private static FavoriteMovieDatabase instance;

    public abstract ResultDao getResultDao();

    public static FavoriteMovieDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FavoriteMovieDatabase.class,
                    "FavoriteMovieDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }


        return instance;
    }
}
