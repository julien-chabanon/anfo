package maximum.widget.com;


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
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.RemoteViews;
import android.widget.Toast;
import application.CPUstatSmall;
import utils.getDisk;
import utils.getCPU;
import java.util.Timer;




public class widgetBG extends AppWidgetProvider {

	Timer t;
	battery bat = new battery();
	memory mem = new memory();
	Intent intentData;
	Intent intentWifi;
	Intent intentDataRceiver;
	Intent intentBattery;
	ComponentName thisWidget;
	PowerManager pm;
	
	
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
   	int internalPercent;
   	int externalPercent;
   	int cpu_temp_AOSP;
   	int cpu_temp_HTC;
   	int cpu_temp_tegra;
   	int cpu_temp_GalaxyS3;
   	int cpu_temp_1;
   	int cpu_temp_2;
   	int cpu_temp_3;
   	int cpu_temp_4;
    int[] app;
    String MY_PREFS = "MY_PREFS";
    TelephonyManager telephonyManager;
    
    getCPU cpu = new getCPU();
    getDisk disk = new getDisk();
    RemoteViews views;
    int compteur = 0;
    
    
	 @Override
	 public void onDisabled(Context context) {
	  // TODO Auto-generated method stub
	     super.onDisabled(context);

	 }

	 @Override
	 public void onEnabled(Context context) {
	  // TODO Auto-generated method stub
	     super.onEnabled(context);
	     
	      AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		  Intent intentAlarmManager = new Intent(context, AlarmManagerBroadcastReceiver.class);
		  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intentAlarmManager, 0);
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
	        	
	  		  
	        	views = new RemoteViews(context.getPackageName(), R.layout.activity_widget_bg);
	        	
