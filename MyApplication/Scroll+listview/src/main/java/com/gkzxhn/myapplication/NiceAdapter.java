package com.gkzxhn.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZengWenZhi on 2016/8/30 0030.
 */

public class NiceAdapter extends ArrayAdapter<Person> {
    public List<Person> personList;
    public Context context;


    public NiceAdapter(Context context, int resource, List<Person> personList) {
        super(context, resource);
        this.personList = personList;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Person getItem(int position) {
        return personList.get(position);
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewHolder.name = (ImageView) convertView.findViewById(R.id.name);
            viewHolder.age = (TextView) convertView.findViewById(R.id.age);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setImageResource(personList.get(position).getName());
        viewHolder.age.setText(personList.get(position).getAge());
        return convertView;
    }

    class ViewHolder{
        public ImageView name;
        public TextView age;
    }
}
