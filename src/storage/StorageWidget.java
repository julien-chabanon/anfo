package storage;


import MaximumWidget.com.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;




public class StorageWidget extends AppWidgetProvider {

	RemoteViews remoteViews;
	PowerManager pm;
	AlarmManager am;
	getDisk dd = new getDisk();
	int compteur = 0;

	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	  // TODO Auto-generated method stub
	  super.onDeleted(context, appWidgetIds);
	  Toast.makeText(context, "Anfo SD card widget removed from desktop successfully", Toast.LENGTH_SHORT).show();
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
;
	 }

	 @Override
	 public void onEnabled(Context context) {
	  // TODO Auto-generated method stub
	     super.onEnabled(context);
		  am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		  Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
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
	        	
	        	remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_storage_widget);

	        	final int N = appWidgetIds.length;
	   		    
	   		    for (int i=0; i<N; i++) 
	   		    {
	        		
	                int appWidgetId = appWidgetIds[i];
	   		    
	   		    
	   		    
	   		    ComponentName thisWidget = new ComponentName(context,StorageWidget.class);
	   		    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	        	

	   		    
	   
	   			
	   		    remoteViews.setTextViewText(R.id.textViewInternalMin, dd.getAvailableInternalMemorySize());		        	
			   	remoteViews.setTextViewText(R.id.textViewInternalMax, dd.getTotalInternalMemorySize());
			   	remoteViews.setTextViewText(R.id.textViewExternalMin, dd.getAvailableExternalMemorySize());
			   	remoteViews.setTextViewText(R.id.textViewExternalMax, dd.getTotalExternalMemorySize());
	   			
	   			   				
	   		    
	   		    //Start service and activity onClick
	   			startService(context, allWidgetIds,appWidgetId, remoteViews);
 			  	
	   			  
	   			  appWidgetManager.updateAppWidget(allWidgetIds,remoteViews);
	   			  
	        }        
	        }
}
	 

	 
	 public void startService(Context context, int[] allWidgetIds, int appWidgetIds, RemoteViews views) 
	 
	 {
		 	
	   		
			  
			//Start coreControl
	   		    Intent intentControlCore = new Intent(context, Disk.class);
	   		 intentControlCore.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
	   		intentControlCore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   			  // Make the pending intent unique...
	   		intentControlCore.setData(Uri.parse(intentControlCore.toUri(Intent.URI_INTENT_SCHEME)));
	   			  PendingIntent pendIntentControlCore = PendingIntent.getActivity(context, appWidgetIds, intentControlCore, PendingIntent.FLAG_UPDATE_CURRENT);
	   			  	views.setOnClickPendingIntent(R.id.imageViewInternalMin, pendIntentControlCore);
	   			  	views.setOnClickPendingIntent(R.id.imageViewInternalMax, pendIntentControlCore);
		   			views.setOnClickPendingIntent(R.id.imageViewExternalMin, pendIntentControlCore);	   			
		   			views.setOnClickPendingIntent(R.id.imageViewExternalMax, pendIntentControlCore);
	   			  
	   			  
		   			  
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
	 
	 