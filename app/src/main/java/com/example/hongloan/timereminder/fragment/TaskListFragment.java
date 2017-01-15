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
import com.example.hongloan.timereminder.activity.EditTaskActivity;
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
//                loadData();
//                displayView();
            }
        }
        if (requestCode == REQUEST_CODE_EDIT) {
            if (resultCode == RESULT_OK) {
                reloadData();
//                loadData();
//                displayView();
            }
        }
    }

    public class ItemEvents implements OnAdapterListener, View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), AddNewTaskActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NEW);
        }

        @Override
        public void onAdapterOnSwitchListener(boolean isChecked) {
            final int pos = adapter.getSelectedPosition();
            TaskDto item = adapter.getItemByPosition(pos);
            item.setNotify(isChecked);
            adapter.notifyDataSetChanged();
            final int itemId = item.getId();
            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
            taskDao.updateNotify(itemId, isChecked);
            Toast.makeText(getContext(), "Updated notification!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAdapterOnCheckIsDoneListener(boolean isChecked) {
            final int pos = adapter.getSelectedPosition();
            TaskDto item = adapter.getItemByPosition(pos);
            item.setDone(isChecked);
            adapter.notifyDataSetChanged();
            final int itemId = item.getId();
            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
            taskDao.updateDone(itemId, isChecked);
            Toast.makeText(getContext(), "Done!", Toast.LENGTH_SHORT).show();
        }


//        @Override
//        public void onAdapterOnSwitchListener() {
//            final int pos = adapter.getSelectedPosition();
//            TaskDto item = adapter.getItemByPosition(pos);
//            item.setNotify(true);
//            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
//            taskDao.updateRowEdit(item, pos);
//            Toast.makeText(getContext(), "Turn on notification!", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onAdapterOffSwitchListener() {
//            final int pos = adapter.getSelectedPosition();
//            TaskDto item = adapter.getItemByPosition(pos);
//            item.setNotify(false);
//            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
//            taskDao.updateRowEdit(item, pos);
//            Toast.makeText(getContext(), "Turn off notification!", Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        public void onAdapterOnCheckIsDoneListener() {
//            final int pos = adapter.getSelectedPosition();
//            TaskDto item = adapter.getItemByPosition(pos);
//            item.setDone(true);
//            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
//            taskDao.updateRowEdit(item, pos);
//            Toast.makeText(getContext(), "Done!", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onAdapterOnCheckNotDoneListener() {
//            final int pos = adapter.getSelectedPosition();
//            TaskDto item = adapter.getItemByPosition(pos);
//            item.setDone(false);
//            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
//            taskDao.updateRowEdit(item, pos);
//        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        TaskDto item = adapter.getItemByPosition(adapter.getSelectedPosition());
        TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
        switch (menuItem.getItemId()) {
            case R.id.action_delete:
                if (item != null) {
                    taskDao.deleteRow(item.getId());
                    Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    reloadData();
                }
                break;
            case R.id.action_edit:
                Intent intent = new Intent(getActivity(), EditTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtra("my_package", bundle);
                startActivityForResult(intent, REQUEST_CODE_EDIT);
                break;

        }
        return super.onContextItemSelected(menuItem);
    }
}





