package com.smartlifecoach.itu.smartlifecoachforchildren.ChildrenDatabesePack;

import android.provider.BaseColumns;

/**
 * Created by Gozde Kaval on 11/27/2015.
 */
public final class ChildDBEntry {

    public ChildDBEntry() {}
    /* Inner class that defines the table contents */
    public static abstract class ChildEntry implements BaseColumns {
        public static final String TABLE_NAME = "children";
        public static final String COLUMN_NAME_CHILD_NAME = "name";
        public static final String COLUMN_NAME_CHILD_SURNAME = "surname";
        public static final String COLUMN_NAME_CHILD_GENDER = "gender";
        public static final String COLUMN_NAME_CHILD_AGE = "age";
        public static final String COLUMN_NAME_CHILD_PARENT_USERNAME = "parentusername";
        public static final String COLUMN_NAME_CHILD_KILO = "kilo";

    }
}

