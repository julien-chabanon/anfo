
package toggle;

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
	    
	    RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_toggle);
	    
	    if(mBluetoothAdapter.isEnabled()){
	     //wifiManager.setWifiEnabled(false);
	     //Toast.makeText(context, "bluetooth enabled", Toast.LENGTH_SHORT).show();
	     remoteViews.setImageViewResource(R.id.bluetooth2, R.drawable.bluetoothon);
	     
	    }
	    else if(!mBluetoothAdapter.isEnabled()){
	     //wifiManager.setWifiEnabled(true);
	     //Toast.makeText(context, "bluetooth disabled", Toast.LENGTH_SHORT).show();
	     remoteViews.setImageViewResource(R.id.bluetooth2, R.drawable.bluetoothoff);
	    }
	    
	    else if(mBluetoothAdapter.isDiscovering()){
		     //wifiManager.setWifiEnabled(true);
		     remoteViews.setImageViewResource(R.id.bluetooth2, R.drawable.bluetooth);
		    }
	    
	    ComponentName thiswidget = new ComponentName(context, ToggleWidget.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(thiswidget, remoteViews);
	    
 }
}
