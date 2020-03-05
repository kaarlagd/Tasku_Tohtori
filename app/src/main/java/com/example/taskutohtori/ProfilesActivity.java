package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ProfilesActivity extends AppCompatActivity {

    ListView profileList;
    Button newProfile;
    DatabaseT database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        database = Room.databaseBuilder(this, DatabaseT.class, "Database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        newProfile = findViewById(R.id.newProfileButton);
        profileList = findViewById(R.id.profileList);
        profileList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, database.getProfileDao().getAllProfiles()));

        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = prefs.edit();
                prefEditor.putInt("CurrentProfile", (int) l);
                finish();
            }
        });
    }

    public void newProfile(View view) {

        Intent intent = new Intent(ProfilesActivity.this, CreateProfileActivity.class);
        startActivity(intent);
        finish();
    }
}