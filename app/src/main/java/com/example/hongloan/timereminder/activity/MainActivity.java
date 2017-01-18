package com.example.hongloan.timereminder.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hongloan.timereminder.R;
import com.example.hongloan.timereminder.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter adapter;
    Toolbar toolbar;
    int tabCount = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customTab();
        customToolBar();

    }

    public void customTab() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < tabCount; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void customToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager.addOnPageChangeListener(this);
    }

    //setTitle for Toolbar when scroll
    public void setTitleToolbar(String name) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
        }
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position) {
            case 0:
                setTitleToolbar(getResources().getString(R.string.main_activity_toolbar_title_task_list));
                break;
            case 1:
                setTitleToolbar(getResources().getString(R.string.main_activity_toolbar_title_undone_task));
                break;
            case 2:
                setTitleToolbar(getResources().getString(R.string.main_activity_toolbar_title_weather));
                break;
            case 3:
                setTitleToolbar(getResources().getString(R.string.main_activity_toolbar_title_location));
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
//do Nothing
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//do Nothing
    }
}

