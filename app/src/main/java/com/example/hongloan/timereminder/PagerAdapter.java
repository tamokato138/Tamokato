package com.example.hongloan.timereminder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hongloan.timereminder.Tabs.GroupTaskList;
import com.example.hongloan.timereminder.Tabs.LocationTab;
import com.example.hongloan.timereminder.Tabs.TaskList;
import com.example.hongloan.timereminder.Tabs.WeatherTab;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;
    public static final String TITLE_TAB_1 = "Task";
    public static final String TITLE_TAB_2 = "To do";
    public static final String TITLE_TAB_3 = "Weather";
    public static final String TITLE_TAB_4 = "Location";


    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
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
        String title = "";
        switch (position) {
            case 0:
                title = TITLE_TAB_1;
                break;
            case 1:
                title = TITLE_TAB_2;
                break;
            case 2:
                title = TITLE_TAB_3;
                break;
            case 3:
                title = TITLE_TAB_4;
                break;
        }
        return title;
    }
}
