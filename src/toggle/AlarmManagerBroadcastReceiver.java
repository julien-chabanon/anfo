package toggle;


import MaximumWidget.com.R;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;


public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
	
	
   	PowerManager pm;
   	ToggleWidget bg = new ToggleWidget();

 @Override
 public void onReceive(Context context, Intent intent) { 
	 
	 
	
   pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  if(pm.isScreenOn())
  {

		  //You can do the processing here update the widget/remote views.
		  RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_toggle);
		


			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		     ComponentName thiswidget = new ComponentName(context.getPackageName(), ToggleWidget.class.getName());
		     int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thiswidget);
		     int appWidgetId = 0;
		     
		     final int N = appWidgetIds.length;
	        	for (int i=0; i<N; i++) 
	        	{
	        		
	                appWidgetId = appWidgetIds[i];
	        	}
	                    
	         bg.startService(context, appWidgetManager, appWidgetIds, remoteViews,appWidgetId);
	         bg.refreshToggle(context, remoteViews);
	         //Toast.makeText(context, "yoyo ch�vre", Toast.LENGTH_LONG).show();
		  
	         appWidgetManager.updateAppWidget(thiswidget, remoteViews);
  }
 }
}