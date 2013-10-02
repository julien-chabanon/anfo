package application;

import MaximumWidget.com.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MobileArrayAdapter extends ArrayAdapter {
    List<ProcessItem> mDocuments;
    LayoutInflater mInflater;



    static class ViewHolder {
        public TextView nameView;
        public TextView cpuView;
        public TextView ramView;
        public TextView pidView;
        public ImageView iconeView;
    }


    /**
     * Constructeur
     */
    public MobileArrayAdapter(Context context, List<ProcessItem> documents) {
        super(context, R.layout.list, documents);
        this.mDocuments = documents;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void notifyDataSetChanged() {
        Log.e("barcode","Actualisaation de la liste notifyDataSetChanged");
        super.notifyDataSetChanged();
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {



        ViewHolder holder;

        View rowView = convertView;

        if (rowView == null) {
            //création de l’objet View à partir de la ressource Layout
            rowView = mInflater.inflate(R.layout.list, null);



            holder = new ViewHolder();
            
            holder.iconeView = (ImageView) rowView.findViewById(R.id.imageViewIcon);
            
            holder.nameView = (TextView) rowView.findViewById(R.id.textViewItem1);

            holder.cpuView = (TextView) rowView.findViewById(R.id.textViewItem2);

            holder.ramView = (TextView) rowView.findViewById(R.id.textViewItem3);
            
            holder.pidView = (TextView) rowView.findViewById(R.id.textViewItem4);

            rowView.setTag(holder);
        }else {
            holder = (ViewHolder) rowView.getTag();
        }


        //récupération du document identifié par sa position dans la liste
        ProcessItem document = mDocuments.get(position);


        //Valorisation du Texte1 de la Vue avec le nom du document
        holder.nameView.setText(document.getName());
        holder.cpuView.setText(document.getCpu());
        holder.ramView.setText(document.getRam());
        holder.pidView.setText(document.getPid());


        //Affichage de l’icône correspondante
        holder.iconeView.setImageDrawable(document.getIcone());



        return rowView;
    }
    
    
    @Override
    public int getCount() {
        return mDocuments.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
}