package com.example.cinemadatabase.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FilmEntity {
    @PrimaryKey(autoGenerate = true)
    public int filmID;

    @ColumnInfo(name="filmName")
    public String filmName;

    @ColumnInfo(name="duration")
    public String duration;
}
