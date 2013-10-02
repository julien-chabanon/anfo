package maximum.widget.com;

import MaximumWidget.com.R;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.widget.RemoteViews;
import application.getDisk;

public class AsyncTaskMaximumWidget extends AsyncTask<Void, Integer, Void>
{
	Context context;
	Long when;
	int cpu_max;
	int cpu_min;
   	int cpu_current = 0;
   	int cpu_temp; 
   	int activeCore = 0;
   	int totalCore = 0;
   	float percent2;
   	int percent = 0;
   	int MemoryAvailable = 0;
   	int MemoryUsing = 0;
   	int percentRamOfFree;
   	int percentRamOfUse = 0;
   	int totalMemory = 0;
   	int internalPercent;
   	int externalPercent;
   	int cpu_temp_AOSP;
   	int cpu_temp_HTC;
   	int cpu_temp_tegra;
   	int cpu_temp_GalaxyS3;
   	int cpu_temp_1;
   	//int cpu_temp_2;
   	//int cpu_temp_3;
   	int cpu_temp_4;
   	float bogo;
   	SystemUtils cpu = new SystemUtils();
   	widgetBG bg = new widgetBG();
   	getDisk disk = new getDisk();
   	TelephonyManager telephonyManager;
   	RemoteViews remoteViews;
	String valueSignal = "";

	    public AsyncTaskMaximumWidget(Context context, String valueSignal) 
	    {
	        this.context = context;
	        this.valueSignal = valueSignal;
	    }


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//Toast.makeText(, "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		// Mise à jour de la ProgressBar

	}

	@Override
	protected Void doInBackground(Void... arg0) {

		

		  //You can do the processing here update the widget/remote views.
		   remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_bg);
		
		  
		//Set the text with the current time.
		  
			try {
		   	
				 cpu_max = cpu.getCPUFrequencyMax()/1000;
				 cpu_min = cpu.getCPUFrequencyMin()/1000;
			   	 bogo = cpu.getCPUBogoMips();
			   	 totalCore = cpu.getNumOfCpus();
			   	 percent2 = cpu.readUsage();
			   	 percent= (int) (percent2*100);
			   	 MemoryAvailable = memory.getMemoryInfo(context);
			   	 totalMemory = cpu.getMemoryTotal();
			   	 MemoryUsing = totalMemory-MemoryAvailable;
			   	 percentRamOfFree = (MemoryAvailable*100)/cpu.getMemoryTotal();
			   	 percentRamOfUse = 100-percentRamOfFree;
			   	 activeCore = cpu.getActiveCore();
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
				   		//cpu_temp_1 = cpu_temp_tegra;
				   		remoteViews.setTextViewText(R.id.CPUTemp, "    "  + cpu_temp_tegra + "°C");
				   	 }

                    else if(cpu_temp_AOSP > 0)
                    {
                        //cpu_temp_3 = cpu_temp_AOSP;
                        remoteViews.setTextViewText(R.id.CPUTemp, "    "  + cpu_temp_AOSP + "°C");
                    }
				   	 
				   	 else if(cpu_temp_GalaxyS3 > 0)
				   	 {
				   		//cpu_temp_1 = cpu_temp_GalaxyS3/1000;
				   		remoteViews.setTextViewText(R.id.CPUTemp, "    "  + cpu_temp_GalaxyS3 + "°C");
				   	 }

				   	
				   	else if(cpu_temp_HTC > 0)
				   	{
				   		//cpu_temp_4 = cpu_temp_HTC;
				   		remoteViews.setTextViewText(R.id.CPUTemp,"    " +  cpu_temp_HTC + "°C");
				   	}
				   	else
				   	{
				   		remoteViews.setTextViewText(R.id.CPUTemp, "No Temp");
				   	}
		   	
		   	
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Toast.makeText(context, "yoyo ch�vre", Toast.LENGTH_LONG).show();
			 AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		     ComponentName thisAppWidget = new ComponentName(context.getPackageName(), widgetBG.class.getName());
		     int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		     int appWidgetId = 0;
		     
		     final int N = appWidgetIds.length;
	        	for (int i=0; i<N; i++) 
	        	{
	        		
	                appWidgetId = appWidgetIds[i];
	        	}
		     


		    	 		//Toast.makeText(context, "yoyo", Toast.LENGTH_LONG).show();
	                	bg.startService(context, appWidgetManager, appWidgetIds, remoteViews, appWidgetId);
	                    bg.refreshToggle(context, remoteViews);
	         
		     
		     

			
			remoteViews.setTextViewText(R.id.CPUUse, percent + "%");		        	
		   	remoteViews.setTextViewText(R.id.CPUcurrent, cpu_current + "MHz");
		   	remoteViews.setTextViewText(R.id.CPUCore, activeCore + "/" + totalCore + " Cores");
		   	remoteViews.setTextViewText(R.id.Ramcurrent," " + internalPercent + "%");		
		   	if(externalPercent == 0)
		   	{
			   	remoteViews.setTextViewText(R.id.TextViewRamUsingMo," " + "No Card");
		   	}
		   	else
		   	{
		   		remoteViews.setTextViewText(R.id.TextViewRamUsingMo," " + externalPercent + "%");
		   	}
		   	remoteViews.setTextViewText(R.id.RamUse, percentRamOfUse + "%");
		   	upNetworkIcon(context);

		   	appWidgetManager.updateAppWidget(thisAppWidget, remoteViews);
	      
	      return null;
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
	 
	 
	 public boolean isOnlineWifi(Context context) {
		    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    
		    if (netInfo != null && netInfo.isConnected()) {
		        return true;
		    }
		    return false;
		}
	 
	 
	 
	public void upNetworkIcon(Context context)
	 {
		//UP wifi toggle
		 WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		 WifiInfo info = wifiManager.getConnectionInfo();
		

		  telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
       
       
		        if(isOnline3G(context) == 2)
				   {
					   
					   remoteViews.setImageViewResource(R.id.imageViewRamTotal, R.drawable.internet_on_mobile);
					   remoteViews.setTextViewText(R.id.RamMax, "   " + valueSignal);
				   }
		        
		        else
		        {
				   if(wifiManager.isWifiEnabled())
				    {
				    	  if(isOnlineWifi(context))
				    	  {  
				    		  remoteViews.setImageViewResource(R.id.imageViewRamTotal, R.drawable.internet_on_wifi); 
				    		  remoteViews.setTextViewText(R.id.RamMax, "   " + valueSignal);
				 		  }
				    	  else
				    	  {
				    		  remoteViews.setImageViewResource(R.id.imageViewRamTotal, R.drawable.no_internet_wifi_on);
				    		  remoteViews.setTextViewText(R.id.RamMax, "   " + valueSignal);
				    	  }
				    }
				   
				   else
				   {
					   remoteViews.setImageViewResource(R.id.imageViewRamTotal, R.drawable.no_internet); 
					   remoteViews.setTextViewText(R.id.RamMax, "   " + valueSignal);
				   }
		        }
			   
	 }
	 
	 
	 
	 
		


	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
		
	}
}