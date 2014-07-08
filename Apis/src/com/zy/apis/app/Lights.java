package com.zy.apis.app;

import com.zy.apis.R;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Lights extends Activity{
	NotificationManager nm = null; 
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.lights);
	
		Button b_open = (Button) findViewById(R.id.lights_open);
		Button b_close = (Button) findViewById(R.id.lights_close);
	
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		b_open.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Notification.Builder builder = new Notification.Builder(Lights.this)
					.setContentTitle("ContentTitle")
					.setContentInfo("content info ")
					.setContentText("content text")
					.setSmallIcon(R.drawable.ic_launcher)
					.setDefaults(Notification.DEFAULT_LIGHTS)
					.setNumber(2)
					.setWhen(12345)
					.setShowWhen(true);
				
				nm.notify(Lights.this.hashCode(), builder.build());
			}
		});
	}

}
