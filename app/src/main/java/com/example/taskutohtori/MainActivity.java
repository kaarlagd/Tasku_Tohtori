package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

        //Starts profile creation activity if the app has not been opened before
        //Else imports previously saved profile data from SharedPreferences to ProfileSingleton
        Intent firstProfileActivity = new Intent(MainActivity.this, CreateProfileActivity.class);
        if (firstStartCheck()) {
            SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putBoolean("check", false);
            prefEditor.commit();
            startActivity(firstProfileActivity);
        }
    }
    public void onPlayButtonClick(View v) {
        startActivity(new Intent(this,PlayActivity.class));
    }

    public void onProfilesButtonClick(View v) {

        startActivity(new Intent(this,ProfilesActivity.class));
    }
    public void onExitButtonClick(View V) {

        finish();
        System.exit(0);
    }

    //Checking if the app has been launched before from a boolean value saved to SharedPreferences
    public boolean firstStartCheck() {

        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        return prefs.getBoolean("check", true);
    }

    //Method to get the integer of the profile that was used in the last session
    public int currentProfile() {

        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        return prefs.getInt("CurrentProfile", 0);
    }

    //Importing profile data from SharedPreferences to ProfileSingleton
    public int prefGet() {

        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        return prefs.getInt("CurrentProfile", 0);
    }
}
