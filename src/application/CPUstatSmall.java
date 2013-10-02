package application;

import MaximumWidget.com.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CPUstatSmall extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpustat_small);

        CpuStat stat = new CpuStat();
        
        ArrayList<String> freq = stat.getFreqFormat();
        ArrayList<String> stats = stat.getFreqStatFormat();

        
        ListView lv= (ListView)findViewById(R.id.listView2);

        // create the grid item mapping
        String[] from = new String[] {"col_1", "col_2"};
        int[] to = new int[] { R.id.textViewItemCaca, R.id.textViewItem2Caca};

        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < freq.size(); i++){
        	HashMap<String, String> map = new HashMap<String, String>();
        	
        		map.put("col_1", freq.get(i));
            	map.put("col_2", stats.get(i));
            	fillMaps.add(map);

        	
        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.list_cpu_state, from, to);
        lv.setAdapter(adapter);
        
        
    }
    
    
    @Override
    public void onBackPressed() 
    {
        // do something on back.
    	finish();
        return;
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
    protected void onDestroy()
	{
		super.onDestroy();
		finish();
	} 
}
