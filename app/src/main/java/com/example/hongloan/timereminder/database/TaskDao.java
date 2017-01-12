package com.example.hongloan.timereminder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        values.put(Task.TaskEntry.COLUMN_DATE, taskDto.getDate());
        values.put(Task.TaskEntry.COLUMN_TIME, taskDto.getTime());
        values.put(Task.TaskEntry.COLUMN_PRIORITY, taskDto.getPriority());
        values.put(Task.TaskEntry.COLUMN_IS_DONE, taskDto.isDone() ? 1 : 0);
        values.put(Task.TaskEntry.COLUMN_IS_NOTIFY, taskDto.isNotify() ? 1 : 0);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long newRowId = db.insert(Task.TaskEntry.TABLE_NAME, null, values);
        Log.d(getClass().getSimpleName() + " Loan", "insert: " + newRowId);
        return newRowId;
    }

    public ArrayList<TaskDto> loadAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {
                Task.TaskEntry.COLUMN_TITLE,
                Task.TaskEntry.COLUMN_DESCRIPTION,
                Task.TaskEntry.COLUMN_DATE,
                Task.TaskEntry.COLUMN_TIME,
                Task.TaskEntry.COLUMN_PRIORITY,
                Task.TaskEntry.COLUMN_IS_DONE,
                Task.TaskEntry.COLUMN_IS_NOTIFY,
        };
        String sortOrder = Task.TaskEntry._ID + " DESC";
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
                taskDto.setDate(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_DATE)));
                taskDto.setTime((cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_TIME))));
                taskDto.setPriority(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_PRIORITY)));
                taskDto.setDone(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_DONE)) == 1);
                taskDto.setNotify(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_NOTIFY)) == 1);
                list.add(taskDto);
            }
            Log.d(getClass().getSimpleName() + " Loan", "load data: "+ list.size());
            cursor.close();
            db.close();
            return list;
        }
        return null;
    }

}
