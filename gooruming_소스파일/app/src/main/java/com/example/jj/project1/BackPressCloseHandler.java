package com.example.jj.project1;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by won on 2016. 12. 4..
 */
public class BackPressCloseHandler{
    private  long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;


    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }


    public void showGuide(){
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
