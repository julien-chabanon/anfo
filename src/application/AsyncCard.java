package application;

import android.content.Context;
import utils.getCPU;
import android.os.AsyncTask;

public class AsyncCard extends AsyncTask<Void, Integer, Void>
{
	Context context;
	Long when;
	

	    public AsyncCard(Context context) {
	        this.context = context;
	        when= getCPU.when;
	    }


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//Toast.makeText(, "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onProgressUpdate(Integer... values){
		super.onProgressUpdate(values);
		// Mise à jour de la ProgressBar

	}

	@Override
	protected Void doInBackground(Void... arg0) {
		
		
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
	}
}