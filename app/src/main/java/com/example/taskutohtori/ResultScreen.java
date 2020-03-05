package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {
    TextView resultBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        Intent intent = getIntent();
        String message = intent.getStringExtra(PlayActivity.EXTRA_MESSAGE);
        resultBox = findViewById(R.id.resultBox);
        printresult(message);

    }
    public void printresult(String message) {
        if(message.equals("cured")) {
            resultBox.setText("En osaa sanoa mikä sinua vaivaa.");
        }
        else {
            resultBox.setText("Sinulla saattaa olla " + message);
        }
    }
}
