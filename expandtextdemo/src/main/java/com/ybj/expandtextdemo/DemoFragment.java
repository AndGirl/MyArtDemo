package com.ybj.expandtextdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {

    private RecyclerView mRecycleView;

    private String[] mListStr = {"姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com",
            "姓名：雨松MOMO", "性别：男", "年龄：25", "居住地：北京", "邮箱：xuanyusong@gmail.com"};

    public DemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mRecycleView.setAdapter(new BaseRecyclerViewAdapter(getActivity(),mListStr));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, null);
        mRecycleView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

}
