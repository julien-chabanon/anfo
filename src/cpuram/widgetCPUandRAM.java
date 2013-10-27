package cpuram;


import MaximumWidget.com.R;
import utils.getCPU;
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
import application.CPUstatSmall;
import maximum.widget.com.ControlCore;
import maximum.widget.com.CpuInfo;
import maximum.widget.com.RamUse;
import maximum.widget.com.memory;




public class widgetCPUandRAM extends AppWidgetProvider {

	RemoteViews views;
	PowerManager pm;
	AlarmManager am;
	
	int cpu_max;
	int cpu_min;
   	int cpu_current;
   	int activeCore;
   	int totalCore;
   	int cpu_temp;
   	float percent2;
   	int percent;
   	int MemoryAvailable;
   	int percentRamOfFree;
   	int percentRamOfUse;
   	int MemoryUsing;
   	int totalMemory;
   	int cpu_temp_AOSP;
   	float cpu_temp_HTC;
   	int cpu_temp_tegra;
   	int cpu_temp_GalaxyS3;
   	int cpu_temp_1;
   	int cpu_temp_2;
   	int cpu_temp_3;
   	float cpu_temp_4;
   	int compteur = 0;

	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	  // TODO Auto-generated method stub
	  super.onDeleted(context, appWidgetIds);
	  Toast.makeText(context, "Anfo cpu & ram widget removed from desktop successfully", Toast.LENGTH_SHORT).show();
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
	        	
