package com.example.yoony.opensourceandroidproject;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.example.yoony.opensourceandroidproject.TabFragment1;
import com.example.yoony.opensourceandroidproject.TabFragment2;
import com.example.yoony.opensourceandroidproject.TabFragment3;



import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {


    private int mPageCount;



    public PagerAdapter(FragmentManager fm, int pageCount) {

        super(fm);

        this.mPageCount = pageCount;

    }



    @Override

    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                TabFragment1 tabFragment1 = new TabFragment1();

                return tabFragment1;


            case 1:

                TabFragment2 tabFragment2 = new TabFragment2();

                return tabFragment2;


            case 2:

                TabFragment3 tabFragment3 = new TabFragment3();

                return tabFragment3;


            default:
                return null;
        }
    }

    @Override

    public int getCount() {

        return mPageCount;

    }
}
