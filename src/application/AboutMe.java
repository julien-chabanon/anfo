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
import android.widget.ImageView;

public class AboutMe extends Fragment {

	private static final String KEY_CONTENT = "TestFragment:Content";

    public static AboutMe newInstance(String content) {
    	AboutMe fragment = new AboutMe();

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
		
		View myFragmentView = inflater.inflate(R.layout.activity_about_me, container, false);
		
		Context context = getActivity();
		
		
//--------------------------------LIKE FACEBOOK-------------------------------------------------------------------------------------------------------------------
				ImageView fb = (ImageView)myFragmentView.findViewById(R.id.imageViewfacebook);
				
				Uri marketUriFb = Uri.parse("https://www.facebook.com/AnfoWidget");
				final Intent marketIntentFb = new Intent(Intent.ACTION_VIEW, marketUriFb);
				
				
				fb.setOnClickListener(new OnClickListener()
		        {
		        	//@Override
			        public void onClick(View view)
			        {
			        	startActivity(marketIntentFb);
				    }
				});
				
				
				//--------------------------------Twitter-------------------------------------------------------------------------------------------------------------------
				ImageView twitter = (ImageView)myFragmentView.findViewById(R.id.imageViewtwitter);
				
				Uri marketUriTwitter = Uri.parse("https://twitter.com/julien_chabanon");
				final Intent marketIntentTwitter = new Intent(Intent.ACTION_VIEW, marketUriTwitter);
				
				
				twitter.setOnClickListener(new OnClickListener()
		        {
		        	//@Override
			        public void onClick(View view)
			        {
			        	startActivity(marketIntentTwitter);
				    }
				});
				
				
				
				//--------------------------------FORUM GENERATION MOBILE-------------------------------------------------------------------------------------------------------------------
				ImageView forum = (ImageView)myFragmentView.findViewById(R.id.imageViewforum);
				
				Uri marketUriForum = Uri.parse("http://www.forum-generationmobiles.net/t63565-soft-anfo-widget-toutes-les-informations-hardware-gratuit");
				final Intent marketIntentForum = new Intent(Intent.ACTION_VIEW, marketUriForum);
				
				
				forum.setOnClickListener(new OnClickListener()
		        {
		        	//@Override
			        public void onClick(View view)
			        {
			        	startActivity(marketIntentForum);
				    }
				});
				
				
//--------------------------------PUB ANFO CORE-------------------------------------------------------------------------------------------------------------------
				ImageView coreImg = (ImageView)myFragmentView.findViewById(R.id.imageViewAnfoCore);
				
				Uri marketUriCore = Uri.parse("market://details?id=" + "Anfo.Core");
				final Intent marketIntentCore = new Intent(Intent.ACTION_VIEW, marketUriCore);
				
				
				coreImg.setOnClickListener(new OnClickListener()
		        {
		        	//@Override
			        public void onClick(View view)
			        {
			        	startActivity(marketIntentCore);
				    }
				});
		
//--------------------------------PUB ANFO PREMIUM-------------------------------------------------------------------------------------------------------------------
		ImageView premium = (ImageView)myFragmentView.findViewById(R.id.imageViewpremium);
		
		Uri marketUri = Uri.parse("market://details?id=" + "anfo.premium");
		final Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
		
		
		premium.setOnClickListener(new OnClickListener()
        {
        	//@Override
	        public void onClick(View view)
	        {
	        	startActivity(marketIntent);
		    }
		});
		
		
//--------------------------------PUB RATE IT !-------------------------------------------------------------------------------------------------------------------
				ImageView imgRate = (ImageView)myFragmentView.findViewById(R.id.imageViewrate);
				
				Uri marketUriRate = Uri.parse("market://details?id=" + "MaximumWidget.com");
				final Intent marketIntentRate = new Intent(Intent.ACTION_VIEW, marketUriRate);
				
				
				imgRate.setOnClickListener(new OnClickListener()
		        {
		        	//@Override
			        public void onClick(View view)
			        {
			        	startActivity(marketIntentRate);
				    }
				});
				
				
				
				
        
        return myFragmentView;
	}

}
