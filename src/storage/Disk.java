package storage;

import utils.getDisk;
import MaximumWidget.com.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Disk extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk);
        
        getDisk dd = new getDisk();
        
        TextView internalAv = (TextView)findViewById(R.id.textViewInternalMin);
        TextView internalTotal = (TextView)findViewById(R.id.textViewInternalMax);
        TextView externalAv = (TextView)findViewById(R.id.textViewExternalMin);
        TextView externalTotal = (TextView)findViewById(R.id.textViewExternalMax);
        
        
        if(dd.getTotalInternalMemorySize() != dd.getTotalExternalMemorySize())
        {
	        internalAv.setText(dd.getAvailableInternalMemorySize());
	        internalTotal.setText(dd.getTotalInternalMemorySize());
	        externalAv.setText(dd.getAvailableExternalMemorySize());
	        externalTotal.setText(dd.getTotalExternalMemorySize());
        }
        else
        {
        	internalAv.setText(dd.getAvailableInternalMemorySize());
	        internalTotal.setText(dd.getTotalInternalMemorySize());
	        externalAv.setText("No SD");
	        externalTotal.setText("No SD");
        }
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
        finish();
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