package storage;

import MaximumWidget.com.R;
import utils.getDisk;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;

public class AsynctaskStorage extends AsyncTask<Void, Integer, Void>
{
	getDisk dd = new getDisk();
   	StorageWidget bg = new StorageWidget();
   	Context context;
	

	    public AsynctaskStorage(Context context) {
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
		    R.layout.activity_storage_widget);
		

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		     ComponentName thisAppWidget = new ComponentName(context.getPackageName(), StorageWidget.class.getName());
		     int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		     int appWidgetId = 0;
		     
		     final int N = appWidgetIds.length;
	        	for (int i=0; i<N; i++) 
	        	{
	        		
	                appWidgetId = appWidgetIds[i];
	        	}
		     
		     
	                    
	         bg.startService(context, appWidgetIds,appWidgetId,remoteViews);
	         

			
			remoteViews.setTextViewText(R.id.textViewInternalMin, dd.getAvailableInternalMemorySize());		        	
		   	remoteViews.setTextViewText(R.id.textViewInternalMax, dd.getTotalInternalMemorySize());
		   	remoteViews.setTextViewText(R.id.textViewExternalMin, dd.getAvailableExternalMemorySize());
		   	remoteViews.setTextViewText(R.id.textViewExternalMax, dd.getTotalExternalMemorySize());
		  
		  
		  AppWidgetManager manager = AppWidgetManager.getInstance(context);
		  manager.updateAppWidget(thisAppWidget, remoteViews);
	      
	      return null;
	}
	 
	 
	
		


	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
	}
}