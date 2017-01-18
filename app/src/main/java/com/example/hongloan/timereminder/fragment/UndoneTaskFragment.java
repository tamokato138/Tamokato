package com.example.hongloan.timereminder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.activity.EditTaskActivity;
import com.example.hongloan.timereminder.adapter.UndoneTaskAdapter;
import com.example.hongloan.timereminder.database.TaskDao;
import com.example.hongloan.timereminder.database.TaskDto;
import com.example.hongloan.timereminder.interfaces.OnUndoneTaskAdapterListener;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class UndoneTaskFragment extends Fragment {
    RecyclerView recyclerView;
    TextView tvEmpty;
    FrameLayout container;
    RecyclerView.LayoutManager layoutManager;
    UndoneTaskAdapter adapter;
    public static final int REQUEST_CODE_EDIT_UNDONE_TASK = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_undone_task, parent, false);
        getFormWidget(view);
        adapter = new UndoneTaskAdapter();
        recyclerView.setAdapter(adapter);
        loadData();
        displayView();
        addEvents();
        return view;
    }

    public static UndoneTaskFragment getInstance() {
        UndoneTaskFragment undoneTaskFragment = new UndoneTaskFragment();
        return undoneTaskFragment;
    }

    private void getFormWidget(View view) {
        tvEmpty = (TextView) view.findViewById(R.id.fragment_undone_task_tv_empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_undone_task_recycler_view);
        container = (FrameLayout) view.findViewById(R.id.container_undone_task);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EDIT_UNDONE_TASK) {
            if (resultCode == RESULT_OK) {
                reloadData();
            }
        }
    }

    private void addEvents() {
        adapter.setOnUndoneTaskAdapterListener(new ItemEvents());
//        taskListFragment.setOnTaskListUpdateUndoneTaskListener(new ItemEvents());
    }

    private void loadData() {
        TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
        ArrayList<TaskDto> listItem = taskDao.loadUnDoneTask();
        adapter.addDataToAdapter(listItem);
        adapter.notifyDataSetChanged();

    }

    private void displayView() {
        if (adapter.getItemCount() == 0) {
            container.setVisibility(View.INVISIBLE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            container.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.INVISIBLE);
        }
    }

    public class ItemEvents implements OnUndoneTaskAdapterListener{


        @Override
        public void onUndoneTaskAdapterCheckIsDoneListener(boolean isChecked) {
            final int pos = adapter.getSelectedPosition();
            TaskDto item = adapter.getItemByPosition(pos);
            item.setDone(isChecked);
            final int itemId = item.getId();
            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
            taskDao.updateDone(itemId, isChecked);
            ArrayList<TaskDto> data = taskDao.loadUnDoneTask();
            adapter.addDataToAdapter(data);
            Handler handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            };
            handler.post(r);
            displayView();
            Toast.makeText(getContext(), "Updated!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUndoneTaskAdapterSwitchListener(boolean isChecked) {
            final int pos = adapter.getSelectedPosition();
            TaskDto item = adapter.getItemByPosition(pos);
            item.setNotify(isChecked);
            final int itemId = item.getId();
            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
            taskDao.updateNotify(itemId, isChecked);
            ArrayList<TaskDto> data = taskDao.loadUnDoneTask();
            adapter.addDataToAdapter(data);
            Handler handler = new Handler();
            final Runnable r = new Runnable() {
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            };
            handler.post(r);
            displayView();
            Toast.makeText(getContext(), "Notification changed!", Toast.LENGTH_SHORT).show();
        }

//        @Override
//        public void onTaskListUpdateUndoneTask() {
//            TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
//            ArrayList<TaskDto> data = taskDao.loadUnDoneTask();
//            adapter.addDataToAdapter(data);
//            Handler handler = new Handler();
//            final Runnable r = new Runnable() {
//                public void run() {
//                    adapter.notifyDataSetChanged();
//                }
//            };
//            handler.post(r);
//            displayView();
//        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        TaskDto item = adapter.getItemByPosition(adapter.getSelectedPosition());
        TaskDao taskDao = new TaskDao(getActivity().getApplicationContext());
        switch (menuItem.getItemId()) {
            case R.id.action_delete_on_undone:
                if (item != null) {
                    taskDao.deleteRow(item.getId());
                    Toast.makeText(getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    reloadData();
                }
                break;
            case R.id.action_edit_on_undone:
                Intent intent = new Intent(getActivity(), EditTaskActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                intent.putExtra("my_package", bundle);
                startActivityForResult(intent, REQUEST_CODE_EDIT_UNDONE_TASK);
                break;

        }
        return super.onContextItemSelected(menuItem);
    }

    private void reloadData() {
        loadData();
        displayView();
    }
}



