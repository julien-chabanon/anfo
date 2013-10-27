package all.in.one;


import MaximumWidget.com.R;
import utils.*;
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
import maximum.widget.com.CpuInfo;
import maximum.widget.com.RamUse;
import maximum.widget.com.memory;
import storage.Disk;




public class AllWidget extends AppWidgetProvider {

	RemoteViews views;
	PowerManager pm;
	AlarmManager am;
	

   	float percent2;
   	int percent;
   	int MemoryAvailable;
   	int percentRamOfFree;
   	int percentRamOfUse;
   	int MemoryUsing;
   	int totalMemory;
   	int compteur = 0;
   	long ddAvailable;
   	 
   	getDisk dd = new getDisk();

	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	  // TODO Auto-generated method stub
	  super.onDeleted(context, appWidgetIds);
	  Toast.makeText(context, "Anfo all in one info removed from desktop successfully", Toast.LENGTH_SHORT).show();
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
	        	
	        	views = new RemoteViews(context.getPackageName(), R.layout.activity_all_widget);

	        	final int N = appWidgetIds.length;
	   		    
	   		    for (int i=0; i<N; i++) 
	   		    {
	        		
	                int appWidgetId = appWidgetIds[i];
	   		    
	   		    
	   		    
	   		    ComponentName thisWidget = new ComponentName(context,AllWidget.class);
	   		    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	        	

	   		    
	   		//Set the text with the current time.
	   		  getCPU cpu = new getCPU();
	   			try {

	   		   	 percent2 = cpu.readUsage();
	   		   	 percent= (int) (percent2*100);
	   		   	 
	   		   	 MemoryAvailable = memory.getMemoryInfo(context);
	   		   	 totalMemory = cpu.getMemoryTotal();
	   		   	 MemoryUsing = totalMemory-MemoryAvailable;
	   		   	 percentRamOfFree = (MemoryAvailable*100)/cpu.getMemoryTotal();
	   		   	 percentRamOfUse = 100-percentRamOfFree;
	   		   	 
	   		     ddAvailable = dd.pourcentageFreeInternal();

	   			
	   			} catch (Exception e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
	   			
	   			views.setTextViewText(R.id.CPUUse, percent + "%");		        	
	   			views.setTextViewText(R.id.RAMUse, "     " + percentRamOfUse + "%");
	   			views.setTextViewText(R.id.SDCardInternalUse, "     " + ddAvailable + "%");
	   			
	   			// Build the intent to call the service
			    Intent intentBattery = new Intent(context.getApplicationContext(),battery.class);
			    intentBattery.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
			    // Update the widgets via the service
			    context.startService(intentBattery);
	   			   				
	   		    
	   		    //Start service and activity onClick
	   			startService(context, allWidgetIds,appWidgetId, views);
 			  	
	   			  
	   			  appWidgetManager.updateAppWidget(allWidgetIds,views);
	   			  
	        }        
	        }
}
	 

	 
	 public void startService(Context context, int[] allWidgetIds, int appWidgetIds, RemoteViews views) 
	 
	 {	   			   			  
	   			//Start CpuInfo
		   		    Intent intentCpuInfo = new Intent(context, CpuInfo.class);
		   		 intentCpuInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
		   		intentCpuInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   			  // Make the pending intent unique...
		   		intentCpuInfo.setData(Uri.parse(intentCpuInfo.toUri(Intent.URI_INTENT_SCHEME)));
		   			  PendingIntent pendIntentCpuInfo = PendingIntent.getActivity(context, appWidgetIds, intentCpuInfo, PendingIntent.FLAG_UPDATE_CURRENT);
		   			  views.setOnClickPendingIntent(R.id.imageViewCpuUse, pendIntentCpuInfo);
   			  
		   			//Start RamInfo
			   		    Intent intentRamInfo = new Intent(context, RamUse.class);
			   		 intentRamInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
			   		intentRamInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  // Make the pending intent unique...
			   		//intentRamInfo.setData(Uri.parse(intentRamInfo.toUri(Intent.URI_INTENT_SCHEME)));
			   			  PendingIntent pendIntentRamInfo = PendingIntent.getActivity(context, appWidgetIds, intentRamInfo, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.imageViewRamUse, pendIntentRamInfo);
			   			  
			   			//Start battery stats
				   		    Intent intentBatt = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
				   		intentBatt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  PendingIntent pendIntentBatt = PendingIntent.getActivity(context.getApplicationContext(), appWidgetIds, intentBatt, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.imageViewBattery, pendIntentBatt);
			   			  
			   			  
			   			//Start coreControl
				   		    Intent intentControlCore = new Intent(context, Disk.class);
				   		 intentControlCore.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
				   		intentControlCore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				   			  // Make the pending intent unique...
				   		intentControlCore.setData(Uri.parse(intentControlCore.toUri(Intent.URI_INTENT_SCHEME)));
				   			  PendingIntent pendIntentControlCore = PendingIntent.getActivity(context, appWidgetIds, intentControlCore, PendingIntent.FLAG_UPDATE_CURRENT);
				   			  	views.setOnClickPendingIntent(R.id.imageViewSDCardInternalUse, pendIntentControlCore);

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
	 
	 