package com.ybj.horizonaldatepicker;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by 杨阳洋 on 2018/5/31.
 */

public class GetDateList {
    /**
     * 存放年份List
     */
    private ArrayList<Long> yearList;
    /**
     * 当前年份
     */
    private int latestYear;
    /**
     * 当前月份
     */
    private int latestMonth;

    /**
     * 获取当前系统时间
     */
    private long currentTimeMillis;

    /**
     * 上一年
     */
    private long preYear;
    /**
     * 存放当前月份
     */
    private ArrayList<Long> monthList;

    /**
     * 公用的实例
     */
    private Calendar instance = Calendar.getInstance();

    /**
     * 上一月份
     */
    private long preMonth;

    /**
     * 下一月份
     */
    private long nextMonth;
    /**
     * 月份或者天数输入的真实数据，用来判断全年数据
     */
    private long realInputMillis;

    /**
     * 存放天数List
     */
    private ArrayList<Long> dayList;

    /**
     * 上一天
     */
    private long preDay;

    /**
     * 下一天
     */
    private long nextDay;

    /**
     * 最大天数
     */
    private int MAX_DAY = 0;

    /**
     * 起始年份用来判断手机年份是否合理
     */
    private int startYear = 2011;

    /**
     * true:相减 false:相加
     */
    private boolean isPre = true;
    private int mDay;

    /**
     * 获取年份List数据
     *
     * @return
     */
    public ArrayList<Long> getYear() {
        yearList = new ArrayList<Long>();
        /**
         * 获取当前年份
         */
        currentTimeMillis = System.currentTimeMillis();
        instance.setTimeInMillis(currentTimeMillis);
        latestYear = instance.get(Calendar.YEAR);
        latestMonth = instance.get(Calendar.MONTH) + 1;

        if (startYear > latestYear) {
            Log.e("TAG", "请检查当前手机时间是否正确");
        } else {
            for (int i = latestYear; i >= 2011; i--) {
                if (MyTimeUtils.isLeapYear(i)) {//闰年
                    preYear = MyTimeUtils.getMillis(currentTimeMillis, -366, TimeConstants.DAY);
                } else {//平年
                    preYear = MyTimeUtils.getMillis(currentTimeMillis, -365, TimeConstants.DAY);
                }
                yearList.add(currentTimeMillis);
                currentTimeMillis = preYear;
            }

            Collections.reverse(yearList);
        }

        return yearList;

    }

    /**
     * 获取月份List数据
     *
     * @param inputMillis
     * @return
     */
    public ArrayList<Long> getMonth(long inputMillis) {
        monthList = new ArrayList<Long>();
        instance.setTimeInMillis(inputMillis);
        //根据年份来返回对应的月份数据
        int inputYear = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        mDay = instance.get(Calendar.DAY_OF_MONTH);
        if (inputYear == latestYear) {//年份相等(月份只有--操作)
            monthList = getMonthPre(inputMillis, inputYear, month);
        } else {//年份不想等(月份有加减操作)
            if (month == 12) {
                monthList = getMonthPre(inputMillis, inputYear, month);
            } else if (month == 1) {
                monthList = getMonthNext(inputMillis, inputYear, month);
            } else {
                realInputMillis = inputMillis;
                ArrayList<Long> equalsYearDatePre = getMonthPre(inputMillis, inputYear, month);
                ArrayList<Long> equalsYearDateNext = getMonthNext(realInputMillis, inputYear, month);
                //去重
                equalsYearDateNext.remove(realInputMillis);
                realInputMillis = 0;
                monthList.addAll(equalsYearDatePre);
                monthList.addAll(equalsYearDateNext);
            }
        }
        return monthList;
    }

    /**
     * 获取天数List数据
     *
     * @param inputMillis
     * @return
     */
    public ArrayList<Long> getDay(long inputMillis) {
        dayList = new ArrayList<Long>();
        instance.setTimeInMillis(inputMillis);
        //根据年份来返回对应的月份数据
        int inputYear = instance.get(Calendar.YEAR);
        int inputMonth = instance.get(Calendar.MONTH) + 1;
        int inputDay = instance.get(Calendar.DAY_OF_MONTH);
        Date date = new Date(inputMillis);
        if (inputYear == latestYear) {//当前年份相等
            if (inputMonth == latestMonth) {//当前月份相等
                ArrayList<Long> dayPre = getDayPre(inputMillis, inputDay);
                dayList = dayPre;
            } else {//当前月份不相等
                if (inputMonth == 2) {
                    if (MyTimeUtils.isLeapYear(inputYear)) {//29天
                        MAX_DAY = 29;
                        getRealDayList(inputMillis, inputDay);
                    } else {//28天
                        MAX_DAY = 28;
                        getRealDayList(inputMillis, inputDay);
                    }
                } else if (isBigMonth(inputMonth)) {//31天
                    MAX_DAY = 31;
                    getRealDayList(inputMillis, inputDay);
                } else if (!isBigMonth(inputMonth)) {//30天
                    MAX_DAY = 30;
                    getRealDayList(inputMillis, inputDay);
                }
            }
        } else {//当前年份不相等
            if (inputMonth == 2) {
                if (MyTimeUtils.isLeapYear(inputYear)) {//29天
                    MAX_DAY = 29;
                    getRealDayList(inputMillis, inputDay);
                } else {//28天
                    MAX_DAY = 28;
                    getRealDayList(inputMillis, inputDay);
                }
            } else if (isBigMonth(inputMonth)) {//31天
                MAX_DAY = 31;
                getRealDayList(inputMillis, inputDay);
            } else if (!isBigMonth(inputMonth)) {//30天
                MAX_DAY = 30;
                getRealDayList(inputMillis, inputDay);
            }
        }
        return dayList;
    }

