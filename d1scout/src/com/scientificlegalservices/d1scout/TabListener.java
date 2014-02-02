package com.scientificlegalservices.d1scout;

import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;

/**
 * @author Kenneth Bogner
 * 
 * */
public class TabListener implements ActionBar.TabListener {
 
    private Fragment mFragment;
 
    public TabListener(Fragment fragment) {
        mFragment = fragment;
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.frameLayoutFragment, mFragment);
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(mFragment);
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // TODO Auto-generated method stub
    	
    }
}