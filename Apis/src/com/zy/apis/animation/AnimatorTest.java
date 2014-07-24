package com.zy.apis.animation;

import com.zy.apis.R;

import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
		PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofFloat("Left", 1f, 0f, 2f, 1f );
		PropertyValuesHolder pvhTop = PropertyValuesHolder.ofFloat("Top", 1f, 0f, 2f, 1f );
		PropertyValuesHolder pvhRight = PropertyValuesHolder.ofFloat("Right", 1f, 0f, 2f, 1f );
		PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofFloat("Bottom", 1f, 0f, 2f, 1f );
		
		Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(buttonContainer, pvhBottom,pvhLeft,pvhRight, pvhTop,pvhRotation);
		changeOut.setDuration(1*1000);
//		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(buttonContainer, pvhBottom,pvhLeft,pvhRight, pvhTop);
//		objectAnimator.setDuration(1*1000);
		
		mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
		
		mLayoutTransition.setDuration(2*1000);
	}
}
