package com.example.jj.project1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by won on 2016. 12. 4..
 */
public class FreeBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){//브로드캐스트 정보를 받으면 토스트
        Toast.makeText(context,"네트워크 연결을 확인해주세요!",Toast.LENGTH_SHORT ).show();
    }
}