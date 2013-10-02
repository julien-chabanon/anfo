package application;


import MaximumWidget.com.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

 
public class Main extends Fragment {
    /** Called when the activity is first created. */
	
	TextView version;
	Context context;
	static Main fragment;
	
	private static final String KEY_CONTENT = "TestFragment:Content";

    public static Main newInstance(String content) {
    	 fragment = new Main();

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
		
		View myFragmentView = inflater.inflate(R.layout.activity_main, container, false);

		
        context = getActivity();
        
        final Intent intent1 = new Intent(context, SystemePanel.class);
        final Intent intent2 = new Intent(context, NotificationConfig.class);
        final Intent intent3 = new Intent(context, CpuInfo.class);
        final Intent intent4 = new Intent(context, RamUse.class);
        final Intent intent5 = new Intent(context, ControlCore.class);
        final Intent intent6 = new Intent(context, Wireless.class);
        final Intent intent7 = new Intent(context, Disk.class);
        final Intent intent8 = new Intent(context, CPUStatActivity.class);
        final Intent intent9 = new Intent(context, Overclock.class);

        //this.overridePendingTransition(R.anim.right_transition,R.anim.left_transition);


        ImageView button = (ImageView)myFragmentView.findViewById(R.id.imageViewSysyemPanel);
        button.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = SystemePanel.newInstance("Task");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
	        }
        });
        
        
        ImageView button2 = (ImageView)myFragmentView.findViewById(R.id.imageView2);
        button2.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = NotificationConfig.newInstance("Notification");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        
        ImageView button3 = (ImageView)myFragmentView.findViewById(R.id.imageView3);
        button3.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = CpuInfo.newInstance("Control Core");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        
        ImageView button4 = (ImageView)myFragmentView.findViewById(R.id.imageView4);
        button4.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = ControlCore.newInstance("Control Core");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        
        ImageView button5 = (ImageView)myFragmentView.findViewById(R.id.imageView5);
        button5.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = RamUse.newInstance("Ram");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        
        ImageView button6 = (ImageView)myFragmentView.findViewById(R.id.imageView6);
        button6.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = Wireless.newInstance("Battery");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        
        ImageView button7 = (ImageView)myFragmentView.findViewById(R.id.imageView7);
        button7.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = Disk.newInstance("SD");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);

	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        
        ImageView button8 = (ImageView)myFragmentView.findViewById(R.id.imageView10);
        button8.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = CPUStatActivity.newInstance("CPU Stats");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
		    }
		});
        
        ImageView button9 = (ImageView)myFragmentView.findViewById(R.id.imageView8);
        button9.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	// Create fragment and give it an argument specifying the article it should show
	        	Fragment newFragment = Overclock.newInstance("Overclock");
	        	Bundle args = new Bundle();
	        	newFragment.setArguments(args);

	        	FragmentTransaction transaction = getFragmentManager().beginTransaction();
	        	transaction.setCustomAnimations(R.anim.activity_down_up_enter, R.anim.activity_down_up_exit, R.anim.activity_down_up_close_enter,R.anim.activity_down_up_close_exit);


	        	// Replace whatever is in the fragment_container view with this fragment,
	        	// and add the transaction to the back stack so the user can navigate back
	        	transaction.replace(R.id.fragment_container, newFragment);
	        	transaction.addToBackStack(null);

	        	// Commit the transaction
	        	transaction.commit();
	        	
	        	//getActivity().getFragmentManager().beginTransaction().remove(anus).commit();
		    }
		});
        
        
        return myFragmentView;
 }
    

    
}
