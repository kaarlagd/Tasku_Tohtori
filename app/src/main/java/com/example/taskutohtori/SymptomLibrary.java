package com.example.taskutohtori;

import java.util.ArrayList;

public class SymptomLibrary {
    private static final SymptomLibrary ourInstance = new SymptomLibrary();

    public static SymptomLibrary getInstance() {
        return ourInstance;
    }

    private ArrayList<Symptom> listOfAllSymptoms;
    private ArrayList<String> listOfAllSymptomNames;

    private SymptomLibrary() {
        listOfAllSymptomNames = new ArrayList<>();
        listOfAllSymptoms = new ArrayList<>();
    }

    public ArrayList<Symptom> getListOfAllSymptoms() {
        return listOfAllSymptoms;
    }

    public ArrayList<String> getListOfAllSymptomNames() {
        return  listOfAllSymptomNames;
    }

    public Symptom addSymptom(String newSymptomName) {
        listOfAllSymptomNames.add(newSymptomName);
        listOfAllSymptoms.add(new Symptom(newSymptomName));
        return listOfAllSymptoms.get(listOfAllSymptoms.size()-1);
    }

    public int getSize() {
        return listOfAllSymptomNames.size();
    }
}
