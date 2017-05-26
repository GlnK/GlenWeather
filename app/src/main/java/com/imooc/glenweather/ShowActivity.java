package com.imooc.glenweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gln on 2017/5/17.
 */
public class ShowActivity extends AppCompatActivity {
    String cityName;
    String URL3Day;
    private TextView tv_cityName, tv_tip, tv_presentTemp, tv_h1, tv_h2, tv_h3, tv_l1, tv_l2, tv_l3;
    private Bitmap bitmap1, bitmap2, bitmap3;
    private ImageView iv_1, iv_2, iv_3;
    List<String> aa = new ArrayList<>();
    private UpDateUI updateUI = new UpDateUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        getUrls();
        tv_cityName.setText(cityName);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(URL3Day);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(30000);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String result = "";
                    result = stringBuilder.toString();
                    Log.i("TAG", "getData: " + result);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject jsonobj = (JSONObject) jsonObject.getJSONArray("HeWeather5").get(0);
                    JSONObject jsonNow = jsonobj.getJSONObject("now");
                    aa.add(jsonNow.getString("tmp"));//                        1111111当前温度
                    aa.add(jsonobj.getJSONObject("suggestion").getJSONObject("comf").getString("txt"));//222222222tip
                    JSONArray jsonobj3day = jsonobj.getJSONArray("daily_forecast");
                    JSONObject j1d = (JSONObject) jsonobj3day.get(0);
                    aa.add(j1d.getJSONObject("tmp").getString("max"));//333333第一天高
                    aa.add(j1d.getJSONObject("tmp").getString("min"));//44444444第一天低
                    JSONObject j2d = (JSONObject) jsonobj3day.get(1);
                    aa.add(j2d.getJSONObject("tmp").getString("max"));//555555555第二天 高
                    aa.add(j2d.getJSONObject("tmp").getString("min"));//6666666666第二天低
                    JSONObject j3d = (JSONObject) jsonobj3day.get(2);
                    aa.add(j3d.getJSONObject("tmp").getString("max"));//777777第三天高
                    aa.add(j3d.getJSONObject("tmp").getString("min"));//88888888第三天低
                    aa.add(j1d.getJSONObject("cond").getString("code_d"));//999999999第一天代码（“101”）
                    aa.add(j2d.getJSONObject("cond").getString("code_d"));//101010101010第二天代码
                    aa.add(j3d.getJSONObject("cond").getString("code_d"));//1111111111第三天代码
                    URL urlnew = new URL(Config.getInstance().getCodeToUrl(aa.get(8)));
                    HttpURLConnection urlConnection = (HttpURLConnection) urlnew.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setConnectTimeout(10 * 1000);
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    bitmap1 = BitmapFactory.decodeStream(inputStream);
                    urlnew = new URL(Config.getInstance().getCodeToUrl(aa.get(9)));
                    urlConnection = (HttpURLConnection) urlnew.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setConnectTimeout(10 * 1000);
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    bitmap2 = BitmapFactory.decodeStream(inputStream);
                    urlnew = new URL(Config.getInstance().getCodeToUrl(aa.get(10)));
                    urlConnection = (HttpURLConnection) urlnew.openConnection();
                    urlConnection.setDoInput(true);
                    urlConnection.setConnectTimeout(10 * 1000);
                    urlConnection.connect();
                    inputStream = urlConnection.getInputStream();
                    bitmap3 = BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        updateUI.run();

    }

    private void testSharePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] aa = new String[10];
        String[] aak = new String[10];
        for (int i = 0; i < 10; i++) {
            aa[i] = "aakey  " + Integer.toString(i);
            aak[i] = "aaValue   " + Integer.toString(i);
            editor.putString(aa[i], aak[i]);
        }
        editor.putString("name", "GlnK");
        editor.putInt("age", 23);
        editor.commit();
        String name = sharedPreferences.getString("name", null);
        int age = sharedPreferences.getInt("age", 0);
        Log.e("TAG", "name:  " + name + " age:  " + age);
    }


    class UpDateUI implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1500);
                updateUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                tv_tip, tv_presentTemp, tv_h1, tv_h2, tv_h3, tv_l1, tv_l2, tv_l3;
                tv_tip.setText(aa.get(1));
                tv_presentTemp.setText(aa.get(0) + "℃");
                tv_h1.setText(aa.get(2) + "℃");
                tv_h2.setText(aa.get(3) + "℃");
                tv_h3.setText(aa.get(4) + "℃");
                tv_l1.setText(aa.get(5) + "℃");
                tv_l2.setText(aa.get(6) + "℃");
                tv_l3.setText(aa.get(7) + "℃");
                iv_1.setImageBitmap(bitmap1);
                iv_2.setImageBitmap(bitmap2);
                iv_3.setImageBitmap(bitmap3);
            }
        });
    }

    private void getUrls() {
        if (getIntent() != null) {
            cityName = getIntent().getStringExtra("cityName");
        }
        URL3Day = Config.getInstance().get3dayUrl(cityName);
    }

    private void initView() {
        tv_cityName = (TextView) findViewById(R.id.id_tv_show_cityName);
        tv_presentTemp = (TextView) findViewById(R.id.id_tv_show_presentTemp);
        tv_tip = (TextView) findViewById(R.id.id_tv_show_tip);
        tv_h1 = (TextView) findViewById(R.id.tv_1day_high);
        tv_h2 = (TextView) findViewById(R.id.tv_2day_high);
        tv_h3 = (TextView) findViewById(R.id.tv_3day_high);
        tv_l1 = (TextView) findViewById(R.id.tv_1day_low);
        tv_l2 = (TextView) findViewById(R.id.tv_2day_low);
        tv_l3 = (TextView) findViewById(R.id.tv_3day_low);
        iv_1 = (ImageView) findViewById(R.id.iv_1day_icon);
        iv_2 = (ImageView) findViewById(R.id.iv_2day_icon);
        iv_3 = (ImageView) findViewById(R.id.iv_3day_icon);
    }

}