package com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel;

import java.util.List;

/**
 * Created by gkaval on 10/3/15.
 */
public interface FoodInterface {
    void addFood(FoodItem food);
    List<FoodItem> getAllFoods();
    FoodItem getFood(String foodname);
    void deleteFood(FoodItem foodItem);

}
