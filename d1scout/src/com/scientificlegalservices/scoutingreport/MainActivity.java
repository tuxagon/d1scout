package com.scientificlegalservices.scoutingreport;

//import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
//import android.text.Editable;
//import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;


/** 
 * @author Kenneth Bogner
 * */
public class MainActivity extends FragmentActivity {

	//private EditText mNameEditText;
	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;
	//private ArrayList<String> mPrompts;
	private static final String TAG = "scouting.report";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*Log.d(TAG,"Before retrieving fragments for mPrompts");
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		mPrompts = new ArrayList<String>();
		Log.d(TAG,"After initializing mPrompts");
		for (int i = 0; i < fragments.size(); i++) {
			mPrompts.add(fragments.get(i).getClass().getName());
			Log.d(TAG,"After setting mPrompts[i]");
			Log.d(TAG,"--- " + mPrompts.get(i));
		}
		Log.d(TAG,"After mPrompts has each fragment name");
		*/
		
		initializeViewPager();
		
		setTypefaces((ViewGroup) findViewById(R.id.scrollView_form).getRootView());
	}
	
	private void initializeViewPager() {
		Log.d(TAG,"initializeViewPager entered");
		List<Fragment> fragments = new Vector<Fragment>();
		
		/*for (int i = 0; i < mPrompts.size(); i++) {
			fragments.add(Fragment.instantiate(this, mPrompts.get(i)));
		}
		Log.d(TAG,"fragments added to list");
		*/
		
		fragments.add(Fragment.instantiate(this, NameFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, HeightFragment.class.getName()));
		
		Log.d(TAG,"Begin ViewPager and PagerAdapter initializing");
		mPagerAdapter = new ScoutingReportPagerAdapter(getSupportFragmentManager(), fragments);
		mViewPager = (ViewPager) findViewById(R.id.viewPager_form);
		mViewPager.setAdapter(mPagerAdapter);
		Log.d(TAG,"initializeViewPager done");
	}

	/*private void setEditTextListener() {
		this.mNameEditText.addTextChangedListener(new TextWatcher() {
		
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() > 0) {
					Button btn = (Button) findViewById(R.id.button_name);
					btn.setVisibility(View.VISIBLE);
				}
				else {
					Button btn = (Button) findViewById(R.id.button_name);
					btn.setVisibility(View.INVISIBLE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				return;
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				return;
			}
		});
	}*/

	private void setTypefaces(ViewGroup container) {
		Log.d(TAG,"setTypefaces entered");
		if (container == null) {
			return;
		}
		
		Typeface roboto_bold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
		Typeface roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		Typeface roboto_light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		TextView welcome = (TextView) findViewById(R.id.textView_welcome);
		
		int childCount = container.getChildCount();
		
		for (int i = 0; i < childCount; i++) {
			View child = container.getChildAt(i);
			if (child instanceof TextView && ((TextView) child).getText().toString().equals(welcome.getText().toString())) {
				((TextView) child).setTypeface(roboto_bold);
			}
			else if (child instanceof TextView) {
				((TextView) child).setTypeface(roboto_light);
			}
			else if (child instanceof EditText) {
				((EditText) child).setTypeface(roboto_thin);
			}
			else if (child instanceof Button) {
				((Button) child).setTypeface(roboto_light);
			}
		}
		Log.d(TAG,"setTypefaces done");
	}
}
