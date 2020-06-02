package com.example.yoony.opensourceandroidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Todo_mainactivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTodo fragmenttodo;
    private FragmentTransaction transaction;
    static TabFragment1 tabfragment1;
    private TabFragment2 tabfragment2;
    private TabFragment3 tabfragment3;
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenttodo_mainactivity);

        //Tablayout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("영화"));
        tabs.addTab(tabs.newTab().setText("여행"));
        tabs.addTab(tabs.newTab().setText("맛집"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //Adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(myPagerAdapter);

        //탭 선택 이벤트
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
/////////////////////////////////////////////////////////////////////////////////////
        fragmentManager = getSupportFragmentManager();
        fragmenttodo = new FragmentTodo();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmenttodo).commitAllowingStateLoss();

    }
}
