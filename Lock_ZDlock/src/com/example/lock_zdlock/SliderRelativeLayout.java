package com.example.lock_zdlock;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class SliderRelativeLayout extends RelativeLayout {
	private static String TAG = "SliderRelativeLayout";
	
	private Handler mainHandler = null;
	public void setMainHandler(Handler mainHandler) {
		this.mainHandler = mainHandler;
	}

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
	
	private int mLastMoveX = 10000;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		Log.i(TAG, String.format("onTouchEvent; x,y : %d %d", x, y ));
		
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				mLastMoveX = (int) event.getX();
				return handleActionDownEvent(event);
			case MotionEvent.ACTION_MOVE:
				mLastMoveX = x;
				invalidate();
				return true;
			case MotionEvent.ACTION_UP:
				handleActionUpEvent(event);
				return true;
		}
		
		return super.onTouchEvent(event);
	}

	private static int BACK_DURATION = 20;
	private static float VE_HORIZONTAL = 0.7f;
	private void handleActionUpEvent(MotionEvent event) {
		int x = (int) event.getX();
		Log.e(TAG, "handleActionUpEvent x->" + x +", " +getRight());
		
		boolean isSuccess = Math.abs(x - getRight()) < 15;
		if(isSuccess){
			Toast.makeText(mContext, "解锁成功", Toast.LENGTH_SHORT).show();
		}
		
		mLastMoveX = x;
		int distance = x - tv_slider_icon.getRight();
		if(distance>0){
			mainHandler.postDelayed(BackDragImgTask, BACK_DURATION);
		}else{
			resetViewStatus();
		}
	}

	private Runnable BackDragImgTask = new Runnable() {
		
		@Override
		public void run() {
			//一下次Bitmap应该到达的坐标值
			mLastMoveX = mLastMoveX - (int)(BACK_DURATION * VE_HORIZONTAL);
			
			Log.e(TAG, "BackDragImgTask ############# mLastMoveX " + mLastMoveX);
			
			invalidate();//重绘		
			//是否需要下一次动画 ？ 到达了初始位置，不在需要绘制
			boolean shouldEnd = Math.abs(mLastMoveX - tv_slider_icon.getRight()) <= 8 ;			
			if(!shouldEnd)
			    mainHandler.postDelayed(BackDragImgTask, BACK_DURATION);
			else { //复原初始场景
				resetViewStatus();	
			}
		}
	};

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
		canvas.drawBitmap(dragBitmap, drawXpoint<0? 5 : drawXpoint, drawYpoint, null);
	}

	private void resetViewStatus() {
		tv_slider_icon.setVisibility(View.VISIBLE);
		mLastMoveX = 10000;
		invalidate();
	}
	
	private boolean handleActionDownEvent(MotionEvent event) {
		Rect rect = new Rect();
		tv_slider_icon.getHitRect(rect);
		boolean isHit = rect.contains((int)event.getX(), (int)event.getY());
		if(isHit){
			tv_slider_icon.setVisibility(View.INVISIBLE);
		}
		return isHit;
	}
	

}
