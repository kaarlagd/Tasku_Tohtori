package com.example.taskutohtori;

import java.util.ArrayList;

public class Disease {
    private String name;
    private int power;
    private ArrayList<Symptom> mainSymptoms;
    private ArrayList<Symptom> rareSymptoms;


    public Disease(String name) {
        this.name = name;
        this.power = 0;
        mainSymptoms = new ArrayList<>();
        rareSymptoms = new ArrayList<>();
        SymptomLibrary.getInstance().getListOfAllDiseases().add(this);

    }
    public void addMainSymptom(String symptomName)  {
        mainSymptoms.add(addSymptom(symptomName));
    }
    public void addRareSymptom(String symptomName) {
        rareSymptoms.add(addSymptom(symptomName));
    }

    private Symptom addSymptom(String symptomName) {
        ///lisää oireen oirekirjastoon jos se ei ole siellä. Jos on,
        // niin lisää taudin oireen omaan listaan.
        // Lisäksi palauttaa lisättävän oireen.///
        if (!SymptomLibrary.getInstance().getListOfAllSymptomNames().contains(symptomName)) {
            return SymptomLibrary.getInstance().addSymptom(symptomName);
        }
        else {
            int number = 0;
            for (int i = 0; i< SymptomLibrary.getInstance().getSize(); i++) {
                if (SymptomLibrary.getInstance().getListOfAllSymptomNames().get(i).equals(symptomName)) {
                    SymptomLibrary.getInstance().getListOfAllSymptoms().get(i).putDisease(this);
                    number = i;
                    break;
                }
            }
            return SymptomLibrary.getInstance().getListOfAllSymptoms().get(number);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Symptom> getMainSymptoms() {
        return mainSymptoms;
    }

    public ArrayList<Symptom> getRareSymptoms() {
        return rareSymptoms;
    }
    public int getPower() {
        return this.power;
    }
}