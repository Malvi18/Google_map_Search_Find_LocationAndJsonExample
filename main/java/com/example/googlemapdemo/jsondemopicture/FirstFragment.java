package com.example.googlemapdemo.jsondemopicture;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.googlemapdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    ArrayList<ImgModel> imgModels;
    RecyclerView recycler;
    ImgAdapter imgAdapter;
    private String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
            "location=-33.8670522,151.1957362&radius=500" +
            "&type=restaurant&key=AIzaSyDIU6taDBr1JfDyiLN81-_znZ_IoyiL614";

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first2, container, false);
        imgModels = new ArrayList<ImgModel>();
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        imgAdapter = new ImgAdapter(getActivity(), imgModels);
        recycler.setAdapter(imgAdapter);
        new MyAsynck().execute(url);
        return view;
    }

    private class MyAsynck extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();

            try {
                Response response = okHttpClient.newCall(request).execute();
                String data = response.body().string();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            try {
                JSONObject jsonObject = new JSONObject(data);

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    JSONObject jsonObject2 = jsonObject1.getJSONObject("geometry");

                    JSONObject jsonObject3 = jsonObject2.getJSONObject("location");

                    ImgModel imgModel = new ImgModel();
                    imgModel.setLat(jsonObject3.getString("lat"));
                    imgModel.setLng(jsonObject3.getString("lng"));


                    JSONObject jsonObject4 = jsonObject2.getJSONObject("viewport");
                    JSONObject jsonObject5 = jsonObject4.getJSONObject("northeast");

                    imgModel.setVlat(jsonObject5.getString("lat"));
                    imgModel.setVlng(jsonObject5.getString("lng"));


                    JSONObject jsonObject6 = jsonObject4.getJSONObject("southwest");

                    imgModel.setSlat(jsonObject6.getString("lat"));
                    imgModel.setSlng(jsonObject6.getString("lng"));

                    imgModel.setIcon(jsonObject1.getString("icon"));
                    imgModel.setName(jsonObject1.getString("name"));

                    try {

                        JSONObject jsonObject9 = jsonObject1.getJSONObject("opening_hours");

                        imgModel.setOpen_now(jsonObject9.getString("open_now"));
                    }
                    catch (JSONException e){

                    }

                    JSONArray jsonArray1 = jsonObject1.getJSONArray("photos");


                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject10 = jsonArray1.getJSONObject(j);

                        imgModel.setHeight(jsonObject10.getString("height"));


                    }

                    imgModel.setRating(jsonObject1.getString("rating"));
                    imgModel.setTypes(jsonObject1.getString("types"));
                    imgModel.setVicinity(jsonObject1.getString("vicinity"));

                    imgModels.add(imgModel);
                }
                imgAdapter.notifyDataSetChanged();
            } catch (JSONException e) {

                e.printStackTrace();
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
