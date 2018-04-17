package com.example.googlemapdemo.Jsonexample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.googlemapdemo.R;

public class MainActivity extends AppCompatActivity {

    /*https://maps.googleapis.com/maps/api/place/radarsearch/json?location=48.859294,2.347589&radius=5000&type=cafe&key=AIzaSyDIU6taDBr1JfDyiLN81-_znZ_IoyiL614*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment=new FirstFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_Layout,fragment)
                .commit();
    }
}
