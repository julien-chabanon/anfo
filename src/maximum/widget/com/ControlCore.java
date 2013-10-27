package maximum.widget.com;

import MaximumWidget.com.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import application.Root;
import utils.getCPU;
import java.util.Timer;
import java.util.TimerTask;

public class ControlCore extends Activity {
	
	getCPU cpu = new getCPU();
	Timer t;
	private ProgressBar mProgressCPU0;
	private ProgressBar mProgressCPU1;
	private ProgressBar mProgressCPU2;
	private ProgressBar mProgressCPU3;
	private ProgressBar mProgressCPU4;
	private ProgressBar mProgressCPU5;
	private ProgressBar mProgressCPU6;
	private ProgressBar mProgressCPU7;
	
    private int mProgressStatusCPU0 = 0;
    private int mProgressStatusCPU1 = 0;
    private int mProgressStatusCPU2 = 0;
    private int mProgressStatusCPU3 = 0;
    private int mProgressStatusCPU4 = 0;
    private int mProgressStatusCPU5 = 0;
    private int mProgressStatusCPU6 = 0;
    private int mProgressStatusCPU7 = 0;
    TextView useCPU0;
    TextView useCPU1;
    TextView useCPU2;
    TextView useCPU3;
    TextView useCPU4;
    TextView useCPU5;
    TextView useCPU6;
    TextView useCPU7;
    
    int cpu_max_core;
    
    String MY_PREF = new String("MY_PREFS");
    String cpuCoreBiatch = new String("CPUCoreBiatch");
    String bootBiatch = new String("CoreBootBiatch");
    
    LinearLayout layoutCpu1;
   	LinearLayout layoutCpu2;
   	LinearLayout layoutCpu3;
   	
	TextView textcpu0;
	TextView textcpu1;
	TextView textcpu2;
	TextView textcpu3;
	TextView textcpu4;
	TextView textcpu5;
	TextView textcpu6;
	TextView textcpu7;
	   	
	TextView textcpu0details;
   	TextView textcpu1details;
   	TextView textcpu2details;
   	TextView textcpu3details;
   	TextView textcpu4details;
   	TextView textcpu5details;
   	TextView textcpu6details;
   	TextView textcpu7details;
   	
   	Button core1;
   	Button core2;
   	Button core3;
   	Button core4;
   	
   	CheckBox checkBoxBoot;
   	
   		int cpu_max;
		int cpu_min;
		int cpu0_current;
	   	int cpu1_current;
	   	int cpu2_current;
	   	int cpu3_current;
	   	int cpu4_current;
	   	int cpu5_current;
	   	int cpu6_current;
	   	int cpu7_current;
	   	int cpu_temp;
	   	int cpu_active_core;

	   	
	   	
	   	float percent2;
  	   	int percent;
  	   	
  	   	float percent2CPU1;
  	   	int percentCPU1;
  	   	
  	   	float percent2CPU2;
  	   	int percentCPU2;
  	   	
  	   	float percent2CPU3;
  	   	int percentCPU3;
  	   	
  	   	float percent2CPU4;
	   	int percentCPU4;
	   	
	   	float percent2CPU5;
  	   	int percentCPU5;
  	   	
  	   	float percent2CPU6;
	   	int percentCPU6;
	   	
	   	float percent2CPU7;
  	   	int percentCPU7;
    	
  	   	Boolean alive;
  	   	
  	   	Root root;
  	   	Boolean isRoot;
  	   	
  	   	int coreClick;
  	   	
  	   	Handler handler;
  	   	
  	   	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int testMaxCoreAllow = 0;
        
