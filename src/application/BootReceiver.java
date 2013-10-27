package application;

import android.app.Activity;
import utils.getCPU;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
	
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
	getCPU cpu = new getCPU();
    String cpuCoreBiatch = new String("CPUCoreBiatch");
    String bootBiatch = new String("CoreBootBiatch");
    String cpuMinFreq = new String("cpuMinFreq");
    String cpuMaxFreq = new String("cpuMaxFreq");
    String cpuGovernor = new String("cpuGovernor");
    String bootFrequency = new String("bootFrequency");
    Context context;
	
    @Override
    public void onReceive(Context context, Intent intent) {
    	
    	this.context = context;
    	
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        Boolean bootStatutCpuLoad = myPrefs.getBoolean(bootCpuLoad, false);
        Boolean bootStatutCpuCore = myPrefs.getBoolean(bootCpuCore, false);
        Boolean bootStatutRam = myPrefs.getBoolean(bootRam, false);
        Boolean bootStatutCpuFreq = myPrefs.getBoolean(bootCpuFreq, false);
        Boolean bootStatutCoreUse = myPrefs.getBoolean(bootCoreUse, false);
    	
        //without RAM
        if(bootStatutCpuLoad)
        {
	        NotificationConfig bg = new NotificationConfig();
	        bg.EnableNotification(context);
        }
        
        // 2 par 2 avec RAM
        if(bootStatutCpuCore)
        {
        	NotificationConfig bg = new NotificationConfig();
	        bg.EnableNotification(context);
        }

        //3 par 3 avec RAM
        if(bootStatutRam)
        {
        	NotificationConfig bg = new NotificationConfig();
	        bg.EnableNotification(context);
        }
        
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        	
            if(!bootStatutCpuLoad)
            {
            	SharedPreferences.Editor editor = myPrefs.edit();
        		editor.putBoolean(cpuLoad, false);
        		editor.commit();
            }
        	
        	
	        if(!bootStatutCpuCore)
	        {
	        	SharedPreferences.Editor editor = myPrefs.edit();
	    		editor.putBoolean(cpuCore, false);
	    		editor.commit();
	        }
	        
	        
	        if(!bootStatutRam)
	        {
	        	SharedPreferences.Editor editor = myPrefs.edit();
	    		editor.putBoolean(ram, false);
	    		editor.commit();
	        }
	        
	        
	        if(!bootStatutCpuFreq)
	        {
	        	SharedPreferences.Editor editor = myPrefs.edit();
	    		editor.putBoolean(bootCpuFreq, false);
	    		editor.commit();
	        }
	        
	        if(!bootStatutCoreUse)
	        {
	        	SharedPreferences.Editor editor = myPrefs.edit();
	    		editor.putBoolean(bootCoreUse, false);
	    		editor.commit();
	        }
	        
	        
	        if(getPrefBoot())
	        {
	        	cpu.setMaxCoreAllowed(getSavedCore());
	        	Toast.makeText(context, "Anfo: Core preference launched !", Toast.LENGTH_SHORT).show();
	        }

            if(getPrefBootFrequency())
            {
                cpu.setMaxFreqCPU0(getPrefMaxFrequency()*1000);
                cpu.setMinFreqCPU0(getPrefMinFrequency()*1000);
                cpu.setGovernor(getPrefGovernor());
                Toast.makeText(context, "Anfo: frequency preference launched !", Toast.LENGTH_SHORT).show();
            }
    }


    public Boolean getPrefBootFrequency()
    {
        SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        Boolean prefBootCore = myPrefs.getBoolean(bootFrequency, false);
        return prefBootCore;
    }

    public int getPrefMinFrequency()
    {
        SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        int prefBootCore = myPrefs.getInt(cpuMinFreq, 1000);
        return prefBootCore;
    }


    public int getPrefMaxFrequency()
    {
        SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        int prefBootCore = myPrefs.getInt(cpuMaxFreq, 1000);
        return prefBootCore;
    }


    public String getPrefGovernor()
    {
        SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        String prefBootCore = myPrefs.getString(cpuGovernor, "ondemand");
        return prefBootCore;
    }
    
    public int getSavedCore()
    {
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        int prefCpuCore = myPrefs.getInt(cpuCoreBiatch, 4);
        return prefCpuCore;
    }
    
    
    public Boolean getPrefBoot()
    {
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        Boolean prefBootCore = myPrefs.getBoolean(bootBiatch, false);
        return prefBootCore;
    }
    
}
 