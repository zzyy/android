package com.zy.msgrelay.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SmsState extends BroadcastReceiver {
	private static String TAG = "zy";

	@Override
	public void onReceive(Context context, Intent intent) {
		switch (getResultCode()) {
		case Activity.RESULT_OK:
			Log.d(TAG, "SmsState, success ");
			Toast.makeText(context, "send success", Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(context, "fail!!", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "SmsState, fail!!! ");
		}
	}

}