        try {
			testMaxCoreAllow = cpu.getCPUMaxOfCoreAllowed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(testMaxCoreAllow == 0)
        {
        	if(cpu_max_core == 8)
       	 	{
        		setContentView(R.layout.activity_control_core_octocore);
            	isRoot = false;
            	
            	mProgressCPU4 = (ProgressBar)findViewById(R.id.progressBarCPU4);
                mProgressCPU5 = (ProgressBar) findViewById(R.id.progressBarCPU5);
                mProgressCPU6 = (ProgressBar) findViewById(R.id.progressBarCPU6);
                mProgressCPU7 = (ProgressBar) findViewById(R.id.progressBarCPU7);
                useCPU4 = (TextView)findViewById(R.id.textViewUseCpu4Info);
                useCPU5 = (TextView)findViewById(R.id.textViewUseCpu5Info);
                useCPU6 = (TextView)findViewById(R.id.textViewUseCpu6Info);
                useCPU7 = (TextView)findViewById(R.id.textViewUseCpu7Info);
                
                textcpu4 = (TextView)findViewById(R.id.textViewCPU4);
            	 textcpu5 = (TextView)findViewById(R.id.textViewCPU5);
            	 textcpu6 = (TextView)findViewById(R.id.textViewCPU6);
            	 textcpu7 = (TextView)findViewById(R.id.textViewCPU7);
            	   	
            	 textcpu4details = (TextView)findViewById(R.id.textViewCPU4details);
                textcpu5details  = (TextView)findViewById(R.id.textViewCPU5details);
                textcpu6details  = (TextView)findViewById(R.id.textViewCPU6details);
                textcpu7details  = (TextView)findViewById(R.id.textViewCPU7details);
       	 	}
        	else
        	{
        		setContentView(R.layout.activity_control_core);
            	isRoot = false;
        	}
        }
        
        else
        {
        	setContentView(R.layout.activity_control_core_tegra);
        	root = new Root();
        	isRoot = root.isDeviceRooted();
        }
        
        handler = new Handler();
        
        
        mProgressCPU0 = (ProgressBar) findViewById(R.id.progressBarCPU0);
        mProgressCPU1 = (ProgressBar) findViewById(R.id.progressBarCPU1);
        mProgressCPU2 = (ProgressBar) findViewById(R.id.progressBarCPU2);
        mProgressCPU3 = (ProgressBar) findViewById(R.id.progressBarCPU3);
        useCPU0 = (TextView)findViewById(R.id.textViewUseCpu0Info);
        useCPU1 = (TextView)findViewById(R.id.textViewUseCpu1Info);
        useCPU2 = (TextView)findViewById(R.id.textViewUseCpu2Info);
        useCPU3 = (TextView)findViewById(R.id.textViewUseCpu3Info);
        
         textcpu0 = (TextView)findViewById(R.id.textViewCPU0);
    	 textcpu1 = (TextView)findViewById(R.id.textViewCPU1);
    	 textcpu2 = (TextView)findViewById(R.id.textViewCPU2);
    	 textcpu3 = (TextView)findViewById(R.id.textViewCPU3);
    	   	
    	 textcpu0details = (TextView)findViewById(R.id.textViewCPU0details);
       	 textcpu1details  = (TextView)findViewById(R.id.textViewCPU1details);
       	 textcpu2details  = (TextView)findViewById(R.id.textViewCPU2details);
       	 textcpu3details  = (TextView)findViewById(R.id.textViewCPU3details);
        
       	 layoutCpu1 = (LinearLayout)findViewById(R.id.LayoutCPU1);
       	 layoutCpu2 = (LinearLayout)findViewById(R.id.LayoutCPU2);
       	 layoutCpu3 = (LinearLayout)findViewById(R.id.LayoutCPU3);
       	 
       	 core1 = (Button)findViewById(R.id.buttonSingleCore);
       	 core2 = (Button)findViewById(R.id.buttonDualCore);
       	 core3 = (Button)findViewById(R.id.buttonTripleCore);
       	 core4 = (Button)findViewById(R.id.buttonQuadCore);
       	 
       	checkBoxBoot = (CheckBox)findViewById(R.id.checkBoxSetOnBootCore);
        
        alive = true;
       	
        cpu_max_core = cpu.getNumOfCpus();
        
        t = new Timer(true);
        t.scheduleAtFixedRate(new Action(),0,3000);
        
        context = this;
        
        checkAuthCore();

    }
    


