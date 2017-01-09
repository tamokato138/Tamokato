package com.example.hongloan.timereminder.database;

/**
 * Created by Hong Loan on 09/01/2017.
 */

public class DatabaseConstants {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ", ";
    public static final String OPEN_PAREN = "( ";
    public static final String CLOSE_PAREN = " )";

    public static final String SQL_CREATE_TABLE_TASK = "CREATE TABLE " + Task.TaskEntry.TABLE_NAME
            + OPEN_PAREN
            + Task.TaskEntry._ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP
            + Task.TaskEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP
            + Task.TaskEntry.COLUMN_DESCRIPTION + TEXT_TYPE + COMMA_SEP
            + Task.TaskEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP
            + Task.TaskEntry.COLUMN_TIME + TEXT_TYPE + COMMA_SEP
            + Task.TaskEntry.COLUMN_PRIORITY + INTEGER_TYPE + COMMA_SEP
            + Task.TaskEntry.COLUMN_IS_NOTIFY + INTEGER_TYPE + COMMA_SEP
            + Task.TaskEntry.COLUMN_IS_DONE + INTEGER_TYPE
            + CLOSE_PAREN;
    public static final String SQL_DELETE_TABLE_TASK = "DROP TABLE IF EXISTS" + Task.TaskEntry.TABLE_NAME;

}
