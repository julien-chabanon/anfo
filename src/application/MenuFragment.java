package application;


import MaximumWidget.com.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

 
public class MenuFragment extends Fragment {
    /** Called when the activity is first created. */
	
	TextView version;
	Context context;

	
	private static final String KEY_CONTENT = "TestFragment:Content";

    public static MenuFragment newInstance(String content) {
    	MenuFragment fragment = new MenuFragment();

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
		 
		View myFragmentView = inflater.inflate(R.layout.my_frame, container, false);
		
		if (myFragmentView.findViewById(R.id.fragment_container) != null) 
		{
 
			// Create fragment and give it an argument specifying the article it should show
        	Fragment newFragment = Main.newInstance("Menu");
        	Bundle args = new Bundle();
        	newFragment.setArguments(args);

        	FragmentTransaction transaction = getFragmentManager().beginTransaction();

        	// Replace whatever is in the fragment_container view with this fragment,
        	// and add the transaction to the back stack so the user can navigate back
        	transaction.replace(R.id.fragment_container, newFragment);

        	// Commit the transaction
        	transaction.commit();
            
		}
		
		else
		{
			// Create an instance of ExampleFragment
	        Fragment firstFragment = Main.newInstance("Menu");
	            
			getFragmentManager().beginTransaction()
            .add(R.id.fragment_container, firstFragment).commit();
		}
		
		
			
			
			

            
		
	return myFragmentView; 
}
	
}