	        	final int N = appWidgetIds.length;
	        	for (int i=0; i<N; i++) {
	        		
	                int appWidgetId = appWidgetIds[i];
	   		    
	   		    ComponentName thisWidget = new ComponentName(context,widgetBG.class);
	   		    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

	   		    
	   		//Set the text with the current time.
	   		  
	   			try 
	   			{
	   		   	
	   			 cpu_max = cpu.getCPUFrequencyMax()/1000;
	   			 cpu_min = cpu.getCPUFrequencyMin()/1000;
	   		   	 activeCore = cpu.getActiveCore();
	   		   	 totalCore = cpu.getNumOfCpus();
	   		   	 percent2 = cpu.readUsage();
	   		   	 percent= (int) (percent2*100);
	   		   	 MemoryAvailable = memory.getMemoryInfo(context);
	   		   	 totalMemory = cpu.getMemoryTotal();
	   		   	 MemoryUsing = totalMemory-MemoryAvailable;
	   		   	 percentRamOfFree = (MemoryAvailable*100)/cpu.getMemoryTotal();
	   		   	 percentRamOfUse = 100-percentRamOfFree;
	   		   	 internalPercent = (int)(long) disk.pourcentageUseInternal();
	   		   	 externalPercent = (int)(long) disk.pourcentageUseExternal();
	   		   	 
	   		   	 
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
	   		   	 
	   		   	 
	   		   	
	   		   	 cpu_temp_AOSP = cpu.getCPUTemp();
			   	 cpu_temp_HTC = (int) cpu.getCPUTempHTC();
			   	 cpu_temp_tegra = cpu.getCPUTempTegra();
			   	 cpu_temp_GalaxyS3 = cpu.getCPUTempGalaxyS3();


			   	 if(cpu_temp_tegra > 0)
			   	 {
			   		//cpu_temp_1 = cpu_temp_tegra/1000;
			   		views.setTextViewText(R.id.CPUTemp, "  "  + cpu_temp_tegra + "°C");
			   	 }

                    else if(cpu_temp_AOSP > 0)
                    {
                        //cpu_temp_3 = cpu_temp_AOSP/1000;
                        views.setTextViewText(R.id.CPUTemp, "  "  + cpu_temp_AOSP + "°C");
                    }
			   	 
			   	 else if(cpu_temp_GalaxyS3 > 0)
			   	 {
			   		//cpu_temp_2 = cpu_temp_GalaxyS3/1000;
			   		views.setTextViewText(R.id.CPUTemp, "  "  + cpu_temp_GalaxyS3 + "°C");
			   	 }

			   	
			   	else if(cpu_temp_HTC > 0)
			   	{
			   		//cpu_temp_4 = cpu_temp_HTC;
			   		views.setTextViewText(R.id.CPUTemp,"  " +  cpu_temp_HTC + "°C");
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
	   			views.setTextViewText(R.id.Ramcurrent,"         " + internalPercent + "%");		
	   			views.setTextViewText(R.id.TextViewRamUsingMo,"         " + externalPercent + "%");
	   			views.setTextViewText(R.id.RamUse, percentRamOfUse + "%");
	   			upNetworkIcon(context);
	   			
	   			   				
	   				

	   			// Build the intent to call the service
	   		    intentBattery = new Intent(context.getApplicationContext(),
	   		      battery.class);
	   		    intentBattery.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
	   		    
	   		    // Update the widgets via the service
	   		    context.startService(intentBattery);
	   		    
	   		    //Start service and activity onClick
	   			startService(context, appWidgetManager, allWidgetIds, views, appWidgetId);
 			  
	   			//Start refreshing toggle icon
	   			refreshToggle(context, views);	
	   			
	   			  appWidgetManager.updateAppWidget(allWidgetIds,views);
	        	} 
	        }        

}
	 
	 
	 
	 
	 public void upNetworkIcon(Context context)
	 {
		//UP wifi toggle
		 WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		 WifiInfo info = wifiManager.getConnectionInfo();
		

		  telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		  
        
		        if(isOnline3G(context) == 2 )
				   {
					   
					   
		        	MyPhoneStateListener myListener = new MyPhoneStateListener();
					   ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).listen(myListener,MyPhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
					   //telephonyManager.listen(myListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
					   
					   views.setImageViewResource(R.id.imageViewRamTotal, R.drawable.internet_on_mobile);
					   
					   ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).listen(myListener,MyPhoneStateListener.LISTEN_NONE);
				   }
		        
