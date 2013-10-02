package all.in.one;

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
/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:50:31 - 14.07.2010
 */

class SystemUtils{
  private static final String BOGOMIPS_PATTERN = "BogoMIPS[\\s]*:[\\s]*(\\d+\\.\\d+)[\\s]*\n";
  private static final String MEMTOTAL_PATTERN = "MemTotal[\\s]*:[\\s]*(\\d+)[\\s]*kB\n";
  private static final String MEMFREE_PATTERN = "MemFree[\\s]*:[\\s]*(\\d+)[\\s]*kB\n";
  public final static Long when = System.currentTimeMillis() - 20000;
  
  
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
	            Thread.sleep(500);
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
	        String load = reader.readLine();
	        load.startsWith("cpu0");
	        load.endsWith("\n");

	        String[] toks = load.split(" ");

	        long idle1 = Long.parseLong(toks[5]);
	        long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	              + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        try {
	            Thread.sleep(360);
	        } catch (Exception e) {}

	        reader.seek(0);
	        load = reader.readLine();
	        load.startsWith("cpu0");
	        load.endsWith("\n");
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
  
  
  
  public float readUsageCPU1() {
	    try {
	    	
	        RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	        reader.seek(0);
	        String load = reader.readLine();
	        load.startsWith("cpu1");
	        load.endsWith("\n");

	        String[] toks = load.split(" ");

	        long idle1 = Long.parseLong(toks[5]);
	        long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	              + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        try {
	            Thread.sleep(360);
	        } catch (Exception e) {}

	        reader.seek(0);
	        load = reader.readLine();
	        load.startsWith("cpu1");
	        load.endsWith("\n");
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
  
  
  
  public float readUsageCPU2() {
	    try {
	    	
	        RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	        reader.seek(0);
	        String load = reader.readLine();
	        load.startsWith("cpu2");
	        load.endsWith("\n");

	        String[] toks = load.split(" ");

	        long idle1 = Long.parseLong(toks[5]);
	        long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	              + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        try {
	            Thread.sleep(360);
	        } catch (Exception e) {}

	        reader.seek(0);
	        load = reader.readLine();
	        load.startsWith("cpu2");
	        load.endsWith("\n");
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
  
  
  
  public float readUsageCPU3() {
	    try {
	    	
	        RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	        reader.seek(0);
	        String load = reader.readLine();
	        load.startsWith("cpu3");
	        load.endsWith("\n");

	        String[] toks = load.split(" ");

	        long idle1 = Long.parseLong(toks[5]);
	        long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[4])
	              + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	        try {
	            Thread.sleep(360);
	        } catch (Exception e) {}

	        reader.seek(0);
	        load = reader.readLine();
	        load.startsWith("cpu3");
	        load.endsWith("\n");
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
	  
	  File f = new File("/sys/devices/system/cpu/cpu_on");
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
