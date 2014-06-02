package org.zy.backup.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.zy.backup.Backup;
import org.zy.backup.R;
import org.zy.backup.fragment.MainFragment;
import org.zy.backup.model.Contact;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.preference.PreferenceManager;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

public class MainActivity extends Activity {

	private String Tag = "MainActivity";
	public Cursor cursor;
	
	private ContentResolver contentResolver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(Tag, "onCreate");
		
		contentResolver = getContentResolver();
		cursor = contentResolver.query(Phone.CONTENT_URI, null, null, null, null);
		int count = cursor.getCount();
		
		List<Contact> contacts = new ArrayList<Contact>();
		while(cursor.moveToNext()){
			Contact contact = new Contact();
			int idIndex = cursor.getColumnIndex(Phone._ID);
			contact.id = cursor.getInt(idIndex);
			int nameIndex = cursor.getColumnIndex(Phone.DISPLAY_NAME);
			contact.name = cursor.getString(nameIndex);
			int phoneIndex = cursor.getColumnIndex(Phone.NUMBER);
			contact.number = cursor.getString(phoneIndex);
			int photoIndex = cursor.getColumnIndex(Phone.PHOTO_ID);
			long phoneId = cursor.getLong(photoIndex);
			if(phoneId>0){
				Uri photoUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, phoneId); 
				InputStream in = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, photoUri);
				contact.photo = BitmapFactory.decodeStream(in);
			}else{
				contact.photo = null;
			}
			
			contacts.add(contact);
		}
		Backup.newInstance().contacts = contacts;
		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Toast.makeText(this, String.valueOf(sharedPreferences.getBoolean("checkbox", false)), Toast.LENGTH_SHORT).show();
		
		Log.d("Phone", String.valueOf(count));
		Bundle bundle = new Bundle();
		bundle.putInt("count", count);
		
		MainFragment mainFragment = new MainFragment();
		mainFragment.setArguments(bundle);
		
		android.app.FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.container, mainFragment);
		ft.commit();
		
	}

	

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(Tag, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(Tag, "onResume");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, Setting.class);
			startActivity(intent);
			break;

		} 
		return super.onOptionsItemSelected(item);
	}
}
