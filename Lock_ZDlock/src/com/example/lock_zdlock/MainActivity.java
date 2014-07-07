package com.example.lock_zdlock;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private SliderRelativeLayout sliderRelativeLayout = null;
	private ImageView iv_arrow = null;
	private AnimationDrawable animArrowDrawable = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sliderRelativeLayout = (SliderRelativeLayout) findViewById(R.id.slider_layout);
		iv_arrow = (ImageView) findViewById(R.id.arrow);
		animArrowDrawable = (AnimationDrawable) iv_arrow.getBackground();
		
		sliderRelativeLayout.setMainHandler(mHandler);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mHandler.postDelayed(animArrowTask, 300);
	}
	
	private Handler mHandler = new Handler();
	
	private Runnable animArrowTask = new Runnable() {
		
		@Override
		public void run() {
			animArrowDrawable.start();
			mHandler.postDelayed(animArrowTask, 300);
		}
	};
}
