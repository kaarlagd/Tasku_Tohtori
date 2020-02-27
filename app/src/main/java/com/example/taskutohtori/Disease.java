package com.example.taskutohtori;

import android.util.Log;

import java.util.ArrayList;

public class Disease {
    private String name;
    private float power;
    private ArrayList<Symptom> mainSymptoms;
    private ArrayList<Symptom> rareSymptoms;


    public Disease(String name) {
        this.name = name;
        this.power = 0;
        mainSymptoms = new ArrayList<>();
        rareSymptoms = new ArrayList<>();
        SymptomLibrary.getInstance().addDiseaseToListOfAllDiseases(this);
        Log.d("TEST","new disease added size of List of all diseases is "+SymptomLibrary.getInstance().getListOfAllDiseases().size());


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
            SymptomLibrary.getInstance().addSymptom(symptomName);
        }

        int number = 0;

        for (int i = 0; i< SymptomLibrary.getInstance().getSize(); i++) {
            if (SymptomLibrary.getInstance().getListOfAllSymptomNames().get(i).equals(symptomName)) {
                SymptomLibrary.getInstance().getListOfAllSymptoms().get(i).putDisease(this);
                number = i;
                break;
            }
        }
        Log.d("TEST","list of diseases is = " + SymptomLibrary.getInstance().getListOfAllSymptoms().get(number).printSymptom());
        return SymptomLibrary.getInstance().getListOfAllSymptoms().get(number);


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
    public float getPower() {
        return this.power;
    }
    public void updatePower(ArrayList<Symptom> askedSymptoms) {
        int containedSymptoms = 0;
        for (int i= 0; i < this.mainSymptoms.size();i++) {
            if (askedSymptoms.contains(this.mainSymptoms.get(i))) {
                containedSymptoms++;
            }
        }
        this.power = (float)containedSymptoms/this.mainSymptoms.size();
        Log.d("TEST",""+containedSymptoms);
        Log.d("TEST",""+this.mainSymptoms.size());
        Log.d("TEST","power updated and is now "+this.power);
    }
    public String printDisease() {
        return this.name+", power = "+this.power;
    }
}