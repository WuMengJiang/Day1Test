package com.example.dell_pc.day1test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.bean.GridViewBeans;

import java.util.ArrayList;

public class GradViewAdapter extends BaseAdapter {
    private ArrayList<GridViewBeans> list;
    private Context context;

    public GradViewAdapter(ArrayList<GridViewBeans> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
             holder = new ViewHolder();
             convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            holder.tv= convertView.findViewById(R.id.tv);
             holder.iv=convertView.findViewById(R.id.iv);
             convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position).getText());
        Glide.with(context).load(list.get(position).getImage()).into(holder.iv);
        return convertView;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }

}
