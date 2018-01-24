package com.ybj.expandtextdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * Created by 杨阳洋 on 2018/1/24.
 */

public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private String [] listStr;
    private LayoutInflater mLayoutInflater;


    public BaseRecyclerViewAdapter(Context context, String[] listStr) {
        this.mContext = context;
        this.listStr = listStr;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.text_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(listStr[position].toString());
    }

    @Override
    public int getItemCount() {
        return listStr.length - 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ExpandableTextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view);
        }
    }

}
