package com.example.dell_pc.day1test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.bean.Stu;

import java.util.ArrayList;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.ViewHolder> {
    private ArrayList<Stu.NewslistBean> list;
    private Context context;

    public MyAdapter4(ArrayList<Stu.NewslistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item4, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stu.NewslistBean bean = list.get(position);
        //RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(15));
        Glide.with(context).load(bean.getPicUrl()).into(holder.iv);
        holder.tv.setText(bean.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
