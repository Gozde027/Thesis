package com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenDatabesePack;

import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodItem;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Child;

import java.util.List;

/**
 * Created by Gozde Kaval on 11/27/2015.
 */
public interface ChildrenDBInterface {
    void addChild(Child child);
    List<Child> getAllChild();
    Child getChild(String childName);
    void deleteChild(Child childItem);
}
