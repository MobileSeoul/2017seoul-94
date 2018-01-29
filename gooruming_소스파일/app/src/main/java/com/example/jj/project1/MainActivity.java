package com.example.jj.project1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.jmw.adaptertest.DB_jungwon;
import com.jmw.adaptertest.seojungwon.won_SQLite;
import com.jmw.src.abs.abs_binooSyncTask;
import com.jmw.src.struct.binoo_sql_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends  BaseActivity  {
    private BackPressCloseHandler backPressCloseHandler;

    String str = null;
    ArrayList<HashMap<String, String>> itemList;
    String myJSON;
    Global myApp;
    won_SQLite won_db;



    //액티비티가 켜질때 onCreate에서 ActiviyManager에 액티비티를 모두 넣어두고
    //onDestroy에서 빼고 종료할때 매니저에 있는 액티비티를 모두 종료시켜야 정상종료됨
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        won_db = new won_SQLite(this);
        SQLiteDatabase db;
        db = won_db.getWritableDatabase();
        db.execSQL("DELETE FROM anna_board;");


        won_db.close();
        ActivityManager.getInstance().setWon_timeline_section(1);
        backPressCloseHandler = new BackPressCloseHandler(this);
        getData();


        myApp = (Global)getApplicationContext();
        ImageView mainWeather = (ImageView)findViewById(R.id.mainWeather);
        TextView locationDong = (TextView)findViewById(R.id.locationDong); // 동명
        ImageView wcon[] = {(ImageView) findViewById(R.id.icon0), (ImageView) findViewById(R.id.icon1),
                (ImageView) findViewById(R.id.icon2), (ImageView) findViewById(R.id.icon3), (ImageView) findViewById(R.id.icon4),
                (ImageView) findViewById(R.id.icon5), (ImageView) findViewById(R.id.icon6), (ImageView) findViewById(R.id.icon7)
        };

        int wnum[] = {23,9,24,1,0,4,18,21};
        for(int i =0 ; i<8; i++){
            if(myApp.Weather_info_get(wnum[i]).getWeather() != null) {
                switch (myApp.Weather_info_get(wnum[i]).getWeather()) {
                    case "맑음":
                        wcon[i].setImageResource(R.drawable.m_sunny);
                        break;
                    case "구름 조금":
                        wcon[i].setImageResource(R.drawable.m_sunny);
                        break;
                    case "구름 많음":
                        wcon[i].setImageResource(R.drawable.m_cloudy);
                        break;
                    case "흐림":
                        wcon[i].setImageResource(R.drawable.m_cloudy);
                        break;
                    case "비":
                        wcon[i].setImageResource(R.drawable.m_rainy);
                        break;
                    case "눈/비":
                        wcon[i].setImageResource(R.drawable.m_rainy);
                        break;
                    case "눈":
                        wcon[i].setImageResource(R.drawable.m_snowy);
                        break;
                }
            }
            else{
                Toast.makeText(this,"api다운로딩 실패!",Toast.LENGTH_SHORT ).show();
            }

        }

        try{
            FileInputStream inFs = openFileInput("LocationSetting.txt");
            FileInputStream inF = openFileInput("TimelineSetting.txt");
            byte[] text = new byte[inF.available()];
            byte[] txt = new byte[inFs.available()];
            inF.read(text);

            inFs.read(txt);

            str = new String(txt);


            int number = Integer.parseInt(str);
            switch (myApp.Weather_info_get(number).getWeather()) {
                case "맑음":
                    mainWeather.setImageResource(R.drawable.sunny);
                    break;
                case "구름 조금":
                    mainWeather.setImageResource(R.drawable.sunny);
                    break;
                case "구름 많음":
                    mainWeather.setImageResource(R.drawable.cloudy);
                    break;
                case "흐림":
                    mainWeather.setImageResource(R.drawable.cloudy);
                    break;
                case "비":
                    mainWeather.setImageResource(R.drawable.rainy);
                    break;
                case "눈/비":
                    mainWeather.setImageResource(R.drawable.rainy);
                    break;
                case "눈":
                    mainWeather.setImageResource(R.drawable.snowy);
                    break;
            }

            if(txt == null) {
            }
            else {                  //여기 case문에서 해당 구의 날씨를 디비에서 불러와서 아이콘을 바꿔줘야함
                switch (number) {
                    case 0:
                        locationDong.setText("강남구");
                        break;
                    case 1:
                        locationDong.setText("강동구");
                        break;
                    case 2:
                        locationDong.setText("강북구");
                        break;
                    case 3:
                        locationDong.setText("강서구");
                        break;
                    case 4:
                        locationDong.setText("관악구");
                        break;
                    case 5:
                        locationDong.setText("광진구");
                        break;
                    case 6:
                        locationDong.setText("구로구");
                        break;
                    case 7:
                        locationDong.setText("금천구");
                        break;
                    case 8:
                        locationDong.setText("노원구");
                        break;
                    case 9:
                        locationDong.setText("도봉구");
                        break;
                    case 10:
                        locationDong.setText("동대문구");
                        break;
                    case 11:
                        locationDong.setText("동작구");
                        break;
                    case 12:
                        locationDong.setText("마포구");
                        break;
                    case 13:
                        locationDong.setText("서대문구");
                        break;
                    case 14:
                        locationDong.setText("서초구");
                        break;
                    case 15:
                        locationDong.setText("성동구");
                        break;
                    case 16:
                        locationDong.setText("성북구");
                        break;
                    case 17:
                        locationDong.setText("송파구");
                        break;
                    case 18:
                        locationDong.setText("양천구");
                        break;
                    case 19:
                        locationDong.setText("영등포구");
                        break;
                    case 20:
                        locationDong.setText("용산구");
                        break;
                    case 21:
                        locationDong.setText("은평구");
                        break;
                    case 22:
                        locationDong.setText("종로구");
                        break;
                    case 23:
                        locationDong.setText("중구");
                        break;
                    case 24:
                        locationDong.setText("중랑구");
                        break;
                }
            }

            inFs.close();
            inF.close();

        }catch(IOException e){};
    }




    public void onIcon0(View v){
        PopupMenu popupMenu0 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu0, popupMenu0.getMenu());
        popupMenu0.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.jongro:
                        ActivityManager.getInstance().setWon_timeline_viewer(22);
                        break;
                    case R.id.junggu:
                        ActivityManager.getInstance().setWon_timeline_viewer(23);
                        break;
                    case R.id.yongsan:
                        ActivityManager.getInstance().setWon_timeline_viewer(20);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu0.show();
    }

    public void onIcon1(View v){
        PopupMenu popupMenu1 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu1, popupMenu1.getMenu());
        popupMenu1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){

                switch(item.getItemId()){
                    case R.id.dobong:
                        ActivityManager.getInstance().setWon_timeline_viewer(9);
                        break;
                    case R.id.gangbuk:
                        ActivityManager.getInstance().setWon_timeline_viewer(2);
                        break;
                    case R.id.sungbuk:
                        ActivityManager.getInstance().setWon_timeline_viewer(16);
                        break;
                    case R.id.nowon:
                        ActivityManager.getInstance().setWon_timeline_viewer(8);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu1.show();
    }

    public void onIcon2(View v){
        PopupMenu popupMenu2 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu2, popupMenu2.getMenu());
        popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){

                switch(item.getItemId()){
                    case R.id.dongdeamun:
                        ActivityManager.getInstance().setWon_timeline_viewer(10);
                        break;
                    case R.id.jungrang:
                        ActivityManager.getInstance().setWon_timeline_viewer(24);
                        break;
                    case R.id.sungdong:
                        ActivityManager.getInstance().setWon_timeline_viewer(15);
                        break;
                    case R.id.gwangjin:
                        ActivityManager.getInstance().setWon_timeline_viewer(5);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu2.show();
    }

    public void onIcon3(View v){
        PopupMenu popupMenu3 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu3, popupMenu3.getMenu());
        popupMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){

                switch(item.getItemId()){
                    case R.id.gangdong:
                        ActivityManager.getInstance().setWon_timeline_viewer(1);
                        break;
                    case R.id.songpa:
                        ActivityManager.getInstance().setWon_timeline_viewer(17);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu3.show();
    }

    public void onIcon4(View v){
        PopupMenu popupMenu4 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu4, popupMenu4.getMenu());
        popupMenu4.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){
                int locanum=0;
                switch(item.getItemId()){
                    case R.id.sucho:
                        ActivityManager.getInstance().setWon_timeline_viewer(14);
                        break;
                    case R.id.gangnam:
                        ActivityManager.getInstance().setWon_timeline_viewer(0);
                        break;

                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu4.show();
    }

    public void onIcon5(View v){
        PopupMenu popupMenu5 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu5, popupMenu5.getMenu());
        popupMenu5.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){

                switch(item.getItemId()){
                    case R.id.dongjak:
                        ActivityManager.getInstance().setWon_timeline_viewer(11);
                        break;
                    case R.id.gwanak:
                        ActivityManager.getInstance().setWon_timeline_viewer(4);
                        break;
                    case R.id.gumchun:
                        ActivityManager.getInstance().setWon_timeline_viewer(7);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu5.show();
    }

    public void onIcon6(View v){
        PopupMenu popupMenu6 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu6, popupMenu6.getMenu());
        popupMenu6.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){

                switch(item.getItemId()){
                    case R.id.gangsue:
                        ActivityManager.getInstance().setWon_timeline_viewer(3);
                        break;
                    case R.id.yangchun:
                        ActivityManager.getInstance().setWon_timeline_viewer(18);
                        break;
                    case R.id.yungdungpo:
                        ActivityManager.getInstance().setWon_timeline_viewer(19);
                        break;
                    case R.id.guro:
                        ActivityManager.getInstance().setWon_timeline_viewer(6);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu6.show();
    }

    public void onIcon7(View v){
        PopupMenu popupMenu7 = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.menu7, popupMenu7.getMenu());
        popupMenu7.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item){

                switch(item.getItemId()){
                    case R.id.eunpyung:
                        ActivityManager.getInstance().setWon_timeline_viewer(21);
                        break;
                    case R.id.mapo:
                        ActivityManager.getInstance().setWon_timeline_viewer(12);
                        break;
                    case R.id.sudeamun:
                        ActivityManager.getInstance().setWon_timeline_viewer(13);
                        break;
                }
                Intent intent = new Intent(MainActivity.this,TimelineActivity.class );

                startActivity(intent);
                return false;
            }
        });
        popupMenu7.show();
    }

    public void onInform(View v){
        Intent intent = new Intent(MainActivity.this,InformActivity.class );
        startActivity(intent);
    }

    public void write(View v){
        Intent intent = new Intent(MainActivity.this,WriteActivity.class );
        startActivity(intent);
    }
    public void apSetting(View v){
        Intent intent = new Intent(MainActivity.this,SettingActivity.class );
        startActivity(intent);
    }
    public void getData(){

        class GetDataJSON extends abs_binooSyncTask {
            @Override
            protected String doInBackground(String... params) {

                String uri= binoo_sql_item.getInstance().SERVER_ADDRESS +"we_bbs.php";
                try {
                    super.setHttpUrlConnect(uri);

                    return getJsonData();
                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                System.out.println(myJSON);
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();

    }

    public void showList(){
        SQLiteDatabase db;
        ContentValues row;


        try {
            JSONArray item = null;
            itemList = new ArrayList<HashMap<String,String>>();

            JSONObject jsonObj = new JSONObject(myJSON);
            item = jsonObj.getJSONArray(binoo_sql_item.TAG_RESULTS);

            for(int i=0; i<item.length();i++){
                db = won_db.getWritableDatabase();
                row = new ContentValues();
                JSONObject c = item.getJSONObject(i);

                String bbsno = c.getString(binoo_sql_item.TAG_BBSNO);
                String title = c.getString(binoo_sql_item.TAG_TITLE);
                String content = c.getString(binoo_sql_item.TAG_CONTENT);
                String seno = c.getString(binoo_sql_item.TAG_SENO);
                String bbscode = c.getString(binoo_sql_item.TAG_BBSCODE);
                String pid = c.getString(binoo_sql_item.TAG_PID);
                String wether = c.getString(binoo_sql_item.TAG_WETHER);
                String tt = c.getString(binoo_sql_item.TAG_TT);
                String shott = c.getString(binoo_sql_item.TAG_SHOTT);
                String cur_im = c.getString(binoo_sql_item.TAG_CUR_IM);

                row.put("_bbscode", bbsno);
                row.put("anna_title",title);
                row.put("anna_content", content);
                row.put("anna_seno", seno);
                row.put("anna_bbscode", bbscode);
                row.put("anna_pid", pid);
                row.put("anna_weather", wether);
                row.put("anna_tt", tt);
                row.put("anna_ts",tt);

                row.put("anna_shott", shott);
                row.put("anna_cur_im", cur_im);

                db.insert("anna_board", null, row);
                System.out.println(bbsno);

            }
            won_db.close();


        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

}