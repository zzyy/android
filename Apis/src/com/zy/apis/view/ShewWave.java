package com.zy.apis.view;


import com.zy.apis.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * used to learn surfaceview
 */
public class ShewWave extends Activity {
	
	int width, height = 320;
	SurfaceView surfaceView = null;
	SurfaceHolder holder = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout .show_wave);
		
		WindowManager vm =  (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		width = vm.getDefaultDisplay().getWidth();
		int center = height / 2;
		
		//paint
		holder = surfaceView.getHolder();
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(3);
		
		
	}
}
