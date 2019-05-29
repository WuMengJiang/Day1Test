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
import com.example.dell_pc.day1test.bean.BeanHost;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    private ArrayList<BeanHost.NewslistBean> beans = new ArrayList<>();
    private Context context;

    public RecAdapter(Context context) {
        this.context = context;
    }

    public void setBeans(ArrayList<BeanHost.NewslistBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.rec_item, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv.setText("["+beans.get(i).getDescription()+"]"+beans.get(i).getTitle());
        Glide.with(context).load(beans.get(i).getPicUrl()).override(800,200).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView image;
        private  TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
