package com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenDatabesePack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel.FoodDBEntry;
import com.smartlifecoach.itu.smartlifecoachforchildren.SignIn.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gozde Kaval on 11/27/2015.
 */
public class ChildrenDatabase extends SQLiteOpenHelper implements ChildrenDBInterface{

    // database version
    private static final int database_VERSION = 3;
    private static final String TAG = "ChildrenDatabase";
    // database name
    private static final String database_NAME = "ChildrenDB";

    public ChildrenDatabase(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG, "onCreate");
        String CREATE_CHILD_TABLE = "CREATE TABLE " + ChildDBEntry.ChildEntry.TABLE_NAME + " ( "
                + ChildDBEntry.ChildEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_NAME + " TEXT, "
                + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_SURNAME + " TEXT, "
                + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_GENDER + " INTEGER, "
                + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_AGE + " INTEGER, "
                + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_PARENT_USERNAME + " TEXT, "
                + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_KILO + " INTEGER )";
        db.execSQL(CREATE_CHILD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ChildDBEntry.ChildEntry.TABLE_NAME);
        this.onCreate(db);
    }

    @Override
    public void addChild(Child child) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_NAME, child.getName());
        values.put(ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_SURNAME, child.getSurname());
        values.put(ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_GENDER, child.getGender());
        values.put(ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_AGE, child.getAge());
        values.put(ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_PARENT_USERNAME, child.getParentUsername());
        values.put(ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_KILO, child.getKilo());

        db.insert(FoodDBEntry.FeedEntry.TABLE_NAME, null, values);
        // close database transaction
        db.close();
    }

    @Override
    public List<Child> getAllChild() {
        List<Child> childList = new ArrayList<Child>();
        // select book query
        String query = "SELECT * FROM " + ChildDBEntry.ChildEntry.TABLE_NAME;

        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Child child = null;
        if (cursor.moveToFirst()) {
            do {

                child = new Child();
                child.setChildID(Integer.parseInt(cursor.getString(0)));
                child.setName(cursor.getString(1));
                child.setSurname(cursor.getString(2));
                child.setGender(Integer.parseInt(cursor.getString(3)));
                child.setAge(Integer.parseInt(cursor.getString(4)));
                child.setParentUsername(cursor.getString(5));
                child.setKilo(Integer.parseInt(cursor.getString(6)));

                childList.add(child);
            } while (cursor.moveToNext());
        }
        return childList;
    }

    @Override
    public Child getChild(String childName) {
        Child child = new Child();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + ChildDBEntry.ChildEntry.TABLE_NAME
                + " WHERE  " + ChildDBEntry.ChildEntry.COLUMN_NAME_CHILD_NAME + " = " + childName + "", null );
        if(res.getCount() == 0)
        {
            Log.i(TAG, "Child yok :(");
        }

        while(res.moveToNext())
        {
            child.setChildID(res.getInt(0));
            child.setName(res.getString(1));
            child.setSurname(res.getString(2));
            child.setGender(res.getInt(3));
            child.setAge(res.getInt(4));
            child.setParentUsername(res.getString(5));
            child.setKilo(res.getInt(6));
        }
        return child;
    }

    @Override
    public void deleteChild(Child childItem) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();
        // delete book
        db.delete(ChildDBEntry.ChildEntry.TABLE_NAME, ChildDBEntry.ChildEntry._ID + " = ?", new String[] { String.valueOf(childItem.getChildID())});
        db.close();
    }
}
