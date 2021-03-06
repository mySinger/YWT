package com.gkzxhn.ywt_gkzx.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.R;

import java.util.List;

import static android.R.attr.handle;
import static android.media.CamcorderProfile.get;
import static java.lang.Integer.parseInt;

/**
 * Created by ZengWenZhi on 2016/8/29 0029.
 * 电子商务模块的商品展示listview的arrayadapter适配器
 */

public class MyAdapter extends ArrayAdapter<Goods> {
    public Context context;
    private ViewHolder viewHolder;

    public List<Goods> list;


    @Override
    public Goods getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public MyAdapter(Context context, int resource, List<Goods> list) {
        super(context, resource);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopping_content, null);
            viewHolder = new ViewHolder();
            viewHolder.goodsReduce = (ImageView) convertView.findViewById(R.id.goods_reduce);
            viewHolder.goodsAdd = (ImageView) convertView.findViewById(R.id.goods_add);
            viewHolder.name = (TextView) convertView.findViewById(R.id.goods_name);
            viewHolder.introduce = (TextView) convertView.findViewById(R.id.goods_introduce);
            viewHolder.price = (TextView) convertView.findViewById(R.id.goods_price);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.goods_image);
            viewHolder.goodsNum = (TextView) convertView.findViewById(R.id.goods_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Goods goods = list.get(position);
        viewHolder.image.setImageResource(goods.getImage());
        viewHolder.name.setText(goods.getName());
        viewHolder.price.setText(goods.getPrice());
        viewHolder.introduce.setText(goods.getIntroduce());
        viewHolder.goodsNum.setText(Integer.toString(goods.getNum()));

        //获取所选商品的减按钮
        ImageView goodsReduce = viewHolder.goodsReduce;
        goodsReduce.setOnClickListener(new View.OnClickListener() {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            @Override
            public void onClick(View v) {
                int num = goods.getNum()-1;
                goods.setNum(num);
                databaseHelper.updateAGoods(goods);
                notifyDataSetChanged();
            }
        });

        //获取所选商品的增按钮
        ImageView goodsAdd = (ImageView) convertView.findViewById(R.id.goods_add);
        goodsAdd.setOnClickListener(new View.OnClickListener() {

            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            @Override
            public void onClick(View v) {
                int num = goods.getNum()+1;
                goods.setNum(num);
                databaseHelper.updateAGoods(goods);
                //当有数据变化时，通知ListView刷新UI
                notifyDataSetChanged();
            }
        });
        Log.e("adapter", "666666666666");
        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView introduce;
        ImageView image;
        TextView price;
        ImageView goodsReduce;
        TextView goodsNum;
        ImageView goodsAdd;
    }
}
