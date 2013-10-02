package maximum.widget.com;

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

public class dataService extends Service {
	
	

 @Override
 public void onStart(Intent intent, int startId) {

  AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
    .getApplicationContext());

  int[] allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

  ComponentName thisWidget = new ComponentName(getApplicationContext(),
    widgetBG.class);
  
  Context context = getApplicationContext();

  for (int widgetId : allWidgetIds) {
   RemoteViews views = new RemoteViews(this
     .getApplicationContext().getPackageName(),
     R.layout.activity_widget_bg);

   EnableDisableConnectivity edConn = new EnableDisableConnectivity(this.getApplicationContext());
   edConn.enableDisableDataPacketConnection(!checkConnectivityState(this.getApplicationContext()));

   // Register an onClickListener
   Intent clickIntent = new Intent(this.getApplicationContext(),
		   widgetBG.class);

   clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
   clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);
   
    
   
   
   //UP data toggle
   if(!isOnlineWifi(context))
   {
	   views.setImageViewResource(R.id.data, R.drawable.dataconnecting);
   }
   
   else
   {
	   Toast.makeText(context, " Disconnect your wifi to activate 3G data ", Toast.LENGTH_SHORT).show();
   }

   
   

   PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), widgetId, clickIntent,
     PendingIntent.FLAG_UPDATE_CURRENT);
   views.setOnClickPendingIntent(R.id.data, pendingIntent);
   appWidgetManager.updateAppWidget(widgetId, views);
  }
  stopSelf();

  super.onStart(intent, startId);
 }

 @Override
 public IBinder onBind(Intent intent) {
  return null;
 }
 
 
 public boolean isOnlineWifi(Context context) {
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    
	    if (netInfo != null && netInfo.isConnected()) {
	        return true;
	    }
	    return false;
	}


 private boolean checkConnectivityState(Context context){
  final TelephonyManager telephonyManager = (TelephonyManager) context
    .getSystemService(Context.TELEPHONY_SERVICE);
  return telephonyManager.getDataState() == TelephonyManager.DATA_CONNECTED;

 }
 

 
}