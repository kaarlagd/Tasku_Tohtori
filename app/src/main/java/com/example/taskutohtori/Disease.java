package com.example.taskutohtori;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Diseases")
public class Disease {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    int ageBias;
    float sexBias;

    public Disease(String name, int ageBias, float sexBias) {
        this.name = name;
        this.ageBias = ageBias;
        this.sexBias = sexBias;
    }
}
