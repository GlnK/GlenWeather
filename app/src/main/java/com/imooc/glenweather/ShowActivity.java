package com.imooc.glenweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
    private ImageView iv_1, iv_2, iv_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        getUrls();
        tv_cityName.setText(cityName);
        new dataTask().execute(URL3Day);
    }

    public class dataTask extends AsyncTask<String, Integer, Weather> {
        @Override
        protected Weather doInBackground(String... strings) {
            List<String> aa = new ArrayList<>();
            try {
                URL url = new URL(strings[0]);
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
                JSONObject jsonObject = new JSONObject(result);
                JSONObject jsonobj = (JSONObject) jsonObject.getJSONArray("HeWeather5").get(0);
                JSONObject jsonNow = jsonobj.getJSONObject("now");
//                aa.add(jsonNow.getJSONObject("cond").getString("code"));//天气code
//                aa.add(jsonNow.getJSONObject("cond").getString("txt"));// 天气中文
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
                Weather weather = new Weather();
                weather.setPresentTemp(aa.get(0));
                weather.setTip(aa.get(1));
                weather.setHighOne(aa.get(2));
                weather.setLowOne(aa.get(3));
                weather.setHighTwo(aa.get(4));
                weather.setLowTwo(aa.get(5));
                weather.setHighThree(aa.get(6));
                weather.setLowThree(aa.get(7));
                weather.setIconStrOne(aa.get(8));
                URL url23 = new URL(Config.getInstance().getCodeToUrl(weather.getIconStrOne()));
                HttpURLConnection urlConnection = (HttpURLConnection) url23.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(10 * 1000);
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                weather.setBitMapOne(bitmap);
                weather.setIconStrTwo(aa.get(9));
                URL url232 = new URL(Config.getInstance().getCodeToUrl(weather.getIconStrTwo()));
                HttpURLConnection urlConnection2 = (HttpURLConnection) url232.openConnection();
                urlConnection2.setDoInput(true);
                urlConnection2.setConnectTimeout(10 * 1000);
                urlConnection2.connect();
                InputStream inputStream2 = urlConnection2.getInputStream();
                Bitmap bitmap2 = BitmapFactory.decodeStream(inputStream2);
                weather.setBitMapTwo(bitmap2);
                weather.setIconStrThree(aa.get(10));
                URL url2322 = new URL(Config.getInstance().getCodeToUrl(weather.getIconStrThree()));
                HttpURLConnection urlConnection22 = (HttpURLConnection) url2322.openConnection();
                urlConnection22.setDoInput(true);
                urlConnection22.setConnectTimeout(10 * 1000);
                urlConnection22.connect();
                InputStream inputStream22 = urlConnection22.getInputStream();
                Bitmap bitmap22 = BitmapFactory.decodeStream(inputStream22);
                weather.setBitMapThree(bitmap22);
                return weather;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            tv_tip.setText(weather.getTip());
            tv_presentTemp.setText(weather.getPresentTemp() + "℃");
            tv_h1.setText(weather.getHighOne() + "℃");
            tv_h2.setText(weather.getHighTwo() + "℃");
            tv_h3.setText(weather.getHighThree() + "℃");
            tv_l1.setText(weather.getLowOne() + "℃");
            tv_l2.setText(weather.getLowTwo() + "℃");
            tv_l3.setText(weather.getLowThree() + "℃");
            iv_1.setImageBitmap(weather.getBitMapOne());
            iv_2.setImageBitmap(weather.getBitMapTwo());
            iv_3.setImageBitmap(weather.getBitMapThree());
        }
    }

    private void getUrls() {
        if (getIntent() != null) {
            cityName = getIntent().getStringExtra("cityName");
        }
        Log.e("TAGccc", "" + cityName);
        URL3Day = Config.getInstance().get3dayUrl(cityName);
        Log.e("TAGddd", "" + URL3Day);
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