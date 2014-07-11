package com.zy.apis.app;

import com.zy.apis.R;
import com.zy.binder.aidl.IMusicPlayService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestBinderService extends Activity {
	private static String TAG = "TestBinderService";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.binder_service);
		
		Log.d(TAG, "onCreate");
		
		Intent stubServiceIntent = new Intent();
		stubServiceIntent.setAction("com.zy.binder.StubService");
		stubServiceIntent.addCategory("com.zy.binder.aidl");
		bindService(stubServiceIntent, myServiceConnection, 0);
		
		Log.v(TAG, "bind: " + stubService);
		
		((Button) findViewById(R.id.stubServiceStart)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					stubService.start("");
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				Toast.makeText(TestBinderService.this, "start", Toast.LENGTH_SHORT).show();
			}
		});;
	}
	
	private IMusicPlayService stubService = null;
	private ServiceConnection myServiceConnection = new ServiceConnection() {
		public void onServiceDisconnected(ComponentName name) {
		}
		
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v(TAG, "onServiceConnected: " + service);
			stubService = IMusicPlayService.Stub.asInterface(service);
		}
	};
}
