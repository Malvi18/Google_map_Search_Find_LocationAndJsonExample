package com.example.googlemapdemo.Jsonexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.googlemapdemo.R;

import java.util.ArrayList;

/**
 * Created by Dell on 13-04-2018.
 */

public class ApiAdapater extends RecyclerView.Adapter<ApiAdapater.MyViewHolder> {

    private Context context;
    private ArrayList<ApiModel> apiModels;

    public ApiAdapater(Context context, ArrayList<ApiModel> apiModels) {
        this.context = context;
        this.apiModels = apiModels;
    }

    @Override
    public ApiAdapater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context)
                .inflate(R.layout.row_layout,parent,false);
       // MyViewHolder myViewHolder=new MyViewHolder(view);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ApiAdapater.MyViewHolder holder, int position) {
        MyViewHolder myViewHolder=(MyViewHolder)holder;
        ApiModel apiModel=apiModels.get(position);

        myViewHolder.lat.setText(apiModel.getLat());
        myViewHolder.lng.setText(apiModel.getLng());
        myViewHolder.place_id.setText(apiModel.getPlace_id());



    }

    @Override
    public int getItemCount() {
        return apiModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lat;
        TextView lng;
        TextView place_id;
        public MyViewHolder(View itemView) {
            super(itemView);

            lat=itemView.findViewById(R.id.lat);
            lng=itemView.findViewById(R.id.lng);
            place_id=itemView.findViewById(R.id.place_id);


        }


    }
}
