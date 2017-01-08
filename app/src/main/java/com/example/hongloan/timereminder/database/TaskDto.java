package com.example.hongloan.timereminder.database;

import java.util.Date;

/**
 * Created by Hong Loan on 06/01/2017.
 */

public class TaskDto {
    private String title;
    private String descripton;
    private Date date;
    private Date time;
    private int priority;
    private boolean isNotify;
    private boolean isDone;

    public TaskDto(String title, String descripton, Date date, Date time, int priority, boolean isNotify, boolean isDone) {
        this.title = title;
        this.descripton = descripton;
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

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
}
