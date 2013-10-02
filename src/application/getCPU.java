package application;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;


class SystemUtils{
  private static final String BOGOMIPS_PATTERN = "BogoMIPS[\\s]*:[\\s]*(\\d+\\.\\d+)[\\s]*\n";
  private static final String MEMTOTAL_PATTERN = "MemTotal[\\s]*:[\\s]*(\\d+)[\\s]*kB\n";
  private static final String MEMFREE_PATTERN = "MemFree[\\s]*:[\\s]*(\\d+)[\\s]*kB\n";
  public final static Long when = System.currentTimeMillis() - 20000;
  String CpuFreq1;
  String CpuTemp1;
  String CurrentCores;
  float core1 = 0;
  float core2 = 0;
  float core3 = 0;
  float core4 = 0;
  float core5 = 0;
  float core6 = 0;
  float core7 = 0;
  float core8 = 0;
  
  
  
  public static String fetch_process_info() {
	    Log.i("fetch_process_info", "start....");
	    String result = null;
	    CMDExecute cmdexe = new CMDExecute();
	    try {
	      String[] args = { "/system/bin/top", "-n", "1" };
	      result = cmdexe.run(args, "/system/bin/");
	    } catch (IOException ex) {
	      Log.i("fetch_process_info", "ex=" + ex.toString());
	    }
	    return result;
	  }
  

  
  private static StringBuffer buffer;

  public static String fetch_tel_status(Context cx) {
    String result = null;
    TelephonyManager tm = (TelephonyManager) cx
        .getSystemService(Context.TELEPHONY_SERVICE);//
    String str = "";
    str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
    str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion()
        + "\n";
    str += "Line1Number = " + tm.getLine1Number() + "\n";
    str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
    str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
    str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
    str += "NetworkType = " + tm.getNetworkType() + "\n";
    str += "PhoneType = " + tm.getPhoneType() + "\n";
    str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
    str += "SimOperator = " + tm.getSimOperator() + "\n";
    str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
    str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
    str += "SimState = " + tm.getSimState() + "\n";
    str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
    str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";

    int mcc = cx.getResources().getConfiguration().mcc;
    int mnc = cx.getResources().getConfiguration().mnc;
    str += "IMSI MCC (Mobile Country Code):" + String.valueOf(mcc) + "\n";
    str += "IMSI MNC (Mobile Network Code):" + String.valueOf(mnc) + "\n";
    result = str;
    return result;
  }
  

  public static String getRunningTasksInfo(Context context) {
    StringBuffer sInfo = new StringBuffer();
    final ActivityManager activityManager = (ActivityManager) context
        .getSystemService(Context.ACTIVITY_SERVICE);
    List<RunningTaskInfo> tasks = activityManager.getRunningTasks(100);
    Iterator<RunningTaskInfo> l = tasks.iterator();
    while (l.hasNext()) {
      RunningTaskInfo ti = (RunningTaskInfo) l.next();
      sInfo.append("id: ").append(ti.id);
      sInfo.append("\nbaseActivity: ").append(
          ti.baseActivity.flattenToString());
      sInfo.append("\nnumActivities: ").append(ti.numActivities);
      sInfo.append("\nnumRunning: ").append(ti.numRunning);
      sInfo.append("\ndescription: ").append(ti.description);
      sInfo.append("\n\n");
    }
    return sInfo.toString();
  }

  
 
  
  
