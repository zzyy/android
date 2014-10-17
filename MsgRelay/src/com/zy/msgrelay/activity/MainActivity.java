package com.zy.msgrelay.activity;

import com.zy.msgrelay.R;
import com.zy.msgrelay.services.RelayService;
import com.zy.msgrelay.util.SmsUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends Activity {
	private static String TAG = "MainActivity";
	
	EditText e_num = null;
	EditText e_msg = null;
	Button b_sent = null;
	
	String num = null;
	String msg = null;
	
	Context mContext = null;
	SharedPreferences sp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		
		setContentView(R.layout.activity_main);
		
		e_num = (EditText) findViewById(R.id.num);
		e_msg = (EditText) findViewById(R.id.msg);
		b_sent = (Button) findViewById(R.id.sent);

        e_num.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    saveNum();
                }
            }
        });
		
		b_sent.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				num = e_num.getText().toString();
				msg = e_msg.getText().toString();
				SmsUtil.sendSms(num, msg, mContext);
			}
		});

		Switch serviceSwitch = (Switch) findViewById(R.id.serviceSwitch);
		serviceSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			Intent serviceiIntent = new Intent(MainActivity.this, RelayService.class);
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					Log.d(TAG, "----> start service");
					startService(serviceiIntent);
				}else{
					Log.d(TAG, "----> stop service");
					stopService(serviceiIntent);
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		sp = getSharedPreferences("Data", MODE_APPEND);
		String preNum = sp.getString("num", "");
		e_num.setText(preNum);
		
		e_num.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus){
                saveNum();
            }
			}
		});
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		saveNum();
	}
	
	private void saveNum(){
		Log.d(TAG, " -- saveNum");
		num = e_num.getText().toString();
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("num", num);
		editor.apply();
	}
}
