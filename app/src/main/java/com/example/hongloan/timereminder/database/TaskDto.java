package com.example.hongloan.timereminder.database;

import java.io.Serializable;

/**
 * Created by Hong Loan on 06/01/2017.
 */

public class TaskDto implements Serializable{
    private int id;
    private String title;
    private String description;
    private String date;
    private String time;
    private int priority;
    private boolean isNotify;
    private boolean isDone;

    public TaskDto(){

    }
    public TaskDto(int id, String title, String description, String date, String time, int priority, boolean isNotify, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.isNotify = isNotify;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isNotify() {
        return isNotify;
    }

    public void setNotify(boolean notify) {
        isNotify = notify;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
