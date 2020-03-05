package com.example.taskutohtori;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiseaseDao {

    @Insert
    void insertDisease(Disease... diseases);

    @Query("SELECT * FROM Diseases")
    List<Disease> getAllDiseases();

    @Query("SELECT Diseases.id FROM Diseases WHERE Diseases.name = :name")
    int getDiseaseIdWithName(String name);
}
