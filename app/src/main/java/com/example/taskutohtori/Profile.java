package com.example.taskutohtori;

public class Profile {
    private String name;
    private int age;
    private boolean male;

    public Profile(String name, int age, boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public int getAge() {
        return this.age;
    }

    public boolean isMale() {
        return this.male;
    }

    @Override
    public String toString() {
        return this.name;
    }
}