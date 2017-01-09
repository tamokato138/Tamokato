package com.example.hongloan.timereminder.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.fragment.GroupTaskListFragment;
import com.example.hongloan.timereminder.fragment.LocationFragment;
import com.example.hongloan.timereminder.fragment.TaskListFragment;
import com.example.hongloan.timereminder.fragment.WeatherFragment;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    int tabCount;
    public int[] tabIconId = {R.drawable.ic_menu, R.drawable.ic_list_task, R.drawable.ic_weather, R.drawable.ic_location};

    public PagerAdapter(FragmentManager fm, int tabCount, Context context) {
        super(fm);
        this.context = context;
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TaskListFragment taskListFragment = TaskListFragment.getInstance();
                return taskListFragment;
            case 1:
                GroupTaskListFragment groupTaskListFragment = GroupTaskListFragment.getInstance();
                return groupTaskListFragment;
            case 2:
                WeatherFragment weatherFragment = WeatherFragment.getInstance();
                return weatherFragment;
            case 3:
                LocationFragment locationFragment = LocationFragment.getInstance();
                return locationFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = ContextCompat.getDrawable(context, tabIconId[position]);
        image.setBounds(1, 1, image.getIntrinsicWidth() / 4, image.getIntrinsicHeight() / 4);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image);
        sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return sb;
    }

}
