package com.example.taskutohtori;

import java.util.ArrayList;

public class ProfileSingleton {
    private static final ProfileSingleton ourInstance = new ProfileSingleton();
    private ArrayList<Profile> profiles;
    private Profile currentProfile;

    public static ProfileSingleton getInstance() {
        return ourInstance;
    }

    private ProfileSingleton() {
        profiles = new ArrayList<>();
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public Profile getProfile(int i) {
        return profiles.get(i);
    }

    public void putProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    public void setCurrentProfile(int i) {
        this.currentProfile = getProfile(i);
    }

    public Profile getCurrentProfile() {
        return this.currentProfile;
    }
}
