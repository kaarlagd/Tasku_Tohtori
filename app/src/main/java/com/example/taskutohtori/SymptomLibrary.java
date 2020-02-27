package com.example.taskutohtori;

import android.util.Log;

import java.util.ArrayList;

public class SymptomLibrary {
    private static final SymptomLibrary ourInstance = new SymptomLibrary();

    public static SymptomLibrary getInstance() {
        return ourInstance;
    }

    private ArrayList<Symptom> listOfAllSymptoms;
    private ArrayList<String> listOfAllSymptomNames;
    private ArrayList<Disease> listOfAllDiseases;

    public SymptomLibrary() {
        listOfAllSymptomNames = new ArrayList<>();
        listOfAllSymptoms = new ArrayList<>();
        listOfAllDiseases = new ArrayList<>();
    }

    public ArrayList<Symptom> getListOfAllSymptoms() {
        return listOfAllSymptoms;
    }

    public ArrayList<String> getListOfAllSymptomNames() {
        return  listOfAllSymptomNames;
    }

    public ArrayList<Disease> getListOfAllDiseases() {
        return listOfAllDiseases;
    }

    public Symptom addSymptom(String newSymptomName) {
        listOfAllSymptomNames.add(newSymptomName);
        listOfAllSymptoms.add(new Symptom(newSymptomName));
        Log.d("TEST","Size of list of all Symptoms is = "+SymptomLibrary.getInstance().getSize());
        return listOfAllSymptoms.get(listOfAllSymptoms.size()-1);
    }
    public void addDiseaseToListOfAllDiseases(Disease disease) {
        listOfAllDiseases.add(disease);
    }

    public int getSize() {
        return listOfAllSymptomNames.size();
    }
}
