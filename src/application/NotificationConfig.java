package application;

import MaximumWidget.com.R;
import utils.getCPU;
import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ToggleButton;

public class NotificationConfig extends Fragment {
	
	int HELLO_ID = 1;
	int icon;
	getCPU cpu = new getCPU();
	String cpuLoad = new String("CPULoad");
	String cpuCore = new String("CPUCore");
	String cpuFreq = new String("CPUFreq");
	String coreUse = new String("CoreUse");
	String bootCpuLoad = new String("EnableBootCpuLoad");
	String bootCpuCore = new String("EnableBootCpuCore");
	String bootCoreUse = new String("EnableBootCoreUse");
	String bootCpuFreq = new String("EnableBootCpuFreq");
	String bootRam = new String("EnableBootRam");
	String ram = new String("EnableRam");
	String MY_PREF = new String("MY_PREFS");
	Boolean active = true;
	ToggleButton togCpuLoad;
	ToggleButton togCpuCore;
	ToggleButton togRam;
	ToggleButton togCoreUse;
	ToggleButton togCpuFreq;
	CheckBox checkCpuLoad;
	CheckBox checkCpuCore;
	CheckBox checkRam;
	CheckBox checkCoreUse;
	CheckBox checkCpuFreq;
	static Context context;
	
	private static final String KEY_CONTENT = "TestFragment:Content";
	private String mContent = "???";

    public static NotificationConfig newInstance(String content) {
    	NotificationConfig fragment = new NotificationConfig();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        } 
        
    }
    
    

    @Override
  	public View onCreateView(LayoutInflater inflater, ViewGroup container,
  			Bundle savedInstanceState) {
  		
    	  View myFragmentView = inflater.inflate(R.layout.activity_notification_config, container, false);
    	  
    	  context = getActivity();

        togCpuLoad = (ToggleButton)myFragmentView.findViewById(R.id.toggleButton1);
        togCpuCore = (ToggleButton)myFragmentView.findViewById(R.id.toggleButton2);
        togRam = (ToggleButton)myFragmentView.findViewById(R.id.toggleButton3);
        togCpuFreq = (ToggleButton)myFragmentView.findViewById(R.id.toggleButton4);
        togCoreUse = (ToggleButton)myFragmentView.findViewById(R.id.toggleButton5);

        checkCpuLoad = (CheckBox)myFragmentView.findViewById(R.id.checkBox1);
        checkCpuCore = (CheckBox)myFragmentView.findViewById(R.id.checkBox2);
        checkRam = (CheckBox)myFragmentView.findViewById(R.id.checkBox3);
        checkCpuFreq = (CheckBox)myFragmentView.findViewById(R.id.checkBoxCpuFreq);
        checkCoreUse = (CheckBox)myFragmentView.findViewById(R.id.checkBox5);
        
        
        //t = new Timer(true);
        //t.schedule(new Action(), 400);
        
        DoJob();
        
        return myFragmentView;
             
    }


//------------ENABLE NOTIF-------------------------------------------------------------------------- 
    
public Boolean EnableCpuLoad()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean prefCpuLoad = myPrefs.getBoolean(cpuLoad, false);
    return prefCpuLoad;
}

public Boolean EnableCpuCore()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean prefCpuCore = myPrefs.getBoolean(cpuCore, false);
    return prefCpuCore;
}


public Boolean EnableRam()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(ram, false);
    return bootStatut;
}


public Boolean enableCpuFreq()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(cpuFreq, false);
    return bootStatut;
}


public Boolean enableCoreUse()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(coreUse, false);
    return bootStatut;
}

//----------------ENABLE BOOT------------------------------------------------------------------------------------

public Boolean EnableBootCpuCore()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(bootCpuCore, false);
    return bootStatut;
}

public Boolean EnableBootCpuFreq()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(bootCpuFreq, false);
    return bootStatut;
}

public Boolean EnableBootCoreUse()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(bootCoreUse, false);
    return bootStatut;
}

public Boolean EnableBootCpuLoad()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(bootCpuLoad, false);
    return bootStatut;
}

public Boolean EnableBootRam()
{
	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    Boolean bootStatut = myPrefs.getBoolean(bootRam, false);
    return bootStatut;
}


