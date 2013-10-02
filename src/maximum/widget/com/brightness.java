package maximum.widget.com;


import MaximumWidget.com.R;
import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;


public class brightness extends Activity  {
    /** Called when the activity is first created. */
        float InitValue = 0.5f; //dummy default value
        float BackLightValue;
        boolean auto;
        boolean change = false;
        int curBrightnessValue;
        ToggleButton toggle;
        SeekBar BackLightControl;
        TextView BackLightSetting;
        Button UpdateSystemSetting;
        
        
          @Override
          public void onCreate(Bundle savedInstanceState) {
              super.onCreate(savedInstanceState);
              setContentView(R.layout.brightness);
              
              
              toggle = (ToggleButton) findViewById(R.id.auto);

              BackLightControl = (SeekBar)findViewById(R.id.backlightcontrol);
              BackLightSetting = (TextView)findViewById(R.id.backlightsetting);
              UpdateSystemSetting = (Button)findViewById(R.id.updatesystemsetting);
              
              DoStuff();
            
          }
          
          
          public void DoStuff()
          {
        	  int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        	  int SCREEN_BRIGHTNESS_MODE_MANUAL = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

        	  
        	  	currentBrightness();
        	  	

        	  	 int max = BackLightControl.getMax();
        	  	 
        	  	 int bonni = (max*curBrightnessValue)/255;
        	  	 
        	  	 
        	  	 BackLightControl.setProgress(bonni);

        	  		              if(SCREEN_BRIGHTNESS_MODE_AUTOMATIC == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
        	  		              {
        	  		            	  toggle.setChecked(true);
        	  		            	  toggle.setText("Auto ON");
        	  		            	  BackLightControl.setEnabled(false);
        	  		            	  UpdateSystemSetting.setText("Apply auto brightness");
        	  		            	  UpdateSystemSetting.setBackgroundColor(0xfff00000);
        	  		            	  auto = true;
        	  		            	  BackLightSetting.setText("Level: auto");
        	  		            	  
        	  		            	  toggle.setOnClickListener(new View.OnClickListener() {
        	  			
        	  			                public void onClick(View view) {
        	  			                	android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        	  			                	
        	  			                	Context context = getBaseContext();
        	  			                	RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_bg);
        	  			                	remoteViews.setImageViewResource(R.id.brightness, R.drawable.brightness_icon);
        	  			                	
        	  			                	ComponentName thiswidget = new ComponentName(context, widgetBG.class);
        	  			            	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
        	  			            	    manager.updateAppWidget(thiswidget, remoteViews);
        	  			                	
        	  			            	    DoStuff();
        	  			                }
        	  			
        	  			              });
        	  		              }
        	  		              
        	  		              if(SCREEN_BRIGHTNESS_MODE_MANUAL == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
        	  		              {
        	  		            	  
        	  		            	  toggle.setChecked(false);
        	  		            	  toggle.setText("Auto OFF");
        	  		            	  BackLightControl.setEnabled(true);
        	  		            	  UpdateSystemSetting.setText("Apply brightness");
        	  		            	  UpdateSystemSetting.setBackgroundColor(0xff669900);
        	  		            	  auto = false;
        	  		            	  
        	  		            	  float affiche = curBrightnessValue/255f;
        	  		            	  int bebe = (int)(affiche*100);
        	  		            	  BackLightSetting.setText("Level: " + bebe + "%");
        	  		            	  
	        	  		              WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
	        	  		              
	     	  	                       if(affiche <= 1 && affiche > 0.1)
	     	  	                       {
	     	  	                    	   layoutParams.screenBrightness = affiche;
	     	  	                       }
	     	  	                       
	     	  	                       getWindow().setAttributes(layoutParams);
        	  		            	  
        	  		            	  toggle.setOnClickListener(new View.OnClickListener() {
        	  			
        	  			                public void onClick(View view) {
        	  			                	
        	  			                  // Display a notification popup during 1 second.
        	  			                	android.provider.Settings.System.putInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE, android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        	  			                	
        	  			                	Context context = getBaseContext();
        	  			                	RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_widget_bg);
        	  			                	remoteViews.setImageViewResource(R.id.brightness, R.drawable.brightness_icon_auto);
        	  			                	
        	  			                	ComponentName thiswidget = new ComponentName(context, widgetBG.class);
        	  			            	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
        	  			            	    manager.updateAppWidget(thiswidget, remoteViews);

        	  			            	    DoStuff();
        	  			                }
        	  			
        	  			              });
        	  		              
        	  		              
        	  		            	  
        	  		            	  
        	  			              UpdateSystemSetting.setOnClickListener(new Button.OnClickListener(){
        	  			                  public void onClick(View arg0) {
        	  			                	  if(!auto)
        	  					              {
        	  			                		  if(change)
        	  			                		  {
        	  			                			   int SysBackLightValue = (int)(BackLightValue * 255);
        	  						                   android.provider.Settings.System.putInt(getContentResolver(),
        	  						                   android.provider.Settings.System.SCREEN_BRIGHTNESS,SysBackLightValue);  
        	  			                		  }
        	  			                		  
        	  			                		  else
        	  			                		  {
        	  			                			  android.provider.Settings.System.putInt(getContentResolver(),
             	  						              android.provider.Settings.System.SCREEN_BRIGHTNESS,curBrightnessValue); 
        	  			                		  }
        	  			                		  
        	  			                		  finish();

        	  					              }
        	  			                	  else if(auto)
        	  					              {
        	  			                		  //findViewById(R.id.updatesystemsetting).startAnimation(shake);
        	  			                		  finish();
        	  					              }
        	  			                  }});
        	  		              
        	  		                

        	                }
        	  		            final Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        	  		              if(!auto)
        	  		              {
        	  		              	  BackLightControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        	  	                      public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
        	  	                       // TODO Auto-generated method stub
        	  	                       InitValue = (float)arg1/100;
        	  	                       BackLightValue = (float) (InitValue);
        	  	                       int delire = (int)(BackLightValue*100);
        	  	                       
        	  	                       BackLightSetting.setText("Level: " + delire + "%");
        	  	                       
        	  	                       change = true;
        	  	                      
        	  	                       WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        	  	                       if(BackLightValue < 1 && BackLightValue > 0.1)
        	  	                       {
        	  	                    	   layoutParams.screenBrightness = BackLightValue;
        	  	                       }
        	  	                       
        	  	                       getWindow().setAttributes(layoutParams);
        	  	                      }
        	  	                      public void onStartTrackingTouch(SeekBar arg0) {
        	  	                      }
        	  	                      public void onStopTrackingTouch(SeekBar arg0) {
        	  	                      }});
        	  		              }
        	  		              
        	  		              else if(auto)
        	  		              {
        	  		            	  findViewById(R.id.backlightcontrol).startAnimation(shake);
        	  		            	  BackLightControl.setOnClickListener(new Button.OnClickListener(){
        	  			                  public void onClick(View arg0) {
        	  			                	  
        	  			                	  findViewById(R.id.backlightcontrol).startAnimation(shake);
        	  			                	  
        	  			                  }});
        	  		              }
        	  		              
          }
          
          public void currentBrightness()
          {
        	  try {
      	  		curBrightnessValue = android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
      	  	} catch (SettingNotFoundException e) {
      	  		// TODO Auto-generated catch block
      	  		e.printStackTrace();
      	  	}
          }
          
          
          @Override
          public void onBackPressed() 
          {
              // do something on back.
          	finish();
              return;
          }
          
          @Override
          protected void onResume() {
              super.onResume();
              // The activity is no longer visible (it is now "stopped")
              DoStuff();
          }
          
          @Override
          protected void onPause() {
              super.onPause();
              // The activity is no longer visible (it is now "stopped")
              //finish();
          }
          @Override
          protected void onStop() {
              super.onStop();
              // The activity is no longer visible (it is now "stopped")
              finish();
          }
          @Override
          protected void onDestroy() {
              super.onDestroy();
              // The activity is about to be destroyed.
              finish();
          }
          
}