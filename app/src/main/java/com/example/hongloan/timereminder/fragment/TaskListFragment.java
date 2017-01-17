package com.example.hongloan.timereminder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.activity.AddNewTaskActivity;
import com.example.hongloan.timereminder.activity.EditTaskActivity;
import com.example.hongloan.timereminder.adapter.TaskListAdapter;
import com.example.hongloan.timereminder.database.TaskDao;
import com.example.hongloan.timereminder.database.TaskDto;
import com.example.hongloan.timereminder.interfaces.OnTaskListAdapterListener;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class TaskListFragment extends Fragment implements View.OnCreateContextMenuListener {
    RecyclerView recyclerView;
    TextView tvEmpty;
    ImageButton btnAddTask;
    RecyclerView.LayoutManager layoutManager;
    TaskListAdapter adapter;
    FrameLayout container;


    public static final int REQUEST_CODE_ADD_NEW = 1;
    public static final int REQUEST_CODE_EDIT = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task_list, parent, false);
        getFormWidget(view);
        adapter = new TaskListAdapter();
        recyclerView.setAdapter(adapter);
        loadData();
        displayView();
        registerForContextMenu(recyclerView);
        addEvents();
        return view;
    }

    private void addEvents() {
        btnAddTask.setOnClickListener(new ItemEvents());
        adapter.setOnTaskListAdapterListener(new ItemEvents());
    }

    public static TaskListFragment getInstance() {
        TaskListFragment taskListFragment = new TaskListFragment();
        return taskListFragment;
    }


    public void getFormWidget(View view) {
        tvEmpty = (TextView) view.findViewById(R.id.fragment_task_list_tv_empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_task_list_recycler_view);
        btnAddTask = (ImageButton) view.findViewById(R.id.fragment_task_list_btn_add_task);
        container = (FrameLayout) view.findViewById(R.id.container);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void displayView() {
        if (adapter.getItemCount() == 0) {
            container.setVisibility(View.INVISIBLE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            container.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.INVISIBLE);
        }
    }

    public void loadData() {
        TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
        ArrayList<TaskDto> data = taskDao.loadAll();
        adapter.addDataToAdapter(data);
        adapter.notifyDataSetChanged();
    }

    private void reloadData() {
        loadData();
        displayView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NEW) {
            if (resultCode == RESULT_OK) {
                reloadData();
            }
        }
        if (requestCode == REQUEST_CODE_EDIT) {
            if (resultCode == RESULT_OK) {
                reloadData();
            }
        }
    }

    public class ItemEvents implements OnTaskListAdapterListener, View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), AddNewTaskActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NEW);
        }

        @Override
        public void onTaskListAdapterSwitchListener(boolean isChecked) {
            final int pos = adapter.getSelectedPosition();
            TaskDto item = adapter.getItemByPosition(pos);
            item.setNotify(isChecked);
            final int itemId = item.getId();
            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
            taskDao.updateNotify(itemId, isChecked);
            Toast.makeText(getContext(), "Notification changed!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTaskListAdapterCheckIsDoneListener(boolean isChecked) {
            final int pos = adapter.getSelectedPosition();
            TaskDto item = adapter.getItemByPosition(pos);
            item.setDone(isChecked);
            final int itemId = item.getId();
            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
            taskDao.updateDone(itemId, isChecked);
            ArrayList<TaskDto> data = taskDao.loadAll();
            adapter.addDataToAdapter(data);
            Handler handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            };
            handler.post(r);
            Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        TaskDto item = adapter.getItemByPosition(adapter.getSelectedPosition());
        TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
        switch (menuItem.getItemId()) {
            case R.id.action_delete_on_task_list:
                if (item != null) {
                    taskDao.deleteRow(item.getId());
                    Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    reloadData();
                }
                break;
            case R.id.action_edit_on_task_list:
                Intent intent = new Intent(getActivity(), EditTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtra("my_package", bundle);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
                Log.d("Loan", "onStartEdit");
                break;

        }
        return super.onContextItemSelected(menuItem);
    }
}





