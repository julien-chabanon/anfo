package application;

import MaximumWidget.com.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Disk extends Fragment {


	private static final String KEY_CONTENT = "TestFragment:Content";

    public static Disk newInstance(String content) {
    	Disk fragment = new Disk();

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