    class Action extends TimerTask 
    {

	    @Override
	    public void run() 
	    {
	    	DoStuff();
	    }
    }
    
    
    
    public int getSavedCore()
    {
    	SharedPreferences myPrefs = this.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        int prefCpuCore = myPrefs.getInt(cpuCoreBiatch, 4);
        return prefCpuCore;
    }
    
    
    public Boolean getPrefBoot()
    {
    	SharedPreferences myPrefs = this.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        Boolean prefBootCore = myPrefs.getBoolean(bootBiatch, false);
        return prefBootCore;
    }
    
    
    public void saveCores(int i)
    {
    	  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		  SharedPreferences.Editor editor = myPrefs.edit();
		  editor.putInt(cpuCoreBiatch, i);
		  editor.commit();
    }
    
    public void saveBootPref(Boolean i)
    {
    	  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		  SharedPreferences.Editor editor = myPrefs.edit();
		  editor.putBoolean(bootBiatch, i);
		  editor.commit();
		  Toast.makeText(context, "Core boot pref saved !", Toast.LENGTH_SHORT).show();
    }
    
    
    
    public void clickCore(int coreClicka)
    {
    	this.coreClick = coreClicka;
    	
    	 Builder dia_not_root = new AlertDialog.Builder(this);
		 Builder dia_root = new AlertDialog.Builder(this);
		
		dia_not_root
        .setTitle("Alert !")
        .setMessage("You are not root, please root your device to disable cpu core")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	
                // do nothing !!!
            }
         });
    	
		
		dia_root.setTitle("Alert !")
        .setMessage("Disable cpu core reduce performance but increase battery life")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	
            	  cpu.setMaxCoreAllowed(coreClick);
            	  saveCores(coreClick);
            	  checkAuthCore();
            }
         }) 
         
         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	
                // do nothing !!!
            }
         });
    	
    	
    	if(isRoot)
  	  {
  		  
      	  dia_root.show();
      	 
  	  }
  	  else
  	  {
  		  dia_not_root.show();
  	  }
    }
    
    
    
    
    
    
    
    
    public void checkAuthCore()
    {
    	Runnable runnable = new Runnable() {
		      @Override
		      public void run() {


		          handler.post(new Runnable() {
		            @Override
		            public void run() {
    	
    	int coreMaxAuth = 0;
    	
    	try {
			coreMaxAuth = cpu.getCPUMaxOfCoreAllowed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(coreMaxAuth > 0)
    	{
    		
    		if(getPrefBoot())
    		{
    			checkBoxBoot.setChecked(true);
    		}
    		else
    		{
    			checkBoxBoot.setChecked(false);
    		}
    		
    		if(coreMaxAuth == 1)
	    	{	    		
	    		core1.setBackgroundResource(R.layout.shape_core_button_on);
	    		core2.setBackgroundResource(R.layout.shape_core_button_off);
	    		core3.setBackgroundResource(R.layout.shape_core_button_off);
	    		core4.setBackgroundResource(R.layout.shape_core_button_off);
	    	}
	    	
	    	else if(coreMaxAuth == 2)
	    	{
	    		core1.setBackgroundResource(R.layout.shape_core_button_off);
	    		core2.setBackgroundResource(R.layout.shape_core_button_on);
	    		core3.setBackgroundResource(R.layout.shape_core_button_off);
	    		core4.setBackgroundResource(R.layout.shape_core_button_off);
	    	}
	    	
	    	else if(coreMaxAuth == 3)
	    	{
	    		core1.setBackgroundResource(R.layout.shape_core_button_off);
	    		core2.setBackgroundResource(R.layout.shape_core_button_off);
	    		core3.setBackgroundResource(R.layout.shape_core_button_on);
	    		core4.setBackgroundResource(R.layout.shape_core_button_off);
	    	}
	    	
	    	else if(coreMaxAuth == 4)
	    	{    		
	    		core1.setBackgroundResource(R.layout.shape_core_button_off);
	    		core2.setBackgroundResource(R.layout.shape_core_button_off);
	    		core3.setBackgroundResource(R.layout.shape_core_button_off);
	    		core4.setBackgroundResource(R.layout.shape_core_button_on);
	    	}
	    	
	    	
	    	core1.setOnClickListener(new Button.OnClickListener(){
	              public void onClick(View arg0) {
	            	  
	            	  
	            	  clickCore(1);
	            	  
	            	  
	              }});
	    	
	    	core2.setOnClickListener(new Button.OnClickListener(){
	            public void onClick(View arg0) {
	          	  

	            		clickCore(2);
	          	  
	            }});
	    	
	    	core3.setOnClickListener(new Button.OnClickListener(){
	            public void onClick(View arg0) {
	          	  
	            	clickCore(3);
	          	  
	            }});
	    	
	    	core4.setOnClickListener(new Button.OnClickListener(){
	            public void onClick(View arg0) {
	          	  
	            	clickCore(4);
	          	  
	            }});
	    	
	    	if(checkBoxBoot.isChecked())
	    	{
	    		checkBoxBoot.setOnClickListener(new Button.OnClickListener(){
		            public void onClick(View arg0) {
		          	  
		            	saveBootPref(false);
		          	  
		            }});
	    	}
	    	else
	    	{
	    		checkBoxBoot.setOnClickListener(new Button.OnClickListener(){
		            public void onClick(View arg0) {
		          	  
		            	saveBootPref(true);
		          	  
		            }});
	    	}
	    	
	    	
    	
    	}
    	
		            }
		          });
		        
		      }
		    };
		    new Thread(runnable).start();
    	
    }

    
    
    public void DoStuff()
    {
    	
    	
    	
    	try {
			cpu_max = cpu.getCPUFrequencyMax()/1000;
			cpu_min = cpu.getCPUFrequencyMin()/1000;
     	   	 cpu_active_core = cpu.getActiveCore();
     	   	 
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
     	   	 
     	   	 if(cpu_active_core == 0)
     	   	 {
     	   		cpu_active_core = cpu_max_core;
     	   	 }
     	   	 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		 

    	
    	Runnable runnable = new Runnable() {
		      @Override
		      public void run() {


		          handler.post(new Runnable() {
		            @Override
		            public void run() {
    					

		      	   		//0 = visible          4 = invisible         8 = gone
		            	
		            	
		            	
		            	//---------------SINGLECORE-----------------------------------------------
			      	   	if(cpu_max_core == 1)
			      	   	{
			      	   		layoutCpu1.setVisibility(4);
			      	   		layoutCpu2.setVisibility(4);
			      	   		layoutCpu3.setVisibility(4);
			      	   		
				      	   	 percent2 = cpu.readUsageCPU0();
				     	   	 percent= (int) (percent2);
				     	   	 mProgressStatusCPU0 = percent;
				     	   	 
				     	   	 
				     	  //--------------CORE 1 ACTIF----------------------------------------
					     	   	if(percent > 0)
					      	   	{
					      	   		textcpu0.setBackgroundResource(R.layout.shape_core_on);
					      	   		
					      	   		textcpu0details.setText(cpu0_current + "MHz");
					      	   		
					      	   		//colorCore();
					      	   	}
					     	   	else if(percent == 0)
					     	   	{
					     	   		textcpu0.setBackgroundColor(0xffCC0000);
					     	   		textcpu0details.setBackgroundResource(R.layout.shape_core);
					     	   	}
				     	   	
				     	    mProgressCPU0.setProgress(mProgressStatusCPU0);
		                    useCPU0.setText( mProgressStatusCPU0 + "%");

			      	   	}
			      	   	
			      	   	
			      	   	
			      	   	
			      	   	
			      	   	
			      	   	
//---------------------------------------DUALCORE---------------------------------------------------------------------------
			      	   	
			      	    if(cpu_max_core == 2)
			    	   	{
			    	   		layoutCpu1.setVisibility(0);
			    	   		layoutCpu2.setVisibility(4);
			    	   		layoutCpu3.setVisibility(4);
			    	   		
			    	   		 percent2 = cpu.readUsageCPU0();
			        	   	 percent= (int) (percent2);
			        	   	 mProgressStatusCPU0 = percent;
			        	   	
			        	   	 percent2CPU1 = cpu.readUsageCPU1();
			        	   	 percentCPU1 = (int) (percent2CPU1);
			        	   	 mProgressStatusCPU1 = percentCPU1;
			        	   	 
			        	   //--------------CORE 1 ACTIF----------------------------------------
					     	   	if(percent > 0)
					      	   	{
					      	   		textcpu0.setBackgroundResource(R.layout.shape_core_on);
					      	   		
					      	   		textcpu0details.setText(cpu0_current + "MHz");
					      	   		
					      	   		//colorCore();
					      	   	}
					     	   	else if(percent == 0)
					     	   	{
					     	   		textcpu0.setBackgroundResource(R.layout.shape_core);
					     	   		textcpu0details.setText("CPU offline");
					     	   	}
					     	   	
					      	   	//-------------------CORE 2 ACTIF------------------------------------------------------
					      	    if(percentCPU1 > 0)
					      	   	{					      	   	
						      	   	try {
										cpu1_current = cpu.getCPU1FrequencyCurrent()/1000;
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						      	   	
						      	    if (cpu1_current == 0)
						      		{
						      			cpu1_current = cpu0_current;
						      		}
						      		
					      	   		textcpu1.setBackgroundResource(R.layout.shape_core_on);
						  	   		textcpu1details.setText(cpu1_current + "MHz");

					      	   		//colorCore();

					      	   	}
					      	    else if(percentCPU1 == 0)
					      	    {
					      	    	textcpu1.setBackgroundResource(R.layout.shape_core);
					     	   		textcpu1details.setText("CPU offline");
					      	    }
			        	   	
			        	   	mProgressCPU0.setProgress(mProgressStatusCPU0);
		                    useCPU0.setText( mProgressStatusCPU0 + "%");
		                    
		                    mProgressCPU1.setProgress(mProgressStatusCPU1);
		                    useCPU1.setText( mProgressStatusCPU1 + "%");
			        	   	
			    	   	}
		      	  
				      	
				      	
				      	
//-----------------------------------QUAD CORE-------------------------------------------------------------------------------------------------------
				      	
				      	if(cpu_max_core == 4)
			      	   	{
			      	   		layoutCpu1.setVisibility(0);
			      	   		layoutCpu2.setVisibility(0);
			      	   		layoutCpu3.setVisibility(0);
			      	   		
				      	   	 percent2 = cpu.readUsageCPU0();
				     	   	 percent= (int) (percent2);
				     	   	 mProgressStatusCPU0 = percent;
				     	   	
				     	   	 percent2CPU1 = cpu.readUsageCPU1();
				     	   	 percentCPU1 = (int) (percent2CPU1);
				     	   	 mProgressStatusCPU1 = percentCPU1;
				     	   	
				     	   	 percent2CPU2 = cpu.readUsageCPU2();
				     	   	 percentCPU2 = (int) (percent2CPU2);
				     	   	 mProgressStatusCPU2 = percentCPU2;
				     	   	
				     	   	 percent2CPU3 = cpu.readUsageCPU3();
				     	   	 percentCPU3 = (int) (percent2CPU3);
				     	   	 mProgressStatusCPU3 = percentCPU3;
				     	   	 
				     	   	
				     	   	//--------------CORE 1 ACTIF----------------------------------------
				     	   	if(percent > 0)
				      	   	{
				      	   		textcpu0.setBackgroundResource(R.layout.shape_core_on);
				      	   		
				      	   		textcpu0details.setText(cpu0_current + "MHz");
				      	   		
				      	   		//colorCore();
				      	   	}
				     	   	else if(percent == 0)
				     	   	{
				     	   		textcpu0.setBackgroundResource(R.layout.shape_core);
				     	   		textcpu0details.setText("CPU offline");
				     	   	}
				     	   	
				      	   	//-------------------CORE 2 ACTIF------------------------------------------------------
				      	    if(percentCPU1 > 0)
				      	   	{					      	   	
					      	   	try {
									cpu1_current = cpu.getCPU1FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      	   	
					      	    if (cpu1_current == 0)
					      		{
					      			cpu1_current = cpu0_current;
					      		}
					      		
				      	   		textcpu1.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu1details.setText(cpu1_current + "MHz");

				      	   		//colorCore();

				      	   	}
				      	    else if(percentCPU1 == 0)
				      	    {
				      	    	textcpu1.setBackgroundResource(R.layout.shape_core);
				     	   		textcpu1details.setText("CPU offline");
				      	    }
				      	   	
				      	    
				      	   //-------------CORE 3 ACTIF-------------------------------------- 
				      	   if(percentCPU2 > 0)
				    	   	{
				      		  
					      		try {
									cpu2_current = cpu.getCPU2FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		if (cpu2_current == 0)
					      		{
					      			cpu2_current = cpu0_current;
					      		}
					      		
				      		  	textcpu2.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu2details.setText(cpu2_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		
				    	   	}
				      	   
				      	   else if(percentCPU2 == 0)
				      	   {
				      		   textcpu2.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu2details.setText("CPU offline");
				      	   }
				      	   
				      	   
				      	   //----------CORE 4 ACTIF--------------------------------------------
					       if(percentCPU3 > 0)
					  	   	{
					      		 
					      		
					      		try {
									cpu3_current = cpu.getCPU3FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		 if(cpu3_current == 0)
					      		{
					      			cpu3_current = cpu0_current;
					      		}

					  	   		textcpu3.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu3details.setText(cpu3_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		 	   		
					  	   	}
					       else if(percentCPU3 == 0)
					       {
					    	   textcpu3.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu3details.setText("CPU offline");
					       }
					       
				      	   	mProgressCPU0.setProgress(mProgressStatusCPU0);
		                    useCPU0.setText( mProgressStatusCPU0 + "%");
		                    
		                    mProgressCPU1.setProgress(mProgressStatusCPU1);
		                    useCPU1.setText( mProgressStatusCPU1 + "%");
		                    
		                    mProgressCPU2.setProgress(mProgressStatusCPU2);
		                    useCPU2.setText( mProgressStatusCPU2 + "%");
		                    
		                    mProgressCPU3.setProgress(mProgressStatusCPU3);
		                    useCPU3.setText( mProgressStatusCPU3 + "%");
			      	   	}
				      	
				      	
				      	
				      	
				      	
//-----------------------------------OCTO CORE-------------------------------------------------------------------------------------------------------
				      	
				      	if(cpu_max_core == 8)
			      	   	{
			      	   		
				      	   	 percent2 = cpu.readUsageCPU0();
				     	   	 percent= (int) (percent2);
				     	   	 mProgressStatusCPU0 = percent;
				     	   	
				     	   	 percent2CPU1 = cpu.readUsageCPU1();
				     	   	 percentCPU1 = (int) (percent2CPU1);
				     	   	 mProgressStatusCPU1 = percentCPU1;
				     	   	
				     	   	 percent2CPU2 = cpu.readUsageCPU2();
				     	   	 percentCPU2 = (int) (percent2CPU2);
				     	   	 mProgressStatusCPU2 = percentCPU2;
				     	   	 
				     	   	 percent2CPU3 = cpu.readUsageCPU3();
				     	   	 percentCPU3 = (int) (percent2CPU3);
				     	   	 mProgressStatusCPU3 = percentCPU3;
				     	   	
				     	   	 percent2CPU4 = cpu.readUsageCPU4();
				     	   	 percentCPU4 = (int) (percent2CPU4);
				     	   	 mProgressStatusCPU4 = percentCPU4;
				     	   	 
				     	   	 percent2CPU5 = cpu.readUsageCPU5();
				     	   	 percentCPU5 = (int) (percent2CPU5);
				     	   	 mProgressStatusCPU5 = percentCPU5;
				     	   	 
				     	   	 percent2CPU6 = cpu.readUsageCPU6();
				     	   	 percentCPU6 = (int) (percent2CPU6);
				     	   	 mProgressStatusCPU6 = percentCPU6;
				     	   	 
				     	   	 percent2CPU7 = cpu.readUsageCPU7();
				     	   	 percentCPU7 = (int) (percent2CPU7);
				     	   	 mProgressStatusCPU7 = percentCPU7;
				     	   	 
				     	   	 
				     	   	 
				     	   	
				     	   	//--------------CORE 1 ACTIF----------------------------------------
				     	   	if(percent > 0)
				      	   	{
				      	   		textcpu0.setBackgroundResource(R.layout.shape_core_on);
				      	   		
				      	   		textcpu0details.setText(cpu0_current + "MHz");
				      	   		
				      	   		//colorCore();
				      	   	}
				     	   	else if(percent == 0)
				     	   	{
				     	   		textcpu0.setBackgroundResource(R.layout.shape_core);
				     	   		textcpu0details.setText("CPU offline");
				     	   	}
				     	   	
				      	   	//-------------------CORE 2 ACTIF------------------------------------------------------
				      	    if(percentCPU1 > 0)
				      	   	{					      	   	
					      	   	try {
									cpu1_current = cpu.getCPU1FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      	   	
					      	    if (cpu1_current == 0)
					      		{
					      			cpu1_current = cpu0_current;
					      		}
					      		
				      	   		textcpu1.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu1details.setText(cpu1_current + "MHz");

				      	   		//colorCore();

				      	   	}
				      	    else if(percentCPU1 == 0)
				      	    {
				      	    	textcpu1.setBackgroundResource(R.layout.shape_core);
				     	   		textcpu1details.setText("CPU offline");
				      	    }
				      	   	
				      	    
				      	   //-------------CORE 3 ACTIF-------------------------------------- 
				      	   if(percentCPU2 > 0)
				    	   	{
				      		  
					      		try {
									cpu2_current = cpu.getCPU2FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		if (cpu2_current == 0)
					      		{
					      			cpu2_current = cpu0_current;
					      		}
					      		
				      		  	textcpu2.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu2details.setText(cpu2_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		
				    	   	}
				      	   
				      	   else if(percentCPU2 == 0)
				      	   {
				      		   textcpu2.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu2details.setText("CPU offline");
				      	   }
				      	   
				      	   
				      	   //----------CORE 4 ACTIF--------------------------------------------
					       if(percentCPU3 > 0)
					  	   	{
					      		 
					      		
					      		try {
									cpu3_current = cpu.getCPU3FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		 if(cpu3_current == 0)
					      		{
					      			cpu3_current = cpu0_current;
					      		}

					  	   		textcpu3.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu3details.setText(cpu3_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		 	   		
					  	   	}
					       else if(percentCPU3 == 0)
					       {
					    	   textcpu3.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu3details.setText("CPU offline");
					       }
					       
					       
					     //----------CORE 5 ACTIF--------------------------------------------
					       if(percentCPU4 > 0)
					  	   	{
					      		 
					      		
					      		try {
									cpu4_current = cpu.getCPU4FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		 if(cpu4_current == 0)
					      		{
					      			cpu4_current = cpu0_current;
					      		}

					  	   		textcpu4.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu4details.setText(cpu4_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		 	   		
					  	   	}
					       else if(percentCPU4 == 0)
					       {
					    	   textcpu4.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu4details.setText("CPU offline");
					       }
					       
					       
					       
					     //----------CORE 6 ACTIF--------------------------------------------
					       if(percentCPU5 > 0)
					  	   	{
					      		 
					      		
					      		try {
									cpu5_current = cpu.getCPU5FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		 if(cpu5_current == 0)
					      		{
					      			cpu5_current = cpu0_current;
					      		}

					  	   		textcpu5.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu5details.setText(cpu5_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		 	   		
					  	   	}
					       else if(percentCPU5 == 0)
					       {
					    	   textcpu5.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu5details.setText("CPU offline");
					       }
					       
					       
					       
					     //----------CORE 7 ACTIF--------------------------------------------
					       if(percentCPU6 > 0)
					  	   	{
					      		 
					      		
					      		try {
									cpu6_current = cpu.getCPU6FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		 if(cpu6_current == 0)
					      		{
					      			cpu6_current = cpu0_current;
					      		}

					  	   		textcpu6.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu6details.setText(cpu6_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		 	   		
					  	   	}
					       else if(percentCPU6 == 0)
					       {
					    	   textcpu6.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu6details.setText("CPU offline");
					       }
					       
					       
					       
					     //----------CORE 8 ACTIF--------------------------------------------
					       if(percentCPU7 > 0)
					  	   	{
					      		 
					      		
					      		try {
									cpu7_current = cpu.getCPU7FrequencyCurrent()/1000;
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					      		
					      		 if(cpu7_current == 0)
					      		{
					      			cpu7_current = cpu0_current;
					      		}

					  	   		textcpu7.setBackgroundResource(R.layout.shape_core_on);
					  	   		textcpu7details.setText(cpu7_current + "MHz");
					  	   		
					  	   		//colorCore();
					  	   		 	   		
					  	   	}
					       else if(percentCPU7 == 0)
					       {
					    	   textcpu7.setBackgroundResource(R.layout.shape_core);
					  	   	   textcpu7details.setText("CPU offline");
					       }
					       
				      	   	mProgressCPU0.setProgress(mProgressStatusCPU0);
		                    useCPU0.setText( mProgressStatusCPU0 + "%");
		                    
		                    mProgressCPU1.setProgress(mProgressStatusCPU1);
		                    useCPU1.setText( mProgressStatusCPU1 + "%");
		                    
		                    mProgressCPU2.setProgress(mProgressStatusCPU2);
		                    useCPU2.setText( mProgressStatusCPU2 + "%");
		                    
		                    mProgressCPU3.setProgress(mProgressStatusCPU3);
		                    useCPU3.setText( mProgressStatusCPU3 + "%");
		                    
		                    mProgressCPU4.setProgress(mProgressStatusCPU4);
		                    useCPU4.setText( mProgressStatusCPU4 + "%");
		                    
		                    mProgressCPU5.setProgress(mProgressStatusCPU5);
		                    useCPU5.setText( mProgressStatusCPU5 + "%");
		                    
		                    mProgressCPU6.setProgress(mProgressStatusCPU6);
		                    useCPU6.setText( mProgressStatusCPU6 + "%");
		                    
		                    mProgressCPU7.setProgress(mProgressStatusCPU7);
		                    useCPU7.setText( mProgressStatusCPU7 + "%");
			      	   	}
				      	
				      	
				      	
		            }
		          });
		        
		      }
		    };
		    new Thread(runnable).start();
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
        t.cancel();
        t.purge();
        finish();
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        t.cancel();
        t.purge();
        finish();
    }
    
    @Override
    protected void onDestroy()
	{
		super.onDestroy();
		t.cancel();
		t.purge();
		finish();
	}

    
}
