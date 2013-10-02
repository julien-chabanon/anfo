package toggle;


import MaximumWidget.com.R;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.widget.RemoteViews;
import maximum.widget.com.WifiReceiver;

public class WifiService extends Service {

 
 @Override
 public void onStart(Intent intent, int startId) {
	 
	 ToggleWidget bg = new ToggleWidget();
	 Context context = getBaseContext();
	 	 
  AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
    .getApplicationContext());

  int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

  ComponentName thisWidget = new ComponentName(getApplicationContext(),
		  ToggleWidget.class);

  for (int widgetId : allWidgetIds) {
   RemoteViews remoteViews = new RemoteViews(this
     .getApplicationContext().getPackageName(),
     R.layout.activity_widget_toggle);
   
   WifiManager wifiManager = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
   
   
   
   if(wifiManager.isWifiEnabled())
   {
	   if(WifiReceiver.statut == 1)
	   {
		   wifiManager.setWifiEnabled(false);
	   }

   }
   else if(!wifiManager.isWifiEnabled())
   {
	   if(WifiReceiver.statut == 2)
	   {
		   wifiManager.setWifiEnabled(true);
	   } 
   }
   

   remoteViews.setImageViewResource(R.id.wifi2, R.drawable.wifiwaiting);
   
   // Register an onClickListener
   Intent clickIntent = new Intent(this.getApplicationContext(),
		   ToggleWidget.class);

   clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
   clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);

   PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), widgetId, clickIntent,
     PendingIntent.FLAG_UPDATE_CURRENT);
   remoteViews.setOnClickPendingIntent(R.id.wifi2, pendingIntent);
   appWidgetManager.updateAppWidget(widgetId, remoteViews);
  }
  stopSelf();

  super.onStart(intent, startId);
 }
 
 @Override
 public IBinder onBind(Intent arg0) {
  // TODO Auto-generated method stub
  return null;
 }

}

