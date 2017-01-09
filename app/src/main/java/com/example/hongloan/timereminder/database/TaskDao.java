package com.example.hongloan.timereminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 09/01/2017.
 */

public class TaskDao {
    private DatabaseHelper databaseHelper;
    private Context context;

    public TaskDao(Context context) {
        this.context = context;
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public long insert(final TaskDto taskDto) {
        ContentValues values = new ContentValues();
        values.put(Task.TaskEntry.COLUMN_TITLE, taskDto.getTitle());
        values.put(Task.TaskEntry.COLUMN_DESCRIPTION, taskDto.getDescription());
        values.put(Task.TaskEntry.COLUMN_DATE, String.valueOf(taskDto.getDate()));
        values.put(Task.TaskEntry.COLUMN_TIME, String.valueOf(taskDto.getTime()));
        values.put(Task.TaskEntry.COLUMN_PRIORITY, taskDto.getPriority());
        values.put(Task.TaskEntry.COLUMN_IS_DONE, String.valueOf(taskDto.isDone()));
        values.put(Task.TaskEntry.COLUMN_IS_NOTIFY, String.valueOf(taskDto.isNotify()));
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long newRowId = db.insert(Task.TaskEntry.TABLE_NAME, null, values);
        return newRowId;
    }

//    public long insert(final String title, String description, String date, String time, int priority, boolean isDone, boolean isNotify) {
//        ContentValues values = new ContentValues();
//        values.put(Task.TaskEntry.COLUMN_TITLE, title);
//        values.put(Task.TaskEntry.COLUMN_DESCRIPTION, description);
//        values.put(Task.TaskEntry.COLUMN_DATE, date);
//        values.put(Task.TaskEntry.COLUMN_TIME, time);
//        values.put(Task.TaskEntry.COLUMN_PRIORITY, priority);
//        values.put(Task.TaskEntry.COLUMN_IS_DONE, isDone);
//        values.put(Task.TaskEntry.COLUMN_IS_NOTIFY, isNotify);
//
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//        long newRowId = db.insert(Task.TaskEntry.TABLE_NAME, null, values);
//        return newRowId;
//    }

    public ArrayList<TaskDto> loadAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {
                Task.TaskEntry.COLUMN_TITLE,
                Task.TaskEntry.COLUMN_DATE,
                Task.TaskEntry.COLUMN_TIME,
                Task.TaskEntry.COLUMN_PRIORITY,
                Task.TaskEntry.COLUMN_IS_DONE,
                Task.TaskEntry.COLUMN_IS_NOTIFY,
        };
        String sortOrder = Task.TaskEntry._ID + " ASC";
        Cursor cursor = db.query(
                Task.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        if (cursor != null) {
            ArrayList<TaskDto> list = new ArrayList<TaskDto>();
            TaskDto taskDto;
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                taskDto = new TaskDto();
                taskDto.setTitle(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_TITLE)));
                taskDto.setDescription(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_DESCRIPTION)));
                taskDto.setPriority(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_PRIORITY)));
                taskDto.setDone(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_DONE))));
                taskDto.setNotify(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_NOTIFY))));
                list.add(taskDto);
            }
            return list;
        }
        return null;
    }


}
