package application;


import MaximumWidget.com.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;


@SuppressLint("ValidFragment")
public class Wireless extends Fragment {
	

	 View myFragmentView = null;
	 Context context;
	 
	 Timer t;
	 Handler handler;
	 
	 ImageView wifiLogo;
	 
	 TextView signalStrenghtWifiText;
	 TextView nameWifiText;
	  TextView wifiIPText;
	  TextView macWifiText;
	  TextView speedWifiText;
	  TextView bssidWifiText;
	  
	  
	  ImageView bluetoothLogo;
		 
		 TextView signalStrenghtBluetoothText;
		 TextView nameBluetoothText;
		  TextView scanModeBluetoothText;
		  TextView addressBluetoothText;
		  
		  ImageView GPSLogo;
			 
		  TextView titreGPSText;
		  
		  
		  ImageView ScreenLogo;
			 
		  TextView titreScreenText;
		  TextView text1Screen;
		  TextView text2Screen;
		  TextView text3Screen;
		  
	  
	  WifiManager wifiManager;
	  WifiInfo info;
	  
	  Activity activity;
	  
	  String mobileSignal = "0%";
	  
	  BluetoothAdapter mBluetoothAdapter;
	  
	  
	    TelephonyManager telephonyManager;
	    MyPhoneStateListener myListener;
	    
	    WindowManager wm;
	  
	  int level;
	  
	 

		private static final String KEY_CONTENT = "TestFragment:Content";

		private int mPos = -1;
		private int mImgRes;
		
		public Wireless() { }
		public Wireless(int pos) {
			mPos = pos;
		}
		

