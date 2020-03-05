package com.example.taskutohtori;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "Joiner",
        foreignKeys = {@ForeignKey(entity = Disease.class, parentColumns = "id", childColumns = "diseaseId", onDelete = CASCADE, onUpdate = CASCADE),
                @ForeignKey(entity = Symptom.class, parentColumns = "id", childColumns = "symptomId", onDelete = CASCADE, onUpdate = CASCADE),
                @ForeignKey(entity = MainSymptom.class, parentColumns = "id", childColumns = "mainSymptomId", onDelete = CASCADE, onUpdate = CASCADE),
                @ForeignKey(entity = RareSymptom.class, parentColumns = "id", childColumns = "rareSymptomId", onDelete = CASCADE, onUpdate = CASCADE)})
public class Joiner {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public Integer diseaseId;
    @NonNull
    public Integer symptomId;
    @Nullable
    public Integer mainSymptomId;
    @Nullable
    public Integer rareSymptomId;

    public Joiner(Integer diseaseId, Integer symptomId, Integer mainSymptomId, Integer rareSymptomId) {
        this.diseaseId = diseaseId;
        this.symptomId = symptomId;
        this.mainSymptomId = mainSymptomId;
        this.rareSymptomId = rareSymptomId;
    }
}
