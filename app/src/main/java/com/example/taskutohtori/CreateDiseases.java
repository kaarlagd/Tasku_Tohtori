package com.example.taskutohtori;

public class CreateDiseases {
    private static final CreateDiseases ourInstance = new CreateDiseases();

    public static CreateDiseases getInstance() {
        return ourInstance;
    }

    private CreateDiseases() {
        Disease flunssa = new Disease("flunssa");
        flunssa.addMainSymptom("nuha");
        flunssa.addMainSymptom("kuume");
        flunssa.addRareSymptom("lihaskipu");
        flunssa.addRareSymptom("yskä");
        flunssa.addRareSymptom("tukkoisuus");

        Disease vatsatauti = new Disease("vatsatauti");
        vatsatauti.addMainSymptom("lievä mahakipu");
        vatsatauti.addMainSymptom("ripuli");
        vatsatauti.addRareSymptom("Oksentelu");
        vatsatauti.addRareSymptom("lievä kuume");
    }
}
