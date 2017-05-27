package com.imooc.glenweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gln on 2017/5/16.
 */
public class LoginActivity extends AppCompatActivity {
    private TextView tv_search;
    private EditText tv_cityName;
    private String str_cityName;
    private String cityNameLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
       // setSP();

    }

    private void setSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dengluchengshi", cityNameLast);
        editor.commit();
    }

    private void initEvent() {

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_cityName = tv_cityName.getText().toString();
                String aa = str_cityName;
                for (int i = 0; i < 100; i++) {
                    if (aa.substring(aa.length() - 1, aa.length()).equals(" ")) {
                        aa = aa.substring(0, aa.length() - 1);
                    }
                }
                Log.e("TAGbbb", "" + aa);
                Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                intent.putExtra("cityName", aa);
                cityNameLast = aa;
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        tv_cityName = (EditText) findViewById(R.id.id_tv_cityName);
        tv_search = (TextView) findViewById(R.id.id_tv_search);
    }
}


