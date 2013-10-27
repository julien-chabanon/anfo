package maximum.widget.com;

import MaximumWidget.com.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import utils.getCPU;


public class RamUse extends Activity {
	
	
	private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    getCPU cpu = new getCPU();
    TextView use;
    TextView max;
    TextView current;
    TextView ramUsingMo;
    int cpu0_current;
    int MemoryAvailable;
    int TotalMemory;
    int MemoryUsing;
    Runnable runnable;
    Thread exec;
    Boolean alive;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ram_use);
        
        
        
        mProgress = (ProgressBar) findViewById(R.id.progressBarrampercent);
        use = (TextView)findViewById(R.id.textViewrampercent);
        ramUsingMo = (TextView)findViewById(R.id.textViewRamUsingSize);
        max = (TextView)findViewById(R.id.textViewramsize);
        current = (TextView)findViewById(R.id.textViewRamMo);
         
        
        try {
        	
        	TotalMemory = cpu.getMemoryTotal();
			max.setText( TotalMemory + " Mo");
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        alive = true;

        // Start lengthy operation in a background thread
           runnable = new Runnable(){
            public void run() {
                while (alive) {

                	try {
                		Context context = getBaseContext();
                		MemoryAvailable = memory.getMemoryInfo(context);
                		MemoryUsing = TotalMemory-MemoryAvailable;  
                		mProgressStatus = 100-((MemoryAvailable*100)/TotalMemory);
               		   	
               		   	
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	

                    // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                        	
                        	current.setText(MemoryAvailable + " Mo");
                   		   	ramUsingMo.setText(MemoryUsing + "Mo");
                            mProgress.setProgress(mProgressStatus);
                            use.setText( mProgressStatus + "%");
                            
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
