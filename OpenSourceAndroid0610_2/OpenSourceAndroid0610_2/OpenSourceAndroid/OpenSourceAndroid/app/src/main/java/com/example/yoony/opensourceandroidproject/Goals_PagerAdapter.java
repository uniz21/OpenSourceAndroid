package com.example.yoony.opensourceandroidproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Goals_PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs; //tab의 갯수

    public Goals_PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Goals_Fragment1 tab1 = new Goals_Fragment1();
                return tab1;
            case 1:
                Goals_Fragment2 tab2 = new Goals_Fragment2();
                return tab2;
            case 2:
                Goals_Fragment3 tab3 = new Goals_Fragment3();
                return tab3;
            default:
                return null;
        }
        //return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
