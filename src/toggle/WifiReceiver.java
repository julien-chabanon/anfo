
package toggle;

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
	
	 
	@Override
	 public void onReceive(Context context, Intent intent) {
		
	   WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	   int extraWifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
	   int WIFI_STATE_ENABLED = WifiManager.WIFI_STATE_ENABLED;
	   int WIFI_STATE_DISABLED = WifiManager.WIFI_STATE_DISABLED;
	   int WIFI_STATE_ENABLING = WifiManager.WIFI_STATE_ENABLING;
	   int WIFI_STATE_DISABLING = intent.getIntExtra(WifiManager.EXTRA_WIFI_INFO, WifiManager.WIFI_STATE_UNKNOWN);
	    
	    RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_toggle);
	    
	    if(extraWifiState == WIFI_STATE_ENABLED)
	    {

		     if(isOnline(context))
		       {
		    	 remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifiactive);
			   }
		     
		     else
			   {
		    	 remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifiactivenot);
			   }
	    }
	    
	    else if(extraWifiState == WIFI_STATE_DISABLED){
	     //Toast.makeText(context, "Wifi disabled", Toast.LENGTH_SHORT).show();
	     remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifidesactive);
	    }
	    
	    else if(extraWifiState == WIFI_STATE_ENABLING || extraWifiState == WIFI_STATE_DISABLING){
		     //Toast.makeText(context, "Wifi disabled", Toast.LENGTH_SHORT).show();
		     remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifiwaiting);
		    }

		   

	    
	    ComponentName thiswidget = new ComponentName(context, ToggleWidget.class);
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
