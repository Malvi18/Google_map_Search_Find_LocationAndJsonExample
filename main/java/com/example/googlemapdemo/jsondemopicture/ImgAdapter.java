package com.example.googlemapdemo.jsondemopicture;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.googlemapdemo.R;

import java.util.ArrayList;


/**
 * Created by Dell on 13-04-2018.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ImgModel> imgModels;

    public ImgAdapter(Context context, ArrayList<ImgModel> imgModels) {
        this.context = context;
        this.imgModels = imgModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.img_row_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MyViewHolder myViewHolder=(MyViewHolder)holder;

        ImgModel imgModel=imgModels.get(position);

        myViewHolder.lat.setText("Lat::"+imgModel.getLat());
        myViewHolder.lng.setText("Lng::"+imgModel.getLng());

        myViewHolder.Vlat.setText("VLat::"+imgModel.getVlat());
        myViewHolder.Vlng.setText("VLng::"+imgModel.getVlng());

        myViewHolder.Slat.setText("SLat::"+imgModel.getSlat());
        myViewHolder.Slng.setText("SLng::"+imgModel.getSlng());

        //myViewHolder.icon.setImageURI();

        myViewHolder.name.setText(imgModel.getName());

        myViewHolder.open_now.setText("Opening Hours::"+imgModel.getOpen_now());

        myViewHolder.height.setText("Height::"+imgModel.getHeight());

        myViewHolder.rating.setText("Rating::"+imgModel.getRating());

        myViewHolder.types.setText("Type::"+imgModel.getTypes());

        myViewHolder.vicinity.setText("Vicinity::"+imgModel.getVicinity());

        Glide.with(context).load(imgModel.getIcon())
                .into(myViewHolder.icon);

        myViewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Fragment fragment=new SecondFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }
        });
    }


    @Override
    public int getItemCount() {
        return imgModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lat;
        TextView lng;
        TextView Vlat;
        TextView Vlng;
        TextView Slat;
        TextView Slng;
        ImageView icon;
        TextView name;
        TextView open_now;
        TextView height;
        TextView rating;
        TextView types;
        TextView vicinity;

        public MyViewHolder(View itemView) {
            super(itemView);

            lat=itemView.findViewById(R.id.lat);
            lng=itemView.findViewById(R.id.lng);

            Vlat=itemView.findViewById(R.id.Vlat);
            Vlng=itemView.findViewById(R.id.Vlng);

            Slat=itemView.findViewById(R.id.Slat);
            Slng=itemView.findViewById(R.id.Slng);

            icon=itemView.findViewById(R.id.icon);

            name=itemView.findViewById(R.id.name);

            open_now=itemView.findViewById(R.id.open_now);

            height=itemView.findViewById(R.id.height);
            rating=itemView.findViewById(R.id.rating);

            types=itemView.findViewById(R.id.types);
            vicinity=itemView.findViewById(R.id.vicinity);

        }
    }
}
