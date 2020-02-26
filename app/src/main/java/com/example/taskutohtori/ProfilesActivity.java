package com.example.taskutohtori;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProfilesActivity extends AppCompatActivity {

    ListView profileList;
    //public final static String EXTRA_PROFILE = "com.example.mobiledoctor.PROFILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        profileList = findViewById(R.id.profileList);
        profileList.setAdapter(new ArrayAdapter<Profile>(this, android.R.layout.simple_list_item_1, ProfileSingleton.getInstance().getProfiles()));
        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("tägi", "onItemClick(" + l + ")");
                //Profile profile = ProfileSingleton.getInstance().getProfile(Math.toIntExact(l));
                //Intent nextActivity = new Intent(ProfilesActivity.this, MainActivity.class);
                //nextActivity.putExtra(EXTRA_PROFILE, Math.toIntExact(l));
                //startActivity(nextActivity);
                ProfileSingleton.getInstance().setCurrentProfile(Math.toIntExact(l));
                finish();
            }
        });
    }
}
