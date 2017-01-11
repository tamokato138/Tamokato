package com.example.hongloan.timereminder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.database.TaskDao;
import com.example.hongloan.timereminder.database.TaskDto;

public class AddNewTaskActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edt_title, edt_description;
    ImageView imgDate, imgTime;
    TextView tvDate, tvTime;
    CheckBox chkNotify;
    RadioButton rdPriority1, rdPriority2, rdPriority3;
    RadioGroup rdGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        getFormWidget();
        customToolbar();

    }

    private void getFormWidget() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edt_title = (EditText) findViewById(R.id.activity_add_new_task_edt_title);
        edt_description = (EditText) findViewById(R.id.activity_add_new_task_edt_description);
        imgDate = (ImageView) findViewById(R.id.activity_add_new_task_img_date);
        imgTime = (ImageView) findViewById(R.id.activity_add_new_task_img_time);
        chkNotify = (CheckBox) findViewById(R.id.activity_add_new_task_chk_notify);
        tvDate = (TextView) findViewById(R.id.activity_add_new_task_tv_date);
        tvTime = (TextView) findViewById(R.id.activity_add_new_task_tv_time);
        rdPriority1 = (RadioButton) findViewById(R.id.activity_add_new_task_rd_priority_1);
        rdPriority2 = (RadioButton) findViewById(R.id.activity_add_new_task_rd_priority_2);
        rdPriority3 = (RadioButton) findViewById(R.id.activity_add_new_task_rd_priority_3);
        rdGroup = (RadioGroup) findViewById(R.id.activity_add_new_task_rd_group_priority);
    }

    private void customToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.add_new_task_toolbar_name));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.action_save) {
            createTaskData();
        }
        if (id == R.id.action_cancel) {
            finish();
        }
        return true;
    }

    private void createTaskData() {
        TaskDto taskDto = new TaskDto();
        taskDto.setTitle(edt_title.getText().toString());
        taskDto.setDescription(edt_description.getText().toString());
        taskDto.setDate(tvDate.getText().toString());
        taskDto.setTime(tvTime.getText().toString());
        taskDto.setPriority(getPriorityRadioBtn());
        taskDto.setNotify(chkNotify.isChecked());

        TaskDao taskDao = new TaskDao(getApplication());
        long userId = taskDao.insert(taskDto);
        if (userId != -1) {
            Toast.makeText(this, "Task create successfully", Toast.LENGTH_SHORT).show();
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private int getPriorityRadioBtn() {
        int priority = 0;
        if (rdPriority1.isChecked()) {
            priority = 1;
        }
        if (rdPriority2.isChecked()) {
            priority = 2;
        }
        if (rdPriority3.isChecked()) {
            priority = 3;
        }
        return priority;
    }

    private void setDateTime() {

    }


}
