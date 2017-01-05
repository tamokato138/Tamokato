package com.example.hongloan.timereminder.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hongloan.timereminder.R;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 03/01/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    ArrayList<String> mData;

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox chkDone;
        TextView tvTitle, tvTime, tvPriority;
        Switch swOnOff;

        public ViewHolder(View itemView) {
            super(itemView);
            chkDone = (CheckBox) itemView.findViewById(R.id.card_view_task_chk_done);
            tvTime = (TextView) itemView.findViewById(R.id.card_view_task_tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.card_view_task_tv_title);
            tvPriority = (TextView) itemView.findViewById(R.id.card_view_task_tv_priority);
            swOnOff = (Switch) itemView.findViewById(R.id.card_view_task_sw_on_off_remind);
        }

    }

    public void addDataToAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

}
