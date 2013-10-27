package maximum.widget.com;

import MaximumWidget.com.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import utils.getCPU;

public class CpuInfo extends Activity {
	
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu_info);
        context = getBaseContext();
        
        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
        use = (TextView)findViewById(R.id.textViewUseCpuInfo);
        max = (TextView)findViewById(R.id.textViewMax);
        min = (TextView)findViewById(R.id.textViewMin);
        current = (TextView)findViewById(R.id.textViewCurrent);
        processesPercent = (TextView)findViewById(R.id.textViewPercentProcesses);
        processesNumbers = (TextView)findViewById(R.id.textViewProcessesNumbers);
        bogoText = (TextView)findViewById(R.id.textViewBogo);
        gouvernorText = (TextView)findViewById(R.id.textViewGovernor);
        
        try {
        	
			cpu_max = cpu.getCPUFrequencyMax()/1000;
			cpu_min = cpu.getCPUFrequencyMin()/1000;
			max.setText( cpu_max + " MHz");
	        min.setText( cpu_min + " MHz");
	        
	        
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
                            gouvernorText.setText(cpu.getGovernor2());
                        }
                    });
                }
            }
        };
        

        exec = new Thread(runnable);
        exec.start();
}
    

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        
        
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        alive = true;
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        finish();
    }

    
}
