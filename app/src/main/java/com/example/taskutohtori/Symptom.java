package com.example.taskutohtori;

import java.util.ArrayList;

public class Symptom {
    private String name;
    private ArrayList<Disease> diseases;

    public Symptom(String name) {
        this.name = name;
        this.diseases = new ArrayList<>();
    }

    public void putDisease(Disease disease) {
        diseases.add(disease);
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }
    public String getName() {
        return this.name;
    }
}