
package maximum.widget.com;

import MaximumWidget.com.R;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;

public class WifiReceiver extends BroadcastReceiver {
	
	public static int statut = 0;
	 
	@Override
	 public void onReceive(Context context, Intent intent) {
		
	   WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	   int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
	   int WIFI_STATE_ENABLED = WifiManager.WIFI_STATE_ENABLED;
	   int WIFI_STATE_DISABLED = WifiManager.WIFI_STATE_DISABLED;
	   int WIFI_STATE_ENABLING = WifiManager.WIFI_STATE_ENABLING;
	   int WIFI_STATE_DISABLING = intent.getIntExtra(WifiManager.EXTRA_WIFI_INFO, WifiManager.WIFI_STATE_UNKNOWN);
	    
	    RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_bg);
	    
	    if(extraWifiState == WIFI_STATE_ENABLED)
	    {

		     if(isOnline(context))
		       {
		    	 remoteViews.setImageViewResource(R.id.wifi, R.drawable.wifiactive);
		    	 statut = 1;
			   }
		     
		     else
			   {
		    	 remoteViews.setImageViewResource(R.id.wifi, R.drawable.wifiactivenot);
		    	 statut = 1;
			   }
	    }
	    
	    else if(extraWifiState == WIFI_STATE_DISABLED){
	     //Toast.makeText(context, "Wifi disabled", Toast.LENGTH_SHORT).show();
	     remoteViews.setImageViewResource(R.id.wifi, R.drawable.wifidesactive);
	     statut = 2;
	    }
	    
	    else if(extraWifiState == WIFI_STATE_ENABLING || extraWifiState == WIFI_STATE_DISABLING){
		     //Toast.makeText(context, "Wifi disabled", Toast.LENGTH_SHORT).show();
		     remoteViews.setImageViewResource(R.id.wifi, R.drawable.wifiwaiting);
		     statut = 3;
		    }

		   

	    
	    ComponentName thiswidget = new ComponentName(context, widgetBG.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(thiswidget, remoteViews);
	    
 }
	
	
	public boolean isOnline(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}
}
