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
    //    private ArrayList<Long> year;
//    private ArrayList<Long> month;
//    private ArrayList<Long> day;
    private GetDateList getDateList;
    private long realLong;
    /**
     * 是否是第一次进入
     */
    private boolean isFirst = true;
    private LinearLayoutManager layoutManager;
    private SimpleDateFormat dateFormatYyyymmdd;
    private Calendar instance = Calendar.getInstance();

    //存放真实年月份
    private int inputYear, inputMonth, inputDay;
    private int monthYear, monthMonth, monthDay;
    /**
     * 临时存放数据
     */
    private ArrayList<Long> saveMonth;

    /**
     * 存放数据
     */
    private ArrayList<Long> preDays;
    private ArrayList<Long> preMonths;
    private ArrayList<Long> realDate;
    private ArrayList<Long> preDate;


    /**
     * 获取当前年月日数据
     */
    private ArrayList<String> daysByYearAndMonth;
    private ArrayList<String> monthsByYear;
    private ArrayList<String> years;
    private ArrayList<String> realDate2;
    private ArrayList<String> list2 = new ArrayList<String>();

    /**
     * 记录年月日
     */
    private String day, year, month;
    private TestDate testDate;
    private String mOriginDay;
    private String mOriginYear;
    private String mOriginMonth;

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

        testDate = new TestDate();
        years = (ArrayList<String>) testDate.getYears();
        monthsByYear = (ArrayList<String>) testDate.getMonthsByYear(years.get(years.size() - 1));
        daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(years.get(years.size() - 1),
                Integer.valueOf(monthsByYear.get(monthsByYear.size() - 1).split("-")[1]));

//        //存一份真实年月份的数据
//        long nowMills = MyTimeUtils.getNowMills();
//        instance.setTimeInMillis(nowMills);
//        //这里直接返回真实月份就行，不用对UI进行考虑(针对Day处理)
//        inputYear = instance.get(Calendar.YEAR);
//        inputMonth = instance.get(Calendar.MONTH);
//        inputDay = instance.get(Calendar.DAY_OF_MONTH);
//
//        //这里直接返回真实月份就行，不用对UI进行考虑(针对Month处理)
//        monthYear = instance.get(Calendar.YEAR);
//        monthMonth = instance.get(Calendar.MONTH) + 1;
//        monthDay = instance.get(Calendar.DAY_OF_MONTH);
//        monthYear = monthYear - 1;//为拼接做准备
//
//        //获取真实数据
//        getDateList = new GetDateList();
//        //获取当前年份数据(寿命期与年公用同一份数据)
//        year = getDateList.getYear();
//        //第一次进来展示最新数据
//        month = getDateList.getMonth(year.get(year.size() - 1));
//        saveMonth = new ArrayList<Long>();
//        saveMonth.addAll(month);
//        //第一次进来展示当前天数
//        day = getDateList.getDay(year.get(year.size() - 1));
        adapter = new MyRecyclerAdapter(this, list2, type, isFirst);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rl.setLayoutManager(layoutManager);
        rl.setAdapter(adapter);

        //保存数据
