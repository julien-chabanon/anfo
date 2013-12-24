package application;

import java.util.ArrayList;
import utils.getCPU;
import java.util.HashMap;
import MaximumWidget.com.R;
import android.R.integer;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class Overclock extends Fragment {
	
	GetAllCPUFreqAvailable CPUFreqAvailable = new GetAllCPUFreqAvailable();
	Spinner minBar;
	Spinner maxBar;
	Spinner spinneurGovernor;
	SpinnerAdapter adapterFreq;
	SpinnerAdapter adapterGoverneur;
	getCPU cpu = new getCPU();
	TextView minText;
	TextView maxText;
	TextView governorText;
	Button applyButton;
	Root root;
	Boolean isRoot;
	String MY_PREF = new String("MY_PREFS");
	String cpuMinFreq = new String("cpuMinFreq");
	String cpuMaxFreq = new String("cpuMaxFreq");
	String cpuGovernor = new String("cpuGovernor");
	String bootFrequency = new String("bootFrequency");
	CheckBox checkBoxBoot;
	

	private static final String KEY_CONTENT = "TestFragment:Content";
    private Context context;

    private int mPos = -1;
	private int mImgRes;
	
	public Overclock() { }
	public Overclock(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, Overclock.class);
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

        context = getActivity();

		root = new Root();
		isRoot = root.isDeviceRooted();
		View myFragmentView = null;
		
		if(isRoot)
		{
			myFragmentView = inflater.inflate(R.layout.activity_overclock, container, false);
			
			context = getActivity();
			
			minBar = (Spinner)myFragmentView.findViewById(R.id.spinnerCPUMin);
			maxBar = (Spinner)myFragmentView.findViewById(R.id.spinnerCPUMax);
			spinneurGovernor = (Spinner)myFragmentView.findViewById(R.id.spinnerGoverneur);
			
			applyButton = (Button)myFragmentView.findViewById(R.id.buttonApplyOverclock);
			
			minText = (TextView)myFragmentView.findViewById(R.id.textViewOverclockMin);
			maxText = (TextView)myFragmentView.findViewById(R.id.textViewOverclockMax);
			governorText = (TextView)myFragmentView.findViewById(R.id.textViewOverclockGoverneur);
			
			checkBoxBoot = (CheckBox)myFragmentView.findViewById(R.id.checkBoxFrequencyOnBoot);
	        
			
			DoStuff();
			onBootManager();
			
		}
		else
		{
			myFragmentView = inflater.inflate(R.layout.is_not_root, container, false);
		}
		
		

		
        
		
		return myFragmentView;
		
	}
	
	
	public void DoStuff()
	{
		
		
		ArrayList<String> governor = CPUFreqAvailable.getAllGovernorList();
        ArrayList<Integer> allCPUFreq = CPUFreqAvailable.getAllFreqList();
        
        adapterFreq = new ArrayAdapter(context,R.layout.my_textview_spinner, allCPUFreq);
        minBar.setAdapter(adapterFreq);
        maxBar.setAdapter(adapterFreq);
        
        adapterGoverneur = new ArrayAdapter(context,R.layout.my_textview_spinner, governor);
        spinneurGovernor.setAdapter(adapterGoverneur);
        
        String governorActive = new String(cpu.getGovernor2());
        governorText.setText("Active governor: " + governorActive);
        
        
		int maxScalingFreq = 0;
        int minScalingFreq = 0;
		try {

            minScalingFreq = cpu.getMinScalingCPUFreq();
            maxScalingFreq = cpu.getMaxScalingCPUFreq();
			
			String minFreq = new String("Min: " + cpu.getCPUFrequencyMin()/1000 + " Mhz --- " + "Now: " + minScalingFreq +  "Mhz");
	        minText.setText(minFreq);
	        
	        String maxFreq = new String("Max: " + cpu.getCPUFrequencyMax()/1000 + " Mhz --- " + "Now: " + maxScalingFreq +  "Mhz");
	        maxText.setText(maxFreq);


			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		
		
		
        for(int i = 0; i < allCPUFreq.size(); i++)
        {
        	try {
				if(maxScalingFreq == allCPUFreq.get(i))
				{
					maxBar.setSelection(i);
				}
				
				if(minScalingFreq == allCPUFreq.get(i))
				{
					minBar.setSelection(i);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        for(int i = 0; i < governor.size(); i++)
        {
        	try {
				if(governor.get(i).equalsIgnoreCase(governorActive))
				{
					spinneurGovernor.setSelection(i);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        
        applyButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0) {
          	  
            	int maxFreq = Integer.parseInt(maxBar.getSelectedItem().toString())*1000;
            	int minFreq = Integer.parseInt(minBar.getSelectedItem().toString())*1000;
            	String gov = new String(spinneurGovernor.getSelectedItem().toString());
            	
            	cpu.setMaxFreqCPU0(maxFreq);
            	cpu.setMaxFreqCPU1(maxFreq);
            	cpu.setMaxFreqCPU2(maxFreq);
            	cpu.setMaxFreqCPU3(maxFreq);
            	
            	cpu.setMinFreqCPU0(minFreq);
            	cpu.setMinFreqCPU1(minFreq);
            	cpu.setMinFreqCPU2(minFreq);
            	cpu.setMinFreqCPU3(minFreq);
            	
            	cpu.setGovernor(gov);
            	
            	DoStuff();
            	onBootManager();
          	  
            }});
	}
	
	
	
	
	public void saveMaxFreq(int i)
    {
    	  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		  SharedPreferences.Editor editor = myPrefs.edit();
		  editor.putInt(cpuMaxFreq, i);
		  editor.commit();
    }
	
	public void saveMinFreq(int i)
    {
    	  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		  SharedPreferences.Editor editor = myPrefs.edit();
		  editor.putInt(cpuMinFreq, i);
		  editor.commit();
    }
	
	
	public void saveGovernor(String i)
    {
    	  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		  SharedPreferences.Editor editor = myPrefs.edit();
		  editor.putString(cpuGovernor, i);
		  editor.commit();
    }
    
    public void saveBootPref(Boolean i)
    {
    	  SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
		  SharedPreferences.Editor editor = myPrefs.edit();
		  editor.putBoolean(bootFrequency, i);
		  editor.commit();
		  Toast.makeText(context, "Pref saved !", Toast.LENGTH_SHORT).show();
    }
    
    public Boolean getPrefBoot()
    {
    	SharedPreferences myPrefs = context.getSharedPreferences(MY_PREF, Activity.MODE_PRIVATE);
        Boolean prefBootCore = myPrefs.getBoolean(bootFrequency, false);
        return prefBootCore;
    }
    
    public void saveAllData()
    {
    	saveMaxFreq(Integer.parseInt(maxBar.getSelectedItem().toString()));
    	saveMinFreq(Integer.parseInt(minBar.getSelectedItem().toString()));
    	saveGovernor(spinneurGovernor.getSelectedItem().toString());
    }
    
    
    public void onBootManager()
    {
    	if(getPrefBoot())
		{
			checkBoxBoot.setChecked(true);
		}
		else
		{
			checkBoxBoot.setChecked(false);
		}
    	
    	
    	if(checkBoxBoot.isChecked())
    	{
    		saveAllData();
    		
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
	            	saveAllData();
	          	  
	            }});
    	}
    }

}
