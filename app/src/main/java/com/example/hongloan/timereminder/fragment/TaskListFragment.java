package com.example.hongloan.timereminder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.hongloan.timereminder.adapter.TaskListAdapter;
import com.example.hongloan.timereminder.database.TaskDao;
import com.example.hongloan.timereminder.database.TaskDto;
import com.example.hongloan.timereminder.interfaces.OnAdapterListener;

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
    public static final int REQUEST_CODE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task_list, parent, false);
        getFormWidget(view);
        adapter = new TaskListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        loadData();
        displayView();
        registerForContextMenu(recyclerView);
        addEvents();
        return view;
    }

    private void addEvents() {
        btnAddTask.setOnClickListener(new ItemEvents());
        adapter.setOnAdapterListener(new ItemEvents());
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                loadData();
                displayView();
            }
        }
    }

    public class ItemEvents implements OnAdapterListener, View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), AddNewTaskActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }


        @Override
        public void onAdapterSwitchListener(int position) {

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_delete:
                TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
                taskDao.deleteRow(adapter.getSelectedPosition());
                Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                loadData();
                displayView();
                break;
            case R.id.action_edit:
                Toast.makeText(getContext(), "Edit!", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onContextItemSelected(menuItem);
    }
}





