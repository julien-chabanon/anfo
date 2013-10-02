package cpuram;

import MaximumWidget.com.R;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;
import maximum.widget.com.memory;

public class AsyncTaskCpuRam extends AsyncTask<Void, Integer, Void>
{
	int cpu_max;
	int cpu_min;
   	int cpu_current;
   	int cpu_temp;
   	int activeCore;
   	int totalCore;
   	float percent2;
   	int percent;
   	int MemoryAvailable;
   	int MemoryUsing;
   	int percentRamOfFree;
   	int percentRamOfUse;
   	int totalMemory;
   	int cpu_temp_AOSP;
   	float cpu_temp_HTC;
   	int cpu_temp_tegra;
   	int cpu_temp_GalaxyS3;
   	int cpu_temp_1;
   	int cpu_temp_2;
   	int cpu_temp_3;
   	float cpu_temp_4;
   	SystemUtils cpu = new SystemUtils();
   	widgetCPUandRAM bg = new widgetCPUandRAM();
   	Context context;
	

	    public AsyncTaskCpuRam(Context context) {
	        this.context = context;
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
		  RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_cpu_and_ram);
		
		  
		//Set the text with the current time.
		  
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
                    //cpu_temp_4 = cpu_temp_HTC/1000;
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
			

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		     ComponentName thisAppWidget = new ComponentName(context.getPackageName(), widgetCPUandRAM.class.getName());
		     int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		     int appWidgetId = 0;
		     
		     final int N = appWidgetIds.length;
	        	for (int i=0; i<N; i++) 
	        	{
	        		
	                appWidgetId = appWidgetIds[i];
	        	}
		     
		     
	                    
	         bg.startService(context, appWidgetIds,appWidgetId,remoteViews);
	         

			
			remoteViews.setTextViewText(R.id.CPUUse, percent + "%");		        	
		   	remoteViews.setTextViewText(R.id.CPUcurrent, cpu_current + "MHz");
		   	remoteViews.setTextViewText(R.id.CPUCore, activeCore + "/" + totalCore + " Cores");
		   	remoteViews.setTextViewText(R.id.Ramcurrent, MemoryAvailable + "mo");
		   	remoteViews.setTextViewText(R.id.TextViewRamUsingMo, MemoryUsing + "mo");
		   	remoteViews.setTextViewText(R.id.RamUse, percentRamOfUse + "%");
		   	remoteViews.setTextViewText(R.id.RamMax,  totalMemory + "mo");
		  
		  
		  AppWidgetManager manager = AppWidgetManager.getInstance(context);
		  manager.updateAppWidget(thisAppWidget, remoteViews);
	      
	      return null;
	}
	 
	 
	
		


	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
	}
}