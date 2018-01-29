package com.example.jj.project1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jmw.adaptertest.DB_jungwon;

import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by won on 2016. 12. 4..
 */
public class WriteActivity extends BaseActivity {
    int[] wrImage = {R.drawable.cloudy, R.drawable.lcloudy,R.drawable.rainy, R.drawable.sunny, R.drawable.thunder,R.drawable.snowy};
    int n=0;

    String str = null;
    final int REQ_CODE_SELECT_IMAGE=100;
    EditText i_text;
    String chk = "1";
    String name_Str;
    public String uploadFilePath ="";
    public String uploadFileName ="";
    String upLoadServerUri = null;
    int aaa = 1;
    private int serverResponseCode = 0;

    String[] i_data;
    int number;
    Global myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        ImageView wrWeather = (ImageView)findViewById(R.id.wrWeather);
        wrWeather.setImageResource(R.drawable.cloudy);
        myApp = (Global)getApplicationContext();
        upLoadServerUri = "http://118.37.234.70:10080/UploadToServer.php";//서버컴퓨터의 ip주소

        i_text = (EditText)findViewById(R.id.wr_content);
        ImageView mainWeather = (ImageView)findViewById(R.id.mainWeather);
        TextView locationDong = (TextView)findViewById(R.id.locationDong);
        try{
            FileInputStream inFs = openFileInput("LocationSetting.txt");
            byte[] txt = new byte[inFs.available()];
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

        }catch(IOException e){};

    }
    public int uploadFile(String sourceFileUri) {

        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {



            Log.e("uploadFile", "Source File not exist :"
                    +uploadFilePath + "" + uploadFileName);

            runOnUiThread(new Runnable() {
                public void run() {

                }
            });

            return 0;

        }
        else
        {
            try {


                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);


                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + fileName + "\"" + lineEnd);

                dos.writeBytes(lineEnd);


                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];


                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {

                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    +uploadFileName;



                        }
                    });
                }


                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {


                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {

                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {

                    }
                });
                Log.e("Upload Exception", "Exception : " + e.getMessage(), e);
            }

            return serverResponseCode;

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Toast.makeText(getBaseContext(), "resultCode : " + resultCode, Toast.LENGTH_SHORT).show();

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {

                    name_Str = getImageNameToUri(data.getData());

                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.SelectedImage);

                    image.setImageBitmap(image_bitmap);





                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        uploadFilePath = cursor.getString(column_index);
        uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf("/")+1);

        return uploadFileName;
    }



    public void mOnClick(View v){
        ImageView wrWeather = (ImageView)findViewById(R.id.wrWeather);
        TextView now = (TextView)findViewById(R.id.now);
        TextView future = (TextView)findViewById(R.id.future);
        TextView cloud = (TextView)findViewById(R.id.cloud);
        Button pUpload = (Button)findViewById(R.id.pUpload);
        String color1 = "#ddffffff";
        String color2 = "#55ffffff";
        Intent intent;


        switch (v.getId()) {
            case R.id.pUpload:
                Intent intent4 = new Intent(Intent.ACTION_PICK);
                intent4.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent4.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent4, REQ_CODE_SELECT_IMAGE);
                break;
            case R.id.wrComplete:
                String title = "cloud6";
                String content = i_text.getText().toString();
                String bbscode = Integer.toString(number);
                String pid = "010-9471-8770";

                String wether = Integer.toString(n);



                if(! uploadFilePath.equals("")){
                    i_data = new String[] {title,content,chk,bbscode,pid,wether,uploadFileName};

                    try{
                        new Thread(new Runnable() {
                            public void run() {
                                uploadFile(uploadFilePath);

                            }
                        }).start();
                    }catch(Exception e){
                        Log.i("runnable - exception : ", e.getMessage());
                    }
                }else{
                    i_data = new String[] {title,content,chk,bbscode,pid,wether,""};
                }
                DB_jungwon.getInstance().insertToDatabase(i_data);
                Toast.makeText(this,"입력완료",Toast.LENGTH_SHORT).show();
                intent = new Intent(WriteActivity.this,MainActivity.class );
                startActivity(intent);




                break;
            case R.id.inform:
                intent = new Intent(WriteActivity.this,InformActivity.class );
                startActivity(intent);
                break;
            case R.id.home:
                intent = new Intent(WriteActivity.this,MainActivity.class );
                startActivity(intent);
                break;
            case R.id.locationsetting:
                break;
            case R.id.appsetting:
                intent = new Intent(WriteActivity.this,SettingActivity.class );
                startActivity(intent);
                break;
            case R.id.now:

                now.setBackgroundColor(Color.parseColor(color1));
                future.setBackgroundColor(Color.parseColor(color2));
                cloud.setBackgroundColor(Color.parseColor(color2));
                pUpload.setVisibility(View.GONE);

                chk = "1";
                break;
            case R.id.future:

                now.setBackgroundColor(Color.parseColor(color2));
                future.setBackgroundColor(Color.parseColor(color1));
                cloud.setBackgroundColor(Color.parseColor(color2));
                pUpload.setVisibility(View.VISIBLE);

                chk = "2";
                break;

            case R.id.cloud:
                now.setBackgroundColor(Color.parseColor(color2));
                future.setBackgroundColor(Color.parseColor(color2));
                cloud.setBackgroundColor(Color.parseColor(color1));
                pUpload.setVisibility(View.VISIBLE);
                chk = "3";
                break;

            case R.id.next:
                n++;
                if(n>5) {
                    n = 0;
                }

                wrWeather.setImageResource(wrImage[n]);
                break;
            case R.id.before:
                n--;
                if(n<0) {
                    n = 5;
                }

                wrWeather.setImageResource(wrImage[n]);

                break;



        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(WriteActivity.this,MainActivity.class );
        startActivity(intent);
    }
}