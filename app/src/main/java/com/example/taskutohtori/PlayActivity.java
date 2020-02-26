package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        unsureButton = findViewById(R.id.unsureButton);
        homeButton = findViewById(R.id.homeButton);

        question = findViewById(R.id.question);
        thisImageManager = new ImageManager();
        listOfAllDiseases = SymptomLibrary.getInstance().getListOfAllDiseases();

    }
    public void onYesButtonClick(View v) {
        updateImage();
        newQuestion();
    }

    public void onNoButtonClick(View v) {
        updateImage();
        newQuestion();
    }

    public void onUnsureButton(View v) {
        updateImage();
    }

    public void onHomeButtonclick(View v) {
        finish();
    }

    public void newQuestion() {

    }

    private void updateImage() {
        ImageView doctorImage = findViewById(R.id.doctorImage);
        doctorImage.setImageResource(thisImageManager.updateImage());
    }

    private void removeDiseases(Symptom currentSymptom) {
        for (int i= 0; i<currentSymptom.getDiseases().size();i++) {
            Disease thisDisease = currentSymptom.getDiseases().get(i);
            listOfAllDiseases.remove(thisDisease);
        }
    }

}
