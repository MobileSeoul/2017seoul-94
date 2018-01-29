package com.jmw.src.manager;

import com.jmw.src.abs.abs_binooSyncTask;
import com.jmw.src.struct.binoo_sql_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by junminwoo on 2016-10-28.
 */
public class proc_binoo_PHP_server {

    private static proc_binoo_PHP_server instance;
    public static proc_binoo_PHP_server getInstance(){
        return instance = (instance == null ? new proc_binoo_PHP_server() : instance);
    }

    private String myJSON="";
    private boolean proc_val = false; //리턴 결과가 있을때만 사용.
    public boolean isExecute(){
        return proc_val;
    }

    public void onPrepare_HitRate(String... params){
        proc_val = false;

        class getData extends abs_binooSyncTask {
            @Override
            protected String doInBackground(String... params) {
                try{
                    String link = binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_PROC_HITRATE_CALL + "?"; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    String data  = URLEncoder.encode("tim", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");
                    data += "&" + URLEncoder.encode("uid", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    data += "&" + URLEncoder.encode("bbcode", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

                    super.setHttpUrlConnect(link+data);

                    return super.getJsonData();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                proc_val = true;
            }
        }
        getData task = new getData();
        task.execute(params);
    }

    public void onPrepare_Login(String... params){
        //아이디를 저장하거나 세션을 유지할 이유가 없음.

        class loginData extends abs_binooSyncTask {
            @Override
            protected String doInBackground(String... params) {
                try{
                    String link = binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_LOGIN_CALL + "?"; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    String data  = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");
                    data += "&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

                    super.setHttpUrlConnect(link);

                    return super.getJsonData();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        loginData task = new loginData();
        task.execute(params);
    }
    public void onProcessing_HitItem(String... params){
        class procData extends abs_binooSyncTask {
            @Override
            protected String doInBackground(String... params) {
                try{
                    String link = binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_PROC_HIT_GO + "?"; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    String data  = URLEncoder.encode("wether", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");
                    data += "&" + URLEncoder.encode("tim", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");

                    super.setHttpUrlConnect(link);

                    return super.getJsonData();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        procData task = new procData();
        task.execute(params);
    }

    public ArrayList<HashMap<String,Double>> getHitRate(){
        try {
            JSONArray item = null;
            JSONObject jsonObj = new JSONObject(myJSON);
            item = jsonObj.getJSONArray(binoo_sql_item.getInstance().TAG_RESULTS);
            double a=0, b=0;
            ArrayList<HashMap<String,Double>> itemList = new ArrayList<HashMap<String,Double>>();

            for(int i=0; i<item.length() ;i++){
                JSONObject c = item.getJSONObject(i);
                String value = c.getString(binoo_sql_item.getInstance().TAG_HITITEM_VAL);

                System.out.println(value);
                if (i%2 == 1){
                    a = Double.parseDouble(value);
                }else{
                    b = Double.parseDouble(value);
                    HashMap<String,Double> hit = new HashMap<String,Double>();
                    hit.put("HitRate", b/a*100);
                    System.out.println(hit);
                    itemList.add(hit);
                }
            }
            return itemList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}