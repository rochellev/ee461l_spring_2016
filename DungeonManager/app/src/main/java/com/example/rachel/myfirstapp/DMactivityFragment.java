package com.example.rachel.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wifidirect.milan.wifidirect.fragments.DevicesList;


/**
 * A placeholder fragment containing a simple view.
 */
public class DMactivityFragment extends Fragment {
    Fragment devicesList;
    public DMactivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

        listInRange("Select Players");

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dmactivity,
                container, false);
        //list users in range to select as the DM
        return view;


    }

    private void listInRange(String title) {
        devicesList = new DevicesList();
    }
}