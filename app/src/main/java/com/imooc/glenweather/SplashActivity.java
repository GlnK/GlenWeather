package com.imooc.glenweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Gln on 2017/5/16.
 */
public class SplashActivity extends AppCompatActivity {
    private Button button;
    private Handler mHandler = new Handler();
    private int denglucishu = 0;
    private String SPcityName;

    private Runnable mRunnableToLogin = new Runnable() {
        @Override
        public void run() {
            toLoginActivity();
        }
    };
    private Runnable mRunnableToShow = new Runnable() {
        @Override
        public void run() {
            toShowActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        button = (Button) findViewById(R.id.id_btn_skip);
        setSP();
        getSP();
        if (denglucishu == 1) {
            initEventToLogin();
            mHandler.postDelayed(mRunnableToLogin, 500);
        } else {
            initEventToShow();
            mHandler.postDelayed(mRunnableToShow, 500);
        }
    }

    private void setSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("anthor", "Glen");
        editor.commit();
    }


    private void getSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        denglucishu = sharedPreferences.getInt("denglucishu", 1);
        SPcityName = sharedPreferences.getString("dengluchengshi", "beijing");
        mHandler.postDelayed(mRunnableToLogin, 500);

    }

    private void initEventToLogin() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnableToLogin);
                toLoginActivity();
            }
        });
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initEventToShow() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(mRunnableToShow);
                toLoginActivity();
            }
        });
    }

    private void toShowActivity() {
        Intent intent = new Intent(this, ShowActivity.class);
        intent.putExtra("cityName", SPcityName);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnableToLogin);
    }
}
