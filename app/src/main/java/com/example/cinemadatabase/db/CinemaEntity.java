package com.example.cinemadatabase.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CinemaEntity {

    @PrimaryKey(autoGenerate = true)
    public int cinemaId;

    @ColumnInfo(name="address")
    public String address;

}
