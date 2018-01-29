package com.example.jj.project1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by won on 2016. 12. 4..
 */
public class InformActivity extends BaseActivity {

    String color1 = "#ddffffff";
    String color2 = "#22ffffff";
    Fragment fr = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        fr = new sinfoFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container2, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    public void info(View v){
        LinearLayout sinfo = (LinearLayout)findViewById(R.id.sinfo);
        LinearLayout cinfo = (LinearLayout)findViewById(R.id.cinfo);

        switch (v.getId()){
            case R.id.sinfo:
                sinfo.setBackgroundColor(Color.parseColor(color1));
                cinfo.setBackgroundColor(Color.parseColor(color2));
                fr = new sinfoFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container2,fr);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;
            case R.id.cinfo:
                sinfo.setBackgroundColor(Color.parseColor(color2));
                cinfo.setBackgroundColor(Color.parseColor(color1));
                fr = new cinfoFragment();
                FragmentManager fm2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
                fragmentTransaction2.replace(R.id.fragment_container2,fr);
                fragmentTransaction2.addToBackStack(null);
                fragmentTransaction2.commit();
                break;

        }

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(InformActivity.this,MainActivity.class );
        startActivity(intent);
    }

    public void write(View v){
        Intent intent = new Intent(InformActivity.this,WriteActivity.class );
        startActivity(intent);
    }

    public void onHome(View v){
        Intent intent = new Intent(InformActivity.this,MainActivity.class );
        startActivity(intent);
    }

    public void apSetting(View v){
        Intent intent = new Intent(InformActivity.this,SettingActivity.class );
        startActivity(intent);
    }



}
