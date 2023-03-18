package com.example.cinemadatabase.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {CinemaEntity.class,FilmEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CinemaDao cinemaDao();
    public abstract FilmDao filmDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Cinema1")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
