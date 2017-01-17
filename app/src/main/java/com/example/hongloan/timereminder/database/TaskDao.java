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
        db.close();
        Log.d(getClass().getSimpleName() + " Loan", "insert: " + newRowId);
        return newRowId;
    }

    public ArrayList<TaskDto> loadAll() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {
                Task.TaskEntry._ID,
                Task.TaskEntry.COLUMN_TITLE,
                Task.TaskEntry.COLUMN_DESCRIPTION,
                Task.TaskEntry.COLUMN_DATE,
                Task.TaskEntry.COLUMN_TIME,
                Task.TaskEntry.COLUMN_PRIORITY,
                Task.TaskEntry.COLUMN_IS_DONE,
                Task.TaskEntry.COLUMN_IS_NOTIFY,
        };
        String sortOrder = Task.TaskEntry.COLUMN_IS_DONE + " ASC"
                + DatabaseConstants.COMMA_SEP + Task.TaskEntry.COLUMN_PRIORITY + " DESC"
                ;
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
            while (cursor.moveToNext()) {
                taskDto = new TaskDto();
                taskDto.setId(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry._ID)));
                taskDto.setTitle(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_TITLE)));
                taskDto.setDescription(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_DESCRIPTION)));
                taskDto.setDate(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_DATE)));
                taskDto.setTime((cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_TIME))));
                taskDto.setPriority(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_PRIORITY)));
                taskDto.setDone(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_DONE)) == 1);
                taskDto.setNotify(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_NOTIFY)) == 1);
                list.add(taskDto);
            }
            Log.d(getClass().getSimpleName() + " Loan", "load data: " + " " + list.size());
            cursor.close();
            db.close();
            return list;
        }
        return null;
    }

    public ArrayList<TaskDto> loadUnDoneTask() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] projection = {
                Task.TaskEntry._ID,
                Task.TaskEntry.COLUMN_TITLE,
                Task.TaskEntry.COLUMN_DESCRIPTION,
                Task.TaskEntry.COLUMN_DATE,
                Task.TaskEntry.COLUMN_TIME,
                Task.TaskEntry.COLUMN_PRIORITY,
                Task.TaskEntry.COLUMN_IS_DONE,
                Task.TaskEntry.COLUMN_IS_NOTIFY,
        };
        String sortOrder = Task.TaskEntry._ID + " DESC";
        String selection = Task.TaskEntry.COLUMN_IS_DONE + " = " + "0";
        Cursor cursor = db.query(
                Task.TaskEntry.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                sortOrder
        );
        if (cursor != null) {
            ArrayList<TaskDto> list = new ArrayList<TaskDto>();
            TaskDto taskDto;
            while (cursor.moveToNext()) {
                taskDto = new TaskDto();
                taskDto.setId(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry._ID)));
                taskDto.setTitle(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_TITLE)));
                taskDto.setDescription(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_DESCRIPTION)));
                taskDto.setDate(cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_DATE)));
                taskDto.setTime((cursor.getString(cursor.getColumnIndex(Task.TaskEntry.COLUMN_TIME))));
                taskDto.setPriority(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_PRIORITY)));
                taskDto.setDone(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_DONE)) == 1);
                taskDto.setNotify(cursor.getInt(cursor.getColumnIndex(Task.TaskEntry.COLUMN_IS_NOTIFY)) == 1);
                list.add(taskDto);
            }
            Log.d(getClass().getSimpleName() + " Loan", "load data: " + " " + list.size());
            cursor.close();
            db.close();
            return list;
        }
        return null;
    }


    public void deleteRow(int taskId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String selection = Task.TaskEntry._ID + " = " + String.valueOf(taskId);
        int rowId = db.delete(Task.TaskEntry.TABLE_NAME, selection, null);
        Log.d(getClass().getSimpleName(), " deleteRow: " + rowId);
        db.close();

    }

    public int updateRowEdit(TaskDto taskDto, int taskId) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(Task.TaskEntry.COLUMN_TITLE, taskDto.getTitle());
        values.put(Task.TaskEntry.COLUMN_DESCRIPTION, taskDto.getDescription());
        values.put(Task.TaskEntry.COLUMN_DATE, taskDto.getDate());
        values.put(Task.TaskEntry.COLUMN_TIME, taskDto.getTime());
        values.put(Task.TaskEntry.COLUMN_PRIORITY, taskDto.getPriority());
        values.put(Task.TaskEntry.COLUMN_IS_DONE, taskDto.isDone() ? 1 : 0);
        values.put(Task.TaskEntry.COLUMN_IS_NOTIFY, taskDto.isNotify() ? 1 : 0);

// Which row to update, based on the title
        String selection = Task.TaskEntry._ID + " = " + String.valueOf(taskId);
        int count = db.update(
                Task.TaskEntry.TABLE_NAME,
                values,
                selection,
                null);
        db.close();
        return count;
    }

    public int updateNotify(final int taskId, final boolean notify) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Task.TaskEntry.COLUMN_IS_NOTIFY, notify ? 1 : 0);
        String selection = Task.TaskEntry._ID + " = " + String.valueOf(taskId);
        int count = db.update(
                Task.TaskEntry.TABLE_NAME,
                values,
                selection,
                null);
        db.close();
        return count;
    }

    public int updateDone(final int taskId, final boolean isDone) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Task.TaskEntry.COLUMN_IS_DONE, isDone ? 1 : 0);
        String selection = Task.TaskEntry._ID + " = " + String.valueOf(taskId);
        int count = db.update(
                Task.TaskEntry.TABLE_NAME,
                values,
                selection,
                null);
        db.close();
        return count;
    }
}
