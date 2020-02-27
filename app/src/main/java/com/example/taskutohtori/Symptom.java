package com.example.taskutohtori;

import android.util.Log;

import java.util.ArrayList;

public class Symptom {
    private String name;
    private ArrayList<Disease> diseases;

    public Symptom(String name) {
        this.name = name;
        this.diseases = new ArrayList<>();
    }

    public void putDisease(Disease disease) {
        Log.d("TEST","puttig disease into "+this.getName());
        diseases.add(disease);
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }
    public String getName() {
        return this.name;
    }

    public String printSymptom() {
        return this.getName()+" size of its diseases list is "+this.getDiseases().size();
    }
}