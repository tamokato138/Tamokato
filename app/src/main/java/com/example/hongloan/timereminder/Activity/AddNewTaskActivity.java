package com.example.hongloan.timereminder.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hongloan.timereminder.R;

public class AddNewTaskActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        getFormWidget();
        customToolbar();
    }

    void getFormWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    void customToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add New Task");
        }
    }
}
