package com.ybj.horizonaldatepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by 杨阳洋 on 2018/6/5.
 */

public class TestDate {
    /**
     * 获取年份数据
     * @return
     */
    public List<String> getYears(){
        List<String> years = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        int endYear = instance.get(Calendar.YEAR);
        for(int i= endYear; i>= 2011; i--){
            int year = instance.get(Calendar.YEAR);
            years.add(year+"");
            instance.add(Calendar.YEAR, -1);
        }
        Collections.reverse(years);
        return years;
    }

    public List<String> getMonthsByYear(String year){
        List<String> months = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        if(currentYear == Integer.valueOf(year)){ //传入的年份是当前年
            for (int i = 0 ; i <= currentMonth ; i ++){
                String month = (calendar.get(Calendar.MONTH)+1)+"";
                if(Integer.parseInt(month) < 10){
                    month = "0"+month;
                }
                months.add(calendar.get(Calendar.YEAR)+"-"+month);
                calendar.add(Calendar.MONTH, -1);
            }
        }else{
            for(int i=12;i >= 1;i--){
                String month = i+"";
                if(i<10){
                    month = "0"+month;
                }
                months.add(year+"-"+month);
            }
        }
        Collections.reverse(months);
        return months;
    }

    public List<String> getDaysByYearAndMonth(String year, int month){
        List<String> days = new ArrayList<>();
        String day;
        String monthTime = month + "";
        if(month < 10){
            monthTime = "0"+monthTime;
        }
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        if(currentYear == Integer.valueOf(year) &&
                currentMonth == month) {//当前年月
            for (int i = 1 ; i <= currentDay ; i ++){
                if(i < 10) {
                    day = "0" + i;
                }else{
                    day = i + "";
                }
                days.add(year+"-"+ monthTime+"-"+day);
            }
        }else{
            calendar.set(Integer.valueOf(year),month,0);
            currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            for (int i = 1 ; i <= currentDay ; i++){
                if(i < 10) {
                    day = "0" + i;
                }else{
                    day = i + "";
                }
                days.add(year+"-"+ monthTime +"-"+day);
            }
        }
        return days;
    }

    public String getMonthDay(String time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String returnTime = time;
        try {
            Date date= df.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, -1);
            returnTime = df.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(returnTime);
        return returnTime;
    }
}
