package com.scientificlegalservices.scoutingreport;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * @author Kenneth Bogner
 * */
public final class ScoutingReportPagerAdapter extends FragmentPagerAdapter {

	private List<Fragment> mFragments;
	
	public ScoutingReportPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		mFragments = fragments;
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}
}
