package com.example.yoony.opensourceandroidproject;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Goals_PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs; //tab의 갯수
    String[] str;

    public Goals_PagerAdapter(FragmentManager fm, int numOfTabs,String[] str) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.str=str;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==(mNumOfTabs-1)){
            Log.e("position",""+position);
            Goals_Fragment1 tab1 = new Goals_Fragment1();
            tab1.getInstance(str[position-1]);
            return tab1;
        }else{
            Log.e("position",""+position);
            Goals_Fragment1 tab1 = new Goals_Fragment1();
            tab1.getInstance(str[position]);
            return tab1;
        }
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}