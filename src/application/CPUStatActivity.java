package application;

import MaximumWidget.com.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("ValidFragment")
public class CPUStatActivity extends Fragment {
	
	
	private static final String KEY_CONTENT = "TestFragment:Content";

	private int mPos = -1;
	private int mImgRes;
	
	public CPUStatActivity() { }
	public CPUStatActivity(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, CPUStatActivity.class);
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
  		
    	  View myFragmentView = inflater.inflate(R.layout.activity_cpustat, container, false);

    	  Context context = getActivity();
    	  
        CpuStat stat = new CpuStat();
        
        ArrayList<String> freq = stat.getFreqFormat();
        ArrayList<String> stats = stat.getFreqStatFormat();
        boolean isSupported = stat.isSupport();
        
        ListView lv= (ListView)myFragmentView.findViewById(R.id.listView2);

        // create the grid item mapping
        String[] from = new String[] {"col_1", "col_2"};
        int[] to = new int[] { R.id.textViewItemCaca, R.id.textViewItem2Caca};
        
     // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
    	

	        if(isSupported)
	        {
		        	for (int j = 0; j < freq.size(); j++) 
		        	{
		        		HashMap<String, String> map = new HashMap<String, String>();
		        		map.put("col_1", freq.get(j));
		            	map.put("col_2", stats.get(j));
		            	fillMaps.add(map);
					}		
		     }
	        
	        else
	        {
	        			HashMap<String, String> map = new HashMap<String, String>();
		        		map.put("col_1", "unsupported feature");
		            	map.put("col_2", "unsupported");
		            	fillMaps.add(map);	
	        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(context, fillMaps, R.layout.list_cpu_state, from, to);
        lv.setAdapter(adapter);
        
        return myFragmentView;
    }
}
