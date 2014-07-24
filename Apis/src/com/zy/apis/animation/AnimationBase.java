package com.zy.apis.animation;

import com.zy.apis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationBase extends Activity {

	ImageView mImageView;
	Button b_scale, b_translate, b_alpha, b_rotate, b_mix;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.anim_base);
		mImageView = (ImageView) findViewById(R.id.animImage);
		b_scale = (Button) findViewById(R.id.animScale);
		b_alpha = (Button) findViewById(R.id.animAlpha);
		b_translate = (Button) findViewById(R.id.animTranslate);
		b_rotate = (Button) findViewById(R.id.animRotate);
		
		//第一个参数fromAlpha为 动画开始时候透明度
		//第二个参数toAlpha为 动画结束时候透明度
		final Animation animAlpha = new AlphaAnimation(0.1f, 1f);
		animAlpha.setDuration(7*1000);
		animAlpha.setInterpolator(new AccelerateInterpolator());
		
		b_alpha.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mImageView.startAnimation(animAlpha);
			}
		});
		
		final Animation animScale = new ScaleAnimation(0f, 2f, 0f, 2f, Animation.RELATIVE_TO_SELF, 01f, 1, 1f);
		animScale.setDuration(3*1000);
		b_scale.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mImageView.startAnimation(animScale);
			}
		});
		
		final AnimationSet mAnimationSet = new AnimationSet(true);
		mAnimationSet.addAnimation(animScale);
		mAnimationSet.addAnimation(animAlpha);
		((Button) findViewById(R.id.animMix)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mImageView.startAnimation(mAnimationSet);
			}
		});
	} 
}
