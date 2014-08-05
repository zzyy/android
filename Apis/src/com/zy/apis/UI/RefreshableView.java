package com.zy.apis.UI;


import com.zy.apis.R;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class RefreshableView extends LinearLayout implements View.OnTouchListener{
	static final String TAG = "RefreshableView";
	final int STATUS_INVISABLE = 0;
	final int STATUS_PULL = 1;
	final int STATUS_PREPARED_REFRESH = 2;
	final int STATUS_REFRESHING = 3;
	
	View header = null;
	private TextView t_description;
	private TextView t_updateTime;
	private ImageView i_arrow;
	private float yDown;
	private ScrollView scrollView;
	//is the layout has been load
	private boolean loadOnce = false;
	private float pullMultiplicator = 0.5f;
	private int hideHeaderHeight;
	private MarginLayoutParams headerLayoutParams;
	private int currentStatus = STATUS_INVISABLE;
	
	public RefreshableView(Context context) {
		super(context);
		init(context, null);
	}

	public RefreshableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs);
	}

	public RefreshableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	protected void init(Context context, AttributeSet attrs){
Log.i("zy", "start init");
		header = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header, null, true);
		t_description = (TextView) header.findViewById(R.id.descriptionInfo);
		t_updateTime = (TextView)header.findViewById(R.id.updateTime);
		i_arrow = (ImageView)header.findViewById(R.id.arrowImage);
		setOrientation(VERTICAL);
		addView(header, 0);
Log.i("zy", "end init");
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if(!loadOnce ){
			hideHeaderHeight = -header.getHeight();
			headerLayoutParams  = (MarginLayoutParams)header.getLayoutParams();
			headerLayoutParams.topMargin = hideHeaderHeight;
			header.setLayoutParams(headerLayoutParams);

			scrollView = (ScrollView)getChildAt(1);
			scrollView.setOnTouchListener(this);
			
			loadOnce = true;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (isEnableToPull(event)) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				yDown = event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				float yMove = event.getRawY();
				float distance = yMove - yDown;
				if(distance <=0 )
					return false;
				if(currentStatus != STATUS_REFRESHING){
					headerLayoutParams.topMargin = (int) (distance*pullMultiplicator + hideHeaderHeight);
					header.setLayoutParams(headerLayoutParams);
					currentStatus = headerLayoutParams.topMargin>0 ? STATUS_PREPARED_REFRESH : STATUS_PULL;
					updateHeaderView();
				}
			default:
				if(currentStatus == STATUS_PREPARED_REFRESH){
					performRefreshing();
				}else if(currentStatus == STATUS_PULL){
					hideHeaderView();
				}
				break;
			}
		}

		return false;
	}
	
	private void hideHeaderView() {
		
		currentStatus = STATUS_INVISABLE;
	}

	private void performRefreshing() {
		currentStatus = STATUS_REFRESHING;
		scrollView.setPressed(false);
		scrollView.setFocusable(false);
		updateHeaderView();
		hideHeaderView();
	}

	private void updateHeaderView() {
		switch (currentStatus) {
		case STATUS_INVISABLE:
			
			break;
		case STATUS_PREPARED_REFRESH:
			
			break;
		case STATUS_REFRESHING:
			
			break;
		}
		
	}

	protected boolean isEnableToPull(MotionEvent event){
//		this.getScaleY();
//		float position = this.getScaleY();
		float currentPosition = scrollView.getScrollY();
		if(currentPosition == 0){
			return true;
		}
		
		return false;
	}
}
