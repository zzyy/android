package com.zy.apis.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zy.apis.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Items extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new SimpleAdapter(this, getData(), R.layout.simple_list_item, 
				new String[]{"itemName"}, new int[]{R.id.itemText} ));
	}

	private List< Map<String, Object>> getData() {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("itemName", "Light");
		item.put("intent", new Intent(this, Lights.class));
		
		result.add(item);
		
		return result;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Map<String, Object> data = (Map<String, Object>) l.getItemAtPosition(position);
		Intent intent = (Intent) data.get("intent");
		startActivity(intent);
	}
}
