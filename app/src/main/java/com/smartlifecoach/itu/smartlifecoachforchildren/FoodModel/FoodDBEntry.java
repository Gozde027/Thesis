package com.smartlifecoach.itu.smartlifecoachforchildren.FoodModel;

import android.provider.BaseColumns;

/**
 * Created by gkaval on 10/3/15.
 */
public final class FoodDBEntry {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FoodDBEntry() {

    }

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "foods";
        public static final String COLUMN_NAME_FOOD_NAME = "name";
        public static final String COLUMN_NAME_FOOD_IMAGE_PATH = "imagepath";
        public static final String COLUMN_NAME_FOOD_CARBONHYDRATE = "carbohydrate";
        public static final String COLUMN_NAME_FOOD_PROTEIN = "protein";
        public static final String COLUMN_NAME_FOOD_FAT = "fat";
        public static final String COLUMN_NAME_FOOD_PORSIYON = "porsiyon";

    }
}
