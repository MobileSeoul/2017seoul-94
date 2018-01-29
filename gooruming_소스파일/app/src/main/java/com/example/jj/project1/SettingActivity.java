package com.example.jj.project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by won on 2016. 12. 4..
 */
public class SettingActivity extends  BaseActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appsetting);
        ImageView mainWeather = (ImageView)findViewById(R.id.mainWeather);

        try{
            FileInputStream inFs = openFileInput("LocationSetting.txt");
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            String str = new String(txt);
            int number = Integer.parseInt(str);


            if(txt == null) {
            }
            else {                  //여기 case문에서 해당 구의 날씨를 디비에서 불러와서 아이콘을 바꿔줘야함
                TextView sloca = (TextView)findViewById(R.id.sloca);
                switch (number) {
                    case 0:
                        sloca.setText("강남구");
                        break;
                    case 1:
                        sloca.setText("강동구");
                        break;
                    case 2:
                        sloca.setText("강북구");
                        break;
                    case 3:
                        sloca.setText("강서구");
                        break;
                    case 4:
                        sloca.setText("관악구");
                        break;
                    case 5:
                        sloca.setText("광진구");
                        break;
                    case 6:
                        sloca.setText("구로구");
                        break;
                    case 7:
                        sloca.setText("금천구");
                        break;
                    case 8:
                        sloca.setText("노원구");
                        break;
                    case 9:
                        sloca.setText("도봉구");
                        break;
                    case 10:
                        sloca.setText("동대문구");
                        break;
                    case 11:
                        sloca.setText("동작구");
                        break;
                    case 12:
                        sloca.setText("마포구");
                        break;
                    case 13:
                        sloca.setText("서대문구");
                        break;
                    case 14:
                        sloca.setText("서초구");
                        break;
                    case 15:
                        sloca.setText("성동구");
                        break;
                    case 16:
                        sloca.setText("성북구");
                        break;
                    case 17:
                        sloca.setText("송파구");
                        break;
                    case 18:
                        sloca.setText("양천구");
                        break;
                    case 19:
                        sloca.setText("영등포구");
                        break;
                    case 20:
                        sloca.setText("용산구");
                        break;
                    case 21:
                        sloca.setText("은평구");
                        break;
                    case 22:
                        sloca.setText("종로구");
                        break;
                    case 23:
                        sloca.setText("중구");
                        break;
                    case 24:
                        sloca.setText("중랑구");
                        break;
                }
            }

            inFs.close();

        }catch(IOException e){};

        try{
            FileInputStream inFs2 = openFileInput("TimelineSetting.txt");
            TextView snum = (TextView)findViewById(R.id.snum);
            byte[] txt = new byte[inFs2.available()];
            inFs2.read(txt);
            String str = new String(txt);
            if(txt == null) {
            }
            else {
                snum.setText(str);
            }
            inFs2.close();
        }catch(IOException e){};



    }

    public void locaSetting(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.allmenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                TextView sloca = (TextView) findViewById(R.id.sloca);
                switch (item.getItemId()) {
                    case R.id.jongro:
                        sloca.setText("종로구");
                        try {//파일에 고른 지역의 번호가 입력된다.
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "22";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.junggu:
                        sloca.setText("중구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "23";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.yongsan:
                        sloca.setText("용산구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "20";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.dobong:
                        sloca.setText("도봉구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "9";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gangbuk:
                        sloca.setText("강북구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "2";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.sungbuk:
                        sloca.setText("성북구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "16";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.nowon:
                        sloca.setText("노원구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "8";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.dongdeamun:
                        sloca.setText("동대문구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "10";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.jungrang:
                        sloca.setText("중랑구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "24";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.sungdong:
                        sloca.setText("성동구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "15";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gwangjin:
                        sloca.setText("광진구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "5";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gangdong:
                        sloca.setText("강동구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "1";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.songpa:
                        sloca.setText("송파구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "17";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.sucho:
                        sloca.setText("서초구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "14";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gangnam:
                        sloca.setText("강남구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "0";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.dongjak:
                        sloca.setText("동작구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "11";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gwanak:
                        sloca.setText("관악구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "4";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gumchun:
                        sloca.setText("금천구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "7";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.gangsue:
                        sloca.setText("강서구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "3";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.yangchun:
                        sloca.setText("양천구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "18";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.yungdungpo:
                        sloca.setText("영등포구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "19";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.guro:
                        sloca.setText("구로구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "6";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.eunpyung:
                        sloca.setText("은평구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "21";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;

                    case R.id.mapo:
                        sloca.setText("마포구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "12";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        ;

                    case R.id.sudeamun:
                        sloca.setText("서대문구");
                        try {
                            FileOutputStream outFs = openFileOutput("LocationSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "13";
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        ;

                }

                return false;
            }
        });
        popupMenu.show();
    }

    public void timelineSetting(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.timemenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                TextView snum = (TextView)findViewById(R.id.snum);
                switch (item.getItemId()) {
                    case R.id.num10:
                        try {//파일에 고른 번호가 입력된다.
                            FileOutputStream outFs = openFileOutput("TimelineSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "10";
                            snum.setText(str);
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }


                        break;
                    case R.id.num20:
                        try {//파일에 고른 번호가 입력된다.
                            FileOutputStream outFs = openFileOutput("TimelineSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "20";
                            snum.setText(str);
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.num30:
                        try {//파일에 고른 번호가 입력된다.
                            FileOutputStream outFs = openFileOutput("TimelineSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "30";
                            snum.setText(str);
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.num40:
                        try {//파일에 고른 번호가 입력된다.
                            FileOutputStream outFs = openFileOutput("TimelineSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "40";
                            snum.setText(str);
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.num50:
                        try {//파일에 고른 번호가 입력된다.
                            FileOutputStream outFs = openFileOutput("TimelineSetting.txt",
                                    Context.MODE_WORLD_WRITEABLE);
                            String str = "50";
                            snum.setText(str);
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e) {
                        }
                        break;

                }

                return false;
            }
        });
        popupMenu.show();


    }


    public void onInform(View v){
        Intent intent = new Intent(SettingActivity.this,InformActivity.class );
        startActivity(intent);
    }

    public void onHome(View v){
        Intent intent = new Intent(SettingActivity.this,MainActivity.class );
        startActivity(intent);
    }

    public void write(View v){
        Intent intent = new Intent(SettingActivity.this,WriteActivity.class );
        startActivity(intent);
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(SettingActivity.this,MainActivity.class );
        startActivity(intent);
    }
}
