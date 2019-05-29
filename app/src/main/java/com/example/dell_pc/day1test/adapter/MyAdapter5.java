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

public class MyAdapter5 extends RecyclerView.Adapter<MyAdapter5.ViewHolder> {
    private ArrayList<Stu.NewslistBean> list;
    private Context context;

    public MyAdapter5(ArrayList<Stu.NewslistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item5, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stu.NewslistBean bean = list.get(position);
        //RequestOptions requestOptions = RequestOptions.bitmapTransform(new RoundedCorners(15));
        Glide.with(context).load(bean.getPicUrl()).into(holder.iv);
        holder.tv1.setText(bean.getDescription());
        holder.tv2.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv1;
        private TextView tv2;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }
}
