package com.ybj.horizonaldatepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    RadioButton radioDay, radioMonth, radioYear, radioTotal;
    private RecyclerView rl;
    private MyRecyclerAdapter adapter;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
        radioDay.setChecked(true);
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.switch_icon);
        radioDay = (RadioButton) findViewById(R.id.radio_day);
        radioMonth = (RadioButton) findViewById(R.id.radio_month);
        radioYear = (RadioButton) findViewById(R.id.radio_year);
        radioTotal = (RadioButton) findViewById(R.id.radio_total);
        rl = (RecyclerView) findViewById(R.id.rl);

    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(this);
        adapter.setOnTextView(new MyRecyclerAdapter.OnTextView() {
            @Override
            public void onClick(int position, View view) {
                //这里主要是为了方便真实数据请求
            }
        });
    }

    private void initData() {
        for (int i = 0 ;i < 30 ; i ++){
            list.add("位置 ; " + i);
        }
        adapter = new MyRecyclerAdapter(this,list);
        rl.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rl.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case  R.id.radio_day:

                break;
            case R.id.radio_month:

                break;
            case R.id.radio_year:

                break;
            case R.id.radio_total:

                break;
        }
    }
}
