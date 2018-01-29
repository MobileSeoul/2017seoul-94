package com.jmw.adaptertest;

/**
 * Created by won on 2016. 10. 1..
 * modified by junminwoo on 2016-10-08.
 */


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.jmw.adaptertest.seojungwon.won_SQLite;
import com.jmw.src.abs.abs_binooSyncTask;
import com.jmw.src.struct.binoo_sql_item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by won on 2016. 10. 1..
 */

public class DB_jungwon {
    String myJSON;
    String replJSON;


    ArrayList<HashMap<String, String>> itemList;
    private static DB_jungwon instance;

    public static DB_jungwon getInstance(){
        if (instance == null){
            instance = new DB_jungwon();
        }
        return instance;
    }

    //DATA를 가져오는 부분
    public void getData(){

        class GetDataJSON extends abs_binooSyncTask {
            @Override
            protected String doInBackground(String... params) {

                String uri=binoo_sql_item.getInstance().SERVER_ADDRESS +"we_bbs.php";
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
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();

    }

    public ArrayList<HashMap<String,String>> showList(){


        try {
            JSONArray item = null;
            itemList = new ArrayList<HashMap<String,String>>();

            JSONObject jsonObj = new JSONObject(myJSON);
            item = jsonObj.getJSONArray(binoo_sql_item.TAG_RESULTS);

            for(int i=0; i<item.length();i++){

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


                HashMap<String,String> persons = new HashMap<String,String>();

                persons.put(binoo_sql_item.TAG_BBSNO,bbsno);
                persons.put(binoo_sql_item.TAG_TITLE,title);
                persons.put(binoo_sql_item.TAG_CONTENT,content);
                persons.put(binoo_sql_item.TAG_SENO,seno);
                persons.put(binoo_sql_item.TAG_BBSCODE,bbscode);
                persons.put(binoo_sql_item.TAG_PID,pid);
                persons.put(binoo_sql_item.TAG_WETHER,wether);
                persons.put(binoo_sql_item.TAG_TT,tt);
                persons.put(binoo_sql_item.TAG_SHOTT,shott);
                persons.put(binoo_sql_item.TAG_CUR_IM,cur_im);

                System.out.println(persons);
                itemList.add(persons);
            }

            return itemList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    public void insertToDatabase(String... params){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String title = (String)params[0];
                    String content = (String)params[1];
                    String seno = (String)params[2];
                    String bbscode = (String)params[3];
                    String pid = (String)params[4];
                    String wether = (String)params[5];

                    String cur_im = (String)params[6];

                    String link = binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_BBS_ADD_CALL + "?";

                    String data  = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
                    data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8");
                    data += "&" + URLEncoder.encode("seno", "UTF-8") + "=" + URLEncoder.encode(seno, "UTF-8");
                    data += "&" + URLEncoder.encode("bbscode", "UTF-8") + "=" + URLEncoder.encode(bbscode, "UTF-8");
                    data += "&" + URLEncoder.encode("pid", "UTF-8") + "=" + URLEncoder.encode(pid, "UTF-8");
                    data += "&" + URLEncoder.encode("wether", "UTF-8") + "=" + URLEncoder.encode(wether, "UTF-8");
                    data += "&" + URLEncoder.encode("cur_im", "UTF-8") + "=" + URLEncoder.encode(cur_im, "UTF-8");



                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(params);
    }


    public void insertToRepl(String... params){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String pid = (String)params[0];
                    String bbsno = (String)params[1];
                    String content = (String)params[2];

                    String link = binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_REPL_ADD_CALL + "?";
                    String data  = URLEncoder.encode("pid", "UTF-8") + "=" + URLEncoder.encode(pid, "UTF-8");
                    data += "&" + URLEncoder.encode("bbsno", "UTF-8") + "=" + URLEncoder.encode(bbsno, "UTF-8");
                    data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8");



                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(params);
    }

    public void deleteToDatabase(String... params){
        class DeleteData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }
            @Override
            protected String doInBackground(String... params) {
                try{

                    String bbsno = (String)params[0];
                    System.out.print(bbsno);

                    String link= binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_BBS_REMOVE_CALL + "?"; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    String data  = URLEncoder.encode("bbsno", "UTF-8") + "=" + URLEncoder.encode(bbsno, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();


                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        DeleteData task = new DeleteData();
        task.execute(params);
    }
    public void updateToDatabase(String... params){
        class UpdateToDatabase extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }
            @Override
            protected String doInBackground(String... params) {
                try{


                    String content = (String)params[0];
                    String bbsno = (String)params[1];

                    String link = binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_BBS_EDIT_CALL + "?"; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    String data  = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8");

                    data += "&" + URLEncoder.encode("bbsno", "UTF-8") + "=" + URLEncoder.encode(bbsno, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }

        }
        UpdateToDatabase task = new UpdateToDatabase();
        task.execute(params);
    }


    public void selectToDatabase(String... params){

        class SeleteData extends abs_binooSyncTask{
            @Override
            protected String doInBackground(String... params) {
                try{
                    String bbsno = (String)params[0];

                    String link=binoo_sql_item.getInstance().SERVER_ADDRESS + binoo_sql_item.getInstance().SERVER_BBS_ITEM_CALL + "?bbsno=" + bbsno; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    System.out.println(link);
                    super.setHttpUrlConnect(link);

                    return getJsonData();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON=result;
            }

        }
        SeleteData g = new SeleteData();
        g.execute(params);
    }
    public void selectToShow(String[] params, final Activity activity,final ListFragment listFragment){

        class SeleteData extends abs_binooSyncTask{
            @Override
            protected String doInBackground(String... params) {
                try{
                    String bbscode = (String)params[0];
                    String seno = (String)params[1];
                    String limit = (String)params[2];

                    String link=binoo_sql_item.getInstance().SERVER_ADDRESS + "jungwon_select_code.php?bbscode="+bbscode+"&seno="+seno+"&limit="+limit; // 매개변수 값을 받아 데이터를 전송하는 부분의 시작
                    System.out.println(link);
                    super.setHttpUrlConnect(link);

                    return getJsonData();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                FragmentManager fm = activity.getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                //fragmentTransaction.replace(R.id.fragment_container, listFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        }
        try {
            SeleteData g = new SeleteData();
            g.execute(params);
        }catch (Exception e){};

    }
    public String show_repl(){
        try {
            JSONArray item = null;


            JSONObject jsonObj = new JSONObject(replJSON);
            item = jsonObj.getJSONArray(binoo_sql_item.TAG_RESULTS);
            String result= " ";
            for(int i=0; i<item.length();i++){

                JSONObject c = item.getJSONObject(i);


                String content = c.getString("content");
                String ts = c.getString("ts");


                result += ("# "+content + " time : " +ts+ "\n");
                System.out.println(result);

            }

            return result;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getrepl(String params){

        class repldata extends abs_binooSyncTask {
            @Override
            protected String doInBackground(String... params) {
                String bbsno = params[0];

                String uri=binoo_sql_item.getInstance().SERVER_ADDRESS + "grumy_list_select_repl.php?bbsno="+bbsno;
                System.out.println(uri);
                try {
                    super.setHttpUrlConnect(uri);

                    return getJsonData();
                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                replJSON=result;


            }
        }
        repldata g = new repldata();
        g.execute(params);


    }

}