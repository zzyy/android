package org.zy.backup.activity;

import org.zy.backup.Backup;
import org.zy.backup.R;
import org.zy.backup.adapt.PhoneListAdapt;
import org.zy.backup.model.Contact;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PhoneListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.phone_list);
		
		ListView phoneList =  (ListView) findViewById(R.id.phoneList);
		ArrayAdapter<Contact> adapter = new PhoneListAdapt(this);
		adapter.addAll(Backup.newInstance().contacts);
		phoneList.setAdapter(adapter);
	}

	
	
}
