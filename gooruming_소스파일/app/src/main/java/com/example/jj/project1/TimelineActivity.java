package com.example.jj.project1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jmw.adaptertest.DB_jungwon;
import com.jmw.adaptertest.seojungwon.won_SQLite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by won on 2016. 12. 4..
 */
public class TimelineActivity extends BaseActivity {

    String color1 = "#ddffffff";
    String color2 = "#22ffffff";

    String str = null;
    int number;
    int locanum;
    ArrayList<MyItem> arItem;
    String w_when;
    String w_id;
    String w_weather;
    String w_time;
    int section;

    won_SQLite won_db;

    String[] selectValue;
    Global myApp;

    //구름 시간 날씨
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        locanum = ActivityManager.getInstance().getWon_timeline_viewer();

        section = ActivityManager.getInstance().getWon_timeline_section();


        myApp = (Global) getApplicationContext();
        arItem = new ArrayList<MyItem>();
        MyItem mi;
        ImageView mainWeather = (ImageView) findViewById(R.id.mainWeather);
        TextView locationDong = (TextView) findViewById(R.id.locationDong);
        TextView where = (TextView) findViewById(R.id.where);
        won_db = new won_SQLite(this);


        switch (locanum) {
            case 0:
                locationDong.setText("강남구 타임라인");
                break;
            case 1:
                locationDong.setText("강동구 타임라인");
                break;
            case 2:
                locationDong.setText("강북구 타임라인");
                break;
            case 3:
                locationDong.setText("강서구 타임라인");
                break;
            case 4:
                locationDong.setText("관악구 타임라인");
                break;
            case 5:
                locationDong.setText("광진구 타임라인");
                break;
            case 6:
                locationDong.setText("구로구 타임라인");
                break;
            case 7:
                locationDong.setText("금천구 타임라인");
                break;
            case 8:
                locationDong.setText("노원구 타임라인");
                break;
            case 9:
                locationDong.setText("도봉구 타임라인");
                break;
            case 10:
                locationDong.setText("동대문구 타임라인");
                break;
            case 11:
                locationDong.setText("동작구 타임라인");
                break;
            case 12:
                locationDong.setText("마포구 타임라인");
                break;
            case 13:
                locationDong.setText("서대문구 타임라인");
                break;
            case 14:
                locationDong.setText("서초구 타임라인");
                break;
            case 15:
                locationDong.setText("성동구 타임라인");
                break;
            case 16:
                locationDong.setText("성북구 타임라인");
                break;
            case 17:
                locationDong.setText("송파구 타임라인");
                break;
            case 18:
                locationDong.setText("양천구 타임라인");
                break;
            case 19:
                locationDong.setText("영등포구 타임라인");
                break;
            case 20:
                locationDong.setText("용산구 타임라인");
                break;
            case 21:
                locationDong.setText("은평구 타임라인");
                break;
            case 22:
                locationDong.setText("종로구 타임라인");
                break;
            case 23:
                locationDong.setText("중구 타임라인");
                break;
            case 24:
                locationDong.setText("중랑구 타임라인");
                break;
        }


        try {
            FileInputStream inFs = openFileInput("LocationSetting.txt");
            FileInputStream inF = openFileInput("TimelineSetting.txt");
            byte[] text = new byte[inF.available()];
            byte[] txt = new byte[inFs.available()];
            inF.read(text);

            inFs.read(txt);

            str = new String(txt);
            number = Integer.parseInt(str);
            switch (myApp.Weather_info_get(number).getWeather()) {
                case "맑음":
                    mainWeather.setImageResource(R.drawable.sunny);
                    break;
                case "구름 조금":
                    mainWeather.setImageResource(R.drawable.lcloudy);
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

            if (txt == null) {
            } else {                  //여기 case문에서 해당 구의 날씨를 디비에서 불러와서 아이콘을 바꿔줘야함
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
        } catch (IOException e) {
        }
        ;


        SQLiteDatabase db;
        //구조에 맞는 입력값 넣기
        ContentValues row;
        db = won_db.getReadableDatabase();
        Cursor cursor;

        switch (section) {

            case 1:
                where.setText("실시간");
                cursor = db.rawQuery("SELECT _bbscode,anna_content,anna_weather,anna_ts FROM anna_board WHERE anna_bbscode =" + locanum + " AND anna_seno = " + section, null);
                while (cursor.moveToNext()) {

                    w_id = cursor.getString(0);

                    w_when = cursor.getString(1);
                    w_weather = cursor.getString(2);
                    w_time = cursor.getString(3);

                    mi = new MyItem(w_id, w_when, w_weather, w_time);
                    arItem.add(mi);


                }
                break;

            case 2:
                where.setText("뭉개구름");
                cursor = db.rawQuery("SELECT _bbscode,anna_content,anna_cur_im,anna_ts FROM anna_board WHERE anna_bbscode =" + locanum + " AND anna_seno = " + section, null);
                while (cursor.moveToNext()) {

                    w_id = cursor.getString(0);

                    w_when = cursor.getString(1);
                    w_weather = cursor.getString(2);
                    w_time = cursor.getString(3);

                    mi = new MyItem(w_id, w_when, w_weather, w_time);
                    arItem.add(mi);


                }
                break;

            case 3:
                where.setText("내일날씨");
                cursor = db.rawQuery("SELECT _bbscode,anna_content,anna_cur_im,anna_ts FROM anna_board WHERE anna_bbscode =" + locanum + " AND anna_seno = " + section, null);
                while (cursor.moveToNext()) {

                    w_id = cursor.getString(0);

                    w_when = cursor.getString(1);
                    w_weather = cursor.getString(2);
                    w_time = cursor.getString(3);

                    mi = new MyItem(w_id, w_when, w_weather, w_time);
                    arItem.add(mi);


                }
                break;


        }
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.anna_listview, arItem);

        ListView MyList;
        MyList = (ListView) findViewById(R.id.list);
        MyList.setAdapter(MyAdapter);

        //cursor.close();
        //a_db.close();

/*
                MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.anna_listview , arItem);

                ListView MyList;
                MyList=(ListView)findViewById(R.id.list);
                MyList.setAdapter(MyAdapter);
*/


    }


