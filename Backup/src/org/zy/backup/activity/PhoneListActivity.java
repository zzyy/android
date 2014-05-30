package org.zy.backup.activity;

import org.zy.backup.Backup;
import org.zy.backup.R;
import org.zy.backup.adapt.PhoneListAdapt;
import org.zy.backup.model.Contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
		
		phoneList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Contact contact =  (Contact) parent.getItemAtPosition(position);
				Toast.makeText(PhoneListActivity.this, contact.name, Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:" + contact.number) );
				startActivity(intent);
			}
		});
	}

	
	
}
