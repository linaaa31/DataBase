package com.example.cinemadatabase.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CinemaDao {
    @Query("Select * From cinemaentity")
    List<CinemaEntity> getAllCinemas();

    @Insert
    void insertCinema(CinemaEntity... cinemaEntity);

    @Delete
    void delete(CinemaEntity cinemaEntity);
}
