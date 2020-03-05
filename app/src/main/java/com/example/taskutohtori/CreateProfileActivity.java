package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class CreateProfileActivity extends AppCompatActivity {
    String name;
    int age;
    boolean male;
    EditText nameInput;
    EditText ageInput;
    RadioButton maleButton;
    Button confirmButton;
    DatabaseT database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        maleButton = findViewById(R.id.maleButton);
        confirmButton = findViewById(R.id.confirmButton);
        database = Room.databaseBuilder(this, DatabaseT.class, "Database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public void createUser(View view) {
        name = nameInput.getText().toString();
        age = Integer.parseInt(ageInput.getText().toString());
        male = maleButton.isChecked();

        database.getProfileDao().insertProfile(new Profile(name, age, male));
        SharedPreferences prefs = getSharedPreferences("Prefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();
        ArrayList<Profile> profiles = (ArrayList<Profile>) database.getProfileDao().getAllProfilesIDsWithAllAttributes(name, age, male);
        prefEditor.putInt("CurrentProfile", profiles.get(0).id);
        finish();
    }
}