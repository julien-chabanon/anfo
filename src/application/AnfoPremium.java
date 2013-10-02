package application;

import MaximumWidget.com.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AnfoPremium extends Fragment {
	
	
	private static final String KEY_CONTENT = "TestFragment:Content";

    public static AnfoPremium newInstance(String content) {
    	AnfoPremium fragment = new AnfoPremium();

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
		
		View myFragmentView = inflater.inflate(R.layout.activity_anfo_premium, container, false);
		
        Context context = getActivity();
		
		
		Button go = (Button)myFragmentView.findViewById(R.id.buttonGoMarket);
		
		Uri marketUri = Uri.parse("market://details?id=" + "anfo.premium");
		final Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
		
		
		go.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	startActivity(marketIntent);
		    }
		});
		
		return myFragmentView;
	}

}
