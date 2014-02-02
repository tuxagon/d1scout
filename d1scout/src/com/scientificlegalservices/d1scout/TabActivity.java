package com.scientificlegalservices.d1scout;

import android.os.Bundle;
import android.util.Log;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;

/**
 * @author Kenneth Bogner
 * 
 * */
public class TabActivity extends Activity {

	private String[] mTabLabels = { "BENCH PRESS", "40-YARD DASH", "VERTICAL JUMP" };
	
	private ActionBar.Tab[] mTabs;
	private Fragment[] mTabFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		
		Log.v(MainActivity.TAG, "TabActivity.onCreate()");
		
		// TODO add exception handling for inappropriate data
		
		mTabs = new ActionBar.Tab[3];
		mTabFragment = new Fragment[] { 
				new BenchPressFragment(),
				new FortyYardDashFragment(),
				new VerticalJumpFragment()
		};
		
		//mPredictedBenchPress = getPredictedBenchPress();
		//Log.d(MainActivity.TAG, mPredictedBenchPress.toString());
		//mPredicted40YardDash = getPredicted40YardDash();
		//Log.d(MainActivity.TAG, mPredicted40YardDash.toString());
		//mPredictedVerticalJump = getPredictedVerticalJump();
		//Log.d(MainActivity.TAG, mPredictedVerticalJump.toString());
		
		ActionBar actionBar = getActionBar();
		
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		for (int i = 0; i < mTabs.length; i++) {
			mTabs[i] = actionBar.newTab().setText(mTabLabels[i]);
			mTabs[i].setTabListener(new TabListener(mTabFragment[i]));
			actionBar.addTab(mTabs[i]);
		}
	}
}
