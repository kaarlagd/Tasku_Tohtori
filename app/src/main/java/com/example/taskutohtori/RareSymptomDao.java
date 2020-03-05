package com.example.taskutohtori;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RareSymptomDao {

    @Insert
    void insertRareSymptom(RareSymptom... rareSymptoms);

    @Query("SELECT * FROM RareSymptoms")
    List<RareSymptom> getAllRareSymptoms();

    @Query("SELECT RareSymptoms.id FROM RareSymptoms WHERE RareSymptoms.name = :name")
    int getRareSymptomIdWithName(String name);
}
