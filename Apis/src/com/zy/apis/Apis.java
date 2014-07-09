package com.zy.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Apis extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String path = intent.getStringExtra("codePath");
		if (path == null) {
			path = "";
		}

		setListAdapter(new SimpleAdapter(this, getData(path),
				R.layout.simple_list_item, new String[] { "itemName" },
				new int[] { R.id.itemText }));
	}

	private List<Map<String, Object>> getData(String prefix) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		Intent intent = new Intent("com.zy.apis.main");
		intent.addCategory("com.zy.apis.default_category");

		PackageManager pm = getPackageManager();
		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
		if (null == resolveInfos)
			return result;
		
		Map<String, Boolean> entries = new HashMap<String, Boolean>();
		for (ResolveInfo resolveInfo : resolveInfos) {
			CharSequence lableSeq = resolveInfo.loadLabel(pm);
			String lable = lableSeq != null ? lableSeq.toString()
					: resolveInfo.activityInfo.name;

			if ("".equals(prefix) || lable.startsWith(prefix)) {

				String itemName = null;
				Intent itemIntent = new Intent();

				String[] prePath = prefix.split("/");
				String[] path = lable.split("/");
				if (("".equals(prefix) ? 0 : prePath.length) == path.length - 1) {
					itemName = path[path.length - 1];
					itemIntent.setClassName(
									resolveInfo.activityInfo.applicationInfo.packageName,
									resolveInfo.activityInfo.name);
					
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("itemName", itemName);
					item.put("intent", itemIntent);
					result.add(item);
				} else {
					itemName = "".equals(prefix) ? path[0] : path[prePath.length];
					if (entries.get(itemName) == null) {
						itemIntent.putExtra("codePath", prefix + itemName + "/");
						itemIntent.setClass(this, Apis.class);
						entries.put(itemName, true);
						
						Map<String, Object> item = new HashMap<String, Object>();
						item.put("itemName", itemName);
						item.put("intent", itemIntent);
						result.add(item);
					}
				}
			}
		}
		return result;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> data = (Map<String, Object>) l
				.getItemAtPosition(position);
		Intent intent = (Intent) data.get("intent");
		startActivity(intent);
	}
}
