package com.zy.msgrelay.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zy.msgrelay.receiver.SmsReceiver;
import com.zy.msgrelay.receiver.SmsState;
import com.zy.msgrelay.util.SmsUtil;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.provider.Telephony;
import android.provider.Telephony.Sms;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class RelayService extends Service {
	private static String TAG = "RelayService";
	public static String StopServiceAction = "com.zy.RelayService_Stop";
	
	private Context mContext = null;
	BroadcastReceiver smsReceiver = null;
	BroadcastReceiver smsStateReceiver = null;
	BroadcastReceiver contralReceiver = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.d(TAG, " --- onCreate");
		
		IntentFilter smsReceiverFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
		smsReceiver = new SmsReceiver();
		registerReceiver(smsReceiver, smsReceiverFilter);
		
		IntentFilter smsStateFilter = new IntentFilter(SmsUtil.SEND_SMS_ACTION);
		smsStateReceiver = new SmsState();
		registerReceiver(smsStateReceiver, smsStateFilter);
		
		contralReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				RelayService.this.stopSelf();
			}
		};
		
		registerReceiver(contralReceiver, new IntentFilter(RelayService.StopServiceAction));
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, " --- onDestory");

		unregisterReceiver(smsReceiver);
		unregisterReceiver(smsStateReceiver);
		unregisterReceiver(contralReceiver);
	}
	
}

