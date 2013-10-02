package application;

import MaximumWidget.com.R;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import maximum.widget.com.memory;

import java.util.Timer;
import java.util.TimerTask;


public class RamUse extends Fragment {
	
	
	private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    SystemUtils cpu = new SystemUtils();
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
    Timer t;
    
    
    private static final String KEY_CONTENT = "TestFragment:Content";

    public static RamUse newInstance(String content) {
    	RamUse fragment = new RamUse();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        } 
        
    }
    
    

    @Override
  	public View onCreateView(LayoutInflater inflater, ViewGroup container,
  			Bundle savedInstanceState) {
  		
    	  View myFragmentView = inflater.inflate(R.layout.activity_ram_use, container, false);
        
        
        
        mProgress = (ProgressBar) myFragmentView.findViewById(R.id.progressBarrampercent);
        use = (TextView)myFragmentView.findViewById(R.id.textViewrampercent);
        ramUsingMo = (TextView)myFragmentView.findViewById(R.id.textViewRamUsingSize);
        max = (TextView)myFragmentView.findViewById(R.id.textViewramsize);
        current = (TextView)myFragmentView.findViewById(R.id.textViewRamMo);
         
        
        try {
        	
        	TotalMemory = cpu.getMemoryTotal();
			max.setText( TotalMemory + " Mo");
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        alive = true;
        
        t = new Timer(true);
        t.scheduleAtFixedRate(new Action(getActivity()),0,3000);
        
        
        
        return myFragmentView;
}
    
    class Action extends TimerTask 
    {
    	Context context;
    	
    	Action(Context context)
    	{
    		this.context = context;
    	}
    	
	    @Override
	    public void run() 
	    {
	    	AsyncRam test = new AsyncRam(context);
	  	  	test.execute();
	  	  	
	  	  	
	    }
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



public class AsyncRam extends AsyncTask<Void, Integer, Void>
{
	Context context;

	    public AsyncRam(Context context) 
	    {
	    	this.context = context;
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
		
		while (alive) {

        	try {
        		
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
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
	}
}

}
