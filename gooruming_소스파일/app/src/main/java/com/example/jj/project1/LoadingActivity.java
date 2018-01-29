package com.example.jj.project1;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jmw.adaptertest.DB_jungwon;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Created by won on 2016. 12. 4..
 */

public class LoadingActivity extends  BaseActivity  {
    Boolean wifi =  false;//네트워크가 연결 되었는지 정보를 저장할 변수
    Boolean wifi2 = false;
    private static final int PROGRESS = 0x1;
    private ProgressBar mProgress;
    private int mProgressStatus = 0;
    int i = 0;

    private ImageView img;



    CountDownTimer a;

    Document doc = null;
    LinearLayout layout = null;

    String Weather_name = new String();
    int Weather_hour;
    double Weather_temp;
    String Weather_state = new String();
    int Weather_count = 0;

    Global myApp;
    int Loading_count =0;

    private class GetXMLTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                finish();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String s = "";
            //data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
            NodeList nodeList = doc.getElementsByTagName("data");
            //data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환
            NodeList nodeList2 = doc.getElementsByTagName("item");
            // Log.i("asdasdsasdsa", String.valueOf(nodeList2.getLength()));
            Element fstElmnt2 = (Element) nodeList2.item(0);
            NodeList hourList2  = fstElmnt2.getElementsByTagName("category");
            Log.i("asdasdsasdsa", hourList2.item(0).getChildNodes().item(0).getNodeValue());
            Weather_name = hourList2.item(0).getChildNodes().item(0).getNodeValue();
            for(int i = 0; i< 3; i++){  ///for(int i = 0; i< nodeList.getLength(); i++){
                //날씨 데이터를 추출
                s += "" +i + ": 날씨 정보: ";
                Node node = nodeList.item(i); //data엘리먼트 노드
                Element fstElmnt = (Element) node;
                NodeList nameList  = fstElmnt.getElementsByTagName("temp");
                Element nameElement = (Element) nameList.item(0);
                NodeList nameList2 = nameElement.getChildNodes();

                NodeList hourList  = fstElmnt.getElementsByTagName("hour");
                String times = hourList.item(0).getChildNodes().item(0).getNodeValue();
                int times_int = Integer.parseInt(times);
                s += "시간 = "+  times +" ,";
                if(i==0) Weather_hour = times_int;

                s += "온도 = "+ ((Node) nameList2.item(0)).getNodeValue() +" ,";
                String tempss = nameList.item(0).getChildNodes().item(0).getNodeValue();
                if(i==0) Weather_temp = Double.parseDouble(tempss);

                NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
                //<wfKor>맑음</wfKor> =====> <wfKor> 태그의 첫번째 자식노드는 TextNode 이고 TextNode의 값은 맑음
                s += "날씨 = "+  websiteList.item(0).getChildNodes().item(0).getNodeValue() +"\n";
                if(i==0) Weather_state = websiteList.item(0).getChildNodes().item(0).getNodeValue();
            }
            Log.i("asdasdsasdsa",s);
            super.onPostExecute(doc);
            myApp.Weather_info_set(Weather_count, Weather_name, Weather_hour, Weather_temp, Weather_state);
            Weather_count++;
            Loading_count++;

        }


    }//end inner class - GetXMLTask // http://www.kma.go.kr/weather/lifenindustry/sevice_rss.jsp?sido=4100000000&gugun=4111300000&dong=4137053000&x=42&y=11

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        myApp = (Global)getApplicationContext();
        img = (ImageView) findViewById(R.id.ion_loading);
        mProgress = (ProgressBar) findViewById(R.id.loading_bar);

        //Ion.with(img).load("android.resource://"+getPackageName()+"/"+R.drawable.ion).intoImageView(imageView);
        Ion.with(this).load("android.resource://"+getPackageName()+"/"+R.drawable.ion).intoImageView(img);
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        wifi = mgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();//네트워크 연결 정보를 가져온다
        wifi2 = mgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();//와이파이 연결 정보를 가져온다
        if(!wifi && !wifi2) {//연결이 안되어있을때 브로드캐스트
            Toast.makeText(this,"네트워크 연결을 확인해주세요!",Toast.LENGTH_SHORT ).show();
            finish();

        }else{
            a = new CountDownTimer(10000, 200) {
                @Override
                public void onTick(long millisUntilFinished) {
                    i = i+Loading_count+3;
                    mProgressStatus = i;
                    mProgress.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
                @Override
                public void onFinish() {
                }
            }.start();


        try {//this.setXML();


            String[] w_address = new String[25];
            w_address[0] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168066000"; // 강남 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168066000
            w_address[1] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1174051500"; // 강동 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1174051500
            w_address[2] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1130553500"; // 강북 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1130553500
            w_address[3] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1150060300"; // 강서 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1150060300
            w_address[4] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500"; // 관악 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500
            w_address[5] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1121581000"; // 광진 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1121581000
            w_address[6] = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153059500"; // 구로 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153059500
            w_address[7] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000"; //  금천 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000
            w_address[8] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1135059500"; //  공릉 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1135059500
            w_address[9] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1132052100"; //  도봉 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1132052100
            w_address[10] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1123060000"; // 동대문구 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1123060000
            w_address[11] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159051000"; // 동작 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159051000
            w_address[12] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1144056500"; // 마포 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1144056500
            w_address[13] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1141069000"; // 서대문 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1141069000
            w_address[14] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1165066000"; // 서초 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1165066000
            w_address[15] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1120059000"; // 성동 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1120059000
            w_address[16] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1129066000"; // 성북 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1129066000
            w_address[17] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1171063100"; // 송파 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1171063100
            w_address[18] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1147051000"; // 양천 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1147051000
            w_address[19] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1156055000"; // 영등포 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1156055000
            w_address[20] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1117053000"; // 용산 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1117053000
            w_address[21] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1138055100"; // 은평 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1138055100
            w_address[22] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1111060000"; // 종로 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1111060000
            w_address[23] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1114059000"; // 중구 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1114059000
            w_address[24] ="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1126065500"; // 중랑 : http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1126065500

            GetXMLTask[] task = new GetXMLTask[25];

            for(int m=0;m<25;m++){
                task[m] = new GetXMLTask();
                task[m].execute(w_address[m]);
            }

        }
        catch(Exception e) {
        }

            new Thread(new Runnable() {
                @Override
                public void run() { //Loading_count
                    while (Loading_count<25) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        mProgress.post(new Runnable() {
                            @Override
                            public void run() {
                                //mProgress.setProgress(mProgressStatus);
                            }
                        });
                    }
                    a.cancel();
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }).start();
        }

    }




}