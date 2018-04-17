package com.example.googlemapdemo.Jsonexample;


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
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    String url = "https://maps.googleapis.com" +
            "/maps/api/place/radarsearch/json?" +
            "location=48.859294,2.347589&radius=5000&type=cafe&key=" +
            "AIzaSyDIU6taDBr1JfDyiLN81-_znZ_IoyiL614";

    private ArrayList<ApiModel> apiModelList;
    private RecyclerView recyclerView;
    private ApiAdapater apiAdapater;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        apiModelList = new ArrayList<ApiModel>();
        recyclerView = view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        apiAdapater = new ApiAdapater(getActivity(), apiModelList);

        recyclerView.setAdapter(apiAdapater);


        new JsonDataClass().execute(url);

        return view;
    }

    private class JsonDataClass extends AsyncTask<String, Void, String> {
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

                    ApiModel apiModel = new ApiModel();
                    apiModel.setLat(jsonObject3.getString("lat"));
                    apiModel.setLng(jsonObject3.getString("lng"));

                    apiModel.setPlace_id(jsonObject1.getString("place_id"));


                    apiModelList.add(apiModel);
                }

                apiAdapater.notifyDataSetChanged();

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
