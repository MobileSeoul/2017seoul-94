package com.jmw.src.manager;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;

/**
 * Created by junminwoo on 2016-10-01.
 */
public class itemManager{

    private static itemManager instance;

    public static itemManager getInstance(){
        return instance = (instance == null ? new itemManager() : instance);
    }

    public String getCurrentMacAddress(AppCompatActivity activity) {
        //리턴값이 Mac Address 입니다.
        String macAddress = "";
        boolean bIsWifiOff = false;
        WifiManager wfManager = (WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
        if (!wfManager.isWifiEnabled()) {
            wfManager.setWifiEnabled(true);
            bIsWifiOff = true;
        }
        WifiInfo wfInfo = wfManager.getConnectionInfo();
        macAddress = wfInfo.getMacAddress();
        if (bIsWifiOff) {
            wfManager.setWifiEnabled(false);
            bIsWifiOff = false;
        }
        return macAddress;
    }

    public String getDeviceId(AppCompatActivity activity){
        //리턴값이 디바이스 아이디입니다.
        String deviceId = "";
        TelephonyManager manager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = manager.getDeviceId();

        return deviceId;
    }
}
