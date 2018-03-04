package com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gkaval on 10/3/15.
 */
public class FoodDatabase extends SQLiteOpenHelper implements FoodInterface {

    // database version
    private static final int database_VERSION = 3;
    private static final String TAG = "FoodDatabase";
    // database name
    private static final String database_NAME = "FoodDB";

    public FoodDatabase(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG,"onCreate");
        String CREATE_FOOD_TABLE = "CREATE TABLE " + FoodDBEntry.FeedEntry.TABLE_NAME + " ( "
                                    + FoodDBEntry.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                    + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_NAME + " TEXT, "
                                    + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_IMAGE_PATH + " TEXT, "
                                    + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_CARBONHYDRATE + " DOUBLE, "
                                    + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_PROTEIN + " DOUBLE, "
                                    + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_FAT + " DOUBLE, "
                                    + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_PORSIYON + " DOUBLE )";
        db.execSQL(CREATE_FOOD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FoodDBEntry.FeedEntry.TABLE_NAME);
        this.onCreate(db);


    }

    @Override
    public void addFood(FoodItem food) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_NAME, food.getFoodname());
        values.put(FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_IMAGE_PATH, food.getImagepath());
        values.put(FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_CARBONHYDRATE, food.getCarbon());
        values.put(FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_PROTEIN, food.getProtein());
        values.put(FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_FAT, food.getFat());
        values.put(FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_PORSIYON, food.getPorsiyon());

        db.insert(FoodDBEntry.FeedEntry.TABLE_NAME, null, values);
        // close database transaction
        db.close();
    }

    @Override
    public List<FoodItem> getAllFoods() {

        List<FoodItem> foodList = new ArrayList<FoodItem>();
        // select book query
        String query = "SELECT * FROM " + FoodDBEntry.FeedEntry.TABLE_NAME;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        FoodItem food = null;
        if (cursor.moveToFirst()) {
            do {

                food = new FoodItem();
                food.setFoodId(Integer.parseInt(cursor.getString(0)));
                Log.i(TAG, cursor.getString(0));

                food.setFoodname(cursor.getString(1));
                food.setImagepath(cursor.getString(2));
                food.setCarbon(Double.parseDouble(cursor.getString(3)));
                food.setProtein(Double.parseDouble(cursor.getString(4)));
                food.setFat(Double.parseDouble(cursor.getString(5)));
                food.setPorsiyon(Double.parseDouble(cursor.getString(6)));

                foodList.add(food);
            } while (cursor.moveToNext());
        }
        return foodList;

    }

    @Override
    public FoodItem getFood(String foodname) {

        FoodItem food = new FoodItem();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + FoodDBEntry.FeedEntry.TABLE_NAME
                                + " WHERE  " + FoodDBEntry.FeedEntry.COLUMN_NAME_FOOD_NAME + " = " + foodname + "", null );
        if(res.getCount() == 0)
        {
            Log.i(TAG, "Food yok :(");
        }

        while(res.moveToNext())
        {
            food.setFoodId(res.getInt(0));
            food.setFoodname(res.getString(1));
            food.setImagepath(res.getString(2));
            food.setCarbon(res.getDouble(3));
            food.setProtein(res.getDouble(4));
            food.setFat(res.getDouble(5));
            food.setPorsiyon(res.getDouble(6));
        }

        return food;

    }

    @Override
    public void deleteFood(FoodItem foodItem) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        // delete book
        db.delete(FoodDBEntry.FeedEntry.TABLE_NAME, FoodDBEntry.FeedEntry._ID + " = ?", new String[] { String.valueOf(foodItem.getFoodId())});
        db.close();

    }
}
