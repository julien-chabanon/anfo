package battery.only;


import MaximumWidget.com.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;



public class BatteryOnly extends AppWidgetProvider {

	battery bat = new battery();
	ComponentName thisWidget;
	public static String YOUR_AWESOME_ACTION_WIFI = "wifi";
	public static String YOUR_AWESOME_ACTION_DATA = "data";
	RemoteViews views;
	PowerManager pm;
	AlarmManager am;
	Intent intentBattery;
    int compteur = 0;

	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	  // TODO Auto-generated method stub
		 super.onDeleted(context, appWidgetIds);
		  Toast.makeText(context, "Anfo battery widget removed from desktop successfully", Toast.LENGTH_SHORT).show();
		  Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		  PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		  AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		  alarmManager.cancel(sender);
		  super.onDeleted(context, appWidgetIds);
	 }

	 @Override
	 public void onDisabled(Context context) {
	  // TODO Auto-generated method stub
	     super.onDisabled(context);

	 }

	 @Override
	 public void onEnabled(Context context) {
	  // TODO Auto-generated method stub
	     super.onEnabled(context); 
		  am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		  Intent intentAlarmManager = new Intent(context, AlarmManagerBroadcastReceiver.class);
		  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intentAlarmManager, 0);
		  //After after 3 seconds
		  am.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+ 0, 20000 , pi);
		  compteur = 5;
		  super.onEnabled(context);
	 }
	 
	 @Override
	 public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
	        super.onUpdate(context, appWidgetManager, appWidgetIds);
	           
	        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	        if(pm.isScreenOn())
	        {

	        	compteur++;
	        	if(compteur == 1)
	        	{
	        		onEnabled(context);
	        	}
	        	    
	        views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_battery_only);

	   		    final int N = appWidgetIds.length;
	   		    ComponentName thisWidget = new ComponentName(context,
	   		      BatteryOnly.class);
	   		    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

	   		    
	   		    
	   		//Start battery stats
	   		 Intent intentBatt = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
	   		 intentBatt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   		  // Make the pending intent unique...
	   		  PendingIntent pendIntentBatt = PendingIntent.getActivity(context, 0, intentBatt, PendingIntent.FLAG_UPDATE_CURRENT);
	   		  views.setOnClickPendingIntent(R.id.imageViewBatteryPercentOnly, pendIntentBatt);
	        	
	   		
	   			
	   			// Build the intent to call the service
	   		    intentBattery = new Intent(context.getApplicationContext(),
	   		      battery.class);
	   		    intentBattery.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
	   		    // Update the widgets via the service
	   		    context.startService(intentBattery);
	   		    		
	   			  
	   			  appWidgetManager.updateAppWidget(allWidgetIds,views);
	   			  
	        }        

	 }
	 
 public void startService(Context context, RemoteViews views) 
	 
	 {
		 //Start battery stats
		 Intent intentBatt = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
		 intentBatt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  // Make the pending intent unique...
		  PendingIntent pendIntentBatt = PendingIntent.getActivity(context, 0, intentBatt, PendingIntent.FLAG_UPDATE_CURRENT);
		  views.setOnClickPendingIntent(R.id.imageViewBatteryPercentOnly, pendIntentBatt);
		   			  
	 }
 
 
 @Override
 public void onReceive(Context context, Intent intent) {
  // TODO Auto-generated method stub
  super.onReceive(context, intent);

  final String action = intent.getAction();
  if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) 
  {
      final int appWidgetId = intent.getExtras().getInt(
              AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
      if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) 
      {
          this.onDeleted(context, new int[] { appWidgetId });
      }
  } 
 }
}
	 
	 