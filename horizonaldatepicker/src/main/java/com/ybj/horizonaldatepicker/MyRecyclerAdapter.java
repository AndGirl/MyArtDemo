package com.ybj.horizonaldatepicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by 杨阳洋 on 2018/5/30.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private Context context;
    private View preView;
    private ArrayList<Long> list;
    private ArrayList<Integer> mClickList = new ArrayList();

    /**
     * 用来区分radioButton
     */
    private int type;
    /**
     * 展示数据用
     */
    private SimpleDateFormat dateFormatYyyymmdd;

    /**
     * 第一次点击事件标志者第一次的状态被破坏
     */
    private boolean isFirst;

    public MyRecyclerAdapter(Context context, ArrayList<Long> list, int type, boolean isFirst) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.isFirst = isFirst;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.time, null));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        switch (type) {
            case 0:
                dateFormatYyyymmdd = (SimpleDateFormat) MyTimeUtils.DATE_FORMAT_DATE;
                break;
            case 1:
                dateFormatYyyymmdd = (SimpleDateFormat) MyTimeUtils.MONTH_DATE_FORMAT_DATE;
                break;
            case 2:
            case 3:
                dateFormatYyyymmdd = MyTimeUtils.YEAR_DATE_FORMAT_DATE;
                break;
        }

        holder.tv.setText(MyTimeUtils.millis2String(list.get(position), dateFormatYyyymmdd));
        holder.tv.setTag(position);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFirst = false;
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
                onTextView.onClick(position, view, type);
                notifyDataSetChanged();
            }
        });

        if (isFirst) {
            if (holder.tv.getText().equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"),dateFormatYyyymmdd))) {
                holder.tv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            } else {
                holder.tv.setTextColor(context.getResources().getColor(android.R.color.black));
            }
        }else if (mClickList.contains(position) || holder.tv.getText().equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"),dateFormatYyyymmdd))) {
            holder.tv.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            holder.tv.setTextColor(context.getResources().getColor(android.R.color.black));
        }

        Log.e("处理真实数据点击事件数据源的问题", "===============================" + mClickList.size());
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

    public ArrayList<Integer> getClickList() {
        return mClickList;
    }

    public void setList(ArrayList<Long> list) {
        this.list = list;
    }

    public void setType(int type) {
        this.type = type;
    }

    public interface OnTextView {
        void onClick(int position, View view, int type);
    }

    public void setOnTextView(OnTextView onSeckillRecyclerView) {
        this.onTextView = onSeckillRecyclerView;
    }

    private OnTextView onTextView;

    public SimpleDateFormat getDateFormatYyyymmdd() {
        return dateFormatYyyymmdd;
    }
}
