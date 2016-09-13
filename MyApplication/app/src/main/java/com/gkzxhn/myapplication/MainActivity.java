package com.gkzxhn.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ImageView btn_back;
    private SlideMenu slideMenu;
    private List<News> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
        initData();
        ListView lv = (ListView) findViewById(R.id.menu_lv);
        lv.setAdapter(new MyAdapter(MainActivity.this,R.layout.layout_item_news,list));


        btn_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu.switchMenu();
            }
        });
    }

    public void initData(){
        list = new ArrayList<News>();

        News news1 = new News();
        news1.setImg(R.drawable.tab_news);
        news1.setContent("新闻");
        list.add(news1);

        News news2 = new News();
        news2.setImg(R.drawable.tab_read);
        news2.setContent("订阅");
        list.add(news2);

        News news3 = new News();
        news3.setImg(R.drawable.tab_ties);
        news3.setContent("跟帖");
        list.add(news3);

        News news4 = new News();
        news4.setImg(R.drawable.tab_pics);
        news4.setContent("图片");
        list.add(news4);

        News news5 = new News();
        news5.setImg(R.drawable.tab_ugc);
        news5.setContent("话题");
        list.add(news5);

        News news6 = new News();
        news6.setImg(R.drawable.tab_vote);
        news6.setContent("投票");
        list.add(news6);

        News news7 = new News();
        news7.setImg(R.drawable.tab_focus);
        news7.setContent("聚合阅读");
        list.add(news7);

        News news8 = new News();
        news8.setImg(R.drawable.tab_news);
        news8.setContent("新闻");
        list.add(news8);

        News news88 = new News();
        news88.setImg(R.drawable.tab_read);
        news88.setContent("订阅");
        list.add(news88);

        News news888 = new News();
        news888.setImg(R.drawable.tab_ties);
        news888.setContent("跟帖");
        list.add(news888);

        News news77 = new News();
        news77.setImg(R.drawable.tab_pics);
        news77.setContent("图片");
        list.add(news77);

        News news777 = new News();
        news777.setImg(R.drawable.tab_ugc);
        news777.setContent("话题");
        list.add(news777);

        News news666 = new News();
        news666.setImg(R.drawable.tab_vote);
        news666.setContent("投票");
        list.add(news666);

        News news555 = new News();
        news555.setImg(R.drawable.tab_focus);
        news555.setContent("聚合阅读");
        list.add(news555);
    }

}

