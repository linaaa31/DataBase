package com.example.cinemadatabase.db;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FilmDao {
    @Query("Select * From filmentity")
    List<FilmEntity> getAllFilms();

    @Insert
    void insertFilm(FilmEntity... filmEntity);

    @Delete
    void delete(FilmEntity filmEntity);
}
