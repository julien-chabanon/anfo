package core.only;


import MaximumWidget.com.R;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.widget.RemoteViews;
import android.widget.Toast;




public class WidgetCore extends AppWidgetProvider {

	RemoteViews views;
	PowerManager pm;
	
	int cpu_max;
	int cpu_min;
   	int cpu_current;
   	int cpu_active_core;
   	int cpu_max_core = 0;
   	int cpu_temp;
   	
   	float percent2;
   	int percent;
   	float percent2CPU1;
   	int percentCPU1;
   	float percent2CPU2;
   	int percentCPU2;
   	float percent2CPU3;
   	int percentCPU3;
   	float percent2CPU4;
   	int percentCPU4;
   	  	
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
   	int cpu0_current;
   	int cpu1_current;
   	int cpu2_current;
   	int cpu3_current;
   	SystemUtils cpu = new SystemUtils();
   	int mProgressStatusCPU0;
   	int mProgressStatusCPU1;
   	int mProgressStatusCPU2;
   	int mProgressStatusCPU3;
   	Handler handler = new Handler();
   	Context context;

	 @Override
	 public void onDeleted(Context context, int[] appWidgetIds) {
	  // TODO Auto-generated method stub
	  super.onDeleted(context, appWidgetIds);
	  Toast.makeText(context, "Anfo Core widget removed from desktop successfully", Toast.LENGTH_SHORT).show();
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
	 public void onEnabled(Context context)
	 {
		 	  super.onEnabled(context);
	 }
	 
	 public void startAlarm(Context context)
	 {
		  AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		  Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
		  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		  //After after 3 seconds
		  am.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+ 0, 5000 , pi);
		  compteur = 5;
	 }
	 
	 
	 @Override
	 public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
	        super.onUpdate(context, appWidgetManager, appWidgetIds);
	           
	        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	        if(pm.isScreenOn())
	        {
	        	this.context = context;
	        	
		   		 try {
						cpu_max_core = cpu.getNumOfCpus();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		   		 

	        	
        		
        		
	        	final int N = appWidgetIds.length;
	   		    
	   		    for (int i=0; i<N; i++) 
	   		    {
	        		
	                int appWidgetId = appWidgetIds[i];
	                ComponentName thisWidget = new ComponentName(context,WidgetCore.class);
		   		    //int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
	   		    
	                if(cpu_max_core == 4)
		        	{
	                	startAlarm(context);
		        		views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_core);
		        		DoStuff();
		        		startService(context, appWidgetId, views);
		        	}
		        	else
		        	{
		        		views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_core_wrong);
		        	}
	   		    
	   		    
	   		    

	   			  appWidgetManager.updateAppWidget(appWidgetId,views);
	   			  
	        }        
	        }
}
	 

	 
	 public void startService(Context context, int appWidgetId, RemoteViews views) 
	 
	 {
		 	
	   		
			  
			//Start coreControl
	   		    Intent intentControlCore = new Intent(context, CoreSwitch.class);
	   		 intentControlCore.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);  // Identifies the particular widget...
	   		intentControlCore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   			  // Make the pending intent unique...
	   		intentControlCore.setData(Uri.parse(intentControlCore.toUri(Intent.URI_INTENT_SCHEME)));
	   	    PendingIntent pendIntentControlCore = PendingIntent.getActivity(context, appWidgetId, intentControlCore, PendingIntent.FLAG_UPDATE_CURRENT);
	   			views.setOnClickPendingIntent(R.id.linearLayoutCore1, pendIntentControlCore);
	   			views.setOnClickPendingIntent(R.id.linearLayoutCore2, pendIntentControlCore);
	   			views.setOnClickPendingIntent(R.id.linearLayoutCore3, pendIntentControlCore);
	   			views.setOnClickPendingIntent(R.id.linearLayoutCore4, pendIntentControlCore);
 			  
		   			  
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
	 
	 
	 
	 
	 public void DoStuff()
	 {

		 try {
				cpu0_current = cpu.getCPU0FrequencyCurrentNormaleWay()/1000;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  	   		 
					     	   	
					     	 //-----------------------------------QUAD CORE-------------------------------------------------------------------------------------------------------
						      	
						      	if(cpu_max_core == 4)
					      	   	{
					      	   		
						      	   	 percent2 = cpu.readUsageCPU0();
						     	   	 percent= (int) (percent2);
						     	   	 mProgressStatusCPU0 = percent;
						     	   	
						     	   	 percent2CPU1 = cpu.readUsageCPU1();
						     	   	 percentCPU1 = (int) (percent2CPU1);
						     	   	 mProgressStatusCPU1 = percentCPU1;
						     	   	
						     	   	 percent2CPU2 = cpu.readUsageCPU2();
						     	   	 percentCPU2 = (int) (percent2CPU2);
						     	   	 mProgressStatusCPU2 = percentCPU2;
						     	   	
						     	   	 percent2CPU3 = cpu.readUsageCPU3();
						     	   	 percentCPU3 = (int) (percent2CPU3);
						     	   	 mProgressStatusCPU3 = percentCPU3;
						     	   	 
						     	   	
						     	   	//--------------CORE 1 ACTIF----------------------------------------
						     	   	if(percent > 0)
						      	   	{
							     	   	setData(1);	
							      		//setColorEnabledCore();
						      	   		
						      	   		//colorCore();
						      	   	}
						     	   	else if(percent == 0)
						     	   	{
							     	   	setData(10);	
							      		//setColorEnabledCore();
						     	   	}
						     	   	
						      	   	//-------------------CORE 2 ACTIF------------------------------------------------------
						      	    if(percentCPU1 > 0)
						      	   	{					      	   	
							      	   	try {
											cpu1_current = cpu.getCPU1FrequencyCurrent()/1000;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
							      	   	
							      	    if (cpu1_current == 0)
							      		{
							      			cpu1_current = cpu0_current;
							      		}
							      		
							      	    setData(2);	
							      		//setColorEnabledCore();

						      	   		//colorCore();

						      	   	}
						      	    else if(percentCPU1 == 0)
						      	    {
						      	    	setData(20);	
							      		//setColorEnabledCore();
						      	    }
						      	   	
						      	    
						      	   //-------------CORE 3 ACTIF-------------------------------------- 
						      	   if(percentCPU2 > 0)
						    	   	{
						      		  
							      		try {
											cpu2_current = cpu.getCPU2FrequencyCurrent()/1000;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
							      		
							      		if (cpu2_current == 0)
							      		{
							      			cpu2_current = cpu0_current;
							      		}
							      		
							      		setData(3);	
							      		//setColorEnabledCore();
							  	   		
							  	   		//colorCore();
							  	   		
						    	   	}
						      	   
						      	   else if(percentCPU2 == 0)
						      	   {
						      		    setData(30);	
							      		//setColorEnabledCore();
						      	   }
						      	   
						      	   
						      	   //----------CORE 4 ACTIF--------------------------------------------
							       if(percentCPU3 > 0)
							  	   	{
							      		 
							      		
							      		try {
											cpu3_current = cpu.getCPU3FrequencyCurrent()/1000;
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
							      		
							      		 if(cpu3_current == 0)
							      		{
							      			cpu3_current = cpu0_current;
							      		}

							      		setData(4);	
							      		//setColorEnabledCore();
							  	   		
							  	   		//colorCore();
							  	   		 	   		
							  	   	}
							       else if(percentCPU3 == 0)
							       {
							    	    setData(40);	
							      		//setColorEnabledCore();
							       }
							       
							        
						      		
						      		views.setProgressBar(R.id.progressBarWidgetCore1, 100, mProgressStatusCPU0, false);
				                    views.setTextViewText(R.id.textViewCore1Load, mProgressStatusCPU0 + "%");
				                    
				                    views.setProgressBar(R.id.progressBarWidgetCore2, 100, mProgressStatusCPU1, false);
				                    views.setTextViewText(R.id.textViewCore2Load, mProgressStatusCPU1 + "%");
				                    
				                    views.setProgressBar(R.id.progressBarWidgetCore3, 100, mProgressStatusCPU2, false);
				                    views.setTextViewText(R.id.textViewCore3Load, mProgressStatusCPU2 + "%");
				                    
				                    views.setProgressBar(R.id.progressBarWidgetCore4, 100, mProgressStatusCPU3, false);
				                    views.setTextViewText(R.id.textViewCore4Load, mProgressStatusCPU3 + "%");

				                    
					      	   	}
	 }



	public void setData(int coreToDraw)
	{
		
			
			 try {
				cpu_min = cpu.getCPUFrequencyMin()/1000;
				cpu_max = cpu.getCPUFrequencyMax()/1000;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		 views.setTextViewText(R.id.textViewMaxFreqAllowCPU1, "Max: " + cpu_max + " MHz");
	   	 views.setTextViewText(R.id.textViewMinFreqAllowCPU1, "Min: " + cpu_min + " MHz");
	   	 views.setTextViewText(R.id.textViewMaxFreqAllowCPU2, "Max: " + cpu_max + " MHz");
	   	 views.setTextViewText(R.id.textViewMinFreqAllowCPU2, "Min: " + cpu_min + " MHz");
	   	 views.setTextViewText(R.id.textViewMaxFreqAllowCPU3, "Max: " + cpu_max + " MHz");
	   	 views.setTextViewText(R.id.textViewMinFreqAllowCPU3, "Min: " + cpu_min + " MHz");
	   	 views.setTextViewText(R.id.textViewMaxFreqAllowCPU4, "Max: " + cpu_max + " MHz");
	   	 views.setTextViewText(R.id.textViewMinFreqAllowCPU4, "Min: " + cpu_min + " MHz");
	   	 
	   	 
	   	 
		 if(coreToDraw == 1)
		 {
			 views.setInt(R.id.linearLayoutCore1, "setBackgroundColor", 0xff669900);
		   	 views.setTextViewText(R.id.textViewCore1Widget, cpu0_current + "MHz");
		 }
		 else if(coreToDraw == 10)
		 {
			 views.setInt(R.id.linearLayoutCore1, "setBackgroundColor", 0xffCC0000);
			 views.setTextViewText(R.id.textViewCore1Widget, "CPU offline");
		 }
		 
		 if(coreToDraw == 2)
		 {
	   		 views.setInt(R.id.linearLayoutCore2, "setBackgroundColor", 0xff669900);   		
		   	 views.setTextViewText(R.id.textViewCore2Widget, cpu1_current + "MHz");
		 }
		 else if(coreToDraw == 20)
		 {
			 views.setInt(R.id.linearLayoutCore2, "setBackgroundColor", 0xffCC0000);
			 views.setTextViewText(R.id.textViewCore2Widget, "CPU offline");
		 }
		 
		 if(coreToDraw == 3)
		 {
	   		 views.setInt(R.id.linearLayoutCore3, "setBackgroundColor", 0xff669900);
		   	 views.setTextViewText(R.id.textViewCore3Widget, cpu2_current + "MHz");
		 }
		 else if(coreToDraw == 30)
		 {
			 views.setInt(R.id.linearLayoutCore3, "setBackgroundColor", 0xffCC0000);
			 views.setTextViewText(R.id.textViewCore3Widget, "CPU offline");
		 }
		 
		 if(coreToDraw == 4)
		 {
	   		 views.setInt(R.id.linearLayoutCore4, "setBackgroundColor", 0xff669900);
		   	 views.setTextViewText(R.id.textViewCore4Widget, cpu3_current + "MHz");
		 }
		 else if(coreToDraw == 40)
		 {
			 views.setInt(R.id.linearLayoutCore4, "setBackgroundColor", 0xffCC0000);
			 views.setTextViewText(R.id.textViewCore4Widget, "CPU offline");
		 }
		 
		 
		 try {
 
  		   	 cpu_max_core = cpu.getNumOfCpus();

  		   	 cpu_temp_AOSP = cpu.getCPUTemp();
		   	 cpu_temp_HTC = cpu.getCPUTempHTC();
		   	 cpu_temp_tegra = cpu.getCPUTempTegra();
		   	 cpu_temp_GalaxyS3 = cpu.getCPUTempGalaxyS3();
		   	 
		   	 if(cpu_temp_tegra != 0)
		   	 {
		   		cpu_temp_1 = cpu_temp_tegra/1000;
		   		views.setTextViewText(R.id.textViewCore1TempWidget, cpu_temp_1 + "°C");
		   		views.setTextViewText(R.id.textViewCore2TempWidget, cpu_temp_1 + "°C");
		   		views.setTextViewText(R.id.textViewCore3TempWidget, cpu_temp_1 + "°C");
		   		views.setTextViewText(R.id.textViewCore4TempWidget, cpu_temp_1 + "°C");
		   	 }
		   	 
		   	 else if(cpu_temp_GalaxyS3 !=0 && cpu_temp_tegra==0)
		   	 {
		   		cpu_temp_2 = cpu_temp_GalaxyS3/1000;
		   		views.setTextViewText(R.id.textViewCore1TempWidget, cpu_temp_2 + "°C");
		   		views.setTextViewText(R.id.textViewCore2TempWidget, cpu_temp_2 + "°C");
		   		views.setTextViewText(R.id.textViewCore3TempWidget, cpu_temp_2 + "°C");
		   		views.setTextViewText(R.id.textViewCore4TempWidget, cpu_temp_2 + "°C");
		   	 }
		 
		   	else if(cpu_temp_AOSP != 0 && cpu_temp_tegra==0 && cpu_temp_GalaxyS3 == 0)
		   	{
		   		 //cpu_temp_3 = cpu_temp_AOSP/1000;
		   		views.setTextViewText(R.id.textViewCore1TempWidget, cpu_temp_AOSP + "°C");
		   		views.setTextViewText(R.id.textViewCore2TempWidget, cpu_temp_AOSP + "°C");
		   		views.setTextViewText(R.id.textViewCore3TempWidget, cpu_temp_AOSP + "°C");
		   		views.setTextViewText(R.id.textViewCore4TempWidget, cpu_temp_AOSP + "°C");
		   	}
		   	
		   	else if(cpu_temp_HTC != 0 && cpu_temp_AOSP == 0 && cpu_temp_tegra==0 && cpu_temp_GalaxyS3 == 0)
		   	{
		   		cpu_temp_4 = cpu_temp_HTC/1000;
		   		views.setTextViewText(R.id.textViewCore1TempWidget, cpu_temp_4 + "°C");
		   		views.setTextViewText(R.id.textViewCore2TempWidget, cpu_temp_4 + "°C");
		   		views.setTextViewText(R.id.textViewCore3TempWidget, cpu_temp_4 + "°C");
		   		views.setTextViewText(R.id.textViewCore4TempWidget, cpu_temp_4 + "°C");
		   	}
		   	else
		   	{
		   		views.setTextViewText(R.id.textViewCore1TempWidget, "No Temp");
		   		views.setTextViewText(R.id.textViewCore2TempWidget, "No Temp");
		   		views.setTextViewText(R.id.textViewCore3TempWidget, "No Temp");
		   		views.setTextViewText(R.id.textViewCore4TempWidget, "No Temp");
		   	}

  			
  			} catch (Exception e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
	 
	 