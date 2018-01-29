package com.example.jj.project1;

/**
 * Created by won on 2016. 10. 31..
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



public class binoo_Image_parse {
    public String getImage(String link, String bbsno){
        try{
            BufferedReader bufR = null;
            OutputStreamWriter oW = null;

            URL url = new URL(link + "/grumy_get_image.php?");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            String data  = URLEncoder.encode("bbsno", "UTF-8") + "=" + URLEncoder.encode(bbsno, "UTF-8");
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
            return showItemRsc(sb.toString());

        }catch (Exception e){

        }
        return null;
    }


    public String showItemRsc(String myJSON){

        try {
            JSONArray item = null;
            JSONObject jsonObj = new JSONObject(myJSON);
            item = jsonObj.getJSONArray("result");
            String cur_im = "";

            for(int i=0; i<item.length();i++){
                JSONObject c = item.getJSONObject(i);
                cur_im = c.getString("cur_im");
                Log.i("", cur_im);
            }

            return cur_im;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}