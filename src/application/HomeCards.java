package application;

import MaximumWidget.com.R;
import utils.getDisk;
import utils.getCPU;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import maximum.widget.com.memory;

import java.util.Timer;
import java.util.TimerTask;

import com.actionbarsherlock.app.SherlockFragment;


@SuppressLint("ValidFragment")
public class HomeCards extends SherlockFragment {
	
	private static final String KEY_CONTENT = "TestFragment:Content";
	
	
	
	private int mPos = -1;
	private int mImgRes;
	
	public HomeCards() { }
	public HomeCards(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, HomeCards.class);
		intent.putExtra("pos", pos);
		return intent;
    }
    
    
    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mPos", mPos);
	}
    
    

    
    

    private String mContent = "???";
    Intent intentBattery;
	 private int batterylevel = 0;
	 private String batteryStatus ="";
	 private String batteryTech ="";
	 private float batteryTemp = 0;
	 private int batteryVolt = 0;
	 float battery = 0;
	 View myFragmentView = null;
	 getCPU cpu;
     Timer t;
    TextView cpuLoadText;
    TextView cpuCoreText;
    TextView currentMhz;
    Handler handler;
    getDisk dd;
    CpuStat cpuStat;
    
    ImageView dataIcon;
    TextView dataText;
    ImageView bluetoothIcon;
    TextView bluetoothText;
    ImageView gpsIcon;
    TextView gpsText;
    ImageView brightnessIcon;
    TextView brightnessText;
    ImageView mobileDataIcon;
    TextView mobileDataText;
    
    ProgressBar barBattery;
    ProgressBar barCPU;
    ProgressBar barRam;
    ProgressBar barScreen;
    ProgressBar barSdCard;
    TextView barBatteryText;
    TextView barCPUText;
    TextView barRamText;
    TextView barScreenText;
    TextView barSdCardText;
    
    
    Context context;
    
    
    WifiManager wifiManager;
	  WifiInfo info;
	  
	  Activity activity;
	  
	  String mobileSignal = "0%";
	  
	  BluetoothAdapter mBluetoothAdapter;
	  
	  
	    TelephonyManager telephonyManager;
	    MyPhoneStateListener myListener;
    
	    int level;
    
    
    
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
         TextView info = (TextView) myFragmentView.findViewById(R.id.BatteryAct1);
       	 TextView info2 = (TextView) myFragmentView.findViewById(R.id.BatteryAct2);
       	 ImageView img = (ImageView) myFragmentView.findViewById(R.id.batteryViewImg);
       	 
       	 info.setText(batterylevel + "%");
       	 barBattery.setProgress(batterylevel);
       	 barBatteryText.setText(batterylevel + "%");
       	 	
       	   info2.setText(
           "Temperature: " + battery + "°C" + "\n" +
           "Voltage: " + batteryVolt + " mV" + "\n" +
           "Techonology: " + batteryTech +  "\n" +
           "Status: " + batteryStatus);
       	 
         
         if(batteryStatus == "Charging") 
         {
       	  img.setImageResource(R.drawable.chargg);
         }
         else if(batterylevel < 4)
         {
       	  img.setImageResource(R.drawable.a4);
         }

         else if(batterylevel < 10)
         {
       	  img.setImageResource(R.drawable.a10);
         }
         
         else if(batterylevel < 10)
         {
       	  img.setImageResource(R.drawable.a10);
         }
         
         else if(batterylevel < 20)
         {
       	  img.setImageResource(R.drawable.a20);
         }
         
         else if(batterylevel < 30)
         {
       	  img.setImageResource(R.drawable.a30);
         }
         
         else if(batterylevel < 40)
         {
       	  img.setImageResource(R.drawable.a40);
         }
         
         else if(batterylevel < 50)
         {
       	  img.setImageResource(R.drawable.a50);
         }
         
         else if(batterylevel < 60)
         {
       	  img.setImageResource(R.drawable.a60);
         }
         
         else if(batterylevel < 70)
         {
       	  img.setImageResource(R.drawable.a70);
         }
         
         else if(batterylevel < 80)
         {
       	  img.setImageResource(R.drawable.a80);
         }
         
         else if(batterylevel < 90)
         {
       	  img.setImageResource(R.drawable.a90);
         }
         
         else if(batterylevel < 100)
         {
       	  img.setImageResource(R.drawable.a100);
         }
         
         else if(batteryStatus == "Full")
         {
       	  img.setImageResource(R.drawable.ar100);
         }

       }
    };
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		myFragmentView = inflater.inflate(R.layout.activity_home_cards, container, false);
		
        context = getActivity();
        activity = getActivity();
        
        handler = new Handler();
        cpu = new getCPU();
        dd = new getDisk();
        cpuStat = new CpuStat();
        
        TextView cpuAverageText = (TextView)myFragmentView.findViewById(R.id.CardCPUAverage);
        cpuCoreText = (TextView)myFragmentView.findViewById(R.id.CardCoreActiv);
        cpuLoadText = (TextView)myFragmentView.findViewById(R.id.CardCPUpourcent);
        currentMhz = (TextView)myFragmentView.findViewById(R.id.CardCPUNow);
        
        TextView ramPourcentText = (TextView)myFragmentView.findViewById(R.id.CardRamPourcent);
        TextView ramTotalText = (TextView)myFragmentView.findViewById(R.id.CardRamTotal);
        TextView ramUseText = (TextView)myFragmentView.findViewById(R.id.CardRamUse);
        TextView ramFreeText = (TextView)myFragmentView.findViewById(R.id.CardRamFree);
        
        
        TextView sdPourcentText = (TextView)myFragmentView.findViewById(R.id.CardSDCardPourcent);
        TextView sdTotalText = (TextView)myFragmentView.findViewById(R.id.CardSDCardTotal);
        TextView sdUseText = (TextView)myFragmentView.findViewById(R.id.CardSDCardUse);
        TextView sdFreeText = (TextView)myFragmentView.findViewById(R.id.CardSDCardFree);
        
        //wifi etc card
         dataIcon = (ImageView)myFragmentView.findViewById(R.id.dataIcon);
         dataText = (TextView)myFragmentView.findViewById(R.id.dataText);
         bluetoothIcon = (ImageView)myFragmentView.findViewById(R.id.bluetoothIconCard);
         bluetoothText = (TextView)myFragmentView.findViewById(R.id.BluetoothText);
         gpsIcon = (ImageView)myFragmentView.findViewById(R.id.gpsIcon);
         gpsText = (TextView)myFragmentView.findViewById(R.id.gpsText);
         brightnessIcon = (ImageView)myFragmentView.findViewById(R.id.brightnessIcon);
         brightnessText = (TextView)myFragmentView.findViewById(R.id.brightnessText);
         mobileDataIcon = (ImageView)myFragmentView.findViewById(R.id.data3gIConTitle);
         mobileDataText = (TextView)myFragmentView.findViewById(R.id.data3gTitleText);
         
          barBattery = (ProgressBar)myFragmentView.findViewById(R.id.progressBarBattery);
          barCPU = (ProgressBar)myFragmentView.findViewById(R.id.progressBarSdCpuMenu);
          barRam = (ProgressBar)myFragmentView.findViewById(R.id.progressBarRamMenu);
          barScreen = (ProgressBar)myFragmentView.findViewById(R.id.progressBarBrightness);
          barSdCard = (ProgressBar)myFragmentView.findViewById(R.id.progressBarSdInternalMenu);
          
           barBatteryText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarBattery);
           barCPUText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarCpuMenuText);
           barRamText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarRamMenu);
           barScreenText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarBrightnessText);
           barSdCardText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarSdInternalMenu);
        
        int cpuMax = 0; 
        int cpuMin = 0;
        int cpuAverage = 0;
        
        int TotalMemory = 0;
        
        try {
        	
			cpuCoreMax = cpu.getNumOfCpus();
			TotalMemory = cpu.getMemoryTotal();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String average = cpuStat.getAverageFreq();
        if(average == "" || average == null)
        {
            cpuAverageText.setText("Average: " + "unsupported feature");
        }
        else
        {
            cpuAverageText.setText("Average: " + cpuStat.getAverageFreq());
        }

        
        int MemoryAvailable = memory.getMemoryInfo(context);
		int MemoryUsing = TotalMemory-MemoryAvailable;  
		int mProgressStatus = 100-((MemoryAvailable*100)/TotalMemory);
        
        
        ramPourcentText.setText(mProgressStatus + "%");
        barRam.setProgress(mProgressStatus);
        barRamText.setText(mProgressStatus + "%");
        ramTotalText.setText("Total: " + TotalMemory + " Mo");
        ramUseText.setText("Use: " + MemoryUsing + " Mo");
        ramFreeText.setText("Free: " + MemoryAvailable + " Mo");
        
        int percentSD = (int)(long)dd.pourcentageUseInternal();
        sdPourcentText.setText(percentSD + "%"); 
        sdTotalText.setText("Total: " + dd.getTotalInternalMemorySize());
        sdUseText.setText("Use: " + dd.usageInternal());
        sdFreeText.setText("Free: " + dd.getAvailableInternalMemorySize());
        barSdCard.setProgress(percentSD);
        barSdCardText.setText(percentSD + "%");
        
        
        
        
        
        
        
      //UP wifi toggle
		 wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		 info = wifiManager.getConnectionInfo();
		

		 telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        
        
        if(isOnline3G(context) == 2)
		   {
			   
			   
			   myListener   = new MyPhoneStateListener();
			   telephonyManager.listen(myListener,MyPhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
			   
			   mobileDataIcon.setImageResource(R.drawable.dataactive);
		   }
        
        else
        {
        	mobileDataIcon.setImageResource(R.drawable.dataoff);
        	mobileDataText.setText("OFF");
        }
		   
		   
			   
			   
			   if(wifiManager.isWifiEnabled())
			    {
			    	  if(isOnlineWifi(context))
			    	  {  
			    		  dataIcon.setImageResource(R.drawable.wifiactive);  
			    		  dataText.setText(getWifiSignalStrength(context) + " %");
			 		  }
			    	  else
			    	  {
			    		  dataIcon.setImageResource(R.drawable.wifiactivenot); 
			    		  dataText.setText("ON");
			    	  }
			    }
			   
			   else
			   {
				   dataIcon.setImageResource(R.drawable.wifidesactive); 
		    		  dataText.setText("OFF");
			   }
			   

	    		  
	    		  
			
		   
		   
		   //UP Bluetooth toggle
		    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		    
		    if(mBluetoothAdapter.isEnabled())
		    {
		    	  bluetoothIcon.setImageResource(R.drawable.bluetoothon);
	    		  bluetoothText.setText("ON");
		    }
		    else
		    {
		    	bluetoothIcon.setImageResource(R.drawable.bluetoothoff);
		    	bluetoothText.setText("OFF");
		    }
		    

		    
		    //UP toggle brightness
			   int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
		       int SCREEN_BRIGHTNESS_MODE_MANUAL = android.provider.Settings.System.getInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
		       
		       if(SCREEN_BRIGHTNESS_MODE_AUTOMATIC == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
		       {
		    	   brightnessIcon.setImageResource(R.drawable.brightness_icon_auto);
		    	   brightnessText.setText("auto"); 
		    	   barScreen.setEnabled(false);
		    	   barScreenText.setText("auto");
		       }
		       
		       if(SCREEN_BRIGHTNESS_MODE_MANUAL == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
		       {
		    	   brightnessIcon.setImageResource(R.drawable.brightness_icon);
		    	   int curBrightness = currentBrightness();
		    	   brightnessText.setText(curBrightness + "%");
		    	   barScreen.setProgress(curBrightness);
		    	   barScreenText.setText(curBrightness + "%");
		       }
		    
		       
		       
		    
		    	//UP toggle GPS
		       LocationManager gpsManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		       if(gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			    {
		    	   gpsIcon.setImageResource(R.drawable.gpson);
		    	   gpsText.setText("ON");

			    }
			    else if(!gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			    {
			    	gpsIcon.setImageResource(R.drawable.gpsoff);
	    			gpsText.setText("OFF");
			    }
        
        
        
        
        
        
        
        
         
        
        t = new Timer(true);
        t.scheduleAtFixedRate(new Action(),0,10000);
        
        
        return myFragmentView;
	}
	
	
	
	class Action extends TimerTask 
    {

	    @Override
	    public void run() 
	    {
	    	DoStuff();
	    }
    }
	
	
	int cpuCore = 0;
    int cpuCoreMax = 0;
    float percent2 = 0;
    int percent = 0;
    int cpuNow = 0;
    
	public void DoStuff()
	{
		
		percent2 = cpu.readUsage();
		percent= (int) (percent2*100);
		
		level = getWifiSignalStrength(context);
		  	  
		
    	try {
			cpuCore = cpu.getActiveCore();
			
			if(cpuCore == 0)
			{
				cpuCore = cpuCoreMax;
			}
			
			cpuNow = cpu.getCPU0FrequencyCurrentNormaleWay();
			
			if(cpuNow == 0)
			{
				cpuNow = cpu.getCPU0FrequencyCurrentCustomWay();
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		Runnable runnable = new Runnable() {
		      @Override
		      public void run() {


		          handler.post(new Runnable() {
		            @Override
		            public void run() 
		            {
		            	currentMhz.setText("Current: " + cpuNow/1000 + " Mhz");
		    			cpuLoadText.setText(percent + "%");
		    			barCPU.setProgress(percent);
		    			barCPUText.setText(percent + "%");
		    			cpuCoreText.setText("Active core: " + cpuCore + "/" + cpuCoreMax);
		    			
		    			
		    			if(wifiManager.isWifiEnabled())
					    {
					    	  if(isOnlineWifi(context))
					    	  {  
					    		  dataIcon.setImageResource(R.drawable.wifiactive);  
					    		  dataText.setText(level + " %");
					 		  }
					    	  else
					    	  {
					    		  dataIcon.setImageResource(R.drawable.wifiactivenot); 
					    		  dataText.setText("ON");
					    	  }
					    }
					   
					   else
					   {
						   dataIcon.setImageResource(R.drawable.wifidesactive); 
				    		  dataText.setText("OFF");
					   }
		    			
		    			
		            }
		            });
			        
			      }
			    };
			    new Thread(runnable).start();
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
		  		int curBrightnessValue = 1;
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
    
    
    private class MyPhoneStateListener extends PhoneStateListener{
        
        @Override
	    public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
       	 
       	// get the signal strength (a value between 0 and 31)
	        if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA) {
	             mobileSignal = (signalStrength.getCdmaDbm()*100)/31 + "%";
	        } else if  (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
	             mobileSignal = (signalStrength.getGsmSignalStrength()*100)/31 + "%";
	        }
	        
	        mobileDataText.setText(mobileSignal);
	    }
        
   }
	
	
	
	@Override
    public void onStart() {
    	super.onStart();
    	IntentFilter intentFilter = new IntentFilter();
    	intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
    	getActivity().registerReceiver(myReceiver, intentFilter);
    }
    @Override
    public void onStop() {
    	super.onStop();
    	getActivity().unregisterReceiver(myReceiver);
    	
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
