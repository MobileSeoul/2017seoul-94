package com.jmw.src.abs;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by junminwoo on 2016-10-08.
 */

public abstract class abs_binooSyncTask extends AsyncTask<String, Void, String> {

    protected BufferedReader bufR = null;
    protected OutputStreamWriter oW = null;

    public abs_binooSyncTask() {
        super();
    }

    protected URLConnection setUrlConnection(String link){
        try{
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            bufR = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            oW = new OutputStreamWriter(conn.getOutputStream());
            return conn;
        }catch (Exception e){
            return null;
        }
    }
    protected HttpURLConnection setHttpUrlConnect(String link){
        try{
            HttpURLConnection conn = (HttpURLConnection) setUrlConnection(link); // 컨넥션을 열어준다
            bufR = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            oW = new OutputStreamWriter(conn.getOutputStream());

            return conn;
        }catch (Exception e){
            //
            return null;
        }
    }

    protected String getJsonData(){
        try{
            StringBuilder sb = new StringBuilder();
            String json;
            while((json = bufR.readLine())!= null){
                sb.append(json+"\n");
            }
            return sb.toString().trim();
        }catch (Exception e){
            //ignore
            return "FALSE";
        }
    }

    @Override
    protected abstract String doInBackground(String... strings);

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}