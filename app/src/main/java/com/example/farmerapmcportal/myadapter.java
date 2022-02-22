package com.example.farmerapmcportal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<model> dataholder;

    public myadapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.dpname.setText(dataholder.get(position).getProname());
        holder.dprice.setText(dataholder.get(position).getPprice());
        holder.davail.setText(dataholder.get(position).getPavail());

    }

    @Override
    public int getItemCount() {

        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView dpname, dprice, davail;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dpname = (TextView)itemView.findViewById(R.id.displayproductname);
            dprice = (TextView)itemView.findViewById(R.id.displayproductPrice);
            davail = (TextView)itemView.findViewById(R.id.displayproductavail);

        }
    }
}
