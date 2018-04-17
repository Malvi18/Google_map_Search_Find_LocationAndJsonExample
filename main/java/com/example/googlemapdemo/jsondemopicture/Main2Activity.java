package com.example.googlemapdemo.jsondemopicture;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.googlemapdemo.R;

public class Main2Activity extends AppCompatActivity {


   /* https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=AIzaSyDIU6taDBr1JfDyiLN81-_znZ_IoyiL614*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Fragment fragment = new FirstFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.Frame_Layout, fragment)
                .commit();
    }
}