  public String getCpuUsageByPid ()
  {
	  BufferedReader in = null;

      try {
			Process process = null;
			process = Runtime.getRuntime().exec("top -n 1 -m 10 -s cpu");

          in = new BufferedReader(new InputStreamReader(process.getInputStream()));

          String line ="";
          String content = "";
          int compt = 0;

          while((line = in.readLine()) != null) {

        		content += line + "\n";
            }
          System.out.println(content);

          return content;
          
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
      return "caca";
  }
  
  
  
  public static float getCPUBogoMips() throws Exception {
	    final MatchResult matchResult = SystemUtils.matchSystemFile("/proc/cpuinfo", BOGOMIPS_PATTERN, 1000);

	    try {
	      if(matchResult.groupCount() > 0) {
	        return Float.parseFloat(matchResult.group(1));
	      } else {
	        throw new Exception();
	      }
	    } catch (final NumberFormatException e) {
	      throw new Exception(e);
	    }
	  }
  

  public float readUsage() {
	    try {
	    	
	        RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	        String load = reader.readLine();
	        

	        String[] toks = load.split(" ");

	        long idle1 = Long.parseLong(toks[5]);
	        long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	              + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        try {
	            Thread.sleep(360);
	        } catch (Exception e) {}

	        reader.seek(0);
	        load = reader.readLine();
	        reader.close();

	        toks = load.split(" ");

	        long idle2 = Long.parseLong(toks[5]);
	        long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	            + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }

	    return 0;
	} 
  
  
  public float readUsageCPU0() {
	    try {
	    	
	        RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	        reader.seek(0);
	        String load;
	        String[] toks;
	        
	        //cpu1
        	long total1 = 0;
        	long user1 = 0;
        	long systeme1 = 0;
        	long total2 = 0;
        	long user2 = 0;
        	long systeme2 = 0;
        	
        	//cpu2
        	long total3 = 0;
        	long user3 = 0;
        	long systeme3 = 0;
        	long total4 = 0;
        	long user4 = 0;
        	long systeme4 = 0;
        	
        	//cpu3
        	long total5 = 0;
        	long user5 = 0;
        	long systeme5 = 0;
        	long total6 = 0;
        	long user6 = 0;
        	long systeme6= 0;
        	
        	//cpu4
        	long total7 = 0;
        	long user7 = 0;
        	long systeme7 = 0;
        	long total8 = 0;
        	long user8 = 0;
        	long systeme8 = 0;
        	
        	//cpu5
        	long total9 = 0;
        	long user9 = 0;
        	long systeme9 = 0;
        	long total10 = 0;
        	long user10 = 0;
        	long systeme10 = 0;
        	
        	//cpu6
        	long total11 = 0;
        	long user11 = 0;
        	long systeme11 = 0;
        	long total12 = 0;
        	long user12 = 0;
        	long systeme12 = 0;
        	
        	//cpu7
        	long total13 = 0;
        	long user13 = 0;
        	long systeme13 = 0;
        	long total14 = 0;
        	long user14 = 0;
        	long systeme14 = 0;
        	
        	//cpu8
        	long total15 = 0;
        	long user15 = 0;
        	long systeme15 = 0;
        	long total16 = 0;
        	long user16 = 0;
        	long systeme16 = 0;

	        while((load = reader.readLine()) != null)
	        {
	        	if(load.startsWith("cpu0"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total1 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user1 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme1 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	if(load.startsWith("cpu1"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total3 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user3 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme3 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	if(load.startsWith("cpu2"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total5 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user5 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme5 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	
	        	if(load.startsWith("cpu3"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total7 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user7 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme7 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	
	        	if(load.startsWith("cpu4"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total9 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user9 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme9 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	if(load.startsWith("cpu5"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total11 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user11 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme11 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	
	        	if(load.startsWith("cpu6"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total13 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user13 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme13 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
	        	
	        	
	        	if(load.startsWith("cpu7"))
	        	{
			        toks = load.trim().split("[ ]+");
		
			        total15 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
			        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
			        
			        user15 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
			        
			        systeme15 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        
	        	}
			        
	        }
	        
	        //--------WAIT---------------------------------------------------------------------------------------------
			        try {
			            Thread.sleep(360);
			        } catch (Exception e) {}
			        
			        
			        
		
			        reader.seek(0);

			        while((load = reader.readLine()) != null)
			        {
			        	if(load.startsWith("cpu0"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total2 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user2 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme2 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
			        	}
			        	
			        	if(load.startsWith("cpu1"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total4 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user4 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme4 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        	
			        	if(load.startsWith("cpu2"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total6 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user6 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme6 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        	
			        	
			        	if(load.startsWith("cpu3"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total8 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user8 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme8 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        	
			        	if(load.startsWith("cpu4"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total10 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user10 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme10 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        	
			        	if(load.startsWith("cpu5"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total12 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user12 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme12 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        	
			        	
			        	if(load.startsWith("cpu6"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total14 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user14 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme14 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        	
			        	
			        	if(load.startsWith("cpu7"))
			        	{
					        toks = load.trim().split("[ ]+");
				
					        total16 = Long.parseLong(toks[1])+Long.parseLong(toks[2])+Long.parseLong(toks[3])+
					        		+Long.parseLong(toks[4])+Long.parseLong(toks[5])+Long.parseLong(toks[6])+Long.parseLong(toks[7]);
					        
					        user16 = Long.parseLong(toks[1]) + Long.parseLong(toks[2]);
					        
					        systeme16 = Long.parseLong(toks[3]) + Long.parseLong(toks[6]) +Long.parseLong(toks[7]);
					        
			        	}
			        }
			        reader.close();
			        
			        //calculate core1 usage
			        long x = 100L * ((user2-user1) + (systeme2-systeme1));
			        long y = (total2-total1);
			        long z = 0;
			        if(y > 0)
			        {
			        	z = (x/y);
			        }
			        else
			        {
			        	z = 0;
			        }
			        this.core1 = (float)z;
			        
			      //calculate core2 usage
			        long a = 100L * ((user4-user3) + (systeme4-systeme3));
			        long b = (total4-total3);
			        long c = 0;
			        
			        if(b > 0)
			        {
			        	c = (a/b);
			        }
			        else
			        {
			        	c = 0;
			        }
			        this.core2 = (float)c;
			        
			        //calculate core3 usage
			        long d = 100L * ((user6-user5) + (systeme6-systeme5));
			        long e = (total6-total5);
			        long f = 0;
			        if(e > 0)
			        {
			        	f = (d/e);
			        }
			        else
			        {
			        	f = 0;
			        }
			        this.core3 = (float)f;
			        
			      //calculate core4 usage
			        long g = 100L * ((user8-user7) + (systeme8-systeme7));
			        long h = (total8-total7);
			        long i = 0;
			        if(h > 0)
			        {
			        	i = (g/h);
			        }
			        else
			        {
			        	i = 0;
			        }
			        this.core4 = (float)i;
			        
			        
			      //calculate core5 usage
			        long j = 100L * ((user10-user9) + (systeme10-systeme9));
			        long k = (total10-total9);
			        long l = 0;
			        if(k > 0)
			        {
			        	k = (j/k);
			        }
			        else
			        {
			        	k = 0;
			        }
			        this.core5 = (float)k;
			        
			        
			      //calculate core6 usage
			        long o = 100L * ((user12-user11) + (systeme12-systeme11));
			        long p = (total12-total11);
			        long q = 0;
			        if(p > 0)
			        {
			        	q = (o/p);
			        }
			        else
			        {
			        	q = 0;
			        }
			        this.core6 = (float)q;
			        
			        
			      //calculate core7 usage
			        long r = 100L * ((user14-user13) + (systeme14-systeme13));
			        long s = (total14-total13);
			        long t = 0;
			        if(s > 0)
			        {
			        	t = (r/s);
			        }
			        else
			        {
			        	t = 0;
			        }
			        this.core7 = (float)t;
			        
			        
			        
			      //calculate core8 usage
			        long m = 100L * ((user16-user15) + (systeme16-systeme15));
			        long n = (total16-total15);
			        long w = 0;
			        if(n > 0)
			        {
			        	w = (m/n);
			        }
			        else
			        {
			        	w = 0;
			        }
			        this.core8 = (float)w;
			        
			        
		
			        return this.core1;


	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    
	    return 0;
	} 
  
  
  
  public float readUsageCPU1() {
 
	    return this.core2;
	}
  
  
  
  public float readUsageCPU2() {

	    return this.core3;
	}
  
  
  public float readUsageCPU3() {
	    return this.core4;
	}
  
  
  
  public float readUsageCPU4() {
	    return this.core5;
	}
  
  
  
  public float readUsageCPU5() {
	    return this.core6;
	}
  
  
  
  public float readUsageCPU6() {
	    return this.core7;
	}
  
  
  
  public float readUsageCPU7() {
	    return this.core8;
	}

  
  
  
  public String getGovernor2()
  {
	  RandomAccessFile reader;
	try {
		reader = new RandomAccessFile("/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor", "r");
		
		
		
		
	      try {
	    	  reader.seek(0);
		      String load;
			load = reader.readLine();
			return load;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "error";
      
  }
  
  public int getNumOfCpus() {

	    class CPUFilter implements FileFilter {
	        public boolean accept(File pathname) {
	            // Check if filename is "cpu0", "cpu1,...
	            if (Pattern.matches("cpu[0-9]", pathname.getName())) {
	                return true;
	            }
	            return false;
	        }
	    }

	    try {
	        // Get directory containing CPU info
	        File dir = new File("/sys/devices/system/cpu/");
	        File[] files = dir.listFiles(new CPUFilter());
	        // Return the number of cores 
	        return files.length;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 1;
	    }
	}



    public void setMaxFreqCPU0(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void setMaxFreqCPU1(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu1/cpufreq/scaling_max_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu1/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu1/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void setMaxFreqCPU2(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu2/cpufreq/scaling_max_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu2/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu2/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void setMaxFreqCPU3(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu3/cpufreq/scaling_max_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu3/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu3/cpufreq/scaling_max_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    public void setMinFreqCPU0(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void setMinFreqCPU1(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu1/cpufreq/scaling_min_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu1/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu1/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void setMinFreqCPU2(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu2/cpufreq/scaling_min_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu2/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu2/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setMinFreqCPU3(int i)
    {
        File f = new File("/sys/devices/system/cpu/cpu3/cpufreq/scaling_min_freq");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu3/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu3/cpufreq/scaling_min_freq\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void setGovernor(String i)
    {
        File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");
        if(f.exists())
        {
            Process localProcess;
            try {
                localProcess = Runtime.getRuntime().exec("su");
                DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
                localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("chmod 777 /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("echo '" + i + "' > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor\n");
                localDataOutputStream.flush();
                localDataOutputStream.writeBytes("exit \n");
                localDataOutputStream.flush();

                try {
                    localProcess.waitFor();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    public  int getMaxScalingCPUFreq() throws Exception {

        File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
        if(f.exists())
        {

            int temp = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
            if (temp > 0)
            {
                return temp/1000;
            }

        }
        return 0;
    }


    public  int getMinScalingCPUFreq() throws Exception {

        File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
        if(f.exists())
        {

            int temp = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
            if (temp > 0)
            {
                return temp/1000;
            }

        }
        return 0;
    }


  
  public  int getCPUMaxOfCoreAllowed() throws Exception {
	   
	   File f = new File("/sys/kernel/debug/tegra_hotplug/max_cpus");
	   if(f.exists()) 
	   {

		   int temp = SystemUtils.readSystemFileAsInt("/sys/kernel/debug/tegra_hotplug/max_cpus");
		   if (temp > 0)
		   {
			   return temp;
		   }

	   }
	   return 0;
	  }
  
  public void setMaxCoreAllowed(int core)
  {

        Process localProcess;
		try {
			localProcess = Runtime.getRuntime().exec("su");
			DataOutputStream localDataOutputStream = new DataOutputStream(localProcess.getOutputStream());
	        localDataOutputStream.writeBytes("mount -o rw,remount -t yaffs2  /dev/block/mtdblock03 /sys\n");
	        localDataOutputStream.flush();
	        localDataOutputStream.writeBytes("chmod 777 /sys/kernel/debug/tegra_hotplug/max_cpus\n");
	        localDataOutputStream.flush();
	        localDataOutputStream.writeBytes("echo '" + core + "' > /sys/kernel/debug/tegra_hotplug/max_cpus\n");
	        localDataOutputStream.flush();
	        localDataOutputStream.writeBytes("exit \n");
	        localDataOutputStream.flush();
	        
	        try {
				localProcess.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  }
  
  
  
  	public int[] getCpuUsageStatistic() {

	    String tempString = executeTop();

	    tempString = tempString.replaceAll(",", "");
	    tempString = tempString.replaceAll("User", "");
	    tempString = tempString.replaceAll("System", "");
	    tempString = tempString.replaceAll("IOW", "");
	    tempString = tempString.replaceAll("IRQ", "");
	    tempString = tempString.replaceAll("%", "");
	    for (int i = 0; i < 10; i++) {
	        tempString = tempString.replaceAll("  ", " ");
	    }
	    tempString = tempString.trim();
	    String[] myString = tempString.split(" ");
	    int[] cpuUsageAsInt = new int[myString.length];
	    for (int i = 0; i < myString.length; i++) {
	        myString[i] = myString[i].trim();
	        cpuUsageAsInt[i] = Integer.parseInt(myString[i]);
	    }
	    return cpuUsageAsInt;
	}

  	public String executeTop() {
	    java.lang.Process p = null;
	    BufferedReader in = null;
	    String returnString = null;
	    try {
	        p = Runtime.getRuntime().exec("top -n 1");
	        in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        while (returnString == null || returnString.contentEquals("")) {
	            returnString = in.readLine();
	        }
	    } catch (IOException e) {
	        Log.e("executeTop", "error in getting first line of top");
	        e.printStackTrace();
	    } finally {
	        try {
	            in.close();
	            p.destroy();
	        } catch (IOException e) {
	            Log.e("executeTop",
	                    "error in closing and destroying top process");
	            e.printStackTrace();
	        }
	    }
	    return returnString;
	}
  
  
	public int getNumProcesses(Context context)
  {
      ActivityManager servMng = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
      List<ActivityManager.RunningAppProcessInfo> list = servMng.getRunningAppProcesses();
      return list.size();
  }
  
  
 

  /**
   * @return in kiloHertz.
   * @throws SystemUtilsException
   */
   
  
  public  int getCPUFrequencyMax() throws Exception {
    return SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
  }

   
   public  int getCPUTemp() throws Exception {
	   
	   File f = new File("/sys/devices/virtual/thermal/thermal_zone0/temp");
	   if(f.exists()) 
	   {

		   int temp = SystemUtils.readSystemFileAsInt("/sys/devices/virtual/thermal/thermal_zone0/temp");
		   if (temp > 3)
		   {
			   return temp;
		   }

	   }
	   return 0;
	  }
   
   
   public  int getCPUTempHTC() throws Exception {
	   
   
	   File f = new File("/sys/htc/cpu_temp");
	   if(f.exists()) 
	   {
	   
		   int temp = SystemUtils.readSystemFileAsInt("/sys/htc/cpu_temp");
		   if (temp > 3)
		   {
			   return temp;
		   } 

	   }
	   return 0;
	  }
   
   
public  int getCPUTempTegra() throws Exception {
	   
	   
	   File f = new File("/sys/kernel/debug/tegra_thermal/temp_tj");
	   if(f.exists()) 
	   {
	   
		   int temp = SystemUtils.readSystemFileAsInt("/sys/kernel/debug/tegra_thermal/temp_tj");
		   if (temp > 3)
		   {
			   return temp;
		   } 

	   }
	   return 0;
	  }



public String getCPUTemperature() throws Exception
{
	 File fileTegra3 = new File("/sys/kernel/debug/tegra_thermal/temp_tj");
	 File fileHTC = new File("/sys/htc/cpu_temp");
	 File fileTemp1 = new File("/sys/devices/virtual/thermal/thermal_zone0/temp");
	 File fileTemp2 = new File("/sys/devices/platform/s5p-tmu/temperature");
	 
	   if(fileTegra3.exists()) 
	   {
	   
		   int temp = SystemUtils.readSystemFileAsInt("/sys/kernel/debug/tegra_thermal/temp_tj");
		   if (temp > 0)
		   {
			   return temp + "°";
		   } 

	   }
	   
	   
	   if(fileHTC.exists() && !fileTegra3.exists()) 
	   {
	   
		   int temp = SystemUtils.readSystemFileAsInt("/sys/htc/cpu_temp");
		   if (temp > 3)
		   {
			   return temp + "°";
		   } 

	   }
	   
	   
	   if(fileTemp1.exists() && !fileTegra3.exists()) 
	   {

		   int temp = SystemUtils.readSystemFileAsInt("/sys/devices/virtual/thermal/thermal_zone0/temp");
		   if (temp > 3)
		   {
			   return temp + "°";
		   }

	   }
	   
	 
	   if(fileTemp2.exists()) 
	   {
	   
		   int temp = SystemUtils.readSystemFileAsInt("/sys/devices/platform/s5p-tmu/temperature");
		   if (temp > 3)
		   {
			   return temp + "°";
		   } 

	   }
	
	return "No temp";
}
	  
	  
	  public  int getCPUTempGalaxyS3() throws Exception {
		   
		   
		   File f = new File("/sys/devices/platform/s5p-tmu/temperature");
		   if(f.exists()) 
		   {
		   
			   int temp = SystemUtils.readSystemFileAsInt("/sys/devices/platform/s5p-tmu/temperature");
			   if (temp > 3)
			   {
				   return temp;
			   } 

		   }
		   return 0;
		  }


  
  public  int getActiveCore() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu_on");
	   if(f.exists()) 
	   {
	   
		   int ActiveCore = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu_on");
		   if (ActiveCore > 0)
		   {
			   return ActiveCore;
		   } 

	   }
	   return 0;
	  
	  }
  
  public  int getMaxActiveCore() throws Exception {
	    return SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/kernel_max");
	  }
  
  public  int getCPUFrequencyMin() throws Exception {
	    return SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
	  }
  
  public  int getCPU0FrequencyCurrentNormaleWay() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return 0;
	  }
  
  
public  int getCPU0FrequencyCurrentCustomWay() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return 0;
	  }
  
  
  
  
  
  
  public  int getCPU1FrequencyCurrent() throws Exception {
	  
	  
	  File f = new File("/sys/devices/system/cpu/cpu1/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu1/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  public  int getCPU2FrequencyCurrent() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu2/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu2/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  public  int getCPU3FrequencyCurrent() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu3/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu3/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  
  
  public  int getCPU4FrequencyCurrent() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu4/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  
  
  public  int getCPU5FrequencyCurrent() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu5/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu5/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  
  
  public  int getCPU6FrequencyCurrent() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu6/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu6/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  
  
  public  int getCPU7FrequencyCurrent() throws Exception {
	  
	  File f = new File("/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq");
	   if(f.exists()) 
	   {
	   
		   int Active = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu7/cpufreq/scaling_cur_freq");
		   if (Active > 0)
		   {
			   return Active;
		   } 

	   }
	   return getCPU0FrequencyCurrentCustomWay();
	  }
  
  
  
  public  int getMemoryTotal() throws Exception {
	  final MatchResult matchResult = SystemUtils.matchSystemFile("/proc/meminfo", MEMTOTAL_PATTERN, 1000);

	  try {
	    if(matchResult.groupCount() > 0) {
	      return Integer.parseInt(matchResult.group(1))/1000;
	    } else {
	      throw new Exception();
	    }
	  } catch (final NumberFormatException e) {
	    throw new Exception(e);
	  }
	}

	  
  //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  



  
  
  
  private static int readSystemFileAsInt(final String pSystemFile) throws Exception {
    InputStream in = null;
    try {
      final Process process = new ProcessBuilder(new String[] { "/system/bin/cat", pSystemFile }).start();

      in = process.getInputStream();
      final String content = readFully(in);
      return Integer.parseInt(content);
    } catch (final Exception e) {
      throw new Exception(e);
    } 
  }
  public static final String readFully(final InputStream pInputStream) throws IOException {
    final StringBuilder sb = new StringBuilder();
    final Scanner sc = new Scanner(pInputStream);
    while(sc.hasNextLine()) {
      sb.append(sc.nextLine());
    }
    return sb.toString();
  }
  private static MatchResult matchSystemFile(final String pSystemFile, final String pPattern, final int pHorizon) throws Exception {
    InputStream in = null;
    try {
      final Process process = new ProcessBuilder(new String[] { "/system/bin/cat", pSystemFile }).start();

      in = process.getInputStream();
      final Scanner scanner = new Scanner(in);

      final boolean matchFound = scanner.findWithinHorizon(pPattern, pHorizon) != null;
      if(matchFound) {
        return scanner.match();
      } else {
        throw new Exception();
      }
    } catch (final IOException e) {
      throw new Exception(e);
    } 
      
  }
}
