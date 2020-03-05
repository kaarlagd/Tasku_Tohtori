package com.example.taskutohtori;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProfileDao {

    @Insert
    void insertProfile(Profile... profiles);

    @Query("SELECT * FROM Profiles")
    List<Profile> getAllProfiles();

    @Query("SELECT * FROM Profiles WHERE Profiles.name LIKE :name AND Profiles.age LIKE :age AND Profiles.male LIKE :male")
    List<Profile> getAllProfilesIDsWithAllAttributes(String name, int age, boolean male);
}
