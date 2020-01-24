package com.example.mysubmission3.function;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetect {
    private Context ctx;
    public ConnectionDetect(Context context){
        this.ctx = context;
    }

    public boolean isConnect(){
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if(info != null ){
                for(int i = 0; i <  info.length; i++){
                    if(info[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
