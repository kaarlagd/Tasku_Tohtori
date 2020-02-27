package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;
    Button unsureButton;
    ImageButton homeButton;
    TextView question;
    ImageManager thisImageManager;

    ArrayList<Disease> listOfAllDiseases;
    ArrayList<Symptom> positiveSymptoms;
    Symptom currentSymptom;
    ArrayList<Symptom> finalSymptoms;
    Boolean allMainQuestionsAsked;
    Disease result;
    boolean decleareDisease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        unsureButton = findViewById(R.id.unsureButton);
        homeButton = findViewById(R.id.homeButton);

        createDiseases();
        question = findViewById(R.id.question);
        thisImageManager = new ImageManager();
        listOfAllDiseases = SymptomLibrary.getInstance().getListOfAllDiseases();
        positiveSymptoms = new ArrayList<>();
        finalSymptoms = new ArrayList<>();
        allMainQuestionsAsked = false;
        decleareDisease = false;
        newQuestion();
        updateUI();


    }
    public void onYesButtonClick(View v) {
        Log.d("TEST","pressed yes buttom");
        increaseDiseasePower(currentSymptom);
        newQuestion();
        updateUI();
    }

    public void onNoButtonClick(View v) {
        Log.d("TEST","pressed no buttom");
        if(!allMainQuestionsAsked) {
            removeDiseases(currentSymptom);
        }
        newQuestion();
        updateUI();
    }

    public void onUnsureButton(View v) {
        updateUI();
    }

    public void onHomeButtonClick(View v) {
        finish();
    }


    public void newQuestion() {
        if(!allMainQuestionsAsked) {
            currentSymptom = newMainQuestion();
        }
        else {
            currentSymptom = newRareQuestion();
        }
        if (currentSymptom.getName().equals("stop")) {
            Log.d("TEST","final result was STOP");
            calculateResult();
            decleareDisease = true;
        }
    }
    public Symptom newMainQuestion() {
        Log.d("TEST","newMainQuestion");
        float maxPower = 0;
        Disease nextDisease = null;
        Log.d("TEST","Size of listOfAllDiseases is = "+SymptomLibrary.getInstance().getListOfAllDiseases().size());
        for(int i = 0; i< SymptomLibrary.getInstance().getListOfAllDiseases().size(); i++) {
            float thisPower = SymptomLibrary.getInstance().getListOfAllDiseases().get(i).getPower();
            Log.d("TEST","this power " +SymptomLibrary.getInstance().getListOfAllDiseases().get(i).printDisease());
            if (thisPower >= maxPower && thisPower != 1) {
                Log.d("TEST","updating maxPower of "+SymptomLibrary.getInstance().getListOfAllDiseases().get(i).printDisease());
                nextDisease = SymptomLibrary.getInstance().getListOfAllDiseases().get(i);
                maxPower = thisPower;
            }
        }
        if (nextDisease != null) {
            Log.d("TEST","nextDisease is not empty");
            for(int i= 0; i < nextDisease.getMainSymptoms().size(); i++) {
                if (!positiveSymptoms.contains(nextDisease.getMainSymptoms().get(i))) {
                    return nextDisease.getMainSymptoms().get(i);
                }
            }
        }
        Log.d("TEST","nextDisease = 0");
        createFinalSymptomList();
        allMainQuestionsAsked = true;
        return newRareQuestion();
    }
    public Symptom newRareQuestion() {
        Log.d("TEST","newRareQuestion");
        if(!finalSymptoms.isEmpty()) {
            currentSymptom = finalSymptoms.get(0);
            finalSymptoms.remove(finalSymptoms.get(0));
            return currentSymptom;
        }
        return new Symptom("stop");
    }

    //to be called after each button press(kyllä, ei, en osaa sanoa)
    private void updateUI() {
        ImageView doctorImage = findViewById(R.id.doctorImage);
        doctorImage.setImageResource(thisImageManager.updateImage());

        if(decleareDisease==false) {
            question.setText("Kuuluuko oireisiisi "+currentSymptom.getName()+"?");
        }
        else {
            question.setText("Sinulla saattaa olla "+result.getName());
        }
    }

    //to be called after user answers no to the question
    private void removeDiseases(Symptom currentSymptom) {
        for (int i= 0; i<currentSymptom.getDiseases().size();i++) {
            Disease thisDisease = currentSymptom.getDiseases().get(i);
            listOfAllDiseases.remove(thisDisease);
            Log.d("TEST","Removed "+thisDisease.printDisease());
        }
    }

    //to Be called after user answers yes to the question
    private void increaseDiseasePower(Symptom currentSymptom) {
        positiveSymptoms.add(currentSymptom);
        Log.d("TEST","Size of list of positive symptoms = "+positiveSymptoms.size());
        Log.d("TEST","Size of current symptoms Diseases list = "+currentSymptom.getDiseases().size());
        for (int i = 0; i < currentSymptom.getDiseases().size();i++) {
            Disease thisDisease = currentSymptom.getDiseases().get(i);
            thisDisease.updatePower(positiveSymptoms);
            Log.d("TEST","current positivesymptoms "+positiveSymptoms.size());
            Log.d("TEST","updating this Symptoms Power "+ thisDisease.printDisease());
        }
    }


    private void createFinalSymptomList() {
        Log.d("TEST","Creating final Symptomlist");
        for(int i= 0; i<listOfAllDiseases.size();i++) {
            for(int l=0;l<listOfAllDiseases.get(i).getRareSymptoms().size();l++) {
                if(!positiveSymptoms.contains(listOfAllDiseases.get(i).getRareSymptoms().get(l))) {
                    finalSymptoms.add(listOfAllDiseases.get(i).getRareSymptoms().get(l));
                }
            }
        }
        Log.d("TEST","Size of final symptomlist is = "+finalSymptoms.size());
    }

    private void calculateResult() {
        float bestResult = 0;
        for (int i= 0; i< listOfAllDiseases.size(); i++) {
            listOfAllDiseases.get(i).updateFinalPower(positiveSymptoms);
            Log.d("TEST","Calculating final power");
            if(listOfAllDiseases.get(i).getPower()>=bestResult) {
                result = listOfAllDiseases.get(i);
                Log.d("TEST","result is "+result.getName());
                bestResult = listOfAllDiseases.get(i).getPower();
            }
        }
    }

    private void createDiseases() {
        Disease flunssa = new Disease("flunssa");
        flunssa.addMainSymptom("nuha");
        flunssa.addMainSymptom("kuume");
        flunssa.addRareSymptom("lihaskipu");
        flunssa.addRareSymptom("yskä");
        flunssa.addRareSymptom("tukkoisuus");

        Disease vatsatauti = new Disease("vatsatauti");
        vatsatauti.addMainSymptom("lievä mahakipu");
        vatsatauti.addMainSymptom("ripuli");
        vatsatauti.addRareSymptom( "Oksentelu");
        vatsatauti.addRareSymptom("lievä kuume");
    }

}