//---------ENABLE/STOP NOTIFICATIONS--------------------------------------------------------------------------------------

public void EnableNotification(Context context)
{
	AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	  Intent intentAlarmManager = new Intent(context, AlarmNotification.class);
	  PendingIntent pi = PendingIntent.getBroadcast(context, 0, intentAlarmManager, 0);
	  //After after 3 seconds
	  am.setRepeating(AlarmManager.RTC, System.currentTimeMillis()+ 0, 5000 , pi);
}


public void StopNotification(Context context)
{
	  Intent intentalarme = new Intent(context, AlarmNotification.class);
	  PendingIntent sender = PendingIntent.getBroadcast(context, 0, intentalarme, 0);
	  AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	  alarmManager.cancel(sender);
}





//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



public void DoJob()
{
	final Context context = getActivity();
	
	
//-------------ENABLE CPU LOAD ----------------------------------------------------------------------------------------------------------------------
	
	if(EnableCpuLoad() != null) 
    {	
    	if(EnableCpuLoad())
    	{
    		togCpuLoad.setChecked(true);
    		checkCpuLoad.setEnabled(true);
    		
    		togCpuLoad.setOnClickListener(new OnClickListener()
            {
             	//@Override
    	        public void onClick(View view)
    	        { 
    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    	        		  SharedPreferences.Editor editor = myPrefs.edit();
    	        		  editor.putBoolean(cpuLoad, false);
    	        		  editor.commit();
    	        		  
    	        		  NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    	        		  mNotificationManager.cancel(1);
    	        		
    	        		  //Stop Alarm Manager
    	        		  //StopNotification(context);
    	        		  DoJob();

    		    }
    		});
    		
    	}
    	
    	else if(!EnableCpuLoad())
    	{
    		togCpuLoad.setChecked(false);
    		checkCpuLoad.setEnabled(false);
    		
    		togCpuLoad.setOnClickListener(new OnClickListener()
            {
             	//@Override
    	        public void onClick(View view)
    	        { 
    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
	        		  	SharedPreferences.Editor editor = myPrefs.edit();
    	        		editor.putBoolean(cpuLoad, true);
    	        		editor.commit();
    	        		
    	        		//Start alarm Manager
    	        		EnableNotification(context);
    	        		DoJob();

    		    }
    		});
    	}
    	
    	
    	
    	
    	
    }
    else
    {
    	togCpuLoad.setChecked(false);
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = myPrefs.edit();
    	editor.putBoolean(cpuLoad, false);
    	editor.commit();
    	DoJob();
    }
	
	
	
//------------------------------BOOT CPU LOAD ---------------------------------------------------------------------------------------------------------------------------------	
	
	
	if(EnableBootCpuLoad() != null) 
    {
    	if(EnableBootCpuLoad())
    	{
    		checkCpuLoad.setChecked(true);
    		
    		
    		checkCpuLoad.setOnClickListener(new OnClickListener()
            {
             	//@Override
    	        public void onClick(View view)
    	        { 
    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
    	        		  SharedPreferences.Editor editor = myPrefs.edit();
    	        		  editor.putBoolean(bootCpuLoad, false);
    	        		  editor.commit();
    	        		  
    	        		  DoJob();

    		    }
    		});
    		
    	}
    	
    	else if(!EnableCpuLoad())
    	{
    		checkCpuLoad.setChecked(false);
    		
    		checkCpuLoad.setOnClickListener(new OnClickListener()
            {
             	//@Override
    	        public void onClick(View view)
    	        { 
    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
	        		  	SharedPreferences.Editor editor = myPrefs.edit();
    	        		editor.putBoolean(bootCpuLoad, true);
    	        		editor.commit();

    	        		DoJob();

    		    }
    		});
    	}
    	
    	
    	
    	
    	
    }
    else
    {
    	checkCpuLoad.setChecked(false);
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = myPrefs.edit();
    	editor.putBoolean(bootCpuLoad, false);
    	editor.commit();
    	DoJob();
    }

	
	
	
	//-------------------------ENABLE CPU CORE----------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	if(EnableCpuCore() != null) 
    {
    	if(EnableCpuCore())
    	{
    		togCpuCore.setChecked(true);
    		checkCpuCore.setEnabled(true);
    		
    		togCpuCore.setOnClickListener(new OnClickListener()
            {
             	//@Override
    	        public void onClick(View view)
    	        { 

	    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
	    	        		  SharedPreferences.Editor editor = myPrefs.edit();
	    	        		  editor.putBoolean(cpuCore, false);
	    	        		  editor.commit();
	    	        		  
	    	        		  NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	    	        		  mNotificationManager.cancel(2);
	    	        		
	    	        		  //Stop Alarm Manager
	    	        		  //StopNotification(context);
	    	        		  DoJob();
	    	        		  
	    	        		  //Toast.makeText(context, "stop gg", Toast.LENGTH_SHORT).show();

    		    }
    		});
    		
    	}
    	
    	else if(!EnableCpuCore())
    	{
    		togCpuCore.setChecked(false);
    		checkCpuCore.setEnabled(false);
    		
    		togCpuCore.setOnClickListener(new OnClickListener()
            {
             	//@Override
    	        public void onClick(View view)
    	        { 
    	        	int cpu_max_core = cpu.getNumOfCpus();
    	        	if(cpu_max_core == 2 || cpu_max_core == 4)
	        		  {
    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
	        		  	SharedPreferences.Editor editor = myPrefs.edit();
    	        		editor.putBoolean(cpuCore, true);
    	        		editor.commit();
    	        		
    	        		//Start alarm Manager
    	        		EnableNotification(context);
    	        		DoJob();
    	        		//Toast.makeText(context, "enable gg", Toast.LENGTH_SHORT).show();
	        		  }
    	        	
    	        	else
	        		  {
	        			    AlertDialog alertDialog = new AlertDialog.Builder(NotificationConfig.context).create();
	        			    alertDialog.setTitle("Anfo alert");
	        			    alertDialog.setMessage("Your CPU only have one core.");
	        			    
	        			    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
	        			        public void onClick(DialogInterface dialog, int which) {
	        			   
	        			        	DoJob();
	        			   
	        			      } }); 
	        			    
	        			    alertDialog.setIcon(R.drawable.ic_launcher);
	        			    alertDialog.show();
	        			    
	        			    
	        		  }
    	        	
    	        		//finish(); startActivity(getIntent());

    		    }
    		});
    	}
    	
    	
    }
    else
    {
    	togCpuCore.setChecked(false);
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = myPrefs.edit();
    	editor.putBoolean(cpuCore, false);
    	editor.commit();
    	DoJob();
    }
	
	
	
	
	
	//------------------------------BOOT CPU CORE ---------------------------------------------------------------------------------------------------------------------------------	
		
		
		
		if(EnableBootCpuCore() != null) 
	    {
	    	if(EnableBootCpuCore())
	    	{
	    		checkCpuCore.setChecked(true);
	    		
	    		
	    		checkCpuCore.setOnClickListener(new OnClickListener()
	            {
	             	//@Override
	    	        public void onClick(View view)
	    	        { 
	    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
	    	        		  SharedPreferences.Editor editor = myPrefs.edit();
	    	        		  editor.putBoolean(bootCpuCore, false);
	    	        		  editor.commit();
	    	        		  
	    	        		  DoJob();

	    		    }
	    		});
	    		
	    	} 
	    	
	    	else if(!EnableBootCpuCore())
	    	{
	    		checkCpuCore.setChecked(false);
	    		
	    		checkCpuCore.setOnClickListener(new OnClickListener()
	            {
	             	//@Override
	    	        public void onClick(View view)
	    	        { 
	    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		        		  	SharedPreferences.Editor editor = myPrefs.edit();
	    	        		editor.putBoolean(bootCpuCore, true);
	    	        		editor.commit();

	    	        		DoJob();

	    		    }
	    		});
	    	}
	    	
	    	
	    	
	    	
	    	
	    }
	    else
	    {
	    	checkCpuCore.setChecked(false);
	    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = myPrefs.edit();
	    	editor.putBoolean(bootCpuCore, false);
	    	editor.commit();
	    	DoJob();
	    }
		
		
		
		
		//-------------------------ENABLE RAM----------------------------------------------------------------------------------------------------------------------------------------------------------------
		

		
		if(EnableRam() != null) 
	    {
	    	if(EnableRam())
	    	{
	    		togRam.setChecked(true);
	    		checkRam.setEnabled(true);
	    		
	    		togRam.setOnClickListener(new OnClickListener()
	            {
	             	//@Override
	    	        public void onClick(View view)
	    	        { 

		    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		    	        		  SharedPreferences.Editor editor = myPrefs.edit();
		    	        		  editor.putBoolean(ram, false);
		    	        		  editor.commit();
		    	        		  
		    	        		  NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	        		  mNotificationManager.cancel(3);
		    	        		
		    	        		  //Stop Alarm Manager
		    	        		  DoJob();
		    	        		  
		    	        		  //Toast.makeText(context, "stop gg", Toast.LENGTH_SHORT).show();

	    		    }
	    		});
	    		
	    	}
	    	
	    	else if(!EnableRam())
	    	{
	    		togRam.setChecked(false);
	    		checkRam.setEnabled(false);
	    		
	    		togRam.setOnClickListener(new OnClickListener()
	            {
	             	//@Override
	    	        public void onClick(View view)
	    	        { 

	    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		        		  	SharedPreferences.Editor editor = myPrefs.edit();
	    	        		editor.putBoolean(ram, true);
	    	        		editor.commit();
	    	        		
	    	        		//Start alarm Manager
	    	        		EnableNotification(context);
	    	        		DoJob();

	    		    }
	    		});
	    	}
	    	
	    	
	    }
	    else
	    {
	    	togRam.setChecked(false);
	    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = myPrefs.edit();
	    	editor.putBoolean(ram, false);
	    	editor.commit();
	    	DoJob();
	    }
		
		
		
		
		
		//------------------------------BOOT CPU CORE ---------------------------------------------------------------------------------------------------------------------------------	
			
			
			
			if(EnableBootRam() != null) 
		    {
		    	if(EnableBootRam())
		    	{
		    		checkRam.setChecked(true);
		    		
		    		
		    		checkRam.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		    	        		  SharedPreferences.Editor editor = myPrefs.edit();
		    	        		  editor.putBoolean(bootRam, false);
		    	        		  editor.commit();
		    	        		  
		    	        		  DoJob();

		    		    }
		    		});
		    		
		    	} 
		    	
		    	else if(!EnableBootRam())
		    	{
		    		checkRam.setChecked(false);
		    		
		    		checkRam.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			        		  	SharedPreferences.Editor editor = myPrefs.edit();
		    	        		editor.putBoolean(bootRam, true);
		    	        		editor.commit();

		    	        		DoJob();

		    		    }
		    		});
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
		    else
		    {
		    	checkRam.setChecked(false);
		    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = myPrefs.edit();
		    	editor.putBoolean(bootRam, false);
		    	editor.commit();
		    	DoJob();
		    }
			
			
			
			
			//-------------ENABLE CPU FREQ ----------------------------------------------------------------------------------------------------------------------
			
			if(enableCpuFreq() != null) 
		    {	
		    	if(enableCpuFreq())
		    	{
		    		togCpuFreq.setChecked(true);
		    		checkCpuFreq.setEnabled(true);
		    		
		    		togCpuFreq.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		    	        		  SharedPreferences.Editor editor = myPrefs.edit();
		    	        		  editor.putBoolean(cpuFreq, false);
		    	        		  editor.commit();
		    	        		  
		    	        		  NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	        		  mNotificationManager.cancel(4);
		    	        		
		    	        		  //Stop Alarm Manager
		    	        		  //StopNotification(context);
		    	        		  DoJob();

		    		    }
		    		});
		    		
		    	}
		    	
		    	else if(!enableCpuFreq())
		    	{
		    		togCpuFreq.setChecked(false);
		    		checkCpuFreq.setEnabled(false);
		    		
		    		togCpuFreq.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			        		  	SharedPreferences.Editor editor = myPrefs.edit();
		    	        		editor.putBoolean(cpuFreq, true);
		    	        		editor.commit();
		    	        		
		    	        		//Start alarm Manager
		    	        		EnableNotification(context);
		    	        		DoJob();

		    		    }
		    		});
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
		    else
		    {
		    	togCpuFreq.setChecked(false);
		    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = myPrefs.edit();
		    	editor.putBoolean(cpuFreq, false);
		    	editor.commit();
		    	DoJob();
		    }
			
			
			//------------------------------BOOT CPU FREQ ---------------------------------------------------------------------------------------------------------------------------------	
			
			
			if(EnableBootCpuFreq()!= null) 
		    {
		    	if(EnableBootCpuFreq())
		    	{
		    		checkCpuFreq.setChecked(true);
		    		
		    		
		    		checkCpuFreq.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		    	        		  SharedPreferences.Editor editor = myPrefs.edit();
		    	        		  editor.putBoolean(bootCpuFreq, false);
		    	        		  editor.commit();
		    	        		  
		    	        		  DoJob();

		    		    }
		    		});
		    		
		    	} 
		    	
		    	else if(!EnableBootCpuFreq())
		    	{
		    		checkCpuFreq.setChecked(false);
		    		
		    		checkCpuFreq.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			        		  	SharedPreferences.Editor editor = myPrefs.edit();
		    	        		editor.putBoolean(bootCpuFreq, true);
		    	        		editor.commit();

		    	        		DoJob();

		    		    }
		    		});
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
		    else
		    {
		    	checkCpuFreq.setChecked(false);
		    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = myPrefs.edit();
		    	editor.putBoolean(bootCpuFreq, false);
		    	editor.commit();
		    	DoJob();
		    }
			
			
			
