package com.example.rachel.myfirstapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wifidirect.milan.wifidirect.fragments.DevicesList;
import com.wifidirect.milan.wifidirect.services.WifiDirectService;

public class UserActivity extends AppCompatActivity implements ServiceConnection {
    public static WifiDirectService mService;
    public static boolean isBind;
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.rachel.myfirstapp.R.layout.activity_user);
        mToolbar = (Toolbar) findViewById(com.example.rachel.myfirstapp.R.id.toolbar);
        setSupportActionBar(mToolbar);

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, new DevicesList());
            fragmentTransaction.commit();
        }

        // start
            startAndBindService();
    }

    /** Start and bind service. */
    public void startAndBindService() {
        Intent intent = new Intent(this, WifiDirectService.class);
        startService(intent);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    /** Stop and unbind service. */
    public void stopAndUnbindService() {
        Intent intent = new Intent(this, WifiDirectService.class);
        stopService(intent);
        unbindService(this);
    }


    // connect - dissconnect from service
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mService = ((WifiDirectService.ServiceBinder) service).getService();
        isBind = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isBind = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // stop service
        stopAndUnbindService();
    }
}
