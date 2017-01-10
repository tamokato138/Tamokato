package com.example.hongloan.timereminder.activity;

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

    public void getFormWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    public void customToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.add_new_task_toolbar_name));
        }
    }
}
