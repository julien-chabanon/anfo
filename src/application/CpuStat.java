package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CpuStat {
	ArrayList<String> cpuFreq = new ArrayList<String>();
	ArrayList<String> cpuFreqFormat = new ArrayList<String>();
	
	ArrayList<String> usePercentFormat = new ArrayList<String>();
	ArrayList<Integer> usePercent = new ArrayList<Integer>();
	
	public static final String FREQ_UNIT = "MHz";
	
	public boolean isSupported;
	
	int inc = 0;
	
	
		
	

	public CpuStat() 
	{
		readStat();
	}
	
	
	
	public void readStat()
	{
		File file = new File("/sys/devices/system/cpu/cpu0/cpufreq/stats/time_in_state");
		if(file.exists())
		{
			isSupported = true;
			
			int compt = 0;
			RandomAccessFile reader = null;
			String line = "";
			try {
				reader = new RandomAccessFile(file, "r");
				
				
				
				
				try {
					while ((line = reader.readLine()) != null)
					{
						if (line.length() != 0) 
						{
							compt++;
							workOnFile(line,compt);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else
		{
			isSupported = false;
		}
	}
	
	
	public void workOnFile(String line, int compt)
	{
		String[] frequence = null;
		int[] stats = null;
		
		if(line != null) {
			String[] processRow = line.split("\\s+");
			if(processRow != null) 
			{
					this.cpuFreq.add(processRow[0]);
					this.usePercent.add(Integer.parseInt(processRow[1]));
			}
		}
	}
	


	
	
	
	
	public boolean isSupport()
	{
		return this.isSupported;
	}
	
	
	public ArrayList<String> getFreq() {
		return cpuFreq;
	}
	
	public ArrayList<String> getFreqFormat() {
		formatCpuFreq();
		return cpuFreqFormat;
	}

	public ArrayList<String> getFreqStatFormat() {
		calculPourcentage();
		return usePercentFormat;
	}

	public ArrayList<Integer> getFreqStat() {
		return usePercent;
	}

	
	public String getAverageFreq()
	{
		int x = 0;
		int selector = 0;
		int max = 0;
		String AverageFreq = "";
		
		for (int i = 0; i < getFreqStat().size(); i++) 
		{
			x = getFreqStat().get(i);
			if(x>max)
			{
				max = x;
				selector = i;
			}
		}
		
		for (int i = 0; i < getFreqFormat().size(); i++) 
		{
			AverageFreq = getFreqFormat().get(selector);
		}
		
		
		return AverageFreq;
	}


	public void calculPourcentage() 
	{
		int total = 0;
		ArrayList<String> aight = new ArrayList<String>();
		
		for (Integer z : getFreqStat()) 
		{
			total += z;
		}
		for (Integer z : getFreqStat()) 
		{
			aight.add((z*100)/total + "%");
		}
		
		this.usePercentFormat = aight;
	}

	public void formatCpuFreq() {
		
		ArrayList<String> aight = new ArrayList<String>();
		int freq = 0;
		int freqMhz = 0;
		
		for (String z : getFreq()) 
		{
			freq = Integer.parseInt(z);
			freqMhz = freq/1000;
			aight.add(freqMhz + FREQ_UNIT);
		}
		
		this.cpuFreqFormat = aight;
	}

}
