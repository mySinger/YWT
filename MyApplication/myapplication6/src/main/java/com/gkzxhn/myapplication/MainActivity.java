package com.gkzxhn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.lv);
        ArrayList<Goods> goodsArrayList = new ArrayList<>();
        goodsArrayList.add(new Goods("sc", "sd", "sds"));
        goodsArrayList.add(new Goods("sc", "ssdd", "sdsdss"));
        goodsArrayList.add(new Goods("sc", "ssdsd", "sds"));
        MyAdapter myAdapter = new MyAdapter(this,R.layout.item,goodsArrayList);
        lv.setAdapter(myAdapter);
    }
}
