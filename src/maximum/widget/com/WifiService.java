package maximum.widget.com;


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
import android.widget.Toast;


public class WifiService extends Service {
	widgetBG bg = new widgetBG();
 
 @Override
 public void onStart(Intent intent, int startId) {
	 
	 Context context = getBaseContext();
	 	 
  AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
    .getApplicationContext());

  int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

  ComponentName thisWidget = new ComponentName(getApplicationContext(),
    widgetBG.class);

  for (int widgetId : allWidgetIds) {
   RemoteViews remoteViews = new RemoteViews(this
     .getApplicationContext().getPackageName(),
     R.layout.activity_widget_bg);
   
   WifiManager wifiManager = (WifiManager)getBaseContext().getSystemService(Context.WIFI_SERVICE);
   
   
   
   if(WifiReceiver.statut == 3)
   {
	   Toast.makeText(context, "wait, wifi statut is changing !", Toast.LENGTH_SHORT).show();
   }
   
   else
	   
   {
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
   }
   
   
   remoteViews.setImageViewResource(R.id.wifi, R.drawable.wifiwaiting);
   
   
   
   /* here you "refresh" the pending intent for the button 
   Intent clickintent=new Intent("net.example.appwidget.ACTION_WIDGET_CLICK");
   PendingIntent pendingIntentClick=PendingIntent.getBroadcast(context, 0, clickintent, 0);
   remoteViews.setOnClickPendingIntent(R.id.wifi, pendingIntentClick);
   */
   
   

   // Register an onClickListener
   Intent clickIntent = new Intent(this.getApplicationContext(),widgetBG.class);

   clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
   clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);

   PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), widgetId, clickIntent,PendingIntent.FLAG_UPDATE_CURRENT);
   remoteViews.setOnClickPendingIntent(R.id.wifi, pendingIntent);
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

