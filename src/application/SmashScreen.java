package application;

import MaximumWidget.com.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
 

public class SmashScreen extends Activity {
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        setContentView(R.layout.activity_smash_screen);
 
        Handler handler = new Handler(); 
        
        PackageInfo pInfo = null;
        
        TextView versionText = (TextView)findViewById(R.id.textViewAppVersion);
        
        
        
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String version = pInfo.versionName;
        
        versionText.setText("Version " + version + " - open source");
 
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {
 
            @Override
            public void run() {
 
                // make sure we close the splash screen so the user won't come back when it presses back key
 
                finish();
                // start the home screen
 
                Intent intent = new Intent(SmashScreen.this, SlidingBar.class);
                SmashScreen.this.startActivity(intent);
 
            }
 
        }, 1000); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
 
    }
 
}
