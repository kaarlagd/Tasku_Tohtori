package com.example.taskutohtori;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "RareSymptoms")
public class RareSymptom {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;

    @Ignore
    public RareSymptom(String name) {
        this.name = name;
    }

    public RareSymptom(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