//-------------ENABLE CORE USE ----------------------------------------------------------------------------------------------------------------------
			
			if(enableCoreUse() != null) 
		    {	
		    	if(enableCoreUse())
		    	{
		    		togCoreUse.setChecked(true);
		    		checkCoreUse.setEnabled(true);
		    		
		    		togCoreUse.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		    	        		  SharedPreferences.Editor editor = myPrefs.edit();
		    	        		  editor.putBoolean(coreUse, false);
		    	        		  editor.commit();
		    	        		  
		    	        		  //NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	        		  //mNotificationManager.cancel(5);
		    	        		
		    	        		  //Stop Alarm Manager
		    	        		  //StopNotification(context);
		    	        		  DoJob();

		    		    }
		    		});
		    		
		    	}
		    	
		    	else if(!enableCoreUse())
		    	{
		    		togCoreUse.setChecked(false);
		    		checkCoreUse.setEnabled(false);
		    		
		    		togCoreUse.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			        		  	SharedPreferences.Editor editor = myPrefs.edit();
		    	        		editor.putBoolean(coreUse, true);
		    	        		editor.commit();
		    	        		
		    	        		//Start alarm Manager
		    	        		EnableNotification(context);
		    	        		DoJob();

		    		    }
		    		});
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
		    else
		    {
		    	togCoreUse.setChecked(false);
		    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = myPrefs.edit();
		    	editor.putBoolean(coreUse, false);
		    	editor.commit();
		    	DoJob();
		    }
			
			
			//------------------------------BOOT CORE USE ---------------------------------------------------------------------------------------------------------------------------------	
			
			
			if(EnableBootCoreUse() != null) 
		    {
		    	if(EnableBootCoreUse())
		    	{
		    		checkCoreUse.setChecked(true);
		    		checkCoreUse.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		    	        		  SharedPreferences.Editor editor = myPrefs.edit();
		    	        		  editor.putBoolean(bootCoreUse, false);
		    	        		  editor.commit();
		    	        		  
		    	        		  DoJob();

		    		    }
		    		});
		    		
		    	}
		    	
		    	else if(!EnableCpuCore())
		    	{
		    		checkCoreUse.setChecked(false);
		    		
		    		checkCoreUse.setOnClickListener(new OnClickListener()
		            {
		             	//@Override
		    	        public void onClick(View view)
		    	        { 
		    	        		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
			        		  	SharedPreferences.Editor editor = myPrefs.edit();
		    	        		editor.putBoolean(bootCoreUse, true);
		    	        		editor.commit();

		    	        		DoJob();

		    		    }
		    		});
		    	}
		    	
		    	
		    	
		    	
		    	
		    }
		    else
		    {
		    	checkCoreUse.setChecked(false);
		    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = myPrefs.edit();
		    	editor.putBoolean(bootCoreUse, false);
		    	editor.commit();
		    	DoJob();
		    }

			
		
	
}

    
}
