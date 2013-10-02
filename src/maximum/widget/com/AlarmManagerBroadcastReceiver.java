package maximum.widget.com;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;


public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {
	
	
	
   	PowerManager pm;
	 MyPhoneStateListener myListener;
	 TelephonyManager telephonyManager;
	 String mobileSignal = " :-( ";
	 Context context;
   	

 @Override
 public void onReceive(Context context, Intent intent) {
	 
	 this.context = context;

	 String valueToSent = " :-( ";
	
   pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
  if(pm.isScreenOn())
  {
	  
	  WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		 WifiInfo info = wifiManager.getConnectionInfo(); 
		 telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	  
		 
	   
	   if(isOnline3G(context) == 2)
	   {
		   myListener = new MyPhoneStateListener();
			//((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).listen(myListener,MyPhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
			   telephonyManager.listen(myListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
			   
			   valueToSent  =myListener.getGsmSignal();
	   }
    
    else
    {
    	telephonyManager.listen(myListener,MyPhoneStateListener.LISTEN_NONE);
	   if(wifiManager.isWifiEnabled())
	    {
	    	  if(isOnlineWifi(context))
	    	  {  
	    		
	  			 valueToSent = getWifiSignalStrength(context) + "%";
	 		  }
	    	  else
	    	  {
	    		  valueToSent = "???";
	    	  }
	    }
	   
	   else if(isOnline3G(context) != 2)
	   {
		   valueToSent = "OFF";
	   }
	   
	   else
	   {
		   valueToSent = "OFF";
	   }
    }
	   
	  if(isOnline3G(context) != 2)
	  {
		  sent(context,valueToSent);
	  }

  }
 }
 
 public void sent(Context context,String valueToSent)
 {
	 AsyncTaskMaximumWidget test = new AsyncTaskMaximumWidget(context,valueToSent);
	  test.execute();
 }
 
 
 
 
 public int getWifiSignalStrength(Context context){
	    int MIN_RSSI        = -100;
	    int MAX_RSSI        = -55;  
	    int levels          = 101;
	    WifiManager wifi    = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);            
	    WifiInfo info       = wifi.getConnectionInfo(); 
	    int rssi            = info.getRssi();

	    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH){
	        return WifiManager.calculateSignalLevel(info.getRssi(), levels);
	    } else {             
	        // this is the code since 4.0.1
	        if (rssi <= MIN_RSSI) {
	            return 0;
	        } else if (rssi >= MAX_RSSI) {
	            return levels - 1;
	        } else {
	            float inputRange = (MAX_RSSI - MIN_RSSI);
	            float outputRange = (levels - 1);
	            return (int)((float)(rssi - MIN_RSSI) * outputRange / inputRange);
	        }
	    }
	}


     
     public boolean isOnlineWifi(Context context) {
	    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    	NetworkInfo netInfoWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    if (netInfoWifi != null && netInfoWifi.isConnected()) {
		        return true;
		    }
		    return false;
		}
	    
	    public int isOnline3G(Context context) {
		    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    boolean connected = false;
		    
		    if (netInfo != null && netInfo.isConnected()) 
		    {
		    	connected = true;
		        return 2;
		    }
		    if (netInfo != null && !connected) 
		    {
		    	connected = false;
		        return 0;
		    }
		    else
		    {
		    	return 0;
		    }
		    
		}


class MyPhoneStateListener extends PhoneStateListener{
	private String gsmSignal;
	@Override
	    public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
    	 	
			
			// get the signal strength (a value between 0 and 31)
	        if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) 
	        {
	             mobileSignal = (signalStrength.getCdmaDbm()*100)/31 + "%";
	             
	        } 
	        else if  (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) 
	        {
	             mobileSignal = (signalStrength.getGsmSignalStrength()*100)/31 + "%";
	        }
	        
	        setGsmSignal(mobileSignal);
	        Log.e("signal", getGsmSignal());
	        
	        if(isOnline3G(context) == 2)
	 	   	{
	        	sent(context, getGsmSignal());
	 	   	}
	        else
	        {
	        	sent(context, "OFF");
	        }
	    }
	

		public String getGsmSignal() {
			return gsmSignal;
		}


		public void setGsmSignal(String gsmSignal) {
			this.gsmSignal = gsmSignal;
		}
} 

}