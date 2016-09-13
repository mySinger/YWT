package com.example.mysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.mysqlite.R.id.add;

public class MainActivity extends AppCompatActivity {


    private ListView lv;
    private List<User> list;
    private DatabaseHelper helper;
    private DatabaseHelper helper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initData();

        initView();
    }

    /*public void test(View v) {
        helper = new DatabaseHelper(this);
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            helper.insertAUser(user);
            Toast.makeText(this, user.toString() + "", Toast.LENGTH_SHORT).show();
        }
    }*/


    public void add() {
        helper1 = new DatabaseHelper(this);
        //通过资源名获取资源Id
       /* int imgID = getResources().getIdentifier("img", "drawable", "com.example.mysqlite");
        Toast.makeText(this, imgID + "", Toast.LENGTH_SHORT).show();
        Log.e("imgID",imgID+"");*/

        User user = new User();
        user.setName("lisa");
        user.setAge(230);
        user.setImgId(R.drawable.img);
       /* Toast.makeText(this, R.drawable.img + "", Toast.LENGTH_SHORT).show();
        Log.e("imgID666",R.drawable.img + "");*/

        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();

        helper1.insertAUser(user);//插入一条数据
        /*Log.e("TAG", helper.readAllUser().toString());*/

        Toast.makeText(this, "增" + helper1.readAllUser().toString(), Toast.LENGTH_LONG).show();
    }

    /*public void delete(View v){
        Toast.makeText(this,"增加、删除",Toast.LENGTH_LONG).show();
        DatabaseHelper helper = new DatabaseHelper(this);
        User user1 = helper.readAUser(1);            //查找id为1的用户
        Toast.makeText(this,user1+"", Toast.LENGTH_SHORT).show();

        user1.setAge(30);                            //将年龄改为30
        Toast.makeText(this,user1+"", Toast.LENGTH_SHORT).show();

        helper.updateAUser(user1);                    //更新数据库
        Toast.makeText(this,helper.readAUser(1)+"", Toast.LENGTH_SHORT).show();

        helper.deleteAUser(user1.getId());            //删除user1
        Toast.makeText(this,helper.readAllUser()+"", Toast.LENGTH_SHORT).show();  }*/

    public void initView(){
        add();
        Toast.makeText(this, helper1.readAllUser().toString()+"6666", Toast.LENGTH_SHORT).show();

        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.img,"zwz",24));
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter(this,R.layout.item,helper1.readAllUser()));
    }



}

