package application;

import MaximumWidget.com.R;
import utils.getDisk;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class Disk extends Fragment {


	private static final String KEY_CONTENT = "TestFragment:Content";

	private int mPos = -1;
	private int mImgRes;
	
	public Disk() { }
	public Disk(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, Disk.class);
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
  		
    	View myFragmentView = inflater.inflate(R.layout.activity_disk, container, false);
        
        getDisk dd = new getDisk();
        
        final TextView internalAv = (TextView)myFragmentView.findViewById(R.id.textViewInternalMin);
        final TextView internalTotal = (TextView)myFragmentView.findViewById(R.id.textViewInternalMax);
        final TextView externalAv = (TextView)myFragmentView.findViewById(R.id.textViewExternalMin);
        final TextView externalTotal = (TextView)myFragmentView.findViewById(R.id.textViewExternalMax);
        final ProgressBar barInternal  =(ProgressBar)myFragmentView.findViewById(R.id.progressBarSdInternal);
        final ProgressBar barExternal  =(ProgressBar)myFragmentView.findViewById(R.id.progressBarSdExternal);
        final TextView barInternalText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarSdInternal);
        final TextView barExternalText = (TextView)myFragmentView.findViewById(R.id.textViewProgressBarSdExternal);
        
        if(dd.getTotalInternalMemorySize() != dd.getTotalExternalMemorySize())
        {
	        internalAv.setText(dd.getAvailableInternalMemorySize());
	        internalTotal.setText(dd.getTotalInternalMemorySize());
	        int percentInternalUse = (int)(long) dd.pourcentageUseInternal();
	        barInternal.setProgress(percentInternalUse);
	        barInternalText.setText(percentInternalUse + "%");
	        
	        externalAv.setText(dd.getAvailableExternalMemorySize());
	        externalTotal.setText(dd.getTotalExternalMemorySize());
	        int percentExternalUse = (int)(long) dd.pourcentageUseExternal();
	        barExternal.setProgress(percentExternalUse);
	        barExternalText.setText(percentExternalUse + "%");
        }
        else if(dd.getTotalInternalMemorySize() == dd.getTotalExternalMemorySize())
        {
        	internalAv.setText(dd.getAvailableInternalMemorySize());
	        internalTotal.setText(dd.getTotalInternalMemorySize());
	        externalAv.setText("No SD");
	        externalTotal.setText("No SD");
        }
        
        return myFragmentView;
    }
    

    
}
