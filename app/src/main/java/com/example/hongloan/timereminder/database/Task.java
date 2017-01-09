package com.example.hongloan.timereminder.database;

import android.provider.BaseColumns;

/**
 * Created by Hong Loan on 09/01/2017.
 */

public class Task {
    private Task() {
    }

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_IS_NOTIFY = "is_notify";
        public static final String COLUMN_IS_DONE = "is_done";
    }

}
