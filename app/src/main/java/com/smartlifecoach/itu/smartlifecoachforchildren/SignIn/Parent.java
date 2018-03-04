package com.smartlifecoach.itu.smartlifecoachforchildren.SignIn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gozde Kaval on 10/27/2015.
 */
public class Parent extends Person {

    private String username;
    private String password;
    private String diyetisyenEmail;
    private String diyetisyenPhoneNo;

    private List<Child> childList;

    public Parent(String n, String un) {
        super(n, un);
    }

    public Parent(String n, String un, String user, String pass,String diyEmail,String diyPhoneNo) {
        super(n, un);
        username = user;
        password = pass;
        diyetisyenEmail = diyEmail;
        childList = new ArrayList<Child>();
        diyetisyenPhoneNo = diyPhoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiyetisyenEmail() {
        return diyetisyenEmail;
    }

    public void setDiyetisyenEmail(String diyetisyenEmail) {
        this.diyetisyenEmail = diyetisyenEmail;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public String getDiyetisyenPhoneNo() {
        return diyetisyenPhoneNo;
    }

    public void setDiyetisyenPhoneNo(String diyetisyenPhoneNo) {
        this.diyetisyenPhoneNo = diyetisyenPhoneNo;
    }

    public void addChild(Child child)
    {
        childList.add(child);
    }

}
