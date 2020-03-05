package com.example.taskutohtori;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Symptoms")
public class Symptom {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;

    @Ignore
    public Symptom(String name) {
        this.name = name;
    }

    public Symptom(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
