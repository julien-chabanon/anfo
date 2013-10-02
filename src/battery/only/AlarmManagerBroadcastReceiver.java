package battery.only;



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
   	BatteryOnly bg = new BatteryOnly();

 @Override
 public void onReceive(Context context, Intent intent) {
	 
	 
	
   pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  if(pm.isScreenOn())
  {

		  //You can do the processing here update the widget/remote views.
		  RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
		    R.layout.activity_widget_battery_only);

			

			 AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		     ComponentName thiswidget = new ComponentName(context.getPackageName(), BatteryOnly.class.getName());
		     //int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thiswidget);
	                    
	         bg.startService(context, remoteViews);
	         //Toast.makeText(context, "yoyo batterie", Toast.LENGTH_LONG).show();
	         
	         
	         appWidgetManager.updateAppWidget(thiswidget, remoteViews);
  }
 }
}