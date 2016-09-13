package com.gkzxhn.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> personArrayList;
    public  ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.parentScrollView);
        initData();
        ListView lv = (ListView) findViewById(R.id.lv);
        NiceAdapter niceAdapter = new NiceAdapter(this,R.layout.item,personArrayList);
        lv.setAdapter(niceAdapter);
       // setListViewHeightBasedOnChildren(lv);
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void initData(){
        personArrayList = new ArrayList<>();
            personArrayList.add(new Person(R.drawable.beizi1,"241"));
            personArrayList.add(new Person(R.drawable.beizi1,"241"));
            personArrayList.add(new Person(R.drawable.beizi1,"242"));
            personArrayList.add(new Person(R.drawable.beizi1,"243"));
            personArrayList.add(new Person(R.drawable.beizi1,"244"));
            personArrayList.add(new Person(R.drawable.beizi1,"245"));


    }


    public void setListViewHeightBasedOnChildren(ListView listView) {

          // 获取ListView对应的Adapter

          ListAdapter listAdapter = listView.getAdapter();
         if (listAdapter == null) {

               return;

               }

           int totalHeight = 0;

          for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目

              View listItem = listAdapter.getView(i, null, listView);

               listItem.measure(0, 0); // 计算子项View 的宽高

                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

               }

           ViewGroup.LayoutParams params = listView.getLayoutParams();

           params.height = totalHeight
            + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

           // listView.getDividerHeight()获取子项间分隔符占用的高度

           // params.height最后得到整个ListView完整显示需要的高度

           listView.setLayoutParams(params);

          }
}
