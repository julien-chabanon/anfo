package maximum.widget.com;



import MaximumWidget.com.R;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;



public class BluetoothService extends Service {

 
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
   
   BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

   if(mBluetoothAdapter.isEnabled())
   {
	   mBluetoothAdapter.disable();
   }
   else 
   {
	   mBluetoothAdapter.enable();
   }
   
   remoteViews.setImageViewResource(R.id.bluetooth, R.drawable.bluetooth);
   
   // Register an onClickListener
   Intent clickIntent = new Intent(this.getApplicationContext(),
		   widgetBG.class);

   clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
   clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,allWidgetIds);

   PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), widgetId, clickIntent,
     PendingIntent.FLAG_UPDATE_CURRENT);
   remoteViews.setOnClickPendingIntent(R.id.bluetooth, pendingIntent);
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

