package org.zy.backup.activity;

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
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
			contacts.add(contact);
		}
		Backup.newInstance().contacts = contacts;
		
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


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
