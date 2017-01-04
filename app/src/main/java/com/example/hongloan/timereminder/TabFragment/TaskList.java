package com.example.hongloan.timereminder.TabFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hongloan.timereminder.Adapter.TaskListAdapter;
import com.example.hongloan.timereminder.R;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class TaskList extends Fragment {
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
        return view;
    }

    void getFormWidget(View view) {
        tvEmpty = (TextView) view.findViewById(R.id.fragment_task_list_tv_empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_task_list_recycler_view);
        btnAddTask = (ImageButton) view.findViewById(R.id.fragment_task_list_btn_add_task);
    }

    void addListTask() {
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
        mData.add("Đi học");
        mData.add("Đi chơi");
        mData.add("Ăn");
        return mData;
    }
}




