

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class RefreshableView extends LinearLayout {
	
	View header = null;
	
	public RefreshableView(Context context) {
		super(context);
	}

	public RefreshableView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RefreshableView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void init(Context context, AttributeSet attrs){
		header = LayoutInflater.from(context).infla
	}

}
