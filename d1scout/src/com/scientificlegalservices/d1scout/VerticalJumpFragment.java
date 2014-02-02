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
public final class VerticalJumpFragment extends Fragment {
	
	private View mRoot;
	private Button mPredictedVerticalJumpButton, mUserVerticalJumpButton;
	private Integer mVerticalJumpPredictionHeight, mUserVerticalJumpHeight;
	private Integer mPredictedVerticalJump;
	private Integer mWeight, mHeight;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRoot = inflater.inflate(R.layout.vertical_jump_fragment, container, false);
		return mRoot;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mPredictedVerticalJumpButton = (Button) mRoot.findViewById(R.id.buttonPredictedVerticalJump);
		mUserVerticalJumpButton = (Button) mRoot.findViewById(R.id.buttonUserVerticalJump);
		
		mHeight = getActivity().getIntent().getIntExtra("height", 0);
		mWeight = getActivity().getIntent().getIntExtra("weight", 0);
		mPredictedVerticalJump = getPredictedVerticalJump();
		
		Log.d(MainActivity.TAG, mHeight.toString() + " " + mWeight.toString());
		Log.d(MainActivity.TAG, "Predicted Vertical Jump: " + mPredictedVerticalJump);
		
		setPredictedVerticalJump();
		
		mUserVerticalJumpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initDialog();
			}
			
		});
	}
	
	private void setPredictedVerticalJump() {
		// TODO Put all calculations in separate class to remove redundant code
		// TODO Add 1 pixel high lines to represent where each section ends
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		float useableScreen = (float) (height * 0.85f);
		
		Log.d(MainActivity.TAG, "Screen Height: " + Integer.valueOf(height).toString());
		Log.d(MainActivity.TAG, "Useable Screen: " + Float.valueOf(useableScreen).toString());
	
		//calculate vertical jump
		// TODO should i make number of sections variable based on user's input?
		int sectionPixelHeight = (int) Math.floor((useableScreen / 6)); // for now, 6 sections: 10in-60in
		
		Log.d(MainActivity.TAG, "Section Pixel Height: " + Integer.valueOf(sectionPixelHeight).toString());
		
		mVerticalJumpPredictionHeight = (int) Math.floor(mPredictedVerticalJump / 10); // 10in per section
		mVerticalJumpPredictionHeight *= sectionPixelHeight;
		
		Log.d(MainActivity.TAG, "Predicted Vertical Height: " + Integer.valueOf(mVerticalJumpPredictionHeight).toString());
		
		ViewGroup.LayoutParams params = mPredictedVerticalJumpButton.getLayoutParams();
		params.height = mVerticalJumpPredictionHeight;
		mPredictedVerticalJumpButton.setLayoutParams(params);
		mPredictedVerticalJumpButton.setText(mPredictedVerticalJump.toString() + " inches");
	}
	
	private void setUserVerticalJump(int userJump) {
		// TODO Put all calculations in separate class to remove redundant code
		// TODO Add 1 pixel high lines to represent where each section ends
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		float useableScreen = (float) (height * 0.85f);
		
		Log.d(MainActivity.TAG, "Screen Height: " + Integer.valueOf(height).toString());
		Log.d(MainActivity.TAG, "Useable Screen: " + Float.valueOf(useableScreen).toString());
	
		//calculate vertical jump
		// TODO should i make number of sections variable based on user's input?
		int sectionPixelHeight = (int) Math.floor((useableScreen / 6)); // for now, 6 sections: 10in-60in
		
		Log.d(MainActivity.TAG, "Section Pixel Height: " + Integer.valueOf(sectionPixelHeight).toString());
		
		mUserVerticalJumpHeight = (int) Math.floor(userJump / 10); // 10in per section
		mUserVerticalJumpHeight *= sectionPixelHeight;
		
		Log.d(MainActivity.TAG, "User Vertical Height: " + Integer.valueOf(mUserVerticalJumpHeight).toString());
		
		ViewGroup.LayoutParams params = mUserVerticalJumpButton.getLayoutParams();
		params.height = mUserVerticalJumpHeight;
		mUserVerticalJumpButton.setLayoutParams(params);
		mUserVerticalJumpButton.setText(Integer.valueOf(userJump).toString() + " inches");
	}
	
	private int getPredictedVerticalJump() {
		return (int) (Math.floor((mHeight * 0.336) + (mWeight * -0.080) + 27.144));
	}
	
	private void initDialog() {
		// set up user input dialog
		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		dialog.setTitle("Set Vertical Jump");
		dialog.setMessage("Please enter your vertical jump in inches");
		final EditText editTextVerticalJump = new EditText(getActivity());
		dialog.setView(editTextVerticalJump);
		
		dialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {				
				String value = editTextVerticalJump.getText().toString();
				Log.d(MainActivity.TAG, value);
				setUserVerticalJump(Integer.valueOf(value));
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