	        	views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_cpu_and_ram);

	        	final int N = appWidgetIds.length;
	   		    
	   		    for (int i=0; i<N; i++) 
	   		    {
	        		
	                int appWidgetId = appWidgetIds[i];
	   		    
	   		    
	   		    
	   		    ComponentName thisWidget = new ComponentName(context,widgetCPUandRAM.class);
	   		    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	        	

	   		    
	   		    //Set the text with the current time.
	   		    getCPU cpu = new getCPU();
	   			try {
	   		   	
	   			 cpu_max = cpu.getCPUFrequencyMax()/1000;
	   			 cpu_min = cpu.getCPUFrequencyMin()/1000;
	   			 
	   			 totalCore = cpu.getNumOfCpus();
	   		   	 activeCore = cpu.getActiveCore();
	   		   	 
	   		   	 
	   		  if(activeCore == 0)
			   	 {
					  float core1 = 0;
			    	  float core2 = 0;
			    	  float core3= 0;
			    	  float core4 = 0;
			    	  
			    	  int core1_load = 0;
			    	  int core2_load = 0;
			    	  int core3_load = 0;
			    	  int core4_load = 0;
			    	  
			    	  int compteur_bg = 0;
			    	  
					  core1 = cpu.readUsageCPU0();
			    	  core1_load = (int) (core1);
			    	  
			    	  core2 = cpu.readUsageCPU1();
			    	  core2_load = (int) (core2);
			    	  
			    	  core3 = cpu.readUsageCPU2();
			    	  core3_load = (int) (core3);
			    	  
			    	  core4 = cpu.readUsageCPU3();
			    	  core4_load = (int) (core4);
			    	  
			    	  //-------compteur bg-----------------
			    	  
			    	  if(core1_load > 0)
			    	  {
			    		  compteur_bg++;
			    	  }
			    	  
			    	  if(core2_load > 0)
			    	  {
			    		  compteur_bg++;
			    	  }
			    	  
			    	  if(core3_load > 0)
			    	  {
			    		  compteur_bg++;
			    	  }
			    	  
			    	  if(core4_load > 0)
			    	  {
			    		  compteur_bg++;
			    	  }
			    	  
			    	  //--------ACTIVE?--------------------------
			    	  
			    	  if(compteur_bg == 4)
			    	  {
			    		  activeCore = 4;
			    	  }
			    	  
			    	  if(compteur_bg == 3)
			    	  {
			    		  activeCore = 3;
			    	  }
			    	  
			    	  if(compteur_bg == 2)
			    	  {
			    		  activeCore = 2;
			    	  }
			    	  
			    	  if(compteur_bg == 1)
			    	  {
			    		  activeCore = 1;
			    	  }
			   	 }
	   		   	 
	   		   	 
	   		   	 percent2 = cpu.readUsage();
	   		   	 percent= (int) (percent2*100);
	   		   	 MemoryAvailable = memory.getMemoryInfo(context);
	   		   	 totalMemory = cpu.getMemoryTotal();
	   		   	 MemoryUsing = totalMemory-MemoryAvailable;
	   		   	 percentRamOfFree = (MemoryAvailable*100)/cpu.getMemoryTotal();
	   		   	 percentRamOfUse = 100-percentRamOfFree;
	   		   	 
	   		   	 if(cpu.getCPU0FrequencyCurrentNormaleWay()/1000 > 0)
			   	 {
	   		   		 cpu_current = cpu.getCPU0FrequencyCurrentNormaleWay()/1000;
			   	 }
			   	 
			   	 else if (cpu.getCPU0FrequencyCurrentNormaleWay()/1000 == 0 && cpu.getCPU0FrequencyCurrentNormaleWay()/1000 > 0)
			   	 {
			   		cpu_current = cpu.getCPU0FrequencyCurrentCustomWay()/1000;
			   	 }
			   	 
			   	 else
			   	 {
			   		cpu_current = cpu_max;
			   	 }




                    cpu_temp_AOSP = cpu.getCPUTemp();
                    cpu_temp_HTC = (int) cpu.getCPUTempHTC();
                    cpu_temp_tegra = cpu.getCPUTempTegra();
                    cpu_temp_GalaxyS3 = cpu.getCPUTempGalaxyS3();



                    if(cpu_temp_tegra > 0)
                    {
                        //cpu_temp_1 = cpu_temp_tegra/1000;
                        views.setTextViewText(R.id.CPUTemp, "    "  + cpu_temp_tegra + "°C");
                    }

                    else if(cpu_temp_AOSP > 0)
                    {
                        //cpu_temp_3 = cpu_temp_AOSP;
                        views.setTextViewText(R.id.CPUTemp, "    "  + cpu_temp_AOSP + "°C");
                    }

                    else if(cpu_temp_GalaxyS3 > 0)
                    {
                        //cpu_temp_1 = cpu_temp_GalaxyS3/1000;
                        views.setTextViewText(R.id.CPUTemp, "    "  + cpu_temp_GalaxyS3 + "°C");
                    }


                    else if(cpu_temp_HTC > 0)
                    {
                        //cpu_temp_4 = cpu_temp_tegra/1000;
                        views.setTextViewText(R.id.CPUTemp,"    " +  cpu_temp_HTC + "°C");
                    }
                    else
                    {
                        views.setTextViewText(R.id.CPUTemp, "No Temp");
                    }


	   			} catch (Exception e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
	   			
	   			views.setTextViewText(R.id.CPUUse, percent + "%");		        	
	   			views.setTextViewText(R.id.CPUcurrent, cpu_current + "MHz");
	   			views.setTextViewText(R.id.CPUCore, activeCore + "/" + totalCore + " Cores");
	   			views.setTextViewText(R.id.Ramcurrent,"       " + MemoryAvailable + "mo");		
	   			views.setTextViewText(R.id.TextViewRamUsingMo,"       " + MemoryUsing + "mo");
	   			views.setTextViewText(R.id.RamUse, percentRamOfUse + "%");
	   			views.setTextViewText(R.id.RamMax, "       " + totalMemory + "mo");
	   			
	   			   				
	   		    
	   		    //Start service and activity onClick
	   			startService(context, allWidgetIds,appWidgetId, views);
 			  	
	   			  
	   			  appWidgetManager.updateAppWidget(allWidgetIds,views);
	   			  
	        }        
	        }
}
	 

	 
	 public void startService(Context context, int[] allWidgetIds, int appWidgetIds, RemoteViews views) 
	 
	 {
		 	
	   		
			  
			//Start coreControl
	   		    Intent intentControlCore = new Intent(context, ControlCore.class);
	   		 intentControlCore.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
	   		intentControlCore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   			  // Make the pending intent unique...
	   		intentControlCore.setData(Uri.parse(intentControlCore.toUri(Intent.URI_INTENT_SCHEME)));
	   			  PendingIntent pendIntentControlCore = PendingIntent.getActivity(context, appWidgetIds, intentControlCore, PendingIntent.FLAG_UPDATE_CURRENT);
	   			  views.setOnClickPendingIntent(R.id.imageViewCore, pendIntentControlCore);
	   			  
	   			  
	   			//Start CpuInfo
		   		    Intent intentCpuInfo = new Intent(context, CpuInfo.class);
		   		 intentCpuInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
		   		intentCpuInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   			  // Make the pending intent unique...
		   		//intentCpuInfo.setData(Uri.parse(intentCpuInfo.toUri(Intent.URI_INTENT_SCHEME)));
		   			  PendingIntent pendIntentCpuInfo = PendingIntent.getActivity(context.getApplicationContext(), appWidgetIds, intentCpuInfo, PendingIntent.FLAG_UPDATE_CURRENT);
		   			  views.setOnClickPendingIntent(R.id.imageViewCpuUse, pendIntentCpuInfo);
		   			  
		   			  
		   			//Start CpuInfo
			   		    Intent intentCpuStats = new Intent(context, CPUstatSmall.class);
			   		 intentCpuStats.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
			   		intentCpuStats.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  // Make the pending intent unique...
			   		//intentCpuInfo.setData(Uri.parse(intentCpuInfo.toUri(Intent.URI_INTENT_SCHEME)));
			   			  PendingIntent pendIntentCpuStats = PendingIntent.getActivity(context.getApplicationContext(), appWidgetIds, intentCpuStats, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.imageViewCurrent, pendIntentCpuStats);
		   			  
		   			  
		   			//Start RamInfo
			   		    Intent intentRamInfo = new Intent(context, RamUse.class);
			   		 intentRamInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
			   		intentRamInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  // Make the pending intent unique...
			   		intentRamInfo.setData(Uri.parse(intentRamInfo.toUri(Intent.URI_INTENT_SCHEME)));
			   			  PendingIntent pendIntentRamInfo = PendingIntent.getActivity(context, appWidgetIds, intentRamInfo, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.imageViewRamAvailableMo, pendIntentRamInfo);
				   		  views.setOnClickPendingIntent(R.id.imageViewRamPercent, pendIntentRamInfo);
				   		  views.setOnClickPendingIntent(R.id.imageViewRamUsingMo, pendIntentRamInfo);
				   		  views.setOnClickPendingIntent(R.id.imageViewRamTotal, pendIntentRamInfo);
		   			  
		   			  
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
	 
	 