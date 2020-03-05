package com.example.taskutohtori;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainSymptomDao {

    @Insert
    void insertMainSymptom(MainSymptom... mainSymptoms);

    @Query("SELECT * FROM MainSymptoms")
    List<MainSymptom> getAllMainSymptoms();

    @Query("SELECT MainSymptoms.id FROM MainSymptoms WHERE MainSymptoms.name = :name")
    int getMainSymptomIdWithName(String name);
}
