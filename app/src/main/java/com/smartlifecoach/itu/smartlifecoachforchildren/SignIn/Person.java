package com.smartlifecoach.itu.smartlifecoachforchildren.SignIn;

/**
 * Created by Gozde Kaval on 10/27/2015.
 */
public class Person {

    private String name;
    private String surname;

    public Person(){
        super();
    };

    public Person(String n,String un)
    {
        name = n;
        surname = un;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
