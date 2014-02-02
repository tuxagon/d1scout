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
public final class BenchPressFragment extends Fragment {

	private View mRoot;
	private Button mPredictedBenchPressButton, mUserBenchPressButton;
	private Integer mBenchPressPredictionHeight, mBenchPressUserHeight;
	private Integer mPredictedBenchPress;
	private Integer mWeight, mHeight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mRoot = inflater.inflate(R.layout.bench_press_fragment, container, false);	
		return mRoot;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mPredictedBenchPressButton = (Button) mRoot.findViewById(R.id.buttonPredictedBenchPress);
		mUserBenchPressButton = (Button) mRoot.findViewById(R.id.buttonUserBenchPress);
		
		mHeight = getActivity().getIntent().getIntExtra("height", 0);
		mWeight = getActivity().getIntent().getIntExtra("weight", 0);
		mPredictedBenchPress = getPredictedBenchPress();
		
		Log.d(MainActivity.TAG, mHeight.toString() + " " + mWeight.toString());
		Log.d(MainActivity.TAG, "Predicted Bench Press: " + mPredictedBenchPress);
		
		setPredictedBenchPress();
		
		mUserBenchPressButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				initDialog();
			}
			
		});
	}
	
	private void setPredictedBenchPress() {
		// TODO Put all calculations in separate class to remove redundant code
		// TODO Add 1 pixel high lines to represent where each section ends
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		float useableScreen = (float) (height * 0.85f);
		
		Log.d(MainActivity.TAG, "Screen Height: " + Integer.valueOf(height).toString());
		Log.d(MainActivity.TAG, "Useable Screen: " + Float.valueOf(useableScreen).toString());
	
		//calculate Bench Press
		// TODO should i make number of sections variable based on user's input?
		int sectionPixelHeight = (int) Math.floor((useableScreen / 6)); // for now, 6 sections: 100lbs-600lbs
		
		Log.d(MainActivity.TAG, "Section Pixel Height: " + Integer.valueOf(sectionPixelHeight).toString());
		
		mBenchPressPredictionHeight = (int) Math.floor(mPredictedBenchPress / 100); // 100lbs per section
		mBenchPressPredictionHeight *= sectionPixelHeight;
		
		Log.d(MainActivity.TAG, "Predicted Bench Height: " + Integer.valueOf(mBenchPressPredictionHeight).toString());
		
		ViewGroup.LayoutParams params = mPredictedBenchPressButton.getLayoutParams();
		params.height = mBenchPressPredictionHeight;
		mPredictedBenchPressButton.setLayoutParams(params);
		mPredictedBenchPressButton.setText(mPredictedBenchPress.toString() + " lbs");
	}
	
	private void setUserBenchPress(int userBench) {
		// TODO Put all calculations in separate class to remove redundant code
		// TODO Add 1 pixel high lines to represent where each section ends
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		float useableScreen = (float) (height * 0.85f);
		
		Log.d(MainActivity.TAG, "Screen Height: " + Integer.valueOf(height).toString());
		Log.d(MainActivity.TAG, "Useable Screen: " + Float.valueOf(useableScreen).toString());
	
		//calculate Bench Press
		// TODO should i make number of sections variable based on user's input?
		int sectionPixelHeight = (int) Math.floor((useableScreen / 6)); // for now, 6 sections: 100lbs-600lbs
		
		Log.d(MainActivity.TAG, "Section Pixel Height: " + Integer.valueOf(sectionPixelHeight).toString());
		
		mBenchPressUserHeight = (int) Math.floor(userBench / 100); // 100lbs per section
		mBenchPressUserHeight *= sectionPixelHeight;
		
		Log.d(MainActivity.TAG, "User Bench Height: " + Integer.valueOf(mBenchPressUserHeight).toString());
		
		ViewGroup.LayoutParams params = mUserBenchPressButton.getLayoutParams();
		params.height = mBenchPressUserHeight;
		mUserBenchPressButton.setLayoutParams(params);
		mUserBenchPressButton.setText(Integer.valueOf(userBench).toString() + " lbs");
	}
	
	private int getPredictedBenchPress() {
		return (int) (Math.floor((mHeight * -7.764) + (mWeight * 1.432) + 642.447));
	}
	
	private void initDialog() {
		// set up user input dialog
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setTitle("Set Bench Press");
		dialog.setMessage("Please enter how much you can bench press in pounds");
		final EditText editTextBenchPress = new EditText(getActivity());
		dialog.setView(editTextBenchPress);
		
		dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {				
				String value = editTextBenchPress.getText().toString();
				Log.d(MainActivity.TAG, value);
				setUserBenchPress(Integer.valueOf(value));
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
