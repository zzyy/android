package org.zy.backup.activity;

import java.util.ArrayList;
import java.util.List;

import org.zy.backup.Backup;
import org.zy.backup.R;
import org.zy.backup.adapt.PhoneListAdapt;
import org.zy.backup.model.Contact;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PhoneListActivity extends Activity {
	ArrayAdapter<Contact> adapter;
	ListView phoneList;
	View footerView;
	boolean isLoadFinish = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.phone_list);
		
		phoneList =  (ListView) findViewById(R.id.phoneList);
		//set footer ∑÷≈˙º”‘ÿ
		footerView = getLayoutInflater().inflate(R.layout.footer, null);
		phoneList.addFooterView(footerView);
		
		adapter = new PhoneListAdapt(this);
		List<Contact> contacts = Backup.newInstance().contacts;
		for(int i = 0; i<15&&i<contacts.size(); i++){
			adapter.addAll(Backup.newInstance().contacts.get(i));
		}
		phoneList.setAdapter(adapter);
		
		phoneList.removeFooterView(footerView);
		
		
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
		
		phoneList.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				Log.i("PhoneList", "onScroll(firstVisibleItem="+ firstVisibleItem+ ",visibleItemCount="+
						visibleItemCount+ ",totalItemCount="+ totalItemCount+ ")");
				
				final int lastItemId = phoneList.getLastVisiblePosition();
				if((lastItemId + 1) == totalItemCount && isLoadFinish){
					isLoadFinish = false;
					phoneList.addFooterView(footerView);
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								Thread.sleep(3000);
								
								List<Contact> result = new ArrayList<Contact>();
								List<Contact> contacts = Backup.newInstance().contacts;
								for(int i = lastItemId +1 ; i < contacts.size() &&	i <= lastItemId +2; i++){
									result.add(contacts.get(i));
								}
								handler.sendMessage(handler.obtainMessage(1, result));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).start();
				}
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}
		});
	}

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			adapter.addAll((List<Contact>) msg.obj);
			adapter.notifyDataSetChanged();
			phoneList.removeFooterView(footerView);
			
			isLoadFinish = true;
		}
		
	};
	
}
