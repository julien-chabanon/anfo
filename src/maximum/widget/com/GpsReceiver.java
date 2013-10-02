
package maximum.widget.com;

import MaximumWidget.com.R;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.RemoteViews;

public class GpsReceiver extends BroadcastReceiver {
	
	 
	@Override
	 public void onReceive(Context context, Intent intent) {
		
		
	    RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_bg);
	    
	    LocationManager gpsManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

	    if(gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
	    {
	    	remoteViews.setImageViewResource(R.id.gps, R.drawable.gpson);
	    }
	    else if(!gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
	    {
	    	remoteViews.setImageViewResource(R.id.gps, R.drawable.gpsoff);
	    }
	    
	    
	    ComponentName thiswidget = new ComponentName(context, widgetBG.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(thiswidget, remoteViews);
	    
 }
}
