package application;

import MaximumWidget.com.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

 
@SuppressLint("ValidFragment")
public class SystemePanel extends Fragment implements AdapterView.OnItemClickListener{
    /** Called when the activity is first created. */
	ProcessInfo p;
	Timer t;
	ListView mListView;
	Context context;
	int compt;
	int iconId;
	int lineNum;
    List<ProcessItem> documents;
    ProcessItem doc;
    List<ProcessItem> liste;
    MobileArrayAdapter adapter;
    private Handler handler;
    String cpu;
	String ram;
	String pid;
	BufferedReader in = null;
	String line;
	String pidToKill;
	Drawable icon = null;
	Process processThread = null;
	String packageName;
	int imageResource;
	

	private static final String KEY_CONTENT = "TestFragment:Content";

	private int mPos = -1;
	private int mImgRes;
	
	public SystemePanel() { }
	public SystemePanel(int pos) {
		mPos = pos;
	}
	

    public static Intent newInstance(Activity activity, int pos) {
    	Intent intent = new Intent(activity, SystemePanel.class);
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
  		
    	  View myFragmentView = inflater.inflate(R.layout.activity_systeme_panel, container, false);
        
        handler = new Handler();
        
        t = new Timer(true);
        t.scheduleAtFixedRate(new Action(),500,5000);
        

        context = getActivity();
        
        mListView = (ListView)myFragmentView.findViewById(R.id.listView1);

        
        //l'activité enregistrée comme listener sur le clic d'item
        mListView.setOnItemClickListener(this);
        
	        compt = 0;
	        
	        return myFragmentView;
}
    
  
    
    
    
    class Action extends TimerTask {

	    @Override
	    public void run() 
	    {
	    	
	    	

	    									
	    									Runnable runnable = new Runnable() {
	    							  		      @Override
	    							  		      public void run() {


	    							  		          handler.post(new Runnable() {
	    							  		            @Override
	    							  		            public void run() {
	    							  		            	
	    							  		            	try {
	    							  		    				
	    							  		            		processThread = Runtime.getRuntime().exec("top -m 30 -n 1 -d 1 -s rss");

	    							  		    				in = new BufferedReader(new InputStreamReader(processThread
	    							  		    						.getInputStream()));
	    							  		    	        
	    							  		    	        
	    							  		    			lineNum = 0;
	    							  		    			
	    							  		    			
	    							  		    			if(compt == 0)
	    							  						{
	    							  		    				liste = new ArrayList<ProcessItem>();
	    							  						}
	    							  		    			liste.clear();
	    							  		            	
	    							  		            	try {
																while ((line = in.readLine()) != null) {
																	if (line.length() != 0) {
																		if (lineNum >= 3) {
																			try {

																						
																						p = new ProcessInfo(lineNum+ " " + line);
																
																 packageName = p.getPackageName();
																
																
																final PackageManager pm = context.getPackageManager();
																ApplicationInfo ai;
																try {
																    ai = pm.getApplicationInfo(packageName, 0);
																    iconId = ai.icon;

																} catch (final NameNotFoundException e) {
																    ai = null;
																}
																final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
																
																
																
																try {
																	icon = pm.getApplicationIcon(packageName);
																} catch (NameNotFoundException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
																if(icon == null)
																{
																	  imageResource = R.drawable.task_icon;
																	  icon = getResources().getDrawable(imageResource);
																}


																String appName = "";
																
																
																if(packageName != null)
																{
																	if(applicationName != "(unknown)")
																	{
																		appName = applicationName;
																	}
																	else if(applicationName == "(unknown)")
																	{
																		appName = packageName;
																	}

																}
																else
																{
																	appName = "System";
																}
																
																 cpu = p.getCpuUsage();
																 ram = p.formatMemUsage(p.getMemUsage());
																 pid = p.getUserName();
																
																
																 //getDocuments : fabrique une liste de documents fixe
																liste.add(new ProcessItem(lineNum, icon, appName, cpu, ram, pid));
																//documents = getDocuments(icon, appName, cpu, ram, pid);
																
																
																			}
																			
																			catch (Exception ex) 
																			{
																				ex.printStackTrace();
																			}
																		}
																		lineNum++;
																	}
																}
															} catch (IOException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
	    							  		            	} catch (Exception ex) {
	    							  		    				ex.printStackTrace();
	    							  		    			} finally {
	    							  		    				if (in != null) {
	    							  		    					try {
	    							  		    						in.close();
	    							  		    					} catch (IOException e) {
	    							  		    						// TODO Auto-generated catch block
	    							  		    						e.printStackTrace();
	    							  		    					}
	    							  		    				}
	    							  		    			}

	    							  		    			compt++;
	    							  		    			documents = liste;
	    							  		    			setList();
	    							  		            }
	    							  		          });
	    							  		        
	    							  		      }
	    							  		    };
	    							  		    new Thread(runnable).start();
	    			
	    	
	    	
  		    
  		    
	    }
	    
	    
	    }


    
    
    public void setList()
    {
    	
    	
    	 adapter = new MobileArrayAdapter(context, documents);

    		
    		
    		
    		Runnable runnable = new Runnable() {
    		      @Override
    		      public void run() {


    		          handler.post(new Runnable() {
    		            @Override
    		            public void run() {
    		            	
    		            	
    		            	//association avec l'adaptateur
    		            	
    		            	mListView.setAdapter(adapter);

    		        		adapter.notifyDataSetChanged();
    		        		
    		            }
    		          });
    		        
    		      }
    		    };
    		    new Thread(runnable).start();
    		  
    		

    }
    
    
    private List<ProcessItem> getDocuments() {
        //List<ProcessItem> liste = new ArrayList<ProcessItem>();

        //ProcessItem doc1 = new ProcessItem(lineNum, icon, name, cpu, ram, pid);
        //liste.add(new ProcessItem(lineNum, icon, appName, cpu, ram, pid));

        return liste;
    }
    
    
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
        //récupération de l’item sélectionné
    	ProcessItem document = (ProcessItem)mListView.getAdapter().getItem(position);

        String name = document.getName();
        pidToKill = document.getPid();
        
        new AlertDialog.Builder(context)
        .setTitle(name + " process")
        .setMessage("Are you sure you want to Kill "  + name + " process?")
        .setPositiveButton("Kill", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	
                kill(pidToKill);
            }
         })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
         .show();
        
        
        
        Runnable runnable = new Runnable() {
		      @Override
		      public void run() {


		          handler.post(new Runnable() {
		            @Override
		            public void run() {
		            	
		            	
		            	
		        		
		            }
		          });
		        
		      }
		    };
		    new Thread(runnable).start();

        //Log.e("HELLO","HELLLLLLOOOOOOO");
        //Toast.makeText(this, "Vous avez cliqué sur : " + name,Toast.LENGTH_LONG).show();
    }
    
    
    public void kill(String pidToKill)
    {
    	Process process = null;
    	
		try {
			process = Runtime.getRuntime().exec("kill -9 " + pidToKill);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public class AdapterHelper {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public void update(ArrayAdapter arrayAdapter, ArrayList<Object> listOfObject){
            arrayAdapter.clear();
            for (Object object : listOfObject){
                arrayAdapter.add(object);
            }
        }
    }

    
    
    
    

    
    @Override
    public void onStart() {
        super.onStart();
        // The activity is about to become visible.
        

    }
    @Override
    public void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        

    }
    @Override
    public void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        t.cancel();
        t.purge();
    }
    @Override
    public void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        t.cancel();
        t.purge();
    }
    
    @Override
    public void onDestroy()
	{
		super.onDestroy();
		t.cancel();
		t.purge();
	}  
}