    public void onNotice(View v) {
        System.out.println("버튼눌려요?");
        ActivityManager.getInstance().setWon_timeline_section(1);
        Intent intent = new Intent(TimelineActivity.this, TimelineActivity.class);
        startActivity(intent);


    }

    public void onAd(View v) {
        System.out.println("버튼눌려요?");
        ActivityManager.getInstance().setWon_timeline_section(2);
        Intent intent = new Intent(TimelineActivity.this, TimelineActivity.class);
        startActivity(intent);


    }

    public void onPrediction(View v) {
        System.out.println("버튼눌려요?");
        ActivityManager.getInstance().setWon_timeline_section(3);
        Intent intent = new Intent(TimelineActivity.this, TimelineActivity.class);
        startActivity(intent);


    }

    public void onInform(View v) {
        Intent intent = new Intent(TimelineActivity.this, InformActivity.class);
        startActivity(intent);
    }

    public void onHome(View v) {
        Intent intent = new Intent(TimelineActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void write(View v) {
        Intent intent = new Intent(TimelineActivity.this, WriteActivity.class);
        startActivity(intent);
    }


    public void apSetting(View v) {
        Intent intent = new Intent(TimelineActivity.this, SettingActivity.class);
        startActivity(intent);
    }

}

    //추가 분
    //junminwoo 10/22.

class MyItem{
    MyItem(String id,String when, String weather, String time){
        m_id = id;
        m_when = when;
        m_weather = weather;
        m_time = time;


    }
    String m_time;
    String m_weather;
    String m_when;
    String m_id;

}

class MyListAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyItem> arSrc;
    //ArrayList<brain_item> arSrc;
    int layout;

    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc) {
        maincon = context;
        Inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        arSrc = aarSrc;
        layout = alayout;

    }

    public int getCount() {
        return arSrc.size();
    }

    public String getItem(int position) {
        return arSrc.get(position).m_when;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = Inflater.inflate(layout, parent, false);
        }
        TextView txt = (TextView) convertView.findViewById(R.id.text);
        txt.setText(arSrc.get(position).m_when);

        Button btn = (Button) convertView.findViewById(R.id.btn);
        btn.setText(arSrc.get(position).m_time);

        final TextView coments = (TextView) convertView.findViewById(R.id.won_conment);

        ImageView img = (ImageView) convertView.findViewById(R.id.won_weather);
        switch (arSrc.get(position).m_weather) {
            case "0":
                img.setImageResource(R.drawable.cloudy);
                break;
            case "1":
                img.setImageResource(R.drawable.lcloudy);
                break;
            case "2":
                img.setImageResource(R.drawable.rainy);
                break;
            case "3":
                img.setImageResource(R.drawable.sunny);
                break;
            case "4":
                img.setImageResource(R.drawable.thunder);
                break;
            case "5":
                img.setImageResource(R.drawable.snowy);
                break;
            default:
                new binoo_Img_getAsynk(img, arSrc.get(position).m_id).execute();

        }


        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                final String get_bbsno = arSrc.get(position).m_id;


                PopupMenu popupMenu = new PopupMenu(parent.getContext(), v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.bbs_menu, popupMenu.getMenu());
                DB_jungwon.getInstance().getrepl(get_bbsno);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.comeon:
                                String result = DB_jungwon.getInstance().show_repl();
                                coments.setVisibility(View.VISIBLE);
                                coments.setText(result);
                                //여기서 텍스트뷰가 보이게 만듭니다.
                                break;
                            //setContentView(R.layout.activity_main);
                            case R.id.delete:
                                DB_jungwon.getInstance().deleteToDatabase(get_bbsno);
                                Intent intent = new Intent(parent.getContext(), MainActivity.class);
                                parent.getContext().startActivity(intent);


                                break;
                            case R.id.repl:
                                final LinearLayout linear2 = (LinearLayout) View.inflate(parent.getContext(), R.layout.dialog_view, null);
                                new AlertDialog.Builder(parent.getContext())
                                        .setTitle("댓글을 입력하세요.")
                                        .setView(linear2)
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                EditText content = (EditText) linear2.findViewById(R.id.wr_repl);
                                                String repl_content;
                                                repl_content = content.getText().toString();
                                                String[] insert_repl = {"010-1234-5678", get_bbsno, repl_content};
                                                DB_jungwon.getInstance().insertToRepl(insert_repl);
                                            }
                                        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                                break;

                        }
                        return false;
                    }
                });
                popupMenu.show();
               /*
                    Intent intent = new Intent(maincon, anna_schadule_view.class);
                    intent.putExtra("get_id", arSrc.get(position).m_id);
                    maincon.startActivity(intent);
*/


            }
        });


        return convertView;

    }

  }

