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

public class data_receiver extends BroadcastReceiver {
	
	boolean connected = false;

 @Override
 public void onReceive(Context context, Intent intent) {
  NetworkInfo info = (NetworkInfo)intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
  
  if(info.getType() == ConnectivityManager.TYPE_MOBILE){
   
   RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
     R.layout.activity_widget_toggle);
   
   if(info.isConnected())
   {
    //Toast.makeText(context, "Data packet enabled", Toast.LENGTH_SHORT).show();
    remoteViews.setImageViewResource(R.id.data, R.drawable.dataactive); 
    connected = true;
   }
   
   else if(info.isConnectedOrConnecting() && !connected)
   {
    //Toast.makeText(context, "Data packet enabled", Toast.LENGTH_SHORT).show();
    remoteViews.setImageViewResource(R.id.data, R.drawable.dataconnecting); 
    connected = false;
   }
   
   else
   {
    //Toast.makeText(context, "Data packet disabled", Toast.LENGTH_SHORT).show();
    remoteViews.setImageViewResource(R.id.data, R.drawable.dataoff);
    connected = false;
   }
   
   ComponentName thiswidget = new ComponentName(context, ToggleWidget.class);
   AppWidgetManager manager = AppWidgetManager.getInstance(context);
   manager.updateAppWidget(thiswidget, remoteViews);
   
  }
  
  
  if(info.getType() == ConnectivityManager.TYPE_WIFI)
  {
	  RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
			     R.layout.activity_widget_toggle);
	  
	  
			  WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		
			    if(wifiManager.isWifiEnabled())
			    {
			    		if(info.isConnectedOrConnecting()){
					    //Toast.makeText(context, "Connected on wifi", Toast.LENGTH_SHORT).show();
					    remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifiactive);
					    
					   }else{
					    //Toast.makeText(context, "No network on wifi", Toast.LENGTH_SHORT).show();
					    remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifiactivenot);
					   }
			    }
			   
			   
			   
			   ComponentName thiswidget = new ComponentName(context, ToggleWidget.class);
			   AppWidgetManager manager = AppWidgetManager.getInstance(context);
			   manager.updateAppWidget(thiswidget, remoteViews);
	  
  }
 }
 


}