package com.zy.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Apis extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String path = intent.getStringExtra("");
		if(path ==null){
			path = "";
		}
		
		setListAdapter(new SimpleAdapter(this, getData(path), 
				R.layout.simple_list_item, new String[]{"itemName"}, new int[]{R.id.itemText}));
	}

	private List<Map<String, Object>> getData(String path) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		Intent intent = new Intent("com.zy.apis.main");
		intent.addCategory("com.zy.apis.default_category");
		
		PackageManager pm = getPackageManager();
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
		if(null == resolveInfos)
			return result;
		for(ResolveInfo resolveInfo :resolveInfos){
			CharSequence lableSeq = resolveInfo.loadLabel(pm);
			String[] names = resolveInfo.activityInfo.name.split(".");
			String itemName = lableSeq != null ? lableSeq.toString() : names[names.length-1];
			
			Map<String , Object> item = new HashMap<String, Object>();
			item.put("itemName" , itemName);
			Intent itemIntent = new Intent();
			
			int index = resolveInfo.activityInfo.name.lastIndexOf(".");
			itemIntent.setClassName(resolveInfo.activityInfo.applicationInfo.packageName, 
					resolveInfo.activityInfo.name);
			
//			itemIntent.setClassName(resolveInfo.activityInfo.applicationInfo.packageName + resolveInfo.activityInfo.name.substring(0, index), 
//					resolveInfo.activityInfo.name.substring(index +1));
			item.put("intent", itemIntent);
			result.add(item);
		}
		
		return result;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> data = (Map<String, Object>) l.getItemAtPosition(position);
		Intent intent = (Intent) data.get("intent");
		startActivity(intent);
	}
}
