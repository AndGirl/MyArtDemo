package com.ybj.horizonaldatepicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 杨阳洋 on 2018/5/30.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private Context context;
    private View preView;
    private ArrayList<String> list;
    private int i = 1;
    private ArrayList<Integer> mClickList = new ArrayList();

    public MyRecyclerAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.time, null));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String s = list.get(position);
        holder.tv.setText(s);
        holder.tv.setTag(position);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preView != null && position != (Integer) (preView.getTag())) {//不是同一个点
                    mClickList.add(position);
                    mClickList.remove(0);
                    ((TextView) preView).setTextColor(context.getResources().getColor(android.R.color.black));
                    holder.tv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
                } else {
                    if (!mClickList.contains(holder.tv.getTag())) {
                        mClickList.add(position);
                    }
                    holder.tv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
                }
                preView = view;
                onTextView.onClick(position, view);
            }
        });

        if (mClickList.contains(position)) {
            holder.tv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            holder.tv.setTextColor(context.getResources().getColor(android.R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public interface OnTextView {
        void onClick(int position, View view);
    }

    public void setOnTextView(OnTextView onSeckillRecyclerView) {
        this.onTextView = onSeckillRecyclerView;
    }

    private OnTextView onTextView;

    public ArrayList<Integer> getClickList() {
        return mClickList;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

}
