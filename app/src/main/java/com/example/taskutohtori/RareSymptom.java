package com.example.taskutohtori;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RareSymptoms")
public class RareSymptom {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;

    public RareSymptom(String name) {
        this.name = name;
    }
}
