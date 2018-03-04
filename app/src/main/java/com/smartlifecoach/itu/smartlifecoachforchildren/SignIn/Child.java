package com.smartlifecoach.itu.smartlifecoachforchildren.SignIn;

/**
 * Created by Gozde Kaval on 10/27/2015.
 */
public class Child extends Person{

    private int age;
    private int gender;
    private int kilo;
    private String parentUsername;
    private int ChildID;

    public Child(){super();};

    public Child(String n, String un) {
        super(n, un);
    }

    public Child(String n, String un, int a, int g, int ki, String parent) {
        super(n, un);
        age = a;
        gender = g;
        kilo = ki;
        parentUsername = parent;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getKilo() {
        return kilo;
    }

    public void setKilo(int kilo) {
        this.kilo = kilo;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public int getChildID() {
        return ChildID;
    }

    public void setChildID(int childID) {
        ChildID = childID;
    }
}
