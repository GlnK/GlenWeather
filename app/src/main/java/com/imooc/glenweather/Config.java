package com.imooc.glenweather;

import android.graphics.Bitmap;

/**
 * Created by Gln on 2017/5/17.
 */
public class Config {
    protected String cityName = "";
    private String threeDaystr1 = "https://free-api.heweather.com/v5/weather?city=";
    private String nowstr1 = "https://api.heweather.com/v5/now?city=";
    private String str2 = "&key=791075f8456044679f0b6169655b21be";

    private static Config instance = null;

    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String get3dayUrl(String cityName) {
        this.cityName = cityName;
        String url = threeDaystr1 + cityName + str2;
        return url;
    }
    public String getCodeToUrl(String code){
        return "https://cdn.heweather.com/cond_icon/"+code+".png";
    }

    private String getNowUrl(String cityName) {
        this.cityName = cityName;
        String url = nowstr1 + cityName + str2;
        return url;
    }
}
