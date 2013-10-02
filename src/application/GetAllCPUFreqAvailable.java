package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GetAllCPUFreqAvailable {

	ArrayList<Integer> CPUfreq = new ArrayList<Integer>();
	ArrayList<String> governorList = new ArrayList<String>();
	
	public static final String FREQ_UNIT = "MHz";

	
	
		
	

	public GetAllCPUFreqAvailable() 
	{
		readAllCPUFreq();
		readAllGovernor();
	}
	
	
	
	public void readAllCPUFreq()
	{
		File file = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_frequencies");
		if(file.exists())
		{
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
							workOnFileAllCPUfreq(line,compt);
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
	}
	
	
	public void readAllGovernor()
	{
		File file = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_available_governors");
		if(file.exists())
		{
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
							workOnFileAllGovernor(line,compt);	
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
	}
	
	
	public void workOnFileAllCPUfreq(String line, int compt)
	{
		String[] frequence = null;
		int[] stats = null;
		
		if(line != null) {
			String[] processRow = line.split("\\s+");
			if(processRow != null) 
			{
				for (int i = 0; i < processRow.length; i++) 
				{
					int z = Integer.parseInt(processRow[i]);
					int y = z/1000;
					this.CPUfreq.add(y);
				}
					
			}
		}
	}
	
	
	public void workOnFileAllGovernor(String line, int compt)
	{
		String[] frequence = null;
		int[] stats = null;
		
		if(line != null) {
			String[] processRow = line.split("\\s+");
			if(processRow != null) 
			{
				for (int i = 0; i < processRow.length; i++) 
				{
					this.governorList.add(processRow[i]);
				}
					
			}
		}
	}
	

	public ArrayList<Integer> getAllFreqList() {
		return CPUfreq;
	}
	
	public ArrayList<String> getAllGovernorList() {
		return governorList;
	}
}
