package com.example.taskutohtori;

import java.util.HashMap;

public class SymptomList {
    private static final SymptomList ourInstance = new SymptomList();
    private HashMap<Symptom, Integer> symptomList;
    private int initialValue;

    public static SymptomList getInstance() {
        return ourInstance;
    }

    private SymptomList() {
        symptomList = new HashMap<>();
        symptomList.put(new Symptom("Kuume"),initialValue);
        symptomList.put(new Symptom("Nuha"),initialValue);
        symptomList.put(new Symptom("LihasSärky"),initialValue);
        symptomList.put(new Symptom("Kuume"),initialValue);
        symptomList.put(new Symptom("Kuume"),initialValue);
        symptomList.put(new Symptom("Kuume"),initialValue);
        symptomList.put(new Symptom("Kuume"),initialValue);

    }
}
