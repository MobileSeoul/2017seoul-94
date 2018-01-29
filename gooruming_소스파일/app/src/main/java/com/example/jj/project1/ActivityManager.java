package com.example.jj.project1;

import java.util.ArrayList;
import android.app.Activity;

/**
 * Created by won on 2016. 12. 4..
 */
public class ActivityManager {

    private static ActivityManager instance;

    public static ActivityManager getInstance(){
        if (instance == null){
            instance = new ActivityManager();
        }
        return instance;
    }





    private static ActivityManager activityManager = null;
    private ArrayList<Activity> activityList = null;
    private int won_timeline_section;
    private int won_timeline_viewer;

    public int getWon_timeline_viewer() {
        return won_timeline_viewer;
    }

    public void setWon_timeline_viewer(int won_timeline_viewer) {
        this.won_timeline_viewer = won_timeline_viewer;
    }

    public int getWon_timeline_section() {
        return won_timeline_section;
    }

    public void setWon_timeline_section(int won_timeline_section) {
        this.won_timeline_section = won_timeline_section;
    }

    private ActivityManager(){
        activityList = new ArrayList<Activity>();
    }


    public ArrayList<Activity> getActivityList(){
        return activityList;
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public boolean removeActivity(Activity activity){
        return activityList.remove(activity);
    }

    public void finishAllActivity(){
        for(Activity activity : activityList){
            activity.finish();
        }
    }
}
