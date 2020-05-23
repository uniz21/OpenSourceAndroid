package com.example.yoony.opensourceandroidproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

    Calendar cal = Calendar.getInstance();
    int maxDayOfMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int thisDay=cal.get(Calendar.DAY_OF_MONTH);
    int thisMonth=cal.get(Calendar.MONTH)+1;

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private static final String KEY_NUMBER = "KEY_NUMBER";
    private  int mNumber=0;
    private FragmentManager.OnBackStackChangedListener mListner = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int count = 0;
            for(Fragment f:fragmentManager.getFragments()){
                if(f!=null){
                    count++;
                }
            }
            mNumber = count;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("당근과 채찍");

        ImageButton drawer_open = (ImageButton) findViewById(R.id.drawer_open);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer_open.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                drawer.openDrawer(navigationView);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
       // fragmentManager.addOnBackStackChangedListener(mListner);
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        Log.d("MainActivity","onCreate fragment ="+fragment);
        if(savedInstanceState==null){//초기 프레그먼트 생성
            Log.d("enterIf","savedInstanceState=null");
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,MyFragment.getInstace(thisDay),FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
        }

        LinearLayout tabLayout = (LinearLayout)findViewById(R.id.tabWidget);

        for(int i=0;i<maxDayOfMonth;i++){//해당월의 날짜 수 만큼 버튼 생성

            Button btn = new Button(this);//버튼 생성
            btn.setText((i+1)+"일");
            btn.setId((thisMonth*100)+(i+1));
            btn.setOnClickListener(this);
            tabLayout.addView(btn);

        }

//        FragmentManager fragmentManager = getSupportFragmentManager();//프레그먼트 삭제
//        fragmentManager.popBackStack();

    }

    @Override
    protected void onDestroy() {//종료시 백스택리스너 삭제
        super.onDestroy();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.removeOnBackStackChangedListener(mListner);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {//홈버튼 누를때 상태 저장
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER,mNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//복원
        super.onRestoreInstanceState(savedInstanceState);
        mNumber = savedInstanceState.getInt(KEY_NUMBER);
    }

    //탭 클릭시
    @Override
    public void onClick(View v) {
        callFragment(v.getId());
    }

    private void callFragment(int fragment_no){//프래그먼트 전환
        int i=(fragment_no%100);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,MyFragment.getInstace(i)).addToBackStack(null).commit();//replace로 했더니 프레그먼트매니저가 초기화된다.

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the main action
        } else if (id == R.id.nav_todo) {

        } else if (id == R.id.nav_goals) {

        } else if (id == R.id.nav_community) {

        } else if (id == R.id.nav_shop) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}