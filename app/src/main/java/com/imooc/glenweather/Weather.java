package com.imooc.glenweather;

import android.graphics.Bitmap;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Gln on 2017/5/18.
 */


@DatabaseTable(tableName = "tb_weather")
public class Weather {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "cityName")
    private String cityName;
    @DatabaseField(columnName = "tip")
    private String tip;
    @DatabaseField(columnName = "presentTemp")
    private String presentTemp;
    @DatabaseField(columnName = "lowOne")
    private String lowOne;
    @DatabaseField(columnName = "highOne")
    private String highOne;
    @DatabaseField(columnName = "lowTwo")
    private String lowTwo;
    @DatabaseField(columnName = "highTwo")
    private String highTwo;
    @DatabaseField(columnName = "lowThree")
    private String lowThree;
    @DatabaseField(columnName = "highThree")
    private String highThree;

    @DatabaseField(columnName = "iconStrOne")
    private String iconStrOne;
    @DatabaseField(columnName = "bitMapOne")
    private Bitmap bitMapOne;
    @DatabaseField(columnName = "iconStrTwo")
    private String iconStrTwo;
    @DatabaseField(columnName = "bitMapTwo")
    private Bitmap bitMapTwo;
    @DatabaseField(columnName = "iconStrThree")
    private String iconStrThree;
    @DatabaseField(columnName = "bitMapThree")
    private Bitmap bitMapThree;


    public Weather() {
    }

    public Weather(String cityName, String tip, String presentTemp, String lowOne, String highOne, String lowTwo, String highTwo, String lowThree, String highThree, String iconStrOne, Bitmap bitMapOne, String iconStrTwo, Bitmap bitMapTwo, String iconStrThree, Bitmap bitMapThree) {
        this.cityName = cityName;
        this.tip = tip;
        this.presentTemp = presentTemp;
        this.lowOne = lowOne;
        this.highOne = highOne;
        this.lowTwo = lowTwo;
        this.highTwo = highTwo;
        this.lowThree = lowThree;
        this.highThree = highThree;
        this.iconStrOne = iconStrOne;
        this.bitMapOne = bitMapOne;
        this.iconStrTwo = iconStrTwo;
        this.bitMapTwo = bitMapTwo;
        this.iconStrThree = iconStrThree;
        this.bitMapThree = bitMapThree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPresentTemp() {
        return presentTemp;
    }

    public void setPresentTemp(String presentTemp) {
        this.presentTemp = presentTemp;
    }

    public String getLowOne() {
        return lowOne;
    }

    public void setLowOne(String lowOne) {
        this.lowOne = lowOne;
    }

    public String getHighOne() {
        return highOne;
    }

    public void setHighOne(String highOne) {
        this.highOne = highOne;
    }

    public String getLowTwo() {
        return lowTwo;
    }

    public void setLowTwo(String lowTwo) {
        this.lowTwo = lowTwo;
    }

    public String getHighTwo() {
        return highTwo;
    }

    public void setHighTwo(String highTwo) {
        this.highTwo = highTwo;
    }

    public String getLowThree() {
        return lowThree;
    }

    public void setLowThree(String lowThree) {
        this.lowThree = lowThree;
    }

    public String getHighThree() {
        return highThree;
    }

    public void setHighThree(String highThree) {
        this.highThree = highThree;
    }

    public String getIconStrOne() {
        return iconStrOne;
    }

    public void setIconStrOne(String iconStrOne) {
        this.iconStrOne = iconStrOne;
    }

    public Bitmap getBitMapOne() {
        return bitMapOne;
    }

    public void setBitMapOne(Bitmap bitMapOne) {
        this.bitMapOne = bitMapOne;
    }

    public String getIconStrTwo() {
        return iconStrTwo;
    }

    public void setIconStrTwo(String iconStrTwo) {
        this.iconStrTwo = iconStrTwo;
    }

    public Bitmap getBitMapTwo() {
        return bitMapTwo;
    }

    public void setBitMapTwo(Bitmap bitMapTwo) {
        this.bitMapTwo = bitMapTwo;
    }

    public String getIconStrThree() {
        return iconStrThree;
    }

    public void setIconStrThree(String iconStrThree) {
        this.iconStrThree = iconStrThree;
    }

    public Bitmap getBitMapThree() {
        return bitMapThree;
    }

    public void setBitMapThree(Bitmap bitMapThree) {
        this.bitMapThree = bitMapThree;
    }
}
