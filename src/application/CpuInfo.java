package application;

import MaximumWidget.com.R;
import utils.getCPU;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class CpuInfo extends Fragment {
	
	private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    getCPU cpu = new getCPU();
    
    TextView use;
    TextView max;
    TextView min;
    TextView current;
    TextView processesPercent;
    TextView processesNumbers;
    TextView bogoText;
    TextView gouvernorText;
    TextView cpuTempText;
    int cpu0_current;
    Context context;
    String bite;
    int cpu_more;
    Runnable runnable;
    Thread exec;
    Boolean alive;
    int cpu_max;
	int cpu_min;
	float bogo;

	private static final String KEY_CONTENT = "TestFragment:Content";

	private int mPos = -1;
	private int mImgRes;
	
	public CpuInfo() { }
	public CpuInfo(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, CpuInfo.class);
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
    
    

    @Override
  	public View onCreateView(LayoutInflater inflater, ViewGroup container,
  			Bundle savedInstanceState) {
  		
    	  View myFragmentView = inflater.inflate(R.layout.activity_cpu_info_app, container, false);
        context = getActivity();
        
        mProgress = (ProgressBar) myFragmentView.findViewById(R.id.progressBar1);
        use = (TextView)myFragmentView.findViewById(R.id.textViewUseCpuInfo);
        max = (TextView)myFragmentView.findViewById(R.id.textViewMax);
        min = (TextView)myFragmentView.findViewById(R.id.textViewMin);
        current = (TextView)myFragmentView.findViewById(R.id.textViewCurrent);
        processesPercent = (TextView)myFragmentView.findViewById(R.id.textViewPercentProcesses);
        processesNumbers = (TextView)myFragmentView.findViewById(R.id.textViewProcessesNumbers);
        bogoText = (TextView)myFragmentView.findViewById(R.id.textViewBogo);
        gouvernorText = (TextView)myFragmentView.findViewById(R.id.textViewGovernor);
        cpuTempText = (TextView)myFragmentView.findViewById(R.id.textViewCPUTemp);
        
        
        try {
        	
			cpu_max = cpu.getCPUFrequencyMax()/1000;
			cpu_min = cpu.getCPUFrequencyMin()/1000;
			max.setText( cpu_max + " MHz");
	        min.setText( cpu_min + " MHz");
	        gouvernorText.setText(cpu.getGovernor2());
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        alive = true;

     // Start lengthy operation in a background thread
        runnable = new Runnable() {
            public void run() {
                while (alive) {

                	try {
                		
                		bogo = cpu.getCPUBogoMips();
                		
                		

						if(cpu.getCPU0FrequencyCurrentNormaleWay()/1000 > 0)
					   	 {
							cpu0_current = cpu.getCPU0FrequencyCurrentNormaleWay()/1000;
					   	 }
					   	 
					   	 else if (cpu.getCPU0FrequencyCurrentNormaleWay()/1000 == 0 && cpu.getCPU0FrequencyCurrentNormaleWay()/1000 > 0)
					   	 {
					   		cpu0_current = cpu.getCPU0FrequencyCurrentCustomWay()/1000;
					   	 }
					   	 
					   	 else
					   	 {
					   		cpu0_current = cpu_max;
					   	 }
						
						
//----------------------------------TEMPERATURE---------------------------------------------------------------------------------
						 int cpu_temp_AOSP = cpu.getCPUTemp();
					   	 int cpu_temp_HTC = (int) cpu.getCPUTempHTC();
					   	 int cpu_temp_tegra = cpu.getCPUTempTegra();
					   	 int cpu_temp_GalaxyS3 = cpu.getCPUTempGalaxyS3();


					   	  
					   	 if(cpu_temp_tegra > 0)
					   	 {
					   		//cpu_temp_1 = cpu_temp_tegra;
					   		cpuTempText.setText("    "  + cpu_temp_tegra + "°C");
					   	 }

	                    else if(cpu_temp_AOSP > 0)
	                    {
	                        //cpu_temp_3 = cpu_temp_AOSP;
	                    	cpuTempText.setText("    "  + cpu_temp_AOSP + "°C");
	                    }
					   	 
					   	 else if(cpu_temp_GalaxyS3 > 0)
					   	 {
					   		//cpu_temp_1 = cpu_temp_GalaxyS3/1000;
					   		cpuTempText.setText("    "  + cpu_temp_GalaxyS3 + "°C");
					   	 }

					   	
					   	else if(cpu_temp_HTC > 0)
					   	{
					   		//cpu_temp_4 = cpu_temp_HTC;
					   		cpuTempText.setText("    " +  cpu_temp_HTC + "°C");
					   	}
					   	else
					   	{
					   		cpuTempText.setText("No Temp");
					   	}
				
//----------------------------------TEMPERATURE END---------------------------------------------------------------------------------
					   	 
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	float percent2 = cpu.readUsage();
    	   		   	int percent= (int) (percent2*100);
                    mProgressStatus = percent;
                    
        			bite = cpu.executeTop();
                    cpu_more = cpu.getNumProcesses(context);
                    

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                            use.setText( mProgressStatus + "%");
                            current.setText(cpu0_current + "MHz");
                            processesPercent.setText( bite);
                            processesNumbers.setText(cpu_more + " processes running!");
                            bogoText.setText(bogo + " BogoMips");
                        }
                    });
                }
            }
        };
        

        exec = new Thread(runnable);
        exec.start();
        
        
        return myFragmentView;
}
    


    @Override
    public void onPause() {
     // TODO Auto-generated method stub
     super.onPause();

     alive = false;
    }    
        
    @Override
    public void onStop() {
     // TODO Auto-generated method stub
     super.onStop();
     alive = false;

    } 

    
}
