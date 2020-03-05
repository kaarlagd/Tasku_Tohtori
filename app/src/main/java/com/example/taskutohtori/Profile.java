package com.example.taskutohtori;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Profiles")
public class Profile {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int age;
    public boolean male;

    public Profile(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }
}