    /**
     * 获取真实天数数据
     *
     * @param inputMillis
     * @param inputDay
     */
    private void getRealDayList(long inputMillis, int inputDay) {
        if (inputDay == MAX_DAY) {
            dayList = getDayPre(inputMillis, inputDay);
        } else if (inputDay == 1) {
            dayList = getDayNext(inputMillis, inputDay);
        } else {//1~30中任意一天
            realInputMillis = MyTimeUtils.getMillis(inputMillis,1,TimeConstants.DAY);
            ArrayList<Long> dayPreList = getDayPre(inputMillis, inputDay);
            ArrayList<Long> dayNextList = getDayNext(realInputMillis, inputDay + 1);
            dayList.addAll(dayPreList);
            dayList.addAll(dayNextList);
        }
    }

    /**
     * 获取当前时间下一天的数据集合
     *
     * @param inputMillis
     * @param inputDay
     * @return
     */
    private ArrayList<Long> getDayNext(long inputMillis, int inputDay) {
        ArrayList<Long> nextList = new ArrayList<>();
        for (int i = inputDay; inputDay <= MAX_DAY; inputDay++) {
            nextList.add(inputMillis);
            nextDay = MyTimeUtils.getMillis(inputMillis, 1, TimeConstants.DAY);
            inputMillis = nextDay;
        }
        return nextList;
    }

    /**
     * 获取当前时间上一天的数据集合
     *
     * @param inputMillis
     * @param inputDay
     * @return
     */
    private ArrayList<Long> getDayPre(long inputMillis, int inputDay) {
        ArrayList<Long> preList = new ArrayList<>();
        for (int i = inputDay; inputDay > 0; inputDay--) {
            preList.add(inputMillis);
            preDay = MyTimeUtils.getMillis(inputMillis, -1, TimeConstants.DAY);
            inputMillis = preDay;
        }
        Collections.reverse(preList);
        return preList;
    }

    /**
     * 获取向下的数据
     *
     * @param inputMillis
     * @param inputYear
     * @param month
     */
    private ArrayList<Long> getMonthNext(long inputMillis, int inputYear, int month) {
        ArrayList<Long> nextList = new ArrayList<>();
        isPre = false;
        for (int i = month; month <= 12; month++) {
            nextList.add(inputMillis);
            int realRemoveDays = realRemoveDays(month, inputYear);
            nextMonth = MyTimeUtils.getMillis(inputMillis, realRemoveDays, TimeConstants.DAY);
            inputMillis = nextMonth;
        }
        return nextList;
    }

    /**
     * 只获取向上的数据
     *
     * @param inputMillis
     * @param inputYear
     * @param month
     */
    private ArrayList<Long> getMonthPre(long inputMillis, int inputYear, int month) {
        ArrayList<Long> preList = new ArrayList<>();
        isPre = true;
        for (int i = month; month > 0; month--) {
            int realRemoveDays = realRemoveDays(month, inputYear);
            preMonth = MyTimeUtils.getMillis(inputMillis, -realRemoveDays, TimeConstants.DAY);
            preList.add(inputMillis);
            inputMillis = preMonth;
        }
        Collections.reverse(preList);
        return preList;
    }

    /**
     * 获取月份相减的天数
     *
     * @param month
     * @param inputYear
     * @return
     */
    public int realRemoveDays(int month, int inputYear) {
        int days = 0;
        if (isPre) {
            if(mDay != 31) {
                switch (month) {
                    case 12:
                    case 10:
                    case 7:
                    case 5:
                        days = 30;
                        break;
                    case 11:
                    case 9:
                    case 8:
                    case 2:
                    case 6:
                    case 4:
                        days = 31;
                        break;
                    case 3:
                        if (mDay == 30) {
                            days = 30;
                        } else if (mDay == 29) {
                            days = 29;
                        } else {
                            if (MyTimeUtils.isLeapYear(inputYear))
                                days = 29;
                            else
                                days = 28;
                        }
                        break;
                }
            }else{
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        days = 30;
                        break;
                    case 2:
                        if (MyTimeUtils.isLeapYear(inputYear))
                            days = 29;
                        else
                            days = 28;
                        break;
                    default:
                        break;

                }
            }
        } else {//相加
            if (mDay != 31) {
                switch (month) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        days = 31;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        days = 30;
                        break;
                    case 2:
                        if (MyTimeUtils.isLeapYear(inputYear))
                            days = 29;
                        else
                            days = 28;
                        break;
                    default:
                        break;

                }
            } else {
                switch (month) {
                    case 1:
                        if (MyTimeUtils.isLeapYear(inputYear))
                            days = 29;
                        else
                            days = 28;
                        break;
                    case 2:
                    case 4:
                    case 6:
                    case 7:
                    case 9:
                    case 11:
                        days = 31;
                        break;
                    case 3:
                    case 5:
                    case 8:
                    case 10:
                        days = 30;
                        break;
                }
            }
        }
        return days;
    }

    /**
     * 判断大小月
     *
     * @param month
     * @return
     */
    public boolean isBigMonth(int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return true;
            default:
                return false;
        }
    }
}
