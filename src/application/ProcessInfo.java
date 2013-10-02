package application;

import java.text.DecimalFormat;

public class ProcessInfo {
	private String cpuUsage;
	private String memUsage;
	private String packageName;
	private String userName;
	public static final String CPU_UNIT = "%";
	public static final String MEM_UNIT = "K";

	public ProcessInfo(String line) {
		if(line != null) {
			String[] processRow = line.split("\\s+");
			//System.out.println(line + " Count " + processRow.length);
			if(processRow != null && processRow.length == 10) 
			{
				this.cpuUsage = processRow[3];
				this.memUsage = processRow[7];
				this.packageName = processRow[9];
				this.userName = processRow[1];
			}
			
			else if(processRow != null && processRow.length > 10) 
			{
				this.cpuUsage = processRow[3];
				this.memUsage = processRow[7];
				this.packageName = processRow[10];
				this.userName = processRow[1];
			}
		}
	}

	public String getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public String getMemUsage() {
		return memUsage;
	}

	public void setMemUsage(String memUsage) {
		this.memUsage = memUsage;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void combine(ProcessInfo p) {
		if(this.packageName != null && this.packageName.equals(p.packageName)) {
			int cpu = format(cpuUsage, CPU_UNIT) + format(p.cpuUsage, CPU_UNIT);
			cpuUsage = cpu + CPU_UNIT;
			int mem = format(memUsage, MEM_UNIT) + format(p.memUsage, MEM_UNIT);
			memUsage = mem + MEM_UNIT;
		}
	}

	public int format(String usage, String units) {

		if(usage != null) {
			try {
			    int num = Integer.parseInt(usage.replace(units, ""));
			    return (num < 0 ? 0 : num);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}

		return 0;
	}

	public String formatMemUsage(String anus) {
		int value = format(anus, MEM_UNIT);
		double d = value/8192.0;
		DecimalFormat   myFormatter   =   new   DecimalFormat( "####.##");
		return myFormatter.format(d) + "MB";
	}

}
