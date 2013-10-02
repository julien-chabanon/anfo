
package maximum.widget.com;

import MaximumWidget.com.R;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class BluetoothReceiver extends BroadcastReceiver {
	
	 
	@Override
	 public void onReceive(Context context, Intent intent) {
		
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	    
	    RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_bg);
	    
	    if(mBluetoothAdapter.isEnabled()){
	     //wifiManager.setWifiEnabled(false);
	     //Toast.makeText(context, "bluetooth enabled", Toast.LENGTH_SHORT).show();
	     remoteViews.setImageViewResource(R.id.bluetooth, R.drawable.bluetoothon);
	     
	    }
	    else if(!mBluetoothAdapter.isEnabled()){
	     //wifiManager.setWifiEnabled(true);
	     //Toast.makeText(context, "bluetooth disabled", Toast.LENGTH_SHORT).show();
	     remoteViews.setImageViewResource(R.id.bluetooth, R.drawable.bluetoothoff);
	    }
	    
	    else if(mBluetoothAdapter.isDiscovering()){
		     //wifiManager.setWifiEnabled(true);
		     remoteViews.setImageViewResource(R.id.bluetooth, R.drawable.bluetooth);
		    }
	    
	    ComponentName thiswidget = new ComponentName(context, widgetBG.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(thiswidget, remoteViews);
	    
 }
}