	    public static Intent newInstance(Activity activity, int pos) {
	    	Intent intent = new Intent(activity, Wireless.class);
			intent.putExtra("pos", pos);
			return intent;
	    }
	    
	    
	    @Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt("mPos", mPos);
		}

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	    }
	    
	    

	    @SuppressLint("NewApi")
		@Override
	  	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	  			Bundle savedInstanceState) {
	  		
	    	  myFragmentView = inflater.inflate(R.layout.activity_wireless, container, false);
	    	  
	    	  context = getActivity();
	    	  handler = new Handler();
	    	  
	    	     wifiLogo = (ImageView)myFragmentView.findViewById(R.id.wifiICon);
	    		 
	    		  signalStrenghtWifiText = (TextView)myFragmentView.findViewById(R.id.WifiSignal);
	    		  nameWifiText = (TextView)myFragmentView.findViewById(R.id.wifiTest1);
	    		   wifiIPText = (TextView)myFragmentView.findViewById(R.id.wifiTest2);
	    		   macWifiText = (TextView)myFragmentView.findViewById(R.id.wifiTest3);
	    		   speedWifiText = (TextView)myFragmentView.findViewById(R.id.wifiTest4);
	    		   bssidWifiText = (TextView)myFragmentView.findViewById(R.id.wifiTest5);
	    		   
	    		   
	    		    bluetoothLogo = (ImageView)myFragmentView.findViewById(R.id.bluetoothICon);
	    			 
	    			  signalStrenghtBluetoothText = (TextView)myFragmentView.findViewById(R.id.bluetoothSignal);
	    			  nameBluetoothText = (TextView)myFragmentView.findViewById(R.id.bluetoothTest1);
	    			   scanModeBluetoothText = (TextView)myFragmentView.findViewById(R.id.bluetoothTest2);
	    			   addressBluetoothText = (TextView)myFragmentView.findViewById(R.id.bluetoothTest3);
	    			   
	    			   GPSLogo = (ImageView)myFragmentView.findViewById(R.id.GPSICon);
		    			 
		    			  titreGPSText = (TextView)myFragmentView.findViewById(R.id.GPSSignal);

	    	  
		    			   ScreenLogo = (ImageView)myFragmentView.findViewById(R.id.screenICon);
		    				 
		    			   titreScreenText = (TextView)myFragmentView.findViewById(R.id.screenSignal);
		    			   text1Screen = (TextView)myFragmentView.findViewById(R.id.screenTest1);
		    			   text2Screen = (TextView)myFragmentView.findViewById(R.id.screenTest2);
		    			  
	    	  
	    	  //UP wifi toggle
				 wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
				 info = wifiManager.getConnectionInfo();
				

				 telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
				

				  activity = getActivity();
	    
			   if(isOnline3G(context) == 2)
			   {
				   
				   
				   myListener   = new MyPhoneStateListener();
				   ((TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE)).listen(myListener,MyPhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
				   //telephonyManager.listen(myListener ,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
				   
				   	  wifiLogo.setImageResource(R.drawable.internet_on_mobile);
			    	
			    	  
		    		  wifiIPText.setText("IP: " + getIpAddress());
		    		  macWifiText.setText("IMEI: " + telephonyManager.getDeviceId());
		    		  speedWifiText.setText("Phone number: " + telephonyManager.getLine1Number());
		    		  nameWifiText.setText("Operator: " + telephonyManager.getNetworkOperatorName());
		    		  bssidWifiText.setText("Speed: " + getMobileNetworkState());
			   }
			   
			   
			   else
				{
				   
				   
				   if(wifiManager.isWifiEnabled())
				    {
				    	  if(isOnlineWifi(context))
				    	  {  
				    		  wifiLogo.setImageResource(R.drawable.internet_on_wifi);
				    		  
				    		  signalStrenghtWifiText.setText(getWifiSignalStrength(context) + " %");
				    		  wifiIPText.setText("IP: " + getIpAddress());
				    		  macWifiText.setText("MAC: " + info.getMacAddress());
				    		  speedWifiText.setText("Speed: " + info.getLinkSpeed() + " Mbit");
				    		  nameWifiText.setText("Connected to: " + info.getSSID());
				    		  bssidWifiText.setText("BSSID: " + info.getBSSID()); 
				 		  }
				    	  else
				    	  {
				    		  wifiLogo.setImageResource(R.drawable.no_internet_wifi_on);
				    		  
				    		  signalStrenghtWifiText.setText("ON");
				    		  wifiIPText.setText("No connection");
				    		  macWifiText.setText("No connection");
				    		  speedWifiText.setText("No connection");
				    		  nameWifiText.setText("No connection");
				    		  bssidWifiText.setText("No connection");
				    	  }
				    }
				   else
				   {
					   wifiLogo.setImageResource(R.drawable.no_internet);
					   
					   signalStrenghtWifiText.setText("OFF");
			    		  wifiIPText.setText("No connection");
			    		  macWifiText.setText("No connection");
			    		  speedWifiText.setText("No connection");
			    		  nameWifiText.setText("No connection");
			    		  bssidWifiText.setText("No connection");
				   }
		    		  
		    		  
				}
			   
			   
			   //UP Bluetooth toggle
			    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			    
			    if(mBluetoothAdapter.isEnabled())
			    {
			    	  bluetoothLogo.setImageResource(R.drawable.bluetoothon);
		    		  
		    		  signalStrenghtBluetoothText.setText("ON");
		    		  addressBluetoothText.setText("Address: " + mBluetoothAdapter.getAddress());
		    		  scanModeBluetoothText.setText("Scan mode: " + mBluetoothAdapter.getScanMode());
		    		  nameBluetoothText.setText("Device's name: " + mBluetoothAdapter.getName());
			    }
			    else
			    {
			    	bluetoothLogo.setImageResource(R.drawable.bluetoothoff);
			    	
			    	  signalStrenghtBluetoothText.setText("OFF");
		    		  addressBluetoothText.setText("Address: " + mBluetoothAdapter.getAddress());
		    		  scanModeBluetoothText.setText("Scan mode: " + mBluetoothAdapter.getScanMode());
		    		  nameBluetoothText.setText("Device's name: " + mBluetoothAdapter.getName());
			    }
			    
			    int api = Integer.valueOf(android.os.Build.VERSION.SDK);
			    int width = 0;
			    int height = 0;
			    
			    wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			    Display display = wm.getDefaultDisplay();
			    
			    if(api>13)
			    {
			    	// get resolution
			       Point size = new Point();
			       display.getSize(size);
			       width = size.x;
			       height = size.y;
			    }
			    
			    else
			    {
			    	DisplayMetrics metrics = new DisplayMetrics();
			    	activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

			    	height = metrics.heightPixels;
			    	width = metrics.widthPixels;
			    }
			    
			    //UP toggle brightness
				   int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
			       int SCREEN_BRIGHTNESS_MODE_MANUAL = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
			       
			       if(SCREEN_BRIGHTNESS_MODE_AUTOMATIC == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
			       {
			    	   ScreenLogo.setImageResource(R.drawable.brightness_icon_auto);
	    				 
	    			   titreScreenText.setText("auto"); 
			       }
			       
			       if(SCREEN_BRIGHTNESS_MODE_MANUAL == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
			       {
			    	   ScreenLogo.setImageResource(R.drawable.brightness_icon);
	    				 
	    			   titreScreenText.setText(currentBrightness() + "%");
			       }
			    
			       
			       text1Screen.setText("Screen: " + width + "x" + height);
    			   text2Screen.setText("Refresh rate: " + display.getRefreshRate() + " fps");
			       
			    
			    	//UP toggle GPS
			       LocationManager gpsManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			       if(gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				    {
			    	   	GPSLogo.setImageResource(R.drawable.gpson);
		    			 
		    			  titreGPSText.setText("ON");

				    }
				    else if(!gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				    {
				    	GPSLogo.setImageResource(R.drawable.gpsoff);
		    			 
		    			  titreGPSText.setText("OFF");
				    }
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			      t = new Timer(true);
		    	  t.scheduleAtFixedRate(new Action(),0,1000);
	    
	    	  return myFragmentView;
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
		}//end method
	    
	    
	    
	    public int currentBrightness()
	     {
			 int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
		  	 int SCREEN_BRIGHTNESS_MODE_MANUAL = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
		  	  

		  	if(SCREEN_BRIGHTNESS_MODE_AUTOMATIC == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
		  	  {
		  			return 777;
		  	  }
		  	  
		  	  if(SCREEN_BRIGHTNESS_MODE_MANUAL == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
		  	  {
			  		int curBrightnessValue = 0;
			     	  try {
			   	  		curBrightnessValue = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
			   	  	} catch (SettingNotFoundException e) {
			   	  		// TODO Auto-generated catch block
			   	  		e.printStackTrace();
			   	  	}
		     	  
		     	  	int percentBrightness = (curBrightnessValue*100)/255;
		     	  	return percentBrightness;
		  	  }
		  	  
			 return 0;
	     }
	    
	    
	    public String getInches()
	    {
	    	
	    	DisplayMetrics dm = new DisplayMetrics();
	    	wm.getDefaultDisplay().getMetrics(dm);
	    	double x = Math.pow(dm.widthPixels/dm.xdpi,2);
	    	double y = Math.pow(dm.heightPixels/dm.ydpi,2);
	    	double screenInches = Math.sqrt(x+y);
	    	DecimalFormat df = new DecimalFormat ( ) ;
	    	df.setMaximumFractionDigits ( 2 );
	    	df.setMinimumFractionDigits ( 2 );
	    	return df.format (screenInches) + "\"";
	    }
	    
	    public String getIpAddress()
	    {
	    	String ipAddress = null;
	        try {
	            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	                NetworkInterface intf = en.nextElement();
	                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                    InetAddress inetAddress = enumIpAddr.nextElement();
	                    if (!inetAddress.isLoopbackAddress()) {
	                        ipAddress = inetAddress.getHostAddress().toString();
	                    }
	                }
	            }
	        } catch (SocketException ex) {}
	        return ipAddress;
	    }
	    
	    
	    private String getMobileNetworkState()
	    {
	    	String networkState = null;
	    	
	    	int type = telephonyManager.getNetworkType();
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_CDMA)
	    	{
	    		networkState = "CDMA";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_EDGE)
	    	{
	    		networkState = "EDGE";
	    	}
	    	
	    	if((type == telephonyManager.NETWORK_TYPE_EVDO_0) || (type == telephonyManager.NETWORK_TYPE_EVDO_A) || (type == telephonyManager.NETWORK_TYPE_EVDO_B))
	    	{
	    		networkState = "EVDO";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_GPRS)
	    	{
	    		networkState = "GPRS";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_LTE)
	    	{
	    		networkState = "LTE";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_UMTS)
	    	{
	    		networkState = "UMTS";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_EHRPD)
	    	{
	    		networkState = "EHRPD";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_HSDPA)
	    	{
	    		networkState = "HSDPA";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_HSPA)
	    	{
	    		networkState = "HSPA";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_HSPAP)
	    	{
	    		networkState = "HSPAP";
	    	}
	    	
	    	if(type == telephonyManager.NETWORK_TYPE_IDEN)
	    	{
	    		networkState = "IDEN";
	    	}
	    	
	    	return networkState;
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
	    
	    
	    class Action extends TimerTask 
	    {

		    @Override
		    public void run() 
		    {
		    	DoStuff();
		    }
	    }
	    
	    
	    
	    private void DoStuff()
	    {
	    	
	    	wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
			 info = wifiManager.getConnectionInfo();
			  	  
			  level = getWifiSignalStrength(context);
  		
  		
	  		Runnable runnable = new Runnable() {
			      @Override
			      public void run() {
	
	
			          handler.post(new Runnable() {
			            @Override
			            public void run() 
			            {
			            	if(wifiManager.isWifiEnabled())
						    {
						    	  if(isOnlineWifi(context))
						    	  {
						    		  
						    		  signalStrenghtWifiText.setText(level + " %");
						    		  speedWifiText.setText("Speed: " + info.getLinkSpeed() + " Mbit");
						 		    
						 		   }
						    	  else
						 		   {
						 			  //views.setImageViewResource(R.id.wifi, R.drawable.wifiactivenot);
						 		   }
						    }
						    else
						    {
						    	//views.setImageViewResource(R.id.wifi, R.drawable.wifidesactive);
						    }
			            }
			            });
				        
				      }
				    };
				    new Thread(runnable).start();
	    }
	    
	    
	    private class MyPhoneStateListener extends PhoneStateListener{
	        
	         @Override
		    public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
	        	 
	        	// get the signal strength (a value between 0 and 31)
		        if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
		             mobileSignal = (signalStrength.getCdmaDbm()*100)/31 + "%";
		        } else if  (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
		             mobileSignal = (signalStrength.getGsmSignalStrength()*100)/31 + "%";
		        }
		        
		        signalStrenghtWifiText.setText(mobileSignal);
		    }
	         
	    }
	    
	    
	    @Override
	    public void onStop() {
	    	super.onStop();
	    	
	    	t.cancel();
	        t.purge();
	    }

	    @Override
	    public void onPause() {
	     // TODO Auto-generated method stub
	     super.onPause();

	     t.cancel();
	     t.purge();
	    }  
    
}
