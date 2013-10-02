package application;

import MaximumWidget.com.R;
import android.content.Context;
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

public class CPUStatActivity extends Fragment {
	
	
	private static final String KEY_CONTENT = "TestFragment:Content";

    public static CPUStatActivity newInstance(String content) {
    	CPUStatActivity fragment = new CPUStatActivity();

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
