package com.zy.msgrelay.util;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class SmsUtil {
	private static String TAG = "zy";
	
	public static void sendSms(String num, String msg, Context mContext){
		Log.d(TAG, "----------sentSms, num= " + num +", msg= " + msg);
		if(TextUtils.isEmpty(num) || msg ==null){
			return;
		}
		SmsManager smsManager = SmsManager.getDefault();
		List<String> msgs = smsManager.divideMessage(msg);
		
		Intent intent = new Intent(SEND_SMS_ACTION);
		PendingIntent sentPI = PendingIntent.getBroadcast(mContext, 0, intent, 0);
		
		for(String msgContent : msgs){
			smsManager.sendTextMessage(num, null, msg, sentPI, null);
		}
	}
	
	public static String SEND_SMS_ACTION = "com.zy.SEND_SMS_ACTION";
	
	public static String queryNameByNumber(String num, Context mContext){
		ContentResolver cr = mContext.getContentResolver();
		Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{Phone.DISPLAY_NAME}, 
							ContactsContract.CommonDataKinds.Phone.NUMBER + "=?", new String[]{num}, null);
		if(cursor != null && cursor.getCount()>0 ){
			cursor.moveToFirst();
			int index = cursor.getColumnIndex(Phone.DISPLAY_NAME);
			return cursor.getString(index);
		}
		
		return null;
	}
}
