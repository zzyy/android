package com.zy.apis.widget;

import com.zy.apis.Apis;
import com.zy.apis.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class SampleWidget extends AppWidgetProvider {
	private static final String TAG = "SampleWidget";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "onReceive");
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.i(TAG, "onUpdate");
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {

			Intent apisIntent = new Intent(context, Apis.class);
			PendingIntent apisPendingIntent = PendingIntent.getActivity(
					context, 0, apisIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.sample_widget);
			remoteViews.setOnClickPendingIntent(R.id.image, apisPendingIntent);
			// remoteViews.
			
			//¸üÐÂ
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.i(TAG, "onDeleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		Log.i(TAG, "onEnabled");
		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		Log.i(TAG, "onDisabled");
		super.onDisabled(context);
	}

	
}
