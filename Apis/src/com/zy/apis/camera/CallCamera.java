package com.zy.apis.camera;

import com.zy.apis.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CallCamera extends Activity {
	Button b_callCamera;
	LinearLayout container;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.call_camera);
		container = (LinearLayout) findViewById(R.id.CallCameraContainer);
		b_callCamera = ((Button) findViewById(R.id.CallCamera));
		b_callCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent callCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				callCamera.putExtra("android.intent.extras.CAMERA_FACING", 
						android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK);
				startActivityForResult(callCamera, 1);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//				CallCamera.this.finish();
			}
		});
		
		((Button)findViewById(R.id.CallFrontCamera)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent callCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				callCamera.putExtra("android.intent.extras.CAMERA_FACING", 
						android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT);
//				callCamera.putExtra(MediaStore.EXTRA_SHOW_ACTION_ICONS, false);
				startActivityForResult(callCamera, 1);
			}
		});;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == Activity.RESULT_OK){
			Bundle bundle = data.getExtras();
			Bitmap bitmap =  (Bitmap) bundle.get("data");
			
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			imageView.setImageBitmap(bitmap);

			container.addView(imageView);
		}
	}
}
