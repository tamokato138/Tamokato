package com.example.hongloan.timereminder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hongloan.timereminder.R;

/**
 * Created by Hong Loan on 30/12/2016.
 */

public class LocationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_weather, parent, false);
        return view;
    }

    public static LocationFragment getInstance() {
        LocationFragment locationFragment = new LocationFragment();
        return locationFragment;
    }
}
