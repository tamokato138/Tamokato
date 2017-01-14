package com.example.hongloan.timereminder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.database.TaskDto;
import com.example.hongloan.timereminder.interfaces.OnAdapterListener;

import java.util.ArrayList;

/**
 * Created by Hong Loan on 03/01/2017.
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
    ArrayList<TaskDto> mData = new ArrayList<>();
    Context context;
    private OnAdapterListener onAdapterListener;
    private int selectedPosition;


    public TaskListAdapter(Context context) {
        this.context = context;
    }

    public void setOnAdapterListener(OnAdapterListener onAdapterListener) {
        this.onAdapterListener = onAdapterListener;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("Huey", "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_task, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final TaskListAdapter.ViewHolder holder, final int position) {
        Log.d("Huey", "onBindViewHolder " + position);
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvTime.setText(mData.get(position).getDate() + " - " + mData.get(position).getTime());
        holder.tvPriority.setText(showPriority(mData.get(position).getPriority()));
        holder.swOnOff.setChecked(mData.get(position).isNotify());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setSelectedPosition(position);
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            Log.d("Huey", "Size: 0");
            return 0;
        }
        Log.d("Huey", "Size:" + mData.size());
        return mData.size();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        CheckBox chkDone;
        TextView tvTitle, tvTime, tvPriority;
        Switch swOnOff;
        LinearLayout llContainer;


        public ViewHolder(View itemView){
            super(itemView);
            chkDone = (CheckBox) itemView.findViewById(R.id.card_view_task_chk_done);
            tvTime = (TextView) itemView.findViewById(R.id.card_view_task_tv_time);
            tvTitle = (TextView) itemView.findViewById(R.id.card_view_task_tv_title);
            tvPriority = (TextView) itemView.findViewById(R.id.card_view_task_tv_priority);
            swOnOff = (Switch) itemView.findViewById(R.id.card_view_task_sw_on_off_remind);
            llContainer = (LinearLayout) itemView.findViewById(R.id.card_view_task_ll_container);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, R.id.action_delete, Menu.NONE, R.string.menu_context_item_delete);
            menu.add(Menu.NONE, R.id.action_edit, Menu.NONE, R.string.menu_context_item_edit);
        }
    }


    public void addDataToAdapter(ArrayList<TaskDto> data) {
        this.mData.clear();
        this.mData.addAll(data);
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
