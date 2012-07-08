package com.syzov.geekyClock;

import java.util.Calendar;

import com.syzov.geekyClock.utils.ClockUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class GeekyClockReceiver extends AppWidgetProvider {
	
	static private PendingIntent pendingIntent=null;
	
	@Override
	public void onDisabled(Context context) {
		Toast.makeText(context, "Geeky Clock, GeekyClockReceiver: onDisabled", Toast.LENGTH_SHORT).show();
		
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel(pendingIntent);
		
		//TODO(vs, 18.12.2011) Stop the service here, as we deleted last instance of widget
		
		super.onDisabled(context);
	}
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Toast.makeText(context, "Geeky Clock, GeekyClockReceiver: onUpdate", Toast.LENGTH_SHORT).show();
		
		//TODO: consider updating in separate Service to prevent ANR; 
		updateWidgets(context);
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Toast.makeText(context, "Geeky Clock, GeekyClockReceiver: onDeleted", Toast.LENGTH_SHORT).show();
		
		//TODO(vs, 27.12.2011) Remove shared preferences records
		
		super.onDeleted(context, appWidgetIds);
	}
	
	/** Method that updates all widget instances.*/
	private void updateWidgets(Context context) {		
		//retrieving array of widget IDs by class
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName componentName = new ComponentName(context, GeekyClockReceiver.class);
		int[] widgetIDs = manager.getAppWidgetIds(componentName);
		
		//getting array of ON/OFF led by current time 
		boolean[][] ledsArray = ClockUtils.getLedsByTime(Calendar.getInstance());
		
		//getting remote views
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.geeky_clock_widget_layout);
		
		//getting proper led_on and led_off drawable from Shared Preferences
		SharedPreferences preferences = getSharedPreferences(ConfigurationActivity.PREFERENCES_NAME, 0);
		int ledOnResourceID = preferences.getInt(Integer.toString(widgetID)+ConfigurationActivity.LED_ON_RESOURCE_ID_KEY_MARKER, 0);
		int ledOffResourceID = preferences.getInt(Integer.toString(widgetID)+ConfigurationActivity.LED_OFF_RESOURCE_ID_KEY_MARKER, 0);
		boolean isLedOffElementVisible = preferences.getBoolean(Integer.toString(widgetID)+ConfigurationActivity.LED_OFF_VISIBILITY_KEY_MARKER, true);
		
		for(int widgetID:widgetIDs){
			updateOneWidget(context, widgetID, ledsArray, views, ledOnResourceID, ledOffResourceID, isLedOffElementVisible);
		}
	}

	/** This method updates one widget.
	 * 
	 * @param context - Context
	 * @param widgetID - widget ID
	 * @param ledsArray - array of LEDs to show/hide
	 * @param views - RemoteViews of widget
	 * @param isLedOffElementVisible 
	 * @param ledOffResourceID 
	 * @param ledOnResourceID 
	 */
	public static void updateOneWidget(ContextWrapper context, int widgetID, boolean[][] ledsArray, RemoteViews views, int ledOnResourceID, int ledOffResourceID, boolean isLedOffElementVisible) {		
		//iterating through led IDs and setting source by ON/OFF value
		String ledIdAsString;
		int ledId;
		for(int i = 0; i<ledsArray.length; i++){
			for(int j = 0; j<ledsArray[i].length; j++){
				ledIdAsString = "led_"+Integer.toString(i)+"_"+Integer.toString(j);
				ledId = context.getResources().getIdentifier(ledIdAsString, "id", "com.syzov.geekyClock");
				
				if(ledsArray[i][j]){
					views.setImageViewResource(ledId, ledOnResourceID);
					views.setViewVisibility(ledId, View.VISIBLE);
				}
				else{
					views.setImageViewResource(ledId, ledOffResourceID);
					//if inactive LEDs supposed to be invisible - hide them
					if(!isLedOffElementVisible){
						views.setViewVisibility(ledId, View.INVISIBLE);
					}
					else{
						views.setViewVisibility(ledId, View.VISIBLE);
					}
				}
			}
		}
				
		//updating one widget
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		appWidgetManager.updateAppWidget(widgetID, views);
	}

	
}
