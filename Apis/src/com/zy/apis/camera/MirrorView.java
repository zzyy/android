package com.zy.apis.camera;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MirrorView extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG ="MirrorView"; 
	Camera camera;
	SurfaceHolder surfaceHolder;
	
	public MirrorView(Context context, Camera camera) {
		super(context);
		this.camera = camera;
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			camera.setPreviewDisplay(surfaceHolder);
			camera.startPreview();
		} catch (IOException e) {
			Log.e(TAG, "surfaceCreated");
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
	}


}
