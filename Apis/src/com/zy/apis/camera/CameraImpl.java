package com.zy.apis.camera;

import com.zy.apis.R;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.FrameLayout;

public class CameraImpl extends Activity {
	private static final String TAG = "Camera";
	
	private MirrorView mMirrorView;
	FrameLayout preViewContainer;
	Camera mCamera;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.camera);
		preViewContainer = (FrameLayout) findViewById(R.id.preViewContainer);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		int cameraNum = Camera.getNumberOfCameras();
		Log.i(TAG, "cameraNum: " + cameraNum);
		mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
		mCamera.autoFocus(null);	//×Ô¶¯¶Ô½¹
		mMirrorView = new MirrorView(this, mCamera);
		preViewContainer.addView(mMirrorView);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mCamera.release();
		mCamera = null;
	}
}