		        else
		        {
		        	
		        	
		        	
				   if(wifiManager.isWifiEnabled())
				    {
				    	  if(isOnlineWifi(context))
				    	  {  
				    		  views.setImageViewResource(R.id.imageViewRamTotal, R.drawable.internet_on_wifi); 
				    		  views.setTextViewText(R.id.RamMax, "     " + getWifiSignalStrength(context) + "%");
				 		  }
				    	  else
				    	  {
				    		  views.setImageViewResource(R.id.imageViewRamTotal, R.drawable.no_internet_wifi_on);
				    		  views.setTextViewText(R.id.RamMax, "     " + "???");
				    	  }
				    }
				   
				   else
				   {
					   views.setImageViewResource(R.id.imageViewRamTotal, R.drawable.no_internet); 
					   views.setTextViewText(R.id.RamMax, "     " + "OFF");
				   }
		        }
			   
	 }
	 
	 public class MyPhoneStateListener extends PhoneStateListener{
	        
	        @Override
		    public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
	       	 
				String mobileSignal = "";
				// get the signal strength (a value between 0 and 31)
		        if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) 
		        {
		             mobileSignal = (signalStrength.getCdmaDbm()*100)/31 + "%";
		        } 
		        else if  (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) 
		        {
		             mobileSignal = (signalStrength.getGsmSignalStrength()*100)/31 + "%";
		        }
		         
		        views.setTextViewText(R.id.RamMax, "     " + mobileSignal);
		    }
	        
	   }
	 
	 public int getWifiSignalStrength(Context context){
		    int MIN_RSSI        = -100;
		    int MAX_RSSI        = -55;  
		    int levels          = 101;
		    WifiManager wifi    = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);            
		    WifiInfo info       = wifi.getConnectionInfo(); 
		    int rssi            = info.getRssi();

		    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
		        return WifiManager.calculateSignalLevel(info.getRssi(), levels);
		    } else {             
		        // this is the code since 4.0.1
		        if (rssi <= MIN_RSSI) {
		            return 0;
		        } else if (rssi >= MAX_RSSI) {
		            return levels - 1;
		        } else {
		            float inputRange = (MAX_RSSI - MIN_RSSI);
		            float outputRange = (levels - 1);
		            return (int)((float)(rssi - MIN_RSSI) * outputRange / inputRange);
		        }
		    }
		}
	 
	 
	 public boolean isOnlineWifi(Context context) {
		    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    
		    if (netInfo != null && netInfo.isConnected()) {
		        return true;
		    }
		    return false;
		}
	     
	 public int isOnline3G(Context context) {
		    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    boolean connected = false;
		    
		    if (netInfo != null && netInfo.isConnected()) 
		    {
		    	connected = true;
		        return 2;
		    }
		    if (netInfo != null && netInfo.isConnectedOrConnecting() && !connected) 
		    {
		    	connected = false;
		        return 1;
		    }
		    return 0;
		}


	 public static final String URI_SCHEME = "images_widget";
	 public void startService(Context context,AppWidgetManager appWidgetManager, int[] allWidgetIds, RemoteViews views, int widgetId) 
	 
	 {
		 
		 
		 	Intent serviceIntent = new Intent(context, WifiService.class);
	   		serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds); 
	   		//serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
	   		PendingIntent pendingServiceIntent = PendingIntent.getService(context, widgetId, serviceIntent,  Intent.FLAG_ACTIVITY_NEW_TASK);
	   		views.setOnClickPendingIntent(R.id.wifi, pendingServiceIntent);
	   		//serviceIntent.setData(Uri.parse("uri::somethingrandomandunique"));
	   		
	   		
	   		Intent BluetoothIntent = new Intent(context, BluetoothService.class);
	   		BluetoothIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds); 
	   		//BluetoothIntent.setData(Uri.parse(BluetoothIntent.toUri(Intent.URI_INTENT_SCHEME)));
	   		PendingIntent pendingBluetoothIntent = PendingIntent.getService(context.getApplicationContext(), widgetId, BluetoothIntent,  Intent.FLAG_ACTIVITY_NEW_TASK);
	   		views.setOnClickPendingIntent(R.id.bluetooth, pendingBluetoothIntent);
	   		//serviceIntent.setData(Uri.parse("uri::somethingrandomandunique"));

	   		
	   		Intent serviceIntent2 = new Intent(context, dataService.class);
	   		serviceIntent2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds); 
	   		//serviceIntent2.setData(Uri.parse(serviceIntent2.toUri(Intent.URI_INTENT_SCHEME)));
	   		PendingIntent pendingServiceIntent2 = PendingIntent.getService(context.getApplicationContext(), widgetId, serviceIntent2, Intent.FLAG_ACTIVITY_NEW_TASK);
	   		views.setOnClickPendingIntent(R.id.data, pendingServiceIntent2);
	   		//serviceIntent2.setData(Uri.parse("uri::somethingrandomandunique"));
	   		
	   		    
	   		//Start brightness
		    Intent intennt = new Intent(context, brightness.class);
		    intennt.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
		    intennt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			  // Make the pending intent unique...
		    //intennt.setData(Uri.parse(intennt.toUri(Intent.URI_INTENT_SCHEME)));
			  PendingIntent pendIntent = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intennt, PendingIntent.FLAG_UPDATE_CURRENT);
			  views.setOnClickPendingIntent(R.id.brightness, pendIntent);
			  
			//Start coreControl
	   		    Intent intentControlCore = new Intent(context, ControlCore.class);
	   		 intentControlCore.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
	   		intentControlCore.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   			  // Make the pending intent unique...
	   	 	intentControlCore.setData(Uri.parse(intentControlCore.toUri(Intent.URI_INTENT_SCHEME)));
	   			  PendingIntent pendIntentControlCore = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentControlCore, PendingIntent.FLAG_UPDATE_CURRENT);
	   			  views.setOnClickPendingIntent(R.id.imageViewCore, pendIntentControlCore);
	   			  
	   			  
	   			//Start CpuInfo
		   		    Intent intentCpuInfo = new Intent(context, CpuInfo.class);
		   		 intentCpuInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
		   		intentCpuInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   			  // Make the pending intent unique...
		   		//intentCpuInfo.setData(Uri.parse(intentCpuInfo.toUri(Intent.URI_INTENT_SCHEME)));
		   			  PendingIntent pendIntentCpuInfo = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentCpuInfo, PendingIntent.FLAG_UPDATE_CURRENT);
		   			  views.setOnClickPendingIntent(R.id.imageViewCpuUse, pendIntentCpuInfo);
		   			  
		   			  
		   			//Start CpuInfo
			   		    Intent intentCpuStats = new Intent(context, CPUstatSmall.class);
			   		 intentCpuStats.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
			   		intentCpuStats.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  // Make the pending intent unique...
			   		//intentCpuInfo.setData(Uri.parse(intentCpuInfo.toUri(Intent.URI_INTENT_SCHEME)));
			   			  PendingIntent pendIntentCpuStats = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentCpuStats, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.imageViewCurrent, pendIntentCpuStats);
		   			  
		   			  
		   			//Start RamInfo
			   		    Intent intentRamInfo = new Intent(context, RamUse.class);
			   		 intentRamInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
			   		intentRamInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  // Make the pending intent unique...
			   		//intentRamInfo.setData(Uri.parse(intentRamInfo.toUri(Intent.URI_INTENT_SCHEME)));
			   			  PendingIntent pendIntentRamInfo = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentRamInfo, PendingIntent.FLAG_UPDATE_CURRENT);
				   		  views.setOnClickPendingIntent(R.id.imageViewRamPercent, pendIntentRamInfo);
				   		  
				   		//Start RamInfo
				   		    Intent intentSDInfo = new Intent(context, SdCard.class);
				   		 intentSDInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
				   		intentSDInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				   			  // Make the pending intent unique...
				   		//intentRamInfo.setData(Uri.parse(intentRamInfo.toUri(Intent.URI_INTENT_SCHEME)));
				   			  PendingIntent pendIntentSDInfo = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentSDInfo, PendingIntent.FLAG_UPDATE_CURRENT);
				   			  views.setOnClickPendingIntent(R.id.imageViewRamAvailableMo, pendIntentSDInfo);
					   		  views.setOnClickPendingIntent(R.id.imageViewRamUsingMo, pendIntentSDInfo);
					   		  //views.setOnClickPendingIntent(R.id.imageViewRamTotal, pendIntentNetworkInfo);
					   		  
					   		  
					   		//Start RamInfo
					   		    Intent intentNetworkInfo = new Intent(context, Network.class);
					   		 intentNetworkInfo.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, allWidgetIds);  // Identifies the particular widget...
					   		intentNetworkInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					   			  // Make the pending intent unique...
					   		//intentRamInfo.setData(Uri.parse(intentRamInfo.toUri(Intent.URI_INTENT_SCHEME)));
					   			  PendingIntent pendIntentNetworkInfo = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentNetworkInfo, PendingIntent.FLAG_UPDATE_CURRENT);
						   		  views.setOnClickPendingIntent(R.id.imageViewRamTotal, pendIntentNetworkInfo);
	   			  
	   			  
		   			//Start battery stats
			   		    Intent intentBatt = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
			   		intentBatt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   			  PendingIntent pendIntentBatt = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentBatt, PendingIntent.FLAG_UPDATE_CURRENT);
		   			  views.setOnClickPendingIntent(R.id.batteryView, pendIntentBatt);
		   			  
		   			//Start gps intent
			   		    Intent intentGps = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			   		 intentGps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			   			  PendingIntent pendIntentGps = PendingIntent.getActivity(context.getApplicationContext(), widgetId, intentGps, PendingIntent.FLAG_UPDATE_CURRENT);
			   			  views.setOnClickPendingIntent(R.id.gps, pendIntentGps);
		   			  
		   			  
	 }
	 
	 
	 
	 public void refreshToggle(Context context, RemoteViews views)
	 
	 {
		 
			 //UP wifi toggle
			WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	
		    if(wifiManager.isWifiEnabled())
		    {
		    	  if(isOnlineWifi(context))
		    	  {
		    		 views.setImageViewResource(R.id.wifi, R.drawable.wifiactive);
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
		    	views.setImageViewResource(R.id.wifi, R.drawable.wifidesactive);
		    	WifiReceiver.statut = 2;
		    }
		    
	
		    //UP data toggle
		   if(isOnline3G(context) == 2)
		   {
			  views.setImageViewResource(R.id.data, R.drawable.dataactive);
		   }
		   else if(isOnline3G(context) == 1)
		   {
			  views.setImageViewResource(R.id.data, R.drawable.dataconnecting);
		   }
		   else
		   {
			  views.setImageViewResource(R.id.data, R.drawable.dataoff);
		   }
		   
		   
		   //UP Bluetooth toggle
		   BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		    if(mBluetoothAdapter.isEnabled())
		    {
		    	views.setImageViewResource(R.id.bluetooth, R.drawable.bluetoothon);
		    }
		    else
		    {
		    	views.setImageViewResource(R.id.bluetooth, R.drawable.bluetoothoff);
		    }
		    
		    
		    //UP toggle brightness
		   int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	       int SCREEN_BRIGHTNESS_MODE_MANUAL = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	       
	       if(SCREEN_BRIGHTNESS_MODE_AUTOMATIC == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
	       {
	     	  views.setImageViewResource(R.id.brightness, R.drawable.brightness_icon_auto);
	       }
	       
	       if(SCREEN_BRIGHTNESS_MODE_MANUAL == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
	       {
	     	  views.setImageViewResource(R.id.brightness, R.drawable.brightness_icon);
	       }
		    
	       
	       //UP toggle GPS
	       LocationManager gpsManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	       if(gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		    {
	     	  views.setImageViewResource(R.id.gps, R.drawable.gpson);
		    }
		    else if(!gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
		    {
		      views.setImageViewResource(R.id.gps, R.drawable.gpsoff);
		    }
		 
		 
	 }
	 
	 
	 //public static String MY_WIDGET_UPDATE = "MY_OWN_WIDGET_UPDATE";
	 @Override
	 public void onReceive(Context context, Intent intent) {
	  // TODO Auto-generated method stub
	  super.onReceive(context, intent);

	  String action = intent.getAction();
      if (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) 
      {
          final int appWidgetId = intent.getExtras().getInt(
                  AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
          if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) 
          {
        	  Toast.makeText(context, "Anfo maximum widget removed from desktop successfully", Toast.LENGTH_SHORT).show();
        	  Intent intentalarme = new Intent(context, AlarmManagerBroadcastReceiver.class);
        	  PendingIntent sender = PendingIntent.getBroadcast(context, 0, intentalarme, 0);
        	  AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        	  alarmManager.cancel(sender);
        	  
              this.onDeleted(context, new int[] { appWidgetId });
          }
      } 
      if (AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)) 
      {
          
        	  //Toast.makeText(context, "onEnabled", Toast.LENGTH_SHORT).show();
  
      }
      }
      
	 
	 
}	 