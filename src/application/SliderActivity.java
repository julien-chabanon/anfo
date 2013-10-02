package application;

import MaximumWidget.com.R;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.widget.TextView;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;
import java.util.Vector;

public class SliderActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "About me", "Home", "Menu"};

	private PagerAdapter mPagerAdapter;
	private int x = 0;
	int api = 0;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    
    api = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
    
    if(api > 11)
    {
    	setContentView(R.layout.viewpager);
    	
    	int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");

        TextView actionBarTitle = (TextView)findViewById(titleId);
        actionBarTitle.setTextColor(getResources().getColor(android.R.color.white));
        actionBarTitle.setTypeface(null, Typeface.BOLD);
        actionBarTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }
    
    else
    {
    	setContentView(R.layout.viewpager_api_less_than_11);
    }
    
    
    	// Création de la liste de Fragments que fera défiler le PagerAdapter
 		List fragments = new Vector();

 		// Ajout des Fragments dans la liste
 		
 		
 		fragments.add(AboutMe.newInstance(CONTENT[0 % CONTENT.length]));
 		fragments.add(HomeCards.newInstance(CONTENT[1 % CONTENT.length]));
 		fragments.add(MenuFragment.newInstance(CONTENT[2 % CONTENT.length]));

    FragmentPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);

    ViewPager pager = (ViewPager)findViewById(R.id.pager);
    pager.setAdapter(adapter);
    

    TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
    indicator.setViewPager(pager);
    
    pager.setCurrentItem(1);
}

class MyPagerAdapter extends FragmentPagerAdapter {


	private final List fragments;

	//On fournit à l'adapter la liste des fragments à afficher
	public MyPagerAdapter(FragmentManager fm, List fragments) 
	{
		super(fm);
		this.fragments = fragments;
	}

    @Override
    public Fragment getItem(int position) {
    	
    	return (Fragment) this.fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length].toUpperCase();
    }

    @Override
    public int getCount() {
      return CONTENT.length;
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