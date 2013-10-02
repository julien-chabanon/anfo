package toggle;


import MaximumWidget.com.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;
import maximum.widget.com.WifiReceiver;




public class ToggleWidget extends AppWidgetProvider {

	Intent intentData;
	Intent intentWifi;
	Intent intentDataRceiver;
	ComponentName thisWidget;
	public static String YOUR_AWESOME_ACTION_WIFI = "wifi";
	public static String YOUR_AWESOME_ACTION_DATA = "data";
	PowerManager pm;
	RemoteViews views;
	int compteur = 0;


	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	  // TODO Auto-generated method stub
		 super.onDeleted(context, appWidgetIds);
		  Toast.makeText(context, "Anfo toggle widget removed from desktop successfully", Toast.LENGTH_SHORT).show();
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
	     
	      AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		  Intent intentAlarmManager = new Intent(context, AlarmManagerBroadcastReceiver.class);
		  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intentAlarmManager, 0);
		  //After after 5 seconds
		  am.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+ 0, 5000 , pi);
		  
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
	        	
	        	views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_toggle);	
	        	
	        	
	        	
	   		    final int N = appWidgetIds.length;
	   		    
	   		    for (int i=0; i<N; i++) 
	   		    {
	        		
	                int appWidgetId = appWidgetIds[i];
	   		    ComponentName thisWidget = new ComponentName(context,ToggleWidget.class);
	   		    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	   		    

	   		    //Start service and activity onClick
	   			startService(context, appWidgetManager, allWidgetIds, views,appWidgetId);
 			  
	   			//Start refreshing toggle icon
	   			refreshToggle(context, views);	
	   			
	  
	   			  appWidgetManager.updateAppWidget(allWidgetIds,views);
	   			  
	        }   
	    }

}
	 
	 public boolean isOnline3G(Context context) {
		    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    
		    if (netInfo != null && netInfo.isConnected()) {
		        return true;
		    }
		    return false;
		}
	 
	 
	 public boolean isOnlineWifi(Context context) {
		    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    
		    if (netInfo != null && netInfo.isConnected()) {
		        return true;
		    }
		    return false;
		}
	     
	 
	
	 
	 
	 
	 
	 public void startService(Context context,AppWidgetManager appWidgetManager, int[] allWidgetIds, RemoteViews views, int widgetId) 
	 
	 {
		 	Intent serviceIntent = new Intent(context, WifiService.class);
	   		serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds); 
	   		PendingIntent pendingServiceIntent = PendingIntent.getService(context, widgetId, serviceIntent,  Intent.FLAG_ACTIVITY_NEW_TASK);
	   		views.setOnClickPendingIntent(R.id.wifi2, pendingServiceIntent);
	   		//serviceIntent.setData(Uri.parse("uri::somethingrandomandunique"));
	   		
	   		
	   		Intent BluetoothIntent = new Intent(context, BluetoothService.class);
	   		BluetoothIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds); 
	   		PendingIntent pendingBluetoothIntent = PendingIntent.getService(context, widgetId, BluetoothIntent,  Intent.FLAG_ACTIVITY_NEW_TASK);
	   		views.setOnClickPendingIntent(R.id.bluetooth2, pendingBluetoothIntent);
	   		//serviceIntent.setData(Uri.parse("uri::somethingrandomandunique"));

	   		
	   		Intent serviceIntent2 = new Intent(context, dataService.class);
	   		serviceIntent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds); 
	   		PendingIntent pendingServiceIntent2 = PendingIntent.getService(context, widgetId, serviceIntent2, Intent.FLAG_ACTIVITY_NEW_TASK);
	   		views.setOnClickPendingIntent(R.id.data2, pendingServiceIntent2);
	   		//serviceIntent2.setData(Uri.parse("uri::somethingrandomandunique"));
	   		
	   		    
		//Start brightness
		    Intent intennt = new Intent(context, brightness.class);
		    intennt.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
		    intennt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  // Make the pending intent unique...
		    intennt.setData(Uri.parse(intennt.toUri(Intent.URI_INTENT_SCHEME)));
			  PendingIntent pendIntent = PendingIntent.getActivity(context, widgetId, intennt, PendingIntent.FLAG_UPDATE_CURRENT);
			  views.setOnClickPendingIntent(R.id.brightness2, pendIntent);
	   			  
	   			  
		   			  
		   			//Start gps intent
			   		    Intent intentGps = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			   		 intentGps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  // Make the pending intent unique...
			   			  PendingIntent pendIntentGps = PendingIntent.getActivity(context, widgetId, intentGps, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.gps2, pendIntentGps);
		   			  
	 }
	 
	 
	 
	 public void refreshToggle(Context context, RemoteViews views)
	 
	 {
		 
			 //UP wifi toggle
			WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	
		    if(wifiManager.isWifiEnabled())
		    {
		    	  if(isOnlineWifi(context))
		    	  {
		    		 views.setImageViewResource(R.id.wifi2, R.drawable.wifiactive);
		    		 WifiReceiver.statut = 1;
		 		  }
		    	  else
		 		   {
		 			  views.setImageViewResource(R.id.wifi, R.drawable.wifiactivenot);
		 			  WifiReceiver.statut = 1;
		 		   }
		    }
		    else
		    {
		    	views.setImageViewResource(R.id.wifi2, R.drawable.wifidesactive);
		    	WifiReceiver.statut = 2;
		    }
		    
	
		    //UP data toggle
		   if(isOnline3G(context)){
			  views.setImageViewResource(R.id.data2, R.drawable.dataactive);
		    
		   }else{
			  views.setImageViewResource(R.id.data2, R.drawable.dataoff);
		   }
		   
		   
		   //UP Bluetooth toggle
		   BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		    if(mBluetoothAdapter.isEnabled()){
	
		    views.setImageViewResource(R.id.bluetooth2, R.drawable.bluetoothon);
		     
		    }
		    else{
	
		    views.setImageViewResource(R.id.bluetooth2, R.drawable.bluetoothoff);
		    }
		    
		    
		    //UP toggle brightness
		      int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	       int SCREEN_BRIGHTNESS_MODE_MANUAL = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	       
	       if(SCREEN_BRIGHTNESS_MODE_AUTOMATIC == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
	       {
	     	  views.setImageViewResource(R.id.brightness2, R.drawable.brightness_icon_auto);
	       }
	       
	       if(SCREEN_BRIGHTNESS_MODE_MANUAL == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
	       {
	     	  views.setImageViewResource(R.id.brightness2, R.drawable.brightness_icon);
	       }
		    
	       
	       //UP toggle GPS
	       LocationManager gpsManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	       if(gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		    {
	     	  views.setImageViewResource(R.id.gps2, R.drawable.gpson);
		    }
		    else if(!gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		    {
		    	views.setImageViewResource(R.id.gps2, R.drawable.gpsoff);
		    }
		 
		 
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
	 
	 