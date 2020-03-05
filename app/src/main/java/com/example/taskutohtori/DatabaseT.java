package com.example.taskutohtori;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Profile.class, Disease.class, Symptom.class, MainSymptom.class, RareSymptom.class, Joiner.class}, version = 1)
public abstract class DatabaseT extends RoomDatabase {

    public abstract ProfileDao getProfileDao();
    public abstract DiseaseDao getDiseaseDao();
    public abstract SymptomDao getSymptomDao();
    public abstract MainSymptomDao getMainSymptomDao();
    public abstract RareSymptomDao getRareSymptomDao();
    public abstract JoinerDao getJoinerDao();
}
