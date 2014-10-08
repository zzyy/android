package com.zy.msgrelay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditTextValue extends EditText {

	private String defaultText = null;
	
	public EditTextValue(Context context) {
		super(context);
	}

	public EditTextValue(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public EditTextValue(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		attrs.getAttributeValue("", "DefaultText");
//		attrs.geta
	}

	
}
