package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class CreateProfileActivity extends AppCompatActivity {
    String name;
    int age;
    boolean male;
    EditText nameInput;
    EditText ageInput;
    RadioButton maleButton;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Log.d("tägi", "Started CreateProfileActivity");

        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);
        maleButton = findViewById(R.id.maleButton);
        confirmButton = findViewById(R.id.confirmButton);
    }

    public void createUser(View view) {
        name = nameInput.getText().toString();
        age = Integer.valueOf(ageInput.getText().toString());
        male = maleButton.isSelected();

        Profile profile = new Profile(name, age, male);
        ProfileSingleton.getInstance().getProfiles().add(profile);

        finish();
    }
}
