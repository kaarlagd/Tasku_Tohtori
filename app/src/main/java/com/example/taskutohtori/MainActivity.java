package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button playButton;
    Button profilesButton;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);
        profilesButton = findViewById(R.id.profilesButton);
        exitButton = findViewById(R.id.exitButton);
    }
    public void onPlayButtonClick(View v) {
        startActivity(new Intent(this,PlayActivity.class));
    }
    public void onProfilesButtonClick(View v) {
        startActivity(new Intent(this,Profiles.class));
    }
    public void onExitButtonClick(View V) {
        finish();
        System.exit(0);
    }
}
