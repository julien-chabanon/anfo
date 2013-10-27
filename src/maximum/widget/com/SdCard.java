package maximum.widget.com;

import MaximumWidget.com.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import utils.getDisk;

public class SdCard extends Activity {


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disk);
    
    getDisk dd = new getDisk();
    
    final TextView internalAv = (TextView)findViewById(R.id.textViewInternalMin);
    final TextView internalTotal = (TextView)findViewById(R.id.textViewInternalMax);
    final TextView externalAv = (TextView)findViewById(R.id.textViewExternalMin);
    final TextView externalTotal = (TextView)findViewById(R.id.textViewExternalMax);
    final ProgressBar barInternal  =(ProgressBar)findViewById(R.id.progressBarSdInternal);
    final ProgressBar barExternal  =(ProgressBar)findViewById(R.id.progressBarSdExternal);
    final TextView barInternalText = (TextView)findViewById(R.id.textViewProgressBarSdInternal);
    final TextView barExternalText = (TextView)findViewById(R.id.textViewProgressBarSdExternal);
    
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
   
}




@Override
public void onStop() {
	super.onStop();
	
	finish();
}

/*
@Override
public void onPause() {
 // TODO Auto-generated method stub
 super.onPause();

 finish();
}    
*/
}