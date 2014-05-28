package org.zy.backup.fragment;

import org.zy.backup.R;
import org.zy.backup.activity.PhoneListActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment {
	private String Tag = "MainFragment";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Log.d(Tag, "onActivityCreated");
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d(Tag, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(Tag, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		int count = getArguments().getInt("count");
		TextView countView = (TextView) view.findViewById(R.id.phoneCount); 
		countView.setText(String.valueOf(count) );
		
		Button btn = (Button) view.findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), PhoneListActivity.class);
				getActivity().startActivity(intent);
			}
		});
		
		return view;
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d(Tag, "onAttach");
		
	}
}
