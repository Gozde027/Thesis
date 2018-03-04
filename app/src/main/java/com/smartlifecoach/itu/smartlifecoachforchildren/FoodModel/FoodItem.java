package com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel;

/**
 * Created by gkaval on 10/1/15.
 */
public class FoodItem {
    private String foodname;
    private String imagepath;
    private double carbon;
    private double protein;
    private double fat;
    private int foodId;
    private double porsiyon;

    public FoodItem(){
        super();
    }

    public FoodItem(String name, String path, double crb, double pro, double fat,double porsiyon){
        super();
        setFoodname(name);
        setImagepath(path);
        setCarbon(crb);
        setFat(fat);
        setProtein(pro);
        setPorsiyon(porsiyon);

    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }


    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public double getCarbon() {
        return carbon;
    }

    public void setCarbon(double carbon) {
        this.carbon = carbon;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getPorsiyon() {
        return porsiyon;
    }

    public void setPorsiyon(double porsiyon) {
        this.porsiyon = porsiyon;
    }

    public void newValues(double porsiyon)
    {
        carbon = carbon * porsiyon;
        protein = protein * porsiyon;
        fat = fat * porsiyon;
    }
}