//        SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));
        mOriginDay = daysByYearAndMonth.get(daysByYearAndMonth.size() - 1).split("-")[2];
        mOriginYear = years.get(years.size() - 1);
        mOriginMonth = monthsByYear.get(monthsByYear.size() - 1).split("-")[1];

        SPUtils.getInstance().put("latestTime", daysByYearAndMonth.get(daysByYearAndMonth.size() - 1));
        SPUtils.getInstance().put("year", mOriginYear);
        SPUtils.getInstance().put("month", mOriginMonth);
        SPUtils.getInstance().put("day", mOriginDay);


    }

    /**
     * 获取真实数据
     *
     * @param year
     * @param month
     * @param day
     */
    private ArrayList<Long> getRealDate(ArrayList<Long> year, ArrayList<Long> month, ArrayList<Long> day) {
        ArrayList<Long> realData = new ArrayList<Long>();
        switch (type) {
            case 2:
            case 3:
                realData.addAll(year);
                break;
            case 1:
                realData.addAll(month);
                break;
            case 0:
                realData.addAll(day);
                break;
        }
        return realData;
    }

    private ArrayList<String> getRealDate2(ArrayList<String> year, ArrayList<String> month, ArrayList<String> day) {
        ArrayList<String> realData = new ArrayList<String>();
        switch (type) {
            case 2:
            case 3:
                realData.addAll(year);
                break;
            case 1:
                realData.addAll(month);
                break;
            case 0:
                realData.addAll(day);
                break;
        }
        return realData;
    }

    /**
     * 这里也会有联网请求
     *
     * @param radioGroup
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        String currentTime = "";
        switch (checkedId) {
            case R.id.radio_day:
                type = 0;
                list2.clear();
                realDate2 = getRealDate2(years, monthsByYear, daysByYearAndMonth);

                //向前添加数据
                String preMonthAndYear = monthsByYear.get(0);
                if(!"2011-01".equals(preMonthAndYear)) {
                    ArrayList<String> monthsPreByYear;
                    if((Integer.valueOf(daysByYearAndMonth.get(0).substring(5,7))) - 1 == 0) {
                        monthsPreByYear =
                                (ArrayList<String>) testDate.getDaysByYearAndMonth("" + (Integer.valueOf(this.monthsByYear.get(0).split("-")[0]) - 1),
                                        12);
                    }else{
                        monthsPreByYear =
                                (ArrayList<String>) testDate.getDaysByYearAndMonth(this.monthsByYear.get(0).split("-")[0],
                                        (Integer.valueOf(daysByYearAndMonth.get(0).substring(5,7))) - 1);
                    }
                    list2.addAll(monthsPreByYear);
                }

                list2.addAll(realDate2);
                adapter.setList(list2);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();

                if (!isFirst && realDate2.size() <
                        Integer.valueOf(SPUtils.getInstance().getString("day", daysByYearAndMonth.get(daysByYearAndMonth.size() - 1).split("-")[2]))) {
                    if (realDate2.size() < 10) {
                        SPUtils.getInstance().put("day", "0" + realDate2.size());
                    } else {
                        SPUtils.getInstance().put("day", realDate2.size() + "");
                    }
                }

                currentTime = SPUtils.getInstance().getString("year", years.get(years.size() - 1)) + "-" +
                        SPUtils.getInstance().getString("month", monthsByYear.get(monthsByYear.size() - 1).split("-")[1]) + "-" +
                        SPUtils.getInstance().getString("day", daysByYearAndMonth.get(daysByYearAndMonth.size() - 1).split("-")[2]);

                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", list2.get(list2.size() - 1));
                    layoutManager.scrollToPositionWithOffset(list2.size() - 1, 0);
                } else {
                    for (int i = 0; i < list2.size(); i++) {
                        if (list2.get(i)
                                .equals(currentTime)) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }


//                realDate = getRealDate(year, month, day);
//                if (inputYear == 2011 && inputMonth == 0) {
//                    Log.e("TAG", "2011年1月");
//                } else {
//                    long string2Millis = MyTimeUtils.string2Millis(getDate(inputYear, inputMonth, inputDay), getSimpleDateFormat(type));
//                    preDays = getDateList.getDay(string2Millis);
//                    preDate = getRealDate(year, month, preDays);
//                    list.addAll(preDate);
//                }
//                adapterChange(realDate,list,type);
//                scrollToPosition(isFirst,list,year,type);
                break;
            case R.id.radio_month:
                type = 1;
                list2.clear();
                realDate2 = getRealDate2(years, monthsByYear, daysByYearAndMonth);
                //向前添加数据
                String preMonthYear = monthsByYear.get(0).split("-")[0];
                if(!"2011".equals(preMonthYear)) {
                    ArrayList<String> monthsPreByYear = (ArrayList<String>) testDate.getMonthsByYear("" + (Integer.valueOf(this.monthsByYear.get(0).split("-")[0]) - 1));
                    list2.addAll(monthsPreByYear);
                }
                list2.addAll(realDate2);



                adapter.setList(list2);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();

                if (!isFirst && realDate2.size() <
                        Integer.valueOf(SPUtils.getInstance().getString("month", monthsByYear.get(monthsByYear.size() - 1).split("-")[1]))) {
                    if (realDate2.size() < 10) {
                        SPUtils.getInstance().put("month", "0" + realDate2.size());
                    } else {
                        SPUtils.getInstance().put("month", realDate2.size() + "");
                    }
                } else if(isFirst) {
                    SPUtils.getInstance().put("month", mOriginMonth);
                }

                currentTime = SPUtils.getInstance().getString("year", years.get(years.size() - 1)) + "-" +
                        SPUtils.getInstance().getString("month", monthsByYear.get(monthsByYear.size() - 1).split("-")[1]);


                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", list2.get(list2.size() - 1));
                    layoutManager.scrollToPositionWithOffset(list2.size() - 1, 0);
                } else {
                    for (int i = 0; i < list2.size(); i++) {
                        if (list2.get(i)
                                .equals(currentTime)) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }

//                realDate = getRealDate(year, month, day);
//                //处理起始年份的问题
//                if (monthYear >= 2011) {
//                    long string2Millis = MyTimeUtils.string2Millis(getDate(monthYear, monthMonth, monthDay), getSimpleDateFormat(type));
//                    preMonths = getDateList.getMonth(string2Millis);
//                    preDate = getRealDate(year, preMonths, day);
//                    list.addAll(preDate);
//                }
//                adapterChange(realDate, list, type);
//                scrollToPosition(isFirst,list,year,type);
                break;
            case R.id.radio_year:
                type = 2;
                list2.clear();
                realDate2 = getRealDate2(years, monthsByYear, daysByYearAndMonth);
                list2.addAll(realDate2);
                adapter.setList(list2);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();

                currentTime = SPUtils.getInstance().getString("year", years.get(years.size() - 1));


                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", list2.get(list2.size() - 1));
                    layoutManager.scrollToPositionWithOffset(list2.size() - 1, 0);
                } else {
                    for (int i = 0; i < list2.size(); i++) {
                        if (list2.get(i)
                                .equals(currentTime)) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }

//                realDate = getRealDate(year, month, day);
//                adapterChange(realDate, list, type);
//                scrollToPosition(isFirst,list,year,type);
                break;
            case R.id.radio_total:
                type = 3;
                list2.clear();
                realDate2 = getRealDate2(years, monthsByYear, daysByYearAndMonth);
                list2.addAll(realDate2);
                adapter.setList(list2);
                adapter.setType(type);
                adapter.getClickList().clear();
                adapter.notifyDataSetChanged();

                currentTime = SPUtils.getInstance().getString("year", years.get(years.size() - 1));

                if (isFirst) {
                    SPUtils.getInstance().put("latestTime", list2.get(list2.size() - 1));
                    layoutManager.scrollToPositionWithOffset(list2.size() - 1, 0);
                } else {
                    for (int i = 0; i < list2.size(); i++) {
                        if (list2.get(i)
                                .equals(currentTime)) {
                            layoutManager.scrollToPositionWithOffset(i, 0);
                            Log.e("回滚的位置", "==============" + (i + 1));
                            return;
                        }
                    }
                }

//                realDate = getRealDate(year, month, day);
//                adapterChange(realDate, list, type);
//                scrollToPosition(isFirst,list,year,type);
                break;
        }
    }

    private void adapterChange(ArrayList<Long> realDate, ArrayList<Long> list, int type) {
//        list.addAll(realDate);
//        adapter.setList(list);
//        adapter.setType(type);
//        adapter.getClickList().clear();
//        adapter.notifyDataSetChanged();
    }

    /**
     * 回滚位置
     *
     * @param isFirst
     * @param list
     * @param year
     * @param type
     */
    private void scrollToPosition(boolean isFirst, ArrayList<Long> list, ArrayList<Long> year, int type) {
        if (isFirst) {
            SPUtils.getInstance().put("latestTime", year.get(year.size() - 1));
            layoutManager.scrollToPositionWithOffset(list.size() - 1, 0);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (MyTimeUtils.millis2String(list.get(i), getSimpleDateFormat(type))
                        .equals(MyTimeUtils.millis2String(SPUtils.getInstance().getLong("latestTime"), getSimpleDateFormat(type)))) {
                    layoutManager.scrollToPositionWithOffset(i, 0);
                    Log.e("回滚的位置", "==============" + (i + 1));
                    return;
                }
            }
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
        isFirst = false;

        String currentDate = list2.get(position);
        if (type == 3 || type == 2) {//yyyy
            year = currentDate;
            monthsByYear = (ArrayList<String>) testDate.getMonthsByYear(year);
            if (month == null) {
                daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(year,
                        Integer.valueOf(mOriginMonth));
            } else {
                if(mOriginYear == year) {
                    if(Integer.valueOf(month)  > Integer.valueOf(mOriginMonth)) {
                        month = mOriginMonth;
                        daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(year, Integer.valueOf(mOriginMonth));
                    }else{
                        daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(year, Integer.valueOf(month));
                    }
                }else{
                    daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(year, Integer.valueOf(month));
                }
            }

//            realLong = list.get(position);
//            month = getDateList.getMonth(list.get(position));
//            day = getDateList.getDay(list.get(position));
//
//            instance.setTimeInMillis(realLong);
//            inputYear = instance.get(Calendar.YEAR);
//            inputMonth = instance.get(Calendar.MONTH);
//            inputDay = instance.get(Calendar.DAY_OF_MONTH);
//
//            monthYear = instance.get(Calendar.YEAR) - 1;
//            monthMonth = instance.get(Calendar.MONTH) + 1;
//            monthDay = instance.get(Calendar.DAY_OF_MONTH);

        } else if (type == 1) {//yyyy-MM
            String[] split = currentDate.split("-");
            year = split[0];
            month = split[1];
            daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(year, Integer.valueOf(month));

//            realLong = list.get(position);
//            day = getDateList.getDay(list.get(position));
//            instance.setTimeInMillis(realLong);
//            inputYear = instance.get(Calendar.YEAR);
//            inputMonth = instance.get(Calendar.MONTH);
//            inputDay = instance.get(Calendar.DAY_OF_MONTH);
//
//            if (inputMonth != 0) {
//                inputDay = dealDay(inputYear, inputMonth, inputDay);
//            }

        } else if (type == 0) {//yyyy-MM-DD

            String[] split = currentDate.split("-");
            year = split[0];
            month = split[1];
            day = split[2];
            daysByYearAndMonth = (ArrayList<String>) testDate.getDaysByYearAndMonth(year, Integer.valueOf(month));

//            realLong = list.get(position);
//            instance.setTimeInMillis(realLong);
//            monthYear = instance.get(Calendar.YEAR) - 1;
//            monthMonth = instance.get(Calendar.MONTH) + 1;
//            monthDay = instance.get(Calendar.DAY_OF_MONTH);
//
//            /**
//             * 防止当前年，月份展示出错
//             */
//            this.month = getDateList.getMonth(list.get(position));
//            if (this.month.size() < 12) {
//                month = saveMonth;
//            }
//
//            Log.e("TAG", "时间轴 : +++++++++" + MyTimeUtils.millis2String(MyTimeUtils.getMillis(list.get(position), 365, TimeConstants.DAY),
//                    getSimpleDateFormat(type)));
        }
        //保存数据
        SPUtils.getInstance().put("latestTime", currentDate);
        SPUtils.getInstance().put("year", year);
        if (month == null) {
            month = mOriginMonth;
        }
        SPUtils.getInstance().put("month", month);
        if (day == null) {
            day = mOriginDay;
        }
        SPUtils.getInstance().put("day", day);

//        Log.e("TAG", "点击事件获取到的具体事件 ： =======" + MyTimeUtils.millis2String(realLong, getSimpleDateFormat(type)));

    }

    public SimpleDateFormat getSimpleDateFormat(int type) {
//        switch (type) {
//            case 0:
//                dateFormatYyyymmdd = MyTimeUtils.DATE_FORMAT_YYYYMMDD;
//                break;
//            case 1:
//                dateFormatYyyymmdd = MyTimeUtils.DATE_FORMAT_YYYYMM;
//                break;
//            case 2:
//            case 3:
//                dateFormatYyyymmdd = MyTimeUtils.DATE_FORMAT_YYYY;
//                break;
//        }
        return dateFormatYyyymmdd;
    }

    /**
     * 根据输入的String获取相应的long
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public String getDate(int year, int month, int day) {
        return year + "-" + month + "-" + day;
    }

    /**
     * 处理天数异常得情况
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public int dealDay(int year, int month, int day) {
        int days = 0;
        switch (month) {
            case 2:
                if (MyTimeUtils.isLeapYear(year) && day > 29)
                    day = 29;
                else if (!MyTimeUtils.isLeapYear(year) && day > 28) {
                    day = 28;
                }
                days = day;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
                days = day;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day > 30) {
                    day = 30;
                }
                days = day;
                break;
        }
        return days;
    }
}
