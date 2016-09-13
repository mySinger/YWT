package com.example.mysqlite;

import android.content.Context;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.R.attr.id;
import static android.R.id.list;
import static android.R.id.redo;

/**
 * Created by ZengWenZhi on 2016/9/8 0008.
 */

public class MyAdapter extends ArrayAdapter<User> {


    public Context context;
    public List<User> list;
    private ViewHolder viewHolder;
    private DatabaseHelper helper;

    public MyAdapter(Context context, int resource, List<User> list) {
        super(context, resource);
        this.context = context;
        this.list = list;
    }

    public MyAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public User getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewHolder.reduceBtn = (Button) convertView.findViewById(R.id.reduce);
            viewHolder.addBtn = (Button) convertView.findViewById(R.id.add);
            viewHolder.ageTv = (TextView) convertView.findViewById(R.id.age);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final User user = list.get(position);

        viewHolder.img.setImageResource(user.getImgId());
        Toast.makeText(context,user.getImgId(),Toast.LENGTH_SHORT).show();


        viewHolder.name.setText(user.getName());
        viewHolder.ageTv.setText(user.getAge()+"");

        Button addBtn = viewHolder.addBtn;
        addBtn.setOnClickListener(new View.OnClickListener() {

            private DatabaseHelper helper;

            @Override
            public void onClick(View v) {
                helper = new DatabaseHelper(context);
                user.setAge(user.getAge()+1);
                helper.updateAUser(user);
                Toast.makeText(context, helper.readAUser(user.getId()).toString(),Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        Button reduceBtn = viewHolder.reduceBtn;
        reduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new DatabaseHelper(context);
                user.setAge(user.getAge()-1);
                helper.updateAUser(user);
                Toast.makeText(context, helper.readAUser(user.getId()).toString(),Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder{

        Button reduceBtn ;
        Button addBtn;
        TextView ageTv;
        ImageView img;
        TextView name;
    }
}



