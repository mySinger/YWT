package com.gkzxhn.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZengWenZhi on 2016/8/17 0017.
 */

public class MyAdapter extends ArrayAdapter<News> {
    public Context context;
    public List<News> list;

    public MyAdapter(Context context, int resource, List<News> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_item_news, null);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.img = (ImageView) convertView.findViewById(R.id.iv_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.img.setBackgroundResource(list.get(position).getImg());
        vh.content.setText(list.get(position).getContent());
        return convertView;
    }

    public class ViewHolder {
        public TextView content;
        public ImageView img;
    }

}

