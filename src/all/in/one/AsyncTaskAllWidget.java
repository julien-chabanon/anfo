package all.in.one;

import MaximumWidget.com.R;
import utils.*;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.widget.RemoteViews;
import maximum.widget.com.memory;

public class AsyncTaskAllWidget extends AsyncTask<Void, Integer, Void>
{
	float percent2;
   	int percent;
   	int MemoryAvailable; 
   	int MemoryUsing;
   	int percentRamOfFree;
   	int percentRamOfUse; 
   	int totalMemory;
   	long ddAvailable;
   	
   	PowerManager pm;
   	getDisk dd = new getDisk();
   	getCPU cpu = new getCPU();
   	AllWidget bg = new AllWidget();
   	Context context;
	

	    public AsyncTaskAllWidget(Context context) {
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
		  RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
		    R.layout.activity_widget_cpu_and_ram);
		

		  //You can do the processing here update the widget/remote views.
		  RemoteViews views = new RemoteViews(context.getPackageName(),
		    R.layout.activity_all_widget);
		
		  
		//Set the text with the current time.
		  
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
	   			views.setTextViewText(R.id.RAMUse, percentRamOfUse + "%");
	   			views.setTextViewText(R.id.SDCardInternalUse, ddAvailable + "%");
		   	

			

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		     ComponentName thisAppWidget = new ComponentName(context.getPackageName(), AllWidget.class.getName());
		     int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		     int appWidgetId = 0;
		     
		     final int N = appWidgetIds.length;
	        	for (int i=0; i<N; i++) 
	        	{
	        		
	                appWidgetId = appWidgetIds[i];
	        	}
		     
		     
	                    
	         bg.startService(context, appWidgetIds,appWidgetId,views);
		  
		  
		  AppWidgetManager manager = AppWidgetManager.getInstance(context);
		  manager.updateAppWidget(thisAppWidget, views);
	      
	      return null;
	}
	 
	 
	
		


	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
	}
}