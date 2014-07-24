package com.zy.apis.animation;

import com.zy.apis.R;

import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class AnimatorTest extends Activity{

	LinearLayout buttonContainer;
	LayoutTransition mLayoutTransition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animator_test);
		
		LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.animatorContainer);
		final CheckBox mGoneCheckBox = (CheckBox) findViewById(R.id.gone);
		buttonContainer = new LinearLayout(this);
		buttonContainer.setLayoutParams(
				new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));;
		for(int i=1; i<5; i++){
			Button button = new Button(this);
			button.setText(String.valueOf(i));
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					v.setVisibility(mGoneCheckBox.isChecked() ? View.GONE : View.INVISIBLE);
				}
			});
			buttonContainer.addView(button);
		}
		((Button)findViewById(R.id.showButton)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for(int i=0;  i<buttonContainer.getChildCount(); i++){
					buttonContainer.getChildAt(i).setVisibility(View.VISIBLE);
				}
			}
		});
		
		mLinearLayout.addView(buttonContainer);
		setupAnimator();
		
		buttonContainer.setLayoutTransition(mLayoutTransition);
	}
	
	private void setupAnimator() {
		mLayoutTransition = new LayoutTransition();
		//change_appearing
		PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("Left",  1, 0 );
		PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("Top",   1, 0 );
		PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("Right",  1, 0);
		PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("Bottom", 1, 0);
		PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("Alpha", 1.0f, 0f, 1f);
		
		Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
//		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(buttonContainer, pvhBottom,pvhLeft,pvhRight, pvhTop);
//		objectAnimator.setDuration(1*1000);
		
		PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("ScaleX", 0f, 2f, 0f ,1f);
		PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("ScaleY", 0f, 2f, 0f ,1f);
		PropertyValuesHolder pvhScaleXInv = PropertyValuesHolder.ofFloat("ScaleX", 0f, 2f, 0f );
		PropertyValuesHolder pvhScaleYInv = PropertyValuesHolder.ofFloat("ScaleY", 0f, 2f, 0f );
		ObjectAnimator apperaing =  ObjectAnimator.ofPropertyValuesHolder(buttonContainer, pvhScaleX, pvhScaleY, pvhRotation);
		ObjectAnimator changeAppearing = ObjectAnimator.ofPropertyValuesHolder(buttonContainer, pvhLeft, pvhTop, pvhRotation, pvhScaleX, pvhScaleY);
		ObjectAnimator disappearing = ObjectAnimator.ofPropertyValuesHolder(buttonContainer,pvhScaleXInv,pvhScaleYInv);
		disappearing.setDuration(1000);
		ObjectAnimator changeDisappearing = ObjectAnimator.ofPropertyValuesHolder(buttonContainer, pvhLeft, pvhTop, pvhRight,pvhBottom, pvhAlpha);
		
		mLayoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 1000);
		
		mLayoutTransition.setAnimator(LayoutTransition.APPEARING, apperaing);
		mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeAppearing);
		
		mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, disappearing);
		mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeDisappearing);
		
		mLayoutTransition.setDuration(2*1000);
	}
}
