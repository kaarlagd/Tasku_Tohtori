package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

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
        if (!firstStartCheck()) {
            SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putBoolean("check", true);
            prefEditor.commit();
            startActivity(firstProfileActivity);
        } else {
            prefGet();
            ProfileSingleton.getInstance().setCurrentProfile(lastSessionProfile());
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
        Log.d("tägi", "Method firstStartCheck called");
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        return prefs.getBoolean("check", false);
    }

    //Method to get the integer of the profile that was used in the last session
    public int lastSessionProfile() {
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        return prefs.getInt("LastSessionProfile", 0);
    }

    //Method to get the current profile
    public Profile getCurrentProfile() {
        return ProfileSingleton.getInstance().getCurrentProfile();
    }

    //Saving profile data to SharedPreferences from ProfileSingleton
    public void prefPut() {
        Log.d("tägi", "Method prefPut called");
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();

        ArrayList<Profile> profiles;
        profiles = ProfileSingleton.getInstance().getProfiles();

        Gson gson = new Gson();
        String json = gson.toJson(profiles);
        prefEditor.putString("profiles", json);
        prefEditor.commit();
    }

    //Importing profile data from SharedPreferences to ProfileSingleton
    public void prefGet() {
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("profiles", "");
        ProfileSingleton.getInstance().putProfiles(gson.fromJson(json, ArrayList.class));
    }

    //Saving profiles to SharedPreferences when app is closed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        prefPut();
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();
        int i = ProfileSingleton.getInstance().getProfiles().indexOf(getCurrentProfile());
        prefEditor.putInt("LastSessionProfile", i);
        prefEditor.commit();
    }
}
