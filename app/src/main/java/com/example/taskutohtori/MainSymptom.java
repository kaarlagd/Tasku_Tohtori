package com.example.taskutohtori;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "MainSymptoms")
public class MainSymptom {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;

    @Ignore
    public MainSymptom(String name) {
        this.name = name;
    }

    public MainSymptom(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
