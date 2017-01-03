package com.example.hongloan.timereminder.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hongloan.timereminder.R;

/**
 * Created by Hong Loan on 03/01/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TaskListAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton rdCheckDone;
        TextView tvTitle, tvTime, tvTimesRemind;
        Switch swOnOff;

        public ViewHolder(View itemView) {
            super(itemView);
            rdCheckDone = (RadioButton) itemView.findViewById(R.id.card_view_task_rd_done);
            tvTime = (TextView) itemView.findViewById(R.id.card_view_task_tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.card_view_task_tv_title);
            tvTimesRemind = (TextView) itemView.findViewById(R.id.card_view_task_tv_times_of_remind);
            swOnOff = (Switch) itemView.findViewById(R.id.card_view_task_sw_on_off_remind);
        }
    }

}
