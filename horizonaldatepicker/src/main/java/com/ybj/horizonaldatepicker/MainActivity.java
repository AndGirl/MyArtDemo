package com.ybj.horizonaldatepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Calendar;

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

        long l = System.currentTimeMillis();
        TimeUtils.getTime(l,TimeUtils.MONTH_DATE_FORMAT_DATE);

        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH) + 1;
        long l1 = MyTimeUtils.string2Millis("2018-01-01" , MyTimeUtils.DATE_FORMAT_DATE);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case  R.id.radio_day:
                list.clear();
                for (int i = 0 ;i < 30 ; i ++){
                    list.add("位置 ; " + i);
                }
                adapter.setList(list);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.radio_month:
                list.clear();
                for (int i = 30 ;i < 60 ; i ++){
                    list.add("位置 ; " + i);
                }
                adapter.setList(list);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.radio_year:
                list.clear();
                for (int i = 60 ;i < 90 ; i ++){
                    list.add("位置 ; " + i);
                }
                adapter.setList(list);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.radio_total:
                list.clear();
                for (int i = 90 ;i < 120 ; i ++){
                    list.add("位置 ; " + i);
                }
                adapter.setList(list);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
