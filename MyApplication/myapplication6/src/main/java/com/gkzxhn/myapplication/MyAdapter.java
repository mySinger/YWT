package com.gkzxhn.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZengWenZhi on 2016/8/29 0029.
 */

public class MyAdapter extends ArrayAdapter<Goods> {


    private View view;
    private List<Goods> goodsList;

    public MyAdapter(Context context, int resource, List<Goods> goodsList) {
        super(context, resource);
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Goods getItem(int position) {

        return goodsList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.introduce = (TextView) convertView.findViewById(R.id.introduce);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Goods goods = goodsList.get(position);
        viewHolder.name.setText(goods.getName());
        viewHolder.price.setText(goods.getPrice());
        viewHolder.introduce.setText(goods.getIntroduce());
        return convertView;
    }

    class ViewHolder{
        public TextView name;
        public TextView price;
        public TextView introduce;

    }
}
