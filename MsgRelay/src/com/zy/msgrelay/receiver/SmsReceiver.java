package com.zy.msgrelay.receiver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zy.msgrelay.util.SmsUtil;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Telephony.Sms;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver{
	private static String TAG = "zy";
	private Context mContext = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
Log.d(TAG, ">>>>>>>>>>>> start onReceive");
		
		mContext = context;

		if(Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())){
			Toast.makeText(context, "receive sms", Toast.LENGTH_SHORT).show();
			
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			
			for(Object pdu : pdus){
				SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
				
				String senderNum = msg.getDisplayOriginatingAddress();
				String sender = SmsUtil.queryNameByNumber(senderNum, mContext);
//				String sender = null;
				sender = sender == null ? senderNum : sender;
				String msgBody = msg.getDisplayMessageBody();
				Date sendDate = new Date(msg.getTimestampMillis());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
				String sendTime = format.format(sendDate);
				
				StringBuilder sb = new StringBuilder();
				sb.append(msgBody)
					.append("\nTime: ")
					.append(sendTime)
					.append("\n Sender: ")
					.append(sender);
Log.d(TAG, "---msg: " +sb.toString());	

				SharedPreferences sp = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
				String num = sp.getString("num", "");
				num = TextUtils.isEmpty(num) ? "" : num;
				SmsUtil.sendSms(num, sb.toString(), context);
				Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
			}
		}
	}
	
}


