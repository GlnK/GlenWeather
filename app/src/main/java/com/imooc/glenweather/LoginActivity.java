package com.imooc.glenweather;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        initEvent();
    }

    private void initEvent() {

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_cityName = tv_cityName.getText().toString();
                Log.e("TAGbbb", ""+str_cityName );
                Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                intent.putExtra("cityName", str_cityName);
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

