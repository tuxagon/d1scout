package com.scientificlegalservices.d1scout;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * This application is still in development
 * @author Kenneth Bogner
 * @version 1.0
 * TODO Replace literal text and numbers with variables both in xml & code
 * */
public final class MainActivity extends Activity {
	
	public static final String TAG = "d1scout";
	
	private EditText mHeightFeet, mHeightInches, mWeight;
	private Button mContinue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v(TAG, "MainActivity.onCreate()");
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		
		mHeightFeet = (EditText) findViewById(R.id.editTextHeightFeet);
		mHeightInches = (EditText) findViewById(R.id.editTextHeightInches);
		mWeight = (EditText) findViewById(R.id.editTextWeight);
		
		mContinue = (Button) findViewById(R.id.buttonContinue);
		mContinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Implement validation & only start TabActivity if data exists
				Log.v(TAG, "continue button onClick()");
				Integer heightInInches = (Integer.valueOf(mHeightFeet.getText().toString()) * 12) + Integer.valueOf(mHeightInches.getText().toString());
				Log.d(TAG, heightInInches.toString());
				Intent intent = new Intent(MainActivity.this, TabActivity.class);
				intent.putExtra("height", heightInInches);
				intent.putExtra("weight", Integer.valueOf(mWeight.getText().toString()));
				startActivity(intent);
			}
		});
	}
	
}
