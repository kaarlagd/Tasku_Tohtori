package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;


public class PlayActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    Button yesButton;
    Button noButton;
    Button unsureButton;
    TextView question;
    ImageManager thisImageManager;

    ArrayList<Disease> listOfAllDiseases;
    ArrayList<Symptom> positiveSymptoms;
    ArrayList<Symptom> askedSymptoms;
    Symptom currentSymptom;
    ArrayList<Symptom> finalSymptoms;
    Boolean allMainQuestionsAsked;
    Disease result;
    boolean declareDisease;
    DatabaseT database;
    HashMap<Disease, Float> powerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        database = Room.databaseBuilder(this, DatabaseT.class, "Database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        createDiseases();

        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        unsureButton = findViewById(R.id.unsureButton);
        question = findViewById(R.id.question);
        thisImageManager = new ImageManager();
        listOfAllDiseases = (ArrayList<Disease>) database.getDiseaseDao().getAllDiseases();
        positiveSymptoms = new ArrayList<>();
        askedSymptoms = new ArrayList<>();
        finalSymptoms = new ArrayList<>();
        allMainQuestionsAsked = false;
        declareDisease = false;
        powerMap = new HashMap<>();
        createPower();
        newQuestion();
        updateUI();
    }
    public void onYesButtonClick(View v) {
        increaseDiseasePower(currentSymptom);
        newQuestion();
        updateUI();
    }

    public void onNoButtonClick(View v) {
        if(!allMainQuestionsAsked) {
            removeDiseases(currentSymptom);
        }
        newQuestion();
        updateUI();
    }

    public void onUnsureButton(View v) {
        updateUI();
    }

    public void newQuestion() {
        
        if(!allMainQuestionsAsked) {
            currentSymptom = new Symptom(newMainQuestion().id, newMainQuestion().name);
        }
        else {
            currentSymptom = newRareQuestion();
        }
        if (currentSymptom.name.equals("Stop")) {
            calculateResult();
            declareDisease = true;
        }
        if (currentSymptom.name.equals("Cured")) {
            declareDisease = true;
            result = new Disease("Cured", 0, 0);
        }
    }

    public Symptom newMainQuestion() {

        float maxPower = 0;
        Disease nextDisease = null;

        for(int i = 0; i < listOfAllDiseases.size(); i++) {
            float thisPower = powerMap.get(listOfAllDiseases.get(i));
            if (thisPower >= maxPower && thisPower != 1) {
                nextDisease = listOfAllDiseases.get(i);
                maxPower = thisPower;
            }
        }
        if(nextDisease != null) {
            for(int i = 0; i < database.getJoinerDao().getMainSymptomsWithDiseaseId(nextDisease.id).size(); i++) {
                if (!positiveSymptoms.contains(new Symptom(database.getJoinerDao().getMainSymptomsWithDiseaseId(nextDisease.id).get(i).id,
                        database.getJoinerDao().getMainSymptomsWithDiseaseId(nextDisease.id).get(i).name))) {
                    return new Symptom(database.getJoinerDao().getMainSymptomsWithDiseaseId(nextDisease.id).get(i).id,
                            database.getJoinerDao().getMainSymptomsWithDiseaseId(nextDisease.id).get(i).name);
                }
            }
        }
        if(listOfAllDiseases.isEmpty()) {
            return new Symptom("Cured");
        }
        createFinalMainSymptomList();
        allMainQuestionsAsked = true;
        return (newRareQuestion());
    }


    public Symptom newRareQuestion() {
        if(!finalSymptoms.isEmpty()) {
            currentSymptom = finalSymptoms.get(0);
            finalSymptoms.remove(finalSymptoms.get(0));
            return currentSymptom;
        }
        return new Symptom("Stop");
    }

    //to be called after each button press(kyllä, ei, en osaa sanoa)
    private void updateUI() {
        ImageView doctorImage = findViewById(R.id.doctorImage);
        doctorImage.setImageResource(thisImageManager.updateImage());

        if(!declareDisease) {
            question.setText("Kuuluuko oireisiisi " + currentSymptom.name + "?");
        }
        else {
            Intent intent = new Intent(this,ResultScreen.class);
            intent.putExtra(EXTRA_MESSAGE, result.name);
            startActivity(intent);
            finish();
        }
    }

    //to be called after user answers no to the question
    private void removeDiseases(Symptom currentSymptom) {
        askedSymptoms.add(currentSymptom);
        for (int i = 0; i < database.getJoinerDao().getDiseasesWithSymptomId(currentSymptom.id).size(); i++) {
            Disease thisDisease = database.getJoinerDao().getDiseasesWithSymptomId(currentSymptom.id).get(i);
            if(database.getJoinerDao().getMainSymptomsWithDiseaseId(thisDisease.id).contains(new MainSymptom(currentSymptom.name)))
            {
                listOfAllDiseases.remove(thisDisease);
            }

        }
    }

    //to Be called after user answers yes to the question
    private void increaseDiseasePower(Symptom currentSymptom) {
        positiveSymptoms.add(currentSymptom);
        askedSymptoms.add(currentSymptom);
        for (int i = 0; i < database.getJoinerDao().getDiseasesWithSymptomId(currentSymptom.id).size(); i++) {
            Disease thisDisease = database.getJoinerDao().getDiseasesWithSymptomId(currentSymptom.id).get(i);
            updatePower(thisDisease);
        }
    }

    public void createPower() {

        for(int i = 0; i < listOfAllDiseases.size(); i++) {
            Log.d("TEST124", "Diseases by name: " + listOfAllDiseases.get(i).name);
            powerMap.put(listOfAllDiseases.get(i), (float) 0.0);
        }
    }

    public void updatePower(Disease thisDisease) {

        int containedSymptoms = 0;
        for (int i= 0; i < database.getJoinerDao().getMainSymptomsWithDiseaseId(thisDisease.id).size();i++) {
            if (askedSymptoms.contains(new Symptom(database.getJoinerDao().getMainSymptomsWithDiseaseId(thisDisease.id).get(i).id,
                    database.getJoinerDao().getMainSymptomsWithDiseaseId(thisDisease.id).get(i).name))) {
                containedSymptoms++;
            }
        }
        powerMap.put(thisDisease, (float) containedSymptoms/database.getJoinerDao().getMainSymptomsWithDiseaseId(thisDisease.id).size());
    }

    public void updateFinalPower(Disease askedDisease) {

        int containedSymptoms = 0;

        ArrayList<Symptom> allSymptoms = (ArrayList<Symptom>) database.getJoinerDao().getSymptomsWithDiseaseId(askedDisease.id);

        for (int i = 0; i < askedSymptoms.size(); i++) {
            if (allSymptoms.contains(askedSymptoms.get(i))) {
                containedSymptoms++;
            } else {
                containedSymptoms--;
            }
        }
        powerMap.put(askedDisease, (float) containedSymptoms / allSymptoms.size());
    }

    private void createFinalMainSymptomList() {
        for(int i = 0; i<listOfAllDiseases.size(); i++) {
            for(int j = 0; j < database.getJoinerDao().getSymptomsWithDiseaseId(listOfAllDiseases.get(i).id).size(); j++) {
                if(!askedSymptoms.contains(new Symptom(database.getJoinerDao().getSymptomsWithDiseaseId(listOfAllDiseases.get(i).id).get(j).id,
                        database.getJoinerDao().getSymptomsWithDiseaseId(listOfAllDiseases.get(i).id).get(j).name))) {
                    finalSymptoms.add(new Symptom(database.getJoinerDao().getSymptomsWithDiseaseId(listOfAllDiseases.get(i).id).get(j).id,
                            database.getJoinerDao().getSymptomsWithDiseaseId(listOfAllDiseases.get(i).id).get(j).name));
                }
            }
        }
    }

    private void calculateResult() {
        float bestResult = -9001;
        for (int i= 0; i < listOfAllDiseases.size(); i++) {
            updateFinalPower(listOfAllDiseases.get(i));
            if(powerMap.get(listOfAllDiseases.get(i)) >= bestResult) {
                result = listOfAllDiseases.get(i);
                bestResult = powerMap.get(listOfAllDiseases.get(i));
            }
        }
    }

    private void createDiseases() {

        database.clearAllTables();

        database.getDiseaseDao().insertDisease(new Disease("Flunssa", 0, 0));

        database.getMainSymptomDao().insertMainSymptom(new MainSymptom("Kuume"));
        database.getMainSymptomDao().insertMainSymptom(new MainSymptom("Nuha"));

        database.getRareSymptomDao().insertRareSymptom(new RareSymptom("Lihaskipu"));
        database.getRareSymptomDao().insertRareSymptom(new RareSymptom("Yskä"));
        database.getRareSymptomDao().insertRareSymptom(new RareSymptom("Tukkoisuus"));
        database.getRareSymptomDao().insertRareSymptom(new RareSymptom("Kurkkukipu"));

        database.getSymptomDao().insertSymptom(new Symptom("Kuume"));
        database.getSymptomDao().insertSymptom(new Symptom("Nuha"));
        database.getSymptomDao().insertSymptom(new Symptom("Lihaskipu"));
        database.getSymptomDao().insertSymptom(new Symptom("Yskä"));
        database.getSymptomDao().insertSymptom(new Symptom("Tukkoisuus"));
        database.getSymptomDao().insertSymptom(new Symptom("Kurkkukipu"));

        database.getJoinerDao().insertJoinerValue(new Joiner
                (database.getDiseaseDao().getDiseaseIdWithName("Flunssa"), 
                        database.getSymptomDao().getSymptomIdWithName("Kuume"), 
                        database.getMainSymptomDao().getMainSymptomIdWithName("Kuume"), null));

        database.getJoinerDao().insertJoinerValue(new Joiner
                (database.getDiseaseDao().getDiseaseIdWithName("Flunssa"),
                        database.getSymptomDao().getSymptomIdWithName("Nuha"),
                        database.getMainSymptomDao().getMainSymptomIdWithName("Nuha"), null));

        database.getJoinerDao().insertJoinerValue(new Joiner
                (database.getDiseaseDao().getDiseaseIdWithName("Flunssa"),
                        database.getSymptomDao().getSymptomIdWithName("Lihaskipu"), null,
                        database.getRareSymptomDao().getRareSymptomIdWithName("Lihaskipu")));

        database.getJoinerDao().insertJoinerValue(new Joiner
                (database.getDiseaseDao().getDiseaseIdWithName("Flunssa"),
                        database.getSymptomDao().getSymptomIdWithName("Yskä"), null,
                        database.getRareSymptomDao().getRareSymptomIdWithName("Yskä")));

        database.getJoinerDao().insertJoinerValue(new Joiner
                (database.getDiseaseDao().getDiseaseIdWithName("Flunssa"),
                        database.getSymptomDao().getSymptomIdWithName("Tukkoisuus"), null,
                        database.getRareSymptomDao().getRareSymptomIdWithName("Tukkoisuus")));

        database.getJoinerDao().insertJoinerValue(new Joiner
                (database.getDiseaseDao().getDiseaseIdWithName("Flunssa"),
                        database.getSymptomDao().getSymptomIdWithName("Kurkkukipu"), null,
                        database.getRareSymptomDao().getRareSymptomIdWithName("Kurkkukipu")));
    }
}
