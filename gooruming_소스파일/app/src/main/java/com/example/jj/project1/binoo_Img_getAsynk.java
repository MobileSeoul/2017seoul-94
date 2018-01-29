package com.example.jj.project1;

/**
 * Created by won on 2016. 10. 31..
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



public class binoo_Img_getAsynk extends AsyncTask<Void, Void, Void> {

    private String server = "http://118.37.234.70:10080"; //서버주소
    private String folder = "/uploads/";
    private Bitmap bit;
    private String imgItem = "";
    private ImageView img;
    private String bbsno;

    public binoo_Img_getAsynk(ImageView img, String bbsno){
        this.img = img;
        this.bbsno = bbsno;
    }

    public binoo_Img_getAsynk(ImageView img){
        this.img = img;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);

        img.setImageBitmap(bit);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Void s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(Void... v) {
        //서버에서 쿼리문으로 cur_im 가져오기
        //그 다음
        //결과는 여기 저장됨

        imgItem = new binoo_Image_parse().getImage(server, bbsno); //param2 -> bbsno 들어감

        try{
            URLConnection conn = null;
            URL imageURL = new URL(server+folder+imgItem);
            conn = imageURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bit = BitmapFactory.decodeStream(bis);
            Log.i("", server+folder+imgItem);
        }catch (Exception e){

        }

        return null;
    }
}