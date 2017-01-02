package com.example.hongloan.timereminder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.hongloan.timereminder.Tabs.GroupTaskList;
import com.example.hongloan.timereminder.Tabs.LocationTab;
import com.example.hongloan.timereminder.Tabs.TaskList;
import com.example.hongloan.timereminder.Tabs.WeatherTab;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    int tabCount;
    public static int[] tabIconId = {R.drawable.ic_plus, R.drawable.ic_justify_paragrap, R.drawable.ic_cloud, R.drawable.ic_location};

//    public static String[] tabTitle = {"Task", "To do", "Weather", "Location"};

    public PagerAdapter(FragmentManager fm, int tabCount, Context context) {
        super(fm);
        this.context = context;
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TaskList taskList = new TaskList();
                return taskList;
            case 1:
                GroupTaskList groupTaskList = new GroupTaskList();
                return groupTaskList;
            case 2:
                WeatherTab weatherTab = new WeatherTab();
                return weatherTab;
            case 3:
                LocationTab locationTab = new LocationTab();
                return locationTab;
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
        //Cần coi lại
        Drawable image = ContextCompat.getDrawable(context, tabIconId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image);
        sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        return sb;
    }

}
