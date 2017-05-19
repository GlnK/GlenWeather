package com.imooc.glenweather;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gln on 2017/5/17.
 */
public class NetGson {

    private static NetGson instance = null;

    public static NetGson getInstance() {
        if (instance == null) {
            instance = new NetGson();
        }
        return instance;
    }


}
