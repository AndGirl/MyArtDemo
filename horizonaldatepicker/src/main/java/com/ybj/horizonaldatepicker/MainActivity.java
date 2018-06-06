package com.ybj.horizonaldatepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, MyRecyclerAdapter.OnTextView {
    private RadioGroup radioGroup;
    RadioButton radioDay, radioMonth, radioYear, radioTotal;
    private RecyclerView rl;
    private MyRecyclerAdapter adapter;
    private ArrayList<Long> list = new ArrayList<Long>();
    /**
     * 0: 天  1:月 2：年 3：寿命期
     */
    private int type = 0;
    private ArrayList<Long> year;
    private ArrayList<Long> month;
    private ArrayList<Long> day;
    private GetDateList getDateList;
    private long realLong;
    /**
     * 是否是第一次进入
     */
    private boolean isFirst = true;
    private LinearLayoutManager layoutManager;
    private SimpleDateFormat dateFormatYyyymmdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
        radioDay.setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        adapter.setOnTextView(this);
    }

    private void initData() {

        //获取真实数据
        getDateList = new GetDateList();
        //获取当前年份数据(寿命期与年公用同一份数据)
        year = getDateList.getYear();
        //第一次进来展示最新数据
        month = getDateList.getMonth(year.get(year.size() - 1));
        //第一次进来展示当前天数
        day = getDateList.getDay(year.get(year.size() - 1));
        adapter = new MyRecyclerAdapter(this, list, type, isFirst);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rl.setLayoutManager(layoutManager);
        rl.setAdapter(adapter);

        //保存数据
        SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));

    }

    /**
     * 获取真实数据
     *
     * @param year
     * @param month
     * @param day
     */
    private void getRealDate(ArrayList<Long> year, ArrayList<Long> month, ArrayList<Long> day) {
        switch (type) {
            case 2:
            case 3:
                list.addAll(year);
                break;
            case 1:
                list.addAll(month);
                break;
            case 0:
                list.addAll(day);
                break;
        }
    }

    /**
     * 这里也会有联网请求
     *
     * @param radioGroup
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_day:
                type = 0;
                list.clear();
                getRealDate(year, month, day);
                adapter.setList(list);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();

                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));
                    layoutManager.scrollToPositionWithOffset(day.size() - 1, 0);
                } else {
                    for (int i = 0; i < day.size(); i++) {
                        if (MyTimeUtils.millis2String(day.get(i), getSimpleDateFormat(type))
                                .equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"), getSimpleDateFormat(type)))) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }
                //先处理第一次进来OK的状态

                break;
            case R.id.radio_month:
                type = 1;
                list.clear();
                getRealDate(year, month, day);
                adapter.setList(list);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));
                    layoutManager.scrollToPositionWithOffset(month.size() - 1, 0);
                } else {
                    for (int i = 0; i < month.size(); i++) {
                        if (MyTimeUtils.millis2String(month.get(i), getSimpleDateFormat(type))
                                .equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"), getSimpleDateFormat(type)))) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }
                break;
            case R.id.radio_year:
                type = 2;
                list.clear();
                getRealDate(year, month, day);
                adapter.setList(list);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));
                    layoutManager.scrollToPositionWithOffset(year.size() - 1, 0);
                } else {
                    for (int i = 0; i < year.size(); i++) {
                        if (MyTimeUtils.millis2String(year.get(i), getSimpleDateFormat(type))
                                .equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"), getSimpleDateFormat(type)))) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }

                break;
            case R.id.radio_total:
                type = 3;
                list.clear();
                getRealDate(year, month, day);
                adapter.setList(list);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();
                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));
                    layoutManager.scrollToPositionWithOffset(year.size() - 1, 0);
                } else {
                    for (int i = 0; i < year.size(); i++) {
                        if (MyTimeUtils.millis2String(year.get(i), getSimpleDateFormat(type))
                                .equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"), getSimpleDateFormat(type)))) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }
                break;
        }
    }

    /**
     * 发生了点击事件坐标的位置就要发生改变
     *
     * @param position
     * @param view
     * @param type
     */
    @Override
    public void onClick(int position, View view, int type) {
        //这里主要是为了方便真实数据请求
        isFirst = false;
        if (type == 3 || type == 2) {
            realLong = year.get(position);
            month = getDateList.getMonth(year.get(position));
            day = getDateList.getDay(year.get(position));
        } else if (type == 1) {
            realLong = month.get(position);
            day = getDateList.getDay(month.get(position));
        } else if (type == 0) {
            realLong = day.get(position);
        }
        //保存数据
        SPUtils.getInstance().put("latestTime", realLong);
        Log.e("TAG", "点击事件获取到的具体事件 ： =======" + MyTimeUtils.millis2String(realLong, getSimpleDateFormat(type)));


        /**
         * 1.点击日： 不会对年、月造成任何影响
         * 2.点击月：当前天数不足的时候会影响日的展示，年不会有任何影响
         * 3.点击年：当前月份不足的时候回影响月份的展示，对日期的影响
         * （eg:2018-05-31 切换到日的时候数据正常日期数据正常展示，标记位不会有问题）
         */
    }

    public SimpleDateFormat getSimpleDateFormat(int type) {
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
        return dateFormatYyyymmdd;
    }
}
