package application;

import MaximumWidget.com.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class AboutMe extends Fragment {

	private static final String KEY_CONTENT = "TestFragment:Content";

    
	private int mPos = -1;
	private int mImgRes;
	
	public AboutMe() { }
	public AboutMe(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, AboutMe.class);
		intent.putExtra("pos", pos);
		return intent;
    }
    
    
    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mPos", mPos);
	}
    
	
    private String mContent = "???";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
