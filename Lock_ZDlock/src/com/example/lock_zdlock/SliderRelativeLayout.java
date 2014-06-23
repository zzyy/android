package com.example.lock_zdlock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SliderRelativeLayout extends RelativeLayout {
	private static String TAG = "SliderRelativeLayout";
	
	private Context mContext = null;
	private Bitmap dragBitmap = null;
	private TextView tv_slider_icon = null;

	public SliderRelativeLayout(Context context) {
		super(context);
		mContext = context;
		initDragBitmap();
	}
	
	public SliderRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		mContext = context;
		initDragBitmap();
	}

	public SliderRelativeLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initDragBitmap();
	}
	

	private void initDragBitmap() {
		if( dragBitmap == null){
			dragBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.getup_slider_ico_pressed);
		}
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		tv_slider_icon =  (TextView) findViewById(R.id.getup_slider);
	}
	
	private int mLastMoveX = 1000;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		Log.i(TAG, String.format("onTouchEvent; x,y : %d %d", x, y ));
		
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				mLastMoveX = (int) event.getX();
				return handleAnctionDownEvent(event);
			case MotionEvent.ACTION_MOVE:
				mLastMoveX = x;
				invalidate();
				return true;
			case MotionEvent.ACTION_UP:
				
		}
		
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.d(TAG, "onDraw");
		super.onDraw(canvas);
		invalidateDragimage(canvas);
	}
	
	private void invalidateDragimage(Canvas canvas) {
		int drawXpoint = mLastMoveX - dragBitmap.getWidth();
		int drawYpoint = tv_slider_icon.getTop();
		Log.i(TAG, "invalidateDrageImage x,y " + drawXpoint +","+ drawYpoint);
		canvas.drawBitmap(dragBitmap, drawXpoint, drawXpoint, null);
	}

	private boolean handleAnctionDownEvent(MotionEvent event) {
		Rect rect = new Rect();
		tv_slider_icon.getHitRect(rect);
		boolean isHit = rect.contains((int)event.getX(), (int)event.getY());
		if(isHit){
			tv_slider_icon.setVisibility(View.INVISIBLE);
		}
		return isHit;
	}
	

}
