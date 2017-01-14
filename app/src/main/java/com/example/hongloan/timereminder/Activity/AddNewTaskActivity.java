package com.example.hongloan.timereminder.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.database.TaskDao;
import com.example.hongloan.timereminder.database.TaskDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNewTaskActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    EditText edt_title, edt_description;
    ImageView imgDate, imgTime;
    TextView tvDate, tvTime;
    CheckBox chkNotify;
    RadioButton rdPriority1, rdPriority2, rdPriority3;
    RadioGroup rdGroup;
    Calendar calendar = Calendar.getInstance();
    String dateFormat = "dd/MM/yyyy";
    String timeFormat = "HH:mm";
    SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        getFormWidget();
        customToolbar();
        getDefaultInfo();
        addEvents();

    }

    private void addEvents() {
        imgDate.setOnClickListener(this);
        imgTime.setOnClickListener(this);
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
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.action_save:
                if (checkInputDataNotNull()) {
                    createTaskData();
                } else {
                    Toast.makeText(this, "Please input data!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.action_cancel:
                finish();
                break;
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
            Toast.makeText(this, "Task create successfully!", Toast.LENGTH_SHORT).show();
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

    private void getDefaultInfo() {
        simpleDateFormat = new SimpleDateFormat(dateFormat);
        tvDate.setText(simpleDateFormat.format(calendar.getTime()));
        simpleDateFormat = new SimpleDateFormat(timeFormat);
        tvTime.setText(simpleDateFormat.format(calendar.getTime()));

    }

    private boolean checkInputDataNotNull() {
        return !(edt_title.getText().toString().equals("")) && (rdPriority1.isChecked() || rdPriority2.isChecked() || rdPriority3.isChecked());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_add_new_task_img_date:
                showDatePickerDialog();
                break;
            case R.id.activity_add_new_task_img_time:
                showTimePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                simpleDateFormat = new SimpleDateFormat(dateFormat);
                tvDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.setTitle("Choose start date!");
        dialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                simpleDateFormat = new SimpleDateFormat(timeFormat);
                tvTime.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.setTitle("Choose start time!");
        timePickerDialog.show();
    }
}
