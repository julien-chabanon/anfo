package core.only;

import MaximumWidget.com.R;
import utils.getCPU;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;
import application.Root;

public class CoreSwitch extends Activity {
	
	RadioButton core1;
	RadioButton core2;
	RadioButton core3;
	RadioButton core4;
	CheckBox checkSetOnBoot;
	
	Handler handler;
	getCPU cpu = new getCPU();
	Root root = new Root();
	
	int coreClick = 4;
	Boolean isRoot;
	int coreMaxAuth = 0;
	
	String MY_PREF = new String("MY_PREFS");
    String cpuCoreBiatch = new String("CPUCoreBiatch");
    String bootBiatch = new String("CoreBootBiatch");
    
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_switch);
        
        core1 = (RadioButton)findViewById(R.id.radio0);
        core2 = (RadioButton)findViewById(R.id.radio1);
        core3 = (RadioButton)findViewById(R.id.radio2);
        core4 = (RadioButton)findViewById(R.id.radio3);
        checkSetOnBoot = (CheckBox)findViewById(R.id.checkBoxSetOnBootCoreSwitch);
        
        handler = new Handler();
        isRoot = root.isDeviceRooted(); 
        context = this;
        

    	
    	try {
			coreMaxAuth = cpu.getCPUMaxOfCoreAllowed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        checkAuthCore();
    }
    
    
    
    
    
    
    
    
    
    
    public void checkAuthCore()
    {
    	Runnable runnable = new Runnable() {
		      @Override
		      public void run() {


		          handler.post(new Runnable() {
		            @Override
		            public void run() {
		            	
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
    			checkSetOnBoot.setChecked(true);
    		}
    		else
    		{
    			checkSetOnBoot.setChecked(false);
    		}
    		
	    	if(coreMaxAuth == 1)
	    	{
	    		core1.setChecked(true);
	    	}
	    	
	    	else if(coreMaxAuth == 2)
	    	{
	    		core2.setChecked(true);
	    	}
	    	
	    	else if(coreMaxAuth == 3)
	    	{
	    		core3.setChecked(true);
	    	}
	    	
	    	else if(coreMaxAuth == 4)
	    	{    		
	    		core4.setChecked(true);
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
	    	
	    	if(checkSetOnBoot.isChecked())
	    	{
	    		checkSetOnBoot.setOnClickListener(new Button.OnClickListener(){
		            public void onClick(View arg0) {
		          	  
		            	saveBootPref(false);
		          	  
		            }});
	    	}
	    	else
	    	{
	    		checkSetOnBoot.setOnClickListener(new Button.OnClickListener(){
		            public void onClick(View arg0) {
		          	  
		            	saveBootPref(true);
		          	  
		            }});
	    	}
	    	
	    	
    	
    	}
    	else
    	{
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
    	}
    	
    	
    	
    	
    	
		            }
		          });
		        
		      }
		    };
		    new Thread(runnable).start();
    	
    }
    
    
    
    
    public void clickCore(int coreClicka)
    {
    	this.coreClick = coreClicka;
    	
    	 Builder dia_not_root = new AlertDialog.Builder(this);
		 Builder dia_root = new AlertDialog.Builder(this);
		 Builder dia_wrong = new AlertDialog.Builder(this);
		
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
		
		dia_wrong.setTitle("Alert !")
        .setMessage("Anfo cannot disable cpu core on your device")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	
            	  // do nothing ^^
            }
         });
    	
    	
    	if(isRoot)
  	  {
    		if(coreMaxAuth > 0)
        	  {
    			 dia_root.show();
        	  }
      	  
      	  
    		else if(coreMaxAuth == 0)
	      	  {
	      		 dia_wrong.show();
	      	  }
      	 
  	  }
  	  else
  	  {
  		  dia_not_root.show();
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
}
