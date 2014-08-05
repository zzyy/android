package com.zy.apis.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zy.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PullToRefresh extends Activity {

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull_to_refresh);
		
		List<HashMap<String, String>> data = new ArrayList<HashMap<String,String>>();
		for(int i=0; i<10; i ++ ){
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("info", "the " + i+ " item");
			data.add(item);
		}
		
		
		listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new SimpleAdapter(this, data, R.layout.simple_list_item, 
				new String[]{"info"}, new int[]{R.id.itemText}));
	}
}
