package com.scientificlegalservices.d1scout;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Kenneth Bogner
 * 
 * */
public final class FortyYardDashFragment extends Fragment {
	
	private View mRoot;
	private Button mPredicted40YardDashButton, mUser40YardDashButton;
	private Integer m40YardDashPredictionHeight, mUser40YardDashHeight;
	private Float mPredicted40YardDash;
	private Integer mWeight, mHeight;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRoot = inflater.inflate(R.layout.forty_yard_dash_fragment, container, false);
		return mRoot;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mPredicted40YardDashButton = (Button) mRoot.findViewById(R.id.buttonPredicted40YardDash);
		mUser40YardDashButton = (Button) mRoot.findViewById(R.id.buttonUser40YardDash);
		
		mHeight = getActivity().getIntent().getIntExtra("height", 0);
		mWeight = getActivity().getIntent().getIntExtra("weight", 0);
		mPredicted40YardDash = getPredicted40YardDash();
		
		Log.d(MainActivity.TAG, mHeight.toString() + " " + mWeight.toString());
		Log.d(MainActivity.TAG, "Predicted 40-yard Dash: " + mPredicted40YardDash);
		
		setPredicted40YardDash();
		
		mUser40YardDashButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initDialog();
			}
		});
	}
	
	private void setPredicted40YardDash() {
		// TODO Put all calculations in separate class to remove redundant code
		// TODO Add 1 pixel high lines to represent where each section ends
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		float useableScreen = (float) (height * 0.85f);
		
		Log.d(MainActivity.TAG, "Screen Height: " + Integer.valueOf(height).toString());
		Log.d(MainActivity.TAG, "Useable Screen: " + Float.valueOf(useableScreen).toString());
	
		//calculate 40-yard dash
		// TODO should i make number of sections variable based on user's input?
		int sectionPixelHeight = (int) Math.floor((useableScreen / 9)); // for now, 9 sections: 1s-9s
		
		Log.d(MainActivity.TAG, "Section Pixel Height: " + Integer.valueOf(sectionPixelHeight).toString());
		
		m40YardDashPredictionHeight = (int) (mPredicted40YardDash * sectionPixelHeight);
		
		Log.d(MainActivity.TAG, "Predicted 40 Height: " + Integer.valueOf(m40YardDashPredictionHeight).toString());
		
		ViewGroup.LayoutParams params = mPredicted40YardDashButton.getLayoutParams();
		params.height = m40YardDashPredictionHeight;
		mPredicted40YardDashButton.setLayoutParams(params);
		mPredicted40YardDashButton.setText(String.format("%.2f", mPredicted40YardDash) + " seconds");
	}
	
	private void setUser40YardDash(float userTime) {
		// TODO Put all calculations in separate class to remove redundant code
		// TODO Add 1 pixel high lines to represent where each section ends
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		float useableScreen = (float) (height * 0.85f);
		
		Log.d(MainActivity.TAG, "Screen Height: " + Integer.valueOf(height).toString());
		Log.d(MainActivity.TAG, "Useable Screen: " + Float.valueOf(useableScreen).toString());
	
		//calculate 40-yard dash
		// TODO should i make number of sections variable based on user's input?
		int sectionPixelHeight = (int) Math.floor((useableScreen / 9)); // for now, 9 sections: 1s-9s
		
		Log.d(MainActivity.TAG, "Section Pixel Height: " + Integer.valueOf(sectionPixelHeight).toString());
		
		mUser40YardDashHeight = (int) (userTime * sectionPixelHeight);
		
		Log.d(MainActivity.TAG, "User 40 Height: " + Integer.valueOf(mUser40YardDashHeight).toString());
		
		ViewGroup.LayoutParams params = mUser40YardDashButton.getLayoutParams();
		params.height = mUser40YardDashHeight;
		mUser40YardDashButton.setLayoutParams(params);
		mUser40YardDashButton.setText(String.format("%.2f", userTime) + " seconds");
	}
	
	private float getPredicted40YardDash() {
		return (float) ((mHeight * -0.002) + (mWeight * 0.007) + 4.754);
	}

	private void initDialog() {
		// set up user input dialog
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setTitle("Set 40-Yard Dash Time");
		dialog.setMessage("Please enter your 40-yard dash time in seconds");
		final EditText editText40YardDash = new EditText(getActivity());
		dialog.setView(editText40YardDash);
		
		dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {				
				String value = editText40YardDash.getText().toString();
				Log.d(MainActivity.TAG, value);
				setUser40YardDash(Float.valueOf(value));
				dialog.dismiss();
			}
			
		});
		
		dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
}
