package application;

import MaximumWidget.com.R;
import utils.getCPU;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.PowerManager;
import maximum.widget.com.ControlCore;
import maximum.widget.com.CpuInfo;
import maximum.widget.com.RamUse;
import maximum.widget.com.memory;

public class asynctask extends AsyncTask<Void, Integer, Void>
{
	Context context;
	Long when;
	

	    public asynctask(Context context) {
	        this.context = context;
	        when= getCPU.when;
	    }


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//Toast.makeText(, "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		// Mise à jour de la ProgressBar

	}

	@Override
	protected Void doInBackground(Void... arg0) {

		PowerManager pm;
		getCPU cpu = new getCPU();
	   	String cpuLoad = new String("CPULoad");
	   	String cpuCore = new String("CPUCore");
		String cpuFreq = new String("CPUFreq");
		String coreUse = new String("CoreUse");
	   	String ram = new String("EnableRam");
		String MY_PREF = new String("MY_PREFS");
		Boolean prefCpuLoad;
	    Boolean prefCpuCore;
	    Boolean prefRam;
	    Boolean prefCpuFreq;
	    Boolean prefCoreUse;
	    int numCore = cpu.getNumOfCpus();
		
		
		
		SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
	      prefCpuLoad = myPrefs.getBoolean(cpuLoad, false);
	      prefCpuCore = myPrefs.getBoolean(cpuCore, false);
	      prefRam = myPrefs.getBoolean(ram, false);
	      prefCpuFreq = myPrefs.getBoolean(cpuFreq, false);
	      prefCoreUse = myPrefs.getBoolean(coreUse, false);
	      
	      if(prefCpuLoad)
	      {
	      
	      
		  int logo = R.drawable.cpu1;
	  	 //Toast.makeText(context, "yy", Toast.LENGTH_SHORT).show();
	      
	      float percent = cpu.readUsage();
	      int load= (int) (percent*100);
	      
	      if(load == 1)
	      {
	   	   logo = R.drawable.cpu1;
	      }
	      else if(load == 2)
	      {
	   	   logo = R.drawable.cpu2;
	      }
	      else if(load == 3)
	      {
	   	   logo = R.drawable.cpu3;
	      }
	      else if(load == 4)
	      {
	   	   logo = R.drawable.cpu4;
	      }
	      else if(load == 5)
	      {
	   	   logo = R.drawable.cpu5;
	      }
	      else if(load == 6)
	      {
	   	   logo = R.drawable.cpu6;
	      }
	      else if(load == 7)
	      {
	   	   logo = R.drawable.cpu7;
	      }
	      else if(load == 8)
	      {
	   	   logo = R.drawable.cpu8;
	      }
	      else if(load == 9)
	      {
	   	   logo = R.drawable.cpu9;
	      }
	      else if(load == 10)
	      {
	   	   logo = R.drawable.cpu10;
	      }
	      else if(load == 11)
	      {
	   	   logo = R.drawable.cpu11;
	      }
	      else if(load == 12)
	      {
	   	   logo = R.drawable.cpu12;
	      }
	      else if(load == 13)
	      {
	   	   logo = R.drawable.cpu13;
	      }
	      else if(load == 14)
	      {
	   	   logo = R.drawable.cpu14;
	      }
	      else if(load == 15)
	      {
	   	   logo = R.drawable.cpu15;
	      }
	      else if(load == 16)
	      {
	   	   logo = R.drawable.cpu16;
	      }
	      else if(load == 17)
	      {
	   	   logo = R.drawable.cpu17;
	      }
	      
	      else if(load == 18)
	      {
	   	   logo = R.drawable.cpu18;
	      }
	      else if(load == 19)
	      {
	   	   logo = R.drawable.cpu19;
	      }
	      else if(load == 20)
	      {
	   	   logo = R.drawable.cpu20;
	      }
	      else if(load == 21)
	      {
	   	   logo = R.drawable.cpu21;
	      }
	      else if(load == 22)
	      {
	   	   logo = R.drawable.cpu22;
	      }
	      else if(load == 23)
	      {
	   	   logo = R.drawable.cpu23;
	      }
	      else if(load == 24)
	      {
	   	   logo = R.drawable.cpu24;
	      }
	      else if(load == 25)
	      {
	   	   logo = R.drawable.cpu25;
	      }
	      else if(load == 26)
	      {
	   	   logo = R.drawable.cpu26;
	      }
	      else if(load == 27)
	      {
	   	   logo = R.drawable.cpu27;
	      }
	      else if(load == 28)
	      {
	   	   logo = R.drawable.cpu28;
	      }
	      else if(load == 29)
	      {
	   	   logo = R.drawable.cpu29;
	      }
	      else if(load == 30)
	      {
	   	   logo = R.drawable.cpu30;
	      }
	      else if(load == 31)
	      {
	   	   logo = R.drawable.cpu31;
	      }
	      else if(load == 32)
	      {
	   	   logo = R.drawable.cpu2;
	      }
	      else if(load == 2)
	      {
	   	   logo = R.drawable.cpu32;
	      }
	      else if(load == 33)
	      {
	   	   logo = R.drawable.cpu33;
	      }
	      else if(load == 34)
	      {
	   	   logo = R.drawable.cpu34;
	      }
	      else if(load == 35)
	      {
	   	   logo = R.drawable.cpu35;
	      }
	      else if(load == 36)
	      {
	   	   logo = R.drawable.cpu36;
	      }
	      else if(load == 37)
	      {
	   	   logo = R.drawable.cpu37;
	      }
	      else if(load == 38)
	      {
	   	   logo = R.drawable.cpu38;
	      }
	      else if(load == 39)
	      {
	   	   logo = R.drawable.cpu39;
	      }
	      else if(load == 40)
	      {
	   	   logo = R.drawable.cpu40;
	      }
	      else if(load == 41)
	      {
	   	   logo = R.drawable.cpu41;
	      }
	      else if(load == 42)
	      {
	   	   logo = R.drawable.cpu42;
	      }
	      else if(load == 43)
	      {
	   	   logo = R.drawable.cpu43;
	      }
	      else if(load == 44)
	      {
	   	   logo = R.drawable.cpu44;
	      }
	      else if(load == 45)
	      {
	   	   logo = R.drawable.cpu45;
	      }
	      else if(load == 46)
	      {
	   	   logo = R.drawable.cpu46;
	      }
	      else if(load == 47)
	      {
	   	   logo = R.drawable.cpu47;
	      }
	      else if(load == 48)
	      {
	   	   logo = R.drawable.cpu48;
	      }
	      else if(load == 49)
	      {
	   	   logo = R.drawable.cpu49;
	      }
	      else if(load == 50)
	      {
	   	   logo = R.drawable.cpu50;
	      }
	      else if(load == 51)
	      {
	   	   logo = R.drawable.cpu51;
	      }
	      else if(load == 52)
	      {
	   	   logo = R.drawable.cpu52;
	      }
	      else if(load == 53)
	      {
	   	   logo = R.drawable.cpu53;
	      }
	      else if(load == 54)
	      {
	   	   logo = R.drawable.cpu54;
	      }
	      else if(load == 55)
	      {
	   	   logo = R.drawable.cpu55;
	      }
	      else if(load == 56)
	      {
	   	   logo = R.drawable.cpu56;
	      }
	      else if(load == 57)
	      {
	   	   logo = R.drawable.cpu57;
	      }
	      else if(load == 58)
	      {
	   	   logo = R.drawable.cpu58;
	      }
	      else if(load == 59)
	      {
	   	   logo = R.drawable.cpu59;
	      }
	      else if(load == 60)
	      {
	   	   logo = R.drawable.cpu60;
	      }
	      else if(load == 61)
	      {
	   	   logo = R.drawable.cpu61;
	      }
	      else if(load == 62)
	      {
	   	   logo = R.drawable.cpu62;
	      }
	      else if(load == 63)
	      {
	   	   logo = R.drawable.cpu63;
	      }
	      else if(load == 64)
	      {
	   	   logo = R.drawable.cpu64;
	      }
	      else if(load == 65)
	      {
	   	   logo = R.drawable.cpu65;
	      }
	      else if(load == 66)
	      {
	   	   logo = R.drawable.cpu66;
	      }
	      else if(load == 67)
	      {
	   	   logo = R.drawable.cpu67;
	      }
	      else if(load == 68)
	      {
	   	   logo = R.drawable.cpu68;
	      }
	      else if(load == 69)
	      {
	   	   logo = R.drawable.cpu69;
	      }
	      else if(load == 70)
	      {
	   	   logo = R.drawable.cpu70;
	      }
	      else if(load == 71)
	      {
	   	   logo = R.drawable.cpu71;
	      }
	      else if(load == 72)
	      {
	   	   logo = R.drawable.cpu72;
	      }
	      else if(load == 73)
	      {
	   	   logo = R.drawable.cpu73;
	      }
	      else if(load == 74)
	      {
	   	   logo = R.drawable.cpu74;
	      }
	      else if(load == 75)
	      {
	   	   logo = R.drawable.cpu75;
	      }
	      else if(load == 76)
	      {
	   	   logo = R.drawable.cpu76;
	      }
	      else if(load == 77)
	      {
	   	   logo = R.drawable.cpu77;
	      }
	      else if(load == 78)
	      {
	   	   logo = R.drawable.cpu78;
	      }
	      else if(load == 79)
	      {
	   	   logo = R.drawable.cpu79;
	      }
	      else if(load == 80)
	      {
	   	   logo = R.drawable.cpu80;
	      }
	      else if(load == 81)
	      {
	   	   logo = R.drawable.cpu81;
	      }
	      else if(load == 82)
	      {
	   	   logo = R.drawable.cpu82;
	      }
	      else if(load == 83)
	      {
	   	   logo = R.drawable.cpu83;
	      }
	      else if(load == 84)
	      {
	   	   logo = R.drawable.cpu84;
	      }
	      else if(load == 85)
	      {
	   	   logo = R.drawable.cpu85;
	      }
	      else if(load == 86)
	      {
	   	   logo = R.drawable.cpu86;
	      }
	      else if(load == 87)
	      {
	   	   logo = R.drawable.cpu87;
	      }
	      else if(load == 88)
	      {
	   	   logo = R.drawable.cpu88;
	      }
	      else if(load == 89)
	      {
	   	   logo = R.drawable.cpu89;
	      }
	      else if(load == 90)
	      {
	   	   logo = R.drawable.cpu90;
	      }
	      else if(load == 91)
	      {
	   	   logo = R.drawable.cpu91;
	      }
	      else if(load == 92)
	      {
	   	   logo = R.drawable.cpu92;
	      }
	      else if(load == 93)
	      {
	   	   logo = R.drawable.cpu93;
	      }
	      else if(load == 94)
	      {
	   	   logo = R.drawable.cpu94;
	      }
	      else if(load == 95)
	      {
	   	   logo = R.drawable.cpu95;
	      }
	      else if(load == 96)
	      {
	   	   logo = R.drawable.cpu96;
	      }
	      else if(load == 97)
	      {
	   	   logo = R.drawable.cpu97;
	      }
	      else if(load == 98)
	      {
	   	   logo = R.drawable.cpu98;
	      }
	      else if(load == 99)
	      {
	   	   logo = R.drawable.cpu99;
	      }
	      else if(load == 100)
	      {
	   	   logo = R.drawable.cpu100;
	      }
	      
	      
	      notificationStatusCpuLoad(context,logo,load);
	      
	}      
	      
	      else
	      {
	    	  //Toast.makeText(context, "alarm kill itself", Toast.LENGTH_SHORT).show();
	    	  CancelNotificationsCpuLoad(context);
	      }
	      
	      
//---------------------------- CORE-------------------------------------------------------------------------------------------------------------------------
	      
	      
	      
	      if(prefCpuCore)
	      {
	    	  int cpu_active_core = 0;
	    	  int logoCpuCore = R.drawable.core41;
	    	  
	    	  
	    	  
			try 
			{
				cpu_active_core = cpu.getActiveCore();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(cpu_active_core == 0)
		   	 {
				  float core1 = 0;
		    	  float core2 = 0;
		    	  float core3= 0;
		    	  float core4 = 0;
		    	  
		    	  int core1_load = 0;
		    	  int core2_load = 0;
		    	  int core3_load = 0;
		    	  int core4_load = 0;
		    	  
		    	  int compteur_bg = 0;
		    	  
				  core1 = cpu.readUsageCPU0();
		    	  core1_load = (int) (core1);
		    	  
		    	  core2 = cpu.readUsageCPU1();
		    	  core2_load = (int) (core2);
		    	  
		    	  core3 = cpu.readUsageCPU2();
		    	  core3_load = (int) (core3);
		    	  
		    	  core4 = cpu.readUsageCPU3();
		    	  core4_load = (int) (core4);
		    	  
		    	  //-------compteur bg-----------------
		    	  
		    	  if(core1_load > 0)
		    	  {
		    		  compteur_bg++;
		    	  }
		    	  
		    	  if(core2_load > 0)
		    	  {
		    		  compteur_bg++;
		    	  }
		    	  
		    	  if(core3_load > 0)
		    	  {
		    		  compteur_bg++;
		    	  }
		    	  
		    	  if(core4_load > 0)
		    	  {
		    		  compteur_bg++;
		    	  }
		    	  
		    	  //--------ACTIVE?--------------------------
		    	  
		    	  if(compteur_bg == 4)
		    	  {
		    		  cpu_active_core = 4;
		    	  }
		    	  
		    	  if(compteur_bg == 3)
		    	  {
		    		  cpu_active_core = 3;
		    	  }
		    	  
		    	  if(compteur_bg == 2)
		    	  {
		    		  cpu_active_core = 2;
		    	  }
		    	  
		    	  if(compteur_bg == 1)
		    	  {
		    		  cpu_active_core = 1;
		    	  }
		   	 }
			
			
			
			
		   	
	    	    if(numCore == 2)
	  	   		{

	      	   	 
	      	   		if(cpu_active_core == 1)
		      	   	{
	      	   			logoCpuCore = R.drawable.core21;   	   		
		      	   	}
		      	   	
		      	   	else if(cpu_active_core == 2)
		      	   	{
		      	   		logoCpuCore = R.drawable.core22;   	
		      	   	}
	      	   	
	      	   	
	  	   	}
		  

		      	if(numCore == 4)
	    	   	{
		     	   	 
		     	   	 
		     	   	if(cpu_active_core == 1)
		      	   	{
		     	   		logoCpuCore = R.drawable.core41;   	
		      	   	}
		      	   	
		      	   	else if(cpu_active_core == 2)
		      	   	{
		      	   		logoCpuCore = R.drawable.core42;   	
		      	   	}
		      	   	
		      	  else if(cpu_active_core == 3)
		    	   	{
		      		  logoCpuCore = R.drawable.core43;   	
		    	   	}
		      	   	
			      	else if(cpu_active_core == 4)
			  	   	{
			      		logoCpuCore = R.drawable.core44;
			  	   	}
	         }
		      	notificationStatusCpuCore(context,logoCpuCore,numCore,cpu_active_core);
	      }
		      	

	      else
	      {
	    	  //Toast.makeText(context, "alarm kill itself", Toast.LENGTH_SHORT).show();
	    	  CancelNotificationsCpuCore(context);
	      }
	      
	      
	      
//------------------------------RAM--------------------------------------------------------------------------------------------------------------------------------------------------------------
	      
	      if(prefRam)
	      {
	    	  int TotalMemory = 0;
	    	  int MemoryAvailable = 0;
	    	  int ramPourcent = 0;
	    	  int logo = 0;
	    	  
	    	  try {
	    		  
				TotalMemory = cpu.getMemoryTotal();
				MemoryAvailable = memory.getMemoryInfo(context);
	    		ramPourcent = 100-((MemoryAvailable*100)/TotalMemory);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	  
	    	  if(ramPourcent > 20)
	    	  {
	    		  logo = R.drawable.ram1;
	    	  }
	    	  
	    	   if(ramPourcent > 30)
	    	  {
	    		  logo = R.drawable.ram2;
	    	  }
	    	  
	    	   if(ramPourcent > 40)
	    	  {
	    		  logo = R.drawable.ram3;
	    	  }
	    	  
	    	   if(ramPourcent > 50)
	    	  {
	    		  logo = R.drawable.ram4;
	    	  }
	    	  
	    	   if(ramPourcent > 60)
	    	  {
	    		  logo = R.drawable.ram5;
	    	  }
	    	  
	    	   if(ramPourcent > 70)
	    	  {
	    		  logo = R.drawable.ram5;
	    	  }
	    	  
	    	   if(ramPourcent > 80)
	    	  {
	    		  logo = R.drawable.ram6;
	    	  }
	    	  
	    	   if(ramPourcent > 90)
	    	  {
	    		  logo = R.drawable.ram7;
	    	  }
	    	  
	    	   if(ramPourcent > 95)
	    	  {
	    		  logo = R.drawable.ram8;
	    	  }
	    	  
	    	  notificationRam(context, logo, ramPourcent);
	    	  
	      }
	      
	      
	      else
	      {
	    	  //Toast.makeText(context, "alarm kill itself", Toast.LENGTH_SHORT).show();
	    	  CancelNotificationsRam(context);
	      }

	      
	      if(prefCpuFreq)
	      {
	    	  int cpuFrequency = 0;
	    	  int logo_freq = 0;
	    	  
	    	  try {
				 cpuFrequency = cpu.getCPU0FrequencyCurrentNormaleWay()/1000;
				 
				 if(cpuFrequency == 0)
				 {
					 cpuFrequency = cpu.getCPU0FrequencyCurrentCustomWay()/1000;
				 }
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  
	    	  if(cpuFrequency <= 100  && cpuFrequency > 0)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_1;
	    	  }
	    	  
	    	  if(cpuFrequency <= 200  && cpuFrequency > 100)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_2;
	    	  }
	    	  
	    	  if(cpuFrequency <= 300 && cpuFrequency > 200)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_3;
	    	  }
	    	  
	    	  if(cpuFrequency <= 400 && cpuFrequency > 300)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_4;
	    	  }
	    	  
	    	  if(cpuFrequency <= 500  && cpuFrequency > 400)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_5;
	    	  }
	    	  
	    	  if(cpuFrequency <= 600 && cpuFrequency > 500)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_6;
	    	  }
	    	  
	    	  if(cpuFrequency <= 700 && cpuFrequency > 600)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_7;
	    	  }
	    	  
	    	  if(cpuFrequency <= 800 && cpuFrequency > 700)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_8;
	    	  }
	    	  
	    	  if(cpuFrequency <= 900 && cpuFrequency > 800)
	    	  {
	    		  logo_freq = R.drawable.mhz_0_9;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1000  && cpuFrequency > 900)
	    	  {
	    		  logo_freq = R.drawable.mhz_1;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1100  && cpuFrequency > 1000)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_1;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1200  && cpuFrequency > 1100)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_2;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1300  && cpuFrequency > 1200)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_3;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1400  && cpuFrequency > 1300)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_4;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1500 && cpuFrequency > 1400)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_5;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1600  && cpuFrequency > 1500)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_6;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1700 && cpuFrequency > 1600)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_7;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1800 && cpuFrequency > 1700)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_8;
	    	  }
	    	  
	    	  if(cpuFrequency <= 1900 && cpuFrequency > 1800)
	    	  {
	    		  logo_freq = R.drawable.mhz_1_9;
	    	  }
	    	  
	    	  if(cpuFrequency <= 2000 && cpuFrequency > 1900)
	    	  {
	    		  logo_freq = R.drawable.mhz_2;
	    	  }
	    	  
	    	  notificationCpuFreq(context, logo_freq, cpuFrequency);
	      }
	      
	      else
	      {
	    	  CancelNotificationsCpuFreq(context);
	      }
	      
	      
	      
	      if(prefCoreUse)
	      {

	    	 if(numCore == 1)
	    	 {
	    		 int logo = 0;
	    		 
	    		  float core = 0; 
		    	  int core_load = 0;
		    	  
	    		  core = cpu.readUsage();
		    	  core_load = (int) (core*100);
		    	  
		    	  logo = core_load_single_core(core_load);
		    	  
		    	  notificationCoreUseSingleCore(context, logo, core_load);
	    	 }
	    	  
	    	  
	    	  if(numCore == 2)
	    	  {
	    		  int logo1 = 0;
		    	  
		    	  float core1 = 0;
		    	  float core2 = 0;
		    	  
		    	  int core1_load = 0;
		    	  int core2_load = 0;
		    	  
	    		  core1 = cpu.readUsageCPU0();
		    	  core1_load = (int) (core1);
		    	  
		    	  core2 = cpu.readUsageCPU1();
		    	  core2_load = (int) (core2);
		    	  
		    	  
		    	  logo1 = core_load(core1_load, core2_load);
	    		  
		    	  notificationCoreUse1(context, logo1, core1_load, core2_load);
	    	  }
	    	  
	    	  
	    	  if(numCore == 4)
	    	  {
	    		  
	    		  int logo1 = 0;
		    	  int logo2 = 0;
		    	  
		    	  float core1 = 0;
		    	  float core2 = 0;
		    	  float core3= 0;
		    	  float core4 = 0;
		    	  
		    	  int core1_load = 0;
		    	  int core2_load = 0;
		    	  int core3_load = 0;
		    	  int core4_load = 0;
		    	  
	    		  core1 = cpu.readUsageCPU0();
		    	  core1_load = (int) (core1);
		    	  
		    	  core2 = cpu.readUsageCPU1();
		    	  core2_load = (int) (core2);
		    	  
		    	  core3 = cpu.readUsageCPU2();
		    	  core3_load = (int) (core3);
		    	  
		    	  core4 = cpu.readUsageCPU3();
		    	  core4_load = (int) (core4);
		    	  
		    	  
		    	  logo1 = core_load(core1_load, core2_load);
		    	  logo2 = core_load(core3_load, core4_load);
	    		  
		    	  notificationCoreUse1(context, logo1, core1_load, core2_load);
		    	  notificationCoreUse2(context, logo2, core3_load, core4_load);
	    	  }
	    	  
	      }
	      
	      
	      else
	      {
	    	  if(numCore == 1)
	    	  {
	    		  CancelNotificationsCoreUseSingleCore(context);
	    	  }
	    	  
	    	  if(numCore == 2)
	    	  {
	    		  CancelNotificationsCoreUseDualCore(context);
	    	  }
	    	  
	    	  if(numCore == 4)
	    	  {
	    		  CancelNotificationsCoreUseDualCore(context);
	    		  CancelNotificationsCoreUseQuadCore(context);
	    	  }
	      }
	      
	      
	      
	      
	      
	      
	      
    
	      
	      
	      if(!prefCpuCore && !prefCpuLoad && !prefRam && !prefCoreUse && !prefCpuFreq)
	      {
	    	  //Toast.makeText(context, "alarm kill itself", Toast.LENGTH_SHORT).show();
	    	  StopAlarm(context);
	      }
	      
	      
	      return null;
	}
	 
	 
	 
	 @SuppressLint("NewApi")
	public void notificationStatusCpuLoad(Context context,int logo,int load) 
	 {
		    int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, CpuInfo.class);
		    	  PendingIntent pendingIntent 
		    	  = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo CPU load")
		        .setContentText("CPU load: " + load + "%")
		        .setSmallIcon(logo)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setProgress(101,load,false)
		        .setWhen(when+300); 	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT; 
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		    	  
		    	  mNotificationManager.notify(1, notification);
		    }
		    
		    else
		    {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		    int icon = logo;
		     CharSequence tickerText = context.getString(R.string.app_name);     
		     
		     
		    final Notification notification = new Notification(icon, "Anfo widget: cpu global usage notification enabled", when+300);
		    notification.flags = Notification.FLAG_NO_CLEAR;
		    notification.flags = Notification.FLAG_ONGOING_EVENT;
		    
		    
		    Intent notificationIntent = new Intent(context, CpuInfo.class);
		    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);
		    

		    notification.setLatestEventInfo(context, tickerText, "CPU global usage: " + load + "%", contentIntent);
		    mNotificationManager.notify(1, notification);
		    }
		    
		}
	 
	 
	 @SuppressLint("NewApi")
	public void notificationStatusCpuCore(Context context,int logoCpuCore,int cpu_max_core,int cpu_active_core) 
	 {
		 
		 int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, ControlCore.class);
		    	  PendingIntent pendingIntent 
		    	  = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo CPU core")
		        .setContentText("Core active: " + cpu_active_core + "/" + cpu_max_core)
		        .setSmallIcon(logoCpuCore)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setWhen(when+200);  	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT;
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;

		    	  
		    	  mNotificationManager.notify(2, notification);
		    }
		    
		    else
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

			    int icon = logoCpuCore;
			     CharSequence tickerText = context.getString(R.string.app_name);

			     final Notification notification = new Notification(icon, "Anfo widget: CPU core notification enabled", when+200);
				    notification.flags = Notification.FLAG_NO_CLEAR;
				    notification.flags = Notification.FLAG_ONGOING_EVENT;
				    
			    Intent notificationIntent = new Intent(context, ControlCore.class);
			    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);

			    notification.setLatestEventInfo(context, tickerText, "Core active: " + cpu_active_core + "/" + cpu_max_core, contentIntent);
			    mNotificationManager.notify(2, notification);
		    }
		     
		} 

	 
	 @SuppressLint("NewApi")
	public void notificationRam(Context context,int logo,int load) 
	 {
		 
		 int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, RamUse.class);
		    	  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo RAM")
		        .setContentText("Ram usage: " + load  + "%")
		        .setSmallIcon(logo)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setProgress(101,load,false)
		        .setWhen(when+100);    	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT;
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		    	  
		    	  mNotificationManager.notify(3, notification);
		    }
		    
		    else
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

			    int icon = logo;
			     CharSequence tickerText = context.getString(R.string.app_name);

			     final Notification notification = new Notification(icon, "Anfo widget: RAM notification enabled", when+100);
				    notification.flags = Notification.FLAG_NO_CLEAR;
				    notification.flags = Notification.FLAG_ONGOING_EVENT;
			     
			    Intent notificationIntent = new Intent(context, RamUse.class);
			    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);

			    notification.setLatestEventInfo(context, tickerText,"Ram memory usage: " + load  + "%" , contentIntent);

			    mNotificationManager.notify(3, notification);
		    }
		    
		}
	 
	 
	 
	 @SuppressLint("NewApi")
	public void notificationCpuFreq(Context context,int logo,int load) 
	 {
		 
		 int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, CPUstatSmall.class);
		    	  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo CPU Frequency")
		        .setContentText("CPU Frequency: " + load + "MHz")
		        .setSmallIcon(logo)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setWhen(when+100);    	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT;
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		    	  
		    	  mNotificationManager.notify(4, notification);
		    }
		    
		    else
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

			    int icon = logo;
			     CharSequence tickerText = context.getString(R.string.app_name);

			     final Notification notification = new Notification(icon, "Anfo: CPU Frequency ", when+100);
				    notification.flags = Notification.FLAG_NO_CLEAR;
				    notification.flags = Notification.FLAG_ONGOING_EVENT;
			     
			    Intent notificationIntent = new Intent(context, CPUstatSmall.class);
			    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);

			    notification.setLatestEventInfo(context, tickerText,"CPU Frequency: " + load  + "MHz" , contentIntent);

			    mNotificationManager.notify(4, notification);
		    }
		    
		}
	 
	 
	 @SuppressLint("NewApi")
	public void notificationCoreUse1(Context context,int logo,int core1_load, int core2_load) 
	 {
		 
		 int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, ControlCore.class);
		    	  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo CPU Core Usage")
		        .setContentText("Core1: " + core1_load + "%" + "     " + "Core2: " + core2_load + "%")
		        .setSmallIcon(logo)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setWhen(when+100);    	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT;
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		    	  
		    	  mNotificationManager.notify(5, notification);
		    }
		    
		    else
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

			    int icon = logo;
			     CharSequence tickerText = context.getString(R.string.app_name);

			     final Notification notification = new Notification(icon, "Anfo: CPU Core Usage ", when+100);
				    notification.flags = Notification.FLAG_NO_CLEAR;
				    notification.flags = Notification.FLAG_ONGOING_EVENT;
			     
			    Intent notificationIntent = new Intent(context, ControlCore.class);
			    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);

			    notification.setLatestEventInfo(context, tickerText,"Core1: " + core1_load + "%" + "     " + "Core2: " + core2_load + "%", contentIntent);

			    mNotificationManager.notify(5, notification);
		    }
		    
		}
	 
	 
	 
	 @SuppressLint("NewApi")
	public void notificationCoreUse2(Context context,int logo,int core3_load, int core4_load) 
	 {
		 
		 int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, ControlCore.class);
		    	  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo CPU Core Usage")
		        .setContentText("Core3: " + core3_load + "%" + "     " + "Core4: " + core4_load + "%")
		        .setSmallIcon(logo)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setWhen(when+100);    	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT;
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		    	  
		    	  mNotificationManager.notify(6, notification);
		    }
		    
		    else
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

			    int icon = logo;
			     CharSequence tickerText = context.getString(R.string.app_name);

			     final Notification notification = new Notification(icon, "Anfo: CPU Core Usage ", when+100);
				    notification.flags = Notification.FLAG_NO_CLEAR;
				    notification.flags = Notification.FLAG_ONGOING_EVENT;
			     
			    Intent notificationIntent = new Intent(context, ControlCore.class);
			    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);

			    notification.setLatestEventInfo(context, tickerText,"Core3: " + "     " + core3_load + "%" + "Core4: " + core4_load + "%", contentIntent);

			    mNotificationManager.notify(6, notification);
		    }
		    
		}
	 
	 
	 @SuppressLint("NewApi")
	public void notificationCoreUseSingleCore(Context context,int logo,int core_load) 
	 {
		 
		 int api = Integer.valueOf(android.os.Build.VERSION.SDK);
		    
		    if(api>=14)
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    	Notification.Builder builder = new Notification.Builder(context);
		    	
		    	Intent intent = new Intent(context, ControlCore.class);
		    	  PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
		    	
		    	 builder
		        .setContentTitle("Anfo CPU Core Usage")
		        .setContentText("Core1: " + core_load + "%")
		        .setSmallIcon(logo)
		        .setContentIntent(pendingIntent)
		        .setOngoing(true)
		        .setAutoCancel(false)
		        .setWhen(when+100);    	
		    	 
		    	  Notification notification = builder.getNotification();
		    	  notification.flags |= Notification.FLAG_ONGOING_EVENT;
		    	  notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		    	  
		    	  mNotificationManager.notify(7, notification);
		    }
		    
		    else
		    {
		    	NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

			    int icon = logo;
			     CharSequence tickerText = context.getString(R.string.app_name);

			     final Notification notification = new Notification(icon, "Anfo: CPU Core Usage ", when+100);
				    notification.flags = Notification.FLAG_NO_CLEAR;
				    notification.flags = Notification.FLAG_ONGOING_EVENT;
			     
			    Intent notificationIntent = new Intent(context, ControlCore.class);
			    PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, 0);

			    notification.setLatestEventInfo(context, tickerText,"Core1: " + core_load + "%", contentIntent);

			    mNotificationManager.notify(7, notification);
		    }
		    
		}
	 
	 
	 
	 
	 
	 public void CancelNotificationsCpuLoad(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(1);
	 }
	 
	 public void CancelNotificationsCpuCore(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(2);
	 }
	 
	 public void CancelNotificationsRam(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(3);
	 }
	 
	 public void CancelNotificationsCpuFreq(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(4);
	 }
	 
	 public void CancelNotificationsCoreUseDualCore(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(5);
	 }
	 
	 public void CancelNotificationsCoreUseQuadCore(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(6);
	 }
	 
	 public void CancelNotificationsCoreUseSingleCore(Context context) 
	 {
		    NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		    mNotificationManager.cancel(7);
	 }

	 public void StopAlarm(Context context)
	 {
	 	  Intent intentalarme = new Intent(context, AlarmNotification.class);
	 	  PendingIntent sender = PendingIntent.getBroadcast(context, 0, intentalarme, 0);
	 	  AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	 	  alarmManager.cancel(sender);
	 }
	 
	 
	 public int core_load(int core1_load, int core2_load)
	 {
		 if(core1_load <= 1 && core2_load <= 1)
   	  {
   		  return R.drawable.notif_cpu_0_0;
   	  }
   	  
   	  if(core1_load <= 20 && core2_load <= 1)
   	  {
   		  return R.drawable.notif_cpu_1_0;
   	  }
   	  
   	  if(core1_load <= 40 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_2_0;
   	  }
   	  
   	  if(core1_load <= 60 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_3_0;
   	  }
   	  
   	  if(core1_load <= 80 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_4_0;
   	  }
   	  
   	  if(core1_load <= 100 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_5_0;
   	  }
   	  
   	  if(core1_load <= 1 && core2_load <= 20)
   	  {
   		return R.drawable.notif_cpu_0_1;
   	  }
   	  
   	  if(core1_load <= 20 && core2_load <= 20)
   	  {
   		return R.drawable.notif_cpu_1_1;
   	  }
   	  
   	  if(core1_load <= 40 && core2_load <= 20)
   	  {
   		return R.drawable.notif_cpu_2_1;
   	  }
   	  
   	  if(core1_load <= 60 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_3_1;
   	  }
   	  
   	  if(core1_load <= 80 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_4_1;
   	  }
   	  
   	  if(core1_load <= 100 && core2_load <= 1)
   	  {
   		return R.drawable.notif_cpu_5_1;
   	  }
   	  
   	  if(core1_load <= 1 && core2_load <= 40)
   	  {
   		return R.drawable.notif_cpu_0_2;
   	  }
   	  
   	  if(core1_load <= 20 && core2_load <= 40)
   	  {
   		return R.drawable.notif_cpu_1_2;
   	  }
   	  
   	  if(core1_load <= 40 && core2_load <= 40)
   	  {
   		return R.drawable.notif_cpu_2_2;
   	  }
   	  
   	  if(core1_load <= 60 && core2_load <= 40)
   	  {
   		return R.drawable.notif_cpu_3_2;
   	  }
   	  
   	  if(core1_load <= 80 && core2_load <= 40)
   	  {
   		return R.drawable.notif_cpu_4_2;
   	  }
   	  
   	  if(core1_load <= 100 && core2_load <= 40)
   	  {
   		return R.drawable.notif_cpu_5_2;
   	  }
   	  
   	  if(core1_load <= 1 && core2_load <= 60)
   	  {
   		return R.drawable.notif_cpu_0_3;
   	  }
   	  
   	  if(core1_load <= 20 && core2_load <= 60)
   	  {
   		return R.drawable.notif_cpu_1_3;
   	  }
   	  
   	  if(core1_load <= 40 && core2_load <= 60)
   	  {
   		return R.drawable.notif_cpu_2_3;
   	  }
   	  
   	  if(core1_load <= 60 && core2_load <= 60)
   	  {
   		return R.drawable.notif_cpu_3_3;
   	  }
   	  
   	  if(core1_load <= 80 && core2_load <= 60)
   	  {
   		return R.drawable.notif_cpu_4_3;
   	  }
   	  
   	  if(core1_load <= 100 && core2_load <= 60)
   	  {
   		return R.drawable.notif_cpu_5_3;
   	  }
   	  
   	  if(core1_load <= 1 && core2_load <= 80)
   	  {
   		return R.drawable.notif_cpu_0_4;
   	  }
   	  
   	  if(core1_load <= 20 && core2_load <= 80)
   	  {
   		return R.drawable.notif_cpu_1_4;
   	  }
   	  
   	  if(core1_load <= 40 && core2_load <= 80)
   	  {
   		return R.drawable.notif_cpu_2_4;
   	  }
   	  
   	  if(core1_load <= 60 && core2_load <= 80)
   	  {
   		return R.drawable.notif_cpu_3_4;
   	  }
   	  
   	  if(core1_load <= 80 && core2_load <= 80)
   	  {
   		return R.drawable.notif_cpu_4_4;
   	  }
   	  
   	  if(core1_load <= 100 && core2_load <= 80)
   	  {
   		return R.drawable.notif_cpu_5_4;
   	  }
   	  
   	  if(core1_load <= 1 && core2_load <= 100)
   	  {
   		return R.drawable.notif_cpu_0_5;
   	  }
   	  
   	  if(core1_load <= 20 && core2_load <= 100)
   	  {
   		return R.drawable.notif_cpu_1_5;
   	  }
   	  
   	  if(core1_load <= 40 && core2_load <= 100)
   	  {
   		return R.drawable.notif_cpu_2_5;
   	  }
   	  
   	  if(core1_load <= 60 && core2_load <= 100)
   	  {
   		return R.drawable.notif_cpu_3_5;
   	  }
   	  
   	  if(core1_load <= 80 && core2_load <= 100)
   	  {
   		return R.drawable.notif_cpu_4_5;
   	  }
   	  
   	  if(core1_load <= 100 && core2_load <= 100)
   	  {
   		return R.drawable.notif_cpu_5_5;
   	  }
   	  return 0;
	 }
	 
	 
	 public int core_load_single_core(int load)
	 {
		 if(load <= 1 && load >= 0)
		 {
			 return R.drawable.notif_cpu_0;
		 }
		 
		 if(load <= 20 && load > 1)
		 {
			 return R.drawable.notif_cpu_1;
		 }
		 
		 if(load <= 40 && load > 20)
		 {
			 return R.drawable.notif_cpu_2;
		 }
		 
		 if(load <= 60 && load > 40)
		 {
			 return R.drawable.notif_cpu_3;
		 }
		 
		 if(load <= 80 && load > 60)
		 {
			 return R.drawable.notif_cpu_4;
		 }
		 
		 if(load <= 100 && load > 80)
		 {
			 return R.drawable.notif_cpu_5;
		 }
		 
		 return 0;
	 }
		


	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
	}
}