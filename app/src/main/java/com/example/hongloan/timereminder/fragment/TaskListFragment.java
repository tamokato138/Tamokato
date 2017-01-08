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

import com.example.hongloan.timereminder.activity.AddNewTaskActivity;
import com.example.hongloan.timereminder.adapter.TaskListAdapter;
import com.example.hongloan.timereminder.R;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class TaskListFragment extends Fragment {
    RecyclerView recyclerView;
    TextView tvEmpty;
    ImageButton btnAddTask;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task_list, parent, false);
        getFormWidget(view);
        addListTask();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getContext(), AddNewTaskActivity.class);
                startActivity(myintent);
            }
        });
        return view;
    }
    public static TaskListFragment getInstance(){
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
        mData.add("Đi chơi");
        mData.add("Ăn");
        mData.add("Đi học");
        mData.add("Đi chơi");
        mData.add("Ăn");
        mData.add("Đi học");
        mData.add("Đi chơi");
        mData.add("Ăn");
        mData.add("Đi học");
        mData.add("Đi chơi");
        mData.add("Ăn");
        mData.add("Đi học");
        mData.add("Đi chơi");
        mData.add("Ăn");
        return mData;
    }
}




