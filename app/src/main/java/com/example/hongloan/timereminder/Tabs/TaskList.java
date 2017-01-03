package com.example.hongloan.timereminder.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.hongloan.timereminder.R;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class TaskList extends Fragment {
    RecyclerView recyclerView;
    TextView tvEmpty;
    ImageButton btnAddTask;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task_list, parent, false);
        return view;
    }
}



