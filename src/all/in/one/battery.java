package all.in.one;


import MaximumWidget.com.R;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.RemoteViews;

public class battery extends Service {
 
 private int batterylevel = 0;
 private String batteryStatus ="";
 private String batteryTech ="";
 private float batteryTemp = 0;
 private int batteryVolt = 0;
 float battery = 0;

   private BroadcastReceiver myReceiver = new BroadcastReceiver()
   {
       @Override
       public void onReceive(Context context, Intent intent)
       {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BATTERY_CHANGED))
        {
         batterylevel = intent.getIntExtra("level", 0);
         batteryTemp = intent.getIntExtra("temperature",0);
         batteryVolt = intent.getIntExtra("voltage",0);
         batteryTech =  intent.getStringExtra("technology");
         battery = batteryTemp/10;
         
         int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
         String strStatus;
         if (status == BatteryManager.BATTERY_STATUS_CHARGING){
          batteryStatus = "Charging"; 
         } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING){
          batteryStatus = "Dis-charging";
         } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
          batteryStatus = "Not charging";
         } else if (status == BatteryManager.BATTERY_STATUS_FULL){
          batteryStatus = "Full";
         } else {
          batteryStatus = "";
         }
         
         updateAppWidget(context);
        }
       }
      
       public void updateAppWidget(Context context){
        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.activity_all_widget);
        updateViews.setTextViewText(R.id.BatteryUse, batterylevel + "%");
        
        if(batteryStatus == "Charging")
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.ar100);
        }
        else if(batterylevel < 4)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a4);
        }
        
        else if(batterylevel < 10)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a10);
        }

        else if(batterylevel < 20)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a20);
        }
        
        else if(batterylevel < 30)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a30);
        }
        
        else if(batterylevel < 40)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a40);
        }
        
        else if(batterylevel < 50)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a50);
        }
        
        else if(batterylevel < 60)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a60);
        }
        
        else if(batterylevel < 70)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a70);
        }
        
        else if(batterylevel < 80)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a80);
        }
        
        else if(batterylevel < 90)
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a90);
        }
        
        else if(batterylevel <= 100 )
        {
        	updateViews.setImageViewResource(R.id.imageViewBattery, R.drawable.a100);
        }

       
        ComponentName myComponentName = new ComponentName(context, AllWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myComponentName, updateViews);
      }
   };
 
 @Override
 public IBinder onBind(Intent arg0) {
  // TODO Auto-generated method stub
  return null;
 }

 @Override
 public void onCreate() {
  // TODO Auto-generated method stub
  super.onCreate();
  
  IntentFilter intentFilter = new IntentFilter();
  intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
  registerReceiver(myReceiver, intentFilter);
 }

 @Override
 public void onDestroy() {
  // TODO Auto-generated method stub
  super.onDestroy();
  unregisterReceiver(myReceiver);
 }

}
