package com.example.jj.project1;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class Global extends Application {
    public class Weather_info{
        int number;
        String name = new String();
        int hour;
        double temp;
        String weather = new String();

        public double getTemp() {
            return temp;
        }
        public String getWeather() {
            return weather;
        }
        public int getHour() {
            return hour;
        }
        public String getName() {
            return name;
        }
        Weather_info(int num,String name,int hour,double temp,String weather){
            this.number = num;
            this.name = name;
            this.hour = hour;
            this.temp = temp;
            this.weather = weather;
        }
        void show(){
            Log.i("정보입니다",this.number + " ^ " + this.name + " 시간:" + String.valueOf(this.hour)+ " 온도:"+String.valueOf(this.temp)+" 날씨:"+ this.weather);
        }
    }
    public Weather_info[] wea;

    @Override
    public void onCreate() {
        //전역 변수 초기화
        wea = new Weather_info[25];
        super.onCreate();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void Weather_info_set(int Weather_count,String Weather_name,int Weather_hour,Double Weather_temp,String Weather_state){
        wea[Weather_count] = new Weather_info(Weather_count,Weather_name,Weather_hour,Weather_temp,Weather_state);
        wea[Weather_count].show();
    }
    public Weather_info Weather_info_get(int count){
        return wea[count];
    }
}