package com.example.hongloan.timereminder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.activity.AddNewTaskActivity;
import com.example.hongloan.timereminder.adapter.TaskListAdapter;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class TaskListFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    TextView tvEmpty;
    ImageButton btnAddTask;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> mData;

    public static final int REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task_list, parent, false);
        getFormWidget(view);
        addListTask();

        btnAddTask.setOnClickListener(this);
        return view;
    }

    public static TaskListFragment getInstance() {
        TaskListFragment taskListFragment = new TaskListFragment();
        return taskListFragment;
    }


    public void getFormWidget(View view) {
        tvEmpty = (TextView) view.findViewById(R.id.fragment_task_list_tv_empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_task_list_recycler_view);
        btnAddTask = (ImageButton) view.findViewById(R.id.fragment_task_list_btn_add_task);
    }

    public void addListTask() {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        TaskListAdapter adapter = new TaskListAdapter();
        adapter.addDataToAdapter(mockData());
        if (mData == null || mData.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    private ArrayList<String> mockData() {
        mData = new ArrayList<String>();
        return mData;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AddNewTaskActivity.class);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}




