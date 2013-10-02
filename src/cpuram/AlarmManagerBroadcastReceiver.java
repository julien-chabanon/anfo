package cpuram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;


public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
	
	
   	PowerManager pm;

 @Override
 public void onReceive(Context context, Intent intent) {
	 
	 
	
   pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  if(pm.isScreenOn())
  {
	  AsyncTaskCpuRam test = new AsyncTaskCpuRam(context);
	  test.execute();
  }
 }
}