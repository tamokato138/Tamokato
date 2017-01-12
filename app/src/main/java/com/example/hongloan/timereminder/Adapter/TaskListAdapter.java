package com.example.hongloan.timereminder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.database.TaskDto;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 03/01/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    ArrayList<TaskDto> mData = new ArrayList<>();

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvTime.setText(mData.get(position).getDate() + " - " + mData.get(position).getTime());
        holder.tvPriority.setText(showPriority(mData.get(position).getPriority()));
        holder.swOnOff.setChecked(mData.get(position).isNotify());
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
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

    public void addDataToAdapter(ArrayList<TaskDto> mData) {
        this.mData.clear();
        this.mData = mData;
    }

    private String showPriority(int priority) {
        String p = "";
        switch (priority) {
            case 1:
                p = "!";
                break;
            case 2:
                p = "!!";
                break;
            case 3:
                p = "!!!";
                break;
        }
        return p;
    }

}
