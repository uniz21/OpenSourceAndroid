package com.example.yoony.opensourceandroidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.yoony.opensourceandroidproject.db.CommonDAOFactory;

public class SplashActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1000);
            //데이터베이스 import
            CommonDAOFactory.executeCreate(this);
        }catch (InterruptedException e){
            e. printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
