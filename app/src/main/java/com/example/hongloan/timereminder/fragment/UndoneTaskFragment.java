package com.example.hongloan.timereminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.adapter.TaskListAdapter;
import com.example.hongloan.timereminder.database.TaskDao;
import com.example.hongloan.timereminder.database.TaskDto;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class UndoneTaskFragment extends Fragment {
    RecyclerView recyclerView;
    TextView tvEmpty;
    FrameLayout container;
    RecyclerView.LayoutManager layoutManager;
    TaskListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_undone_task, parent, false);
        getFormWidget(view);
        adapter = new TaskListAdapter();
        recyclerView.setAdapter(adapter);
        loadData();
        displayView();
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


}
