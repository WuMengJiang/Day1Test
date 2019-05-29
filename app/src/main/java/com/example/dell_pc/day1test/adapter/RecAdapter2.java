package com.example.dell_pc.day1test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell_pc.day1test.R;
import com.example.dell_pc.day1test.bean.BeanUrl;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter2 extends RecyclerView.Adapter<RecAdapter2.ViewHolder> {
    private ArrayList<BeanUrl.ResultBean> beans = new ArrayList<>();
    private Context context;

    public void setBeans(ArrayList<BeanUrl.ResultBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    public RecAdapter2(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public RecAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rec_item2, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter2.ViewHolder holder, int i) {
            holder.tv1.setText("["+beans.get(i).getTop_comments_name()+"]"+beans.get(i).getTop_comments_content());
            holder.tv2.setText(beans.get(i).getComment());
            holder.tv3.setText(beans.get(i).getForward());
            holder.tv4.setText(beans.get(i).getDown());
            holder.tv5.setText("$"+beans.get(i).getUp()+"元");
        Glide.with(context).load(beans.get(i).getHeader()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView iv;
        private  TextView tv1;
        private  TextView tv2;
        private  TextView tv3;
        private  TextView tv4;
        private  TextView tv5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
        }
    }
}
