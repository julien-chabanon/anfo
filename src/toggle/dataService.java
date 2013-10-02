package toggle;

import MaximumWidget.com.R;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.RemoteViews;
import android.widget.Toast;
import maximum.widget.com.EnableDisableConnectivity;

public class dataService extends Service {
	
	

 @Override
 public void onStart(Intent intent, int startId) {

  AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
    .getApplicationContext());

  int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

  ComponentName thisWidget = new ComponentName(getApplicationContext(),
		  ToggleWidget.class);
  
  Context context = getApplicationContext();

  for (int widgetId : allWidgetIds) {
   RemoteViews remoteViews = new RemoteViews(this
     .getApplicationContext().getPackageName(),
     R.layout.activity_widget_toggle);

   EnableDisableConnectivity edConn = new EnableDisableConnectivity(this.getApplicationContext());
   edConn.enableDisableDataPacketConnection(!checkConnectivityState(this.getApplicationContext()));
   
   //UP data toggle
   if(!isOnlineWifi(context))
   {
	   remoteViews.setImageViewResource(R.id.data2, R.drawable.dataconnecting);
   }
   
   else
   {
	   Toast.makeText(context, " Disconnect your wifi to activate 3G data ", Toast.LENGTH_SHORT).show();
   }

   // Register an onClickListener
   Intent clickIntent = new Intent(this.getApplicationContext(),
		   ToggleWidget.class);

   clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
   clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);

   PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
     PendingIntent.FLAG_UPDATE_CURRENT);
   remoteViews.setOnClickPendingIntent(R.id.data2, pendingIntent);
   appWidgetManager.updateAppWidget(widgetId, remoteViews);
  }
  stopSelf();

  super.onStart(intent, startId);
 }

 @Override
 public IBinder onBind(Intent intent) {
  return null;
 }
 


 private boolean checkConnectivityState(Context context){
  final TelephonyManager telephonyManager = (TelephonyManager) context
    .getSystemService(Context.TELEPHONY_SERVICE);
  return telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED;

 }
 
 public boolean isOnlineWifi(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}
 
}