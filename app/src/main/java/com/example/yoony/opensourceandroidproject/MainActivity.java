package com.example.yoony.opensourceandroidproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{

    Calendar cal = Calendar.getInstance();
    int maxDayOfMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int thisMonth=cal.get(Calendar.MONTH);

    ArrayList<SampleData> todoDataList;//샘플 리스트 데이터

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private static final String KEY_NUMBER = "KEY_NUMBER";
    private  int mNumber=0;
    private FragmentManager.OnBackStackChangedListener mListner = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int count = 0;
            Log.d("s",fragmentManager.getFragments().toString());
            for(Fragment f:fragmentManager.getFragments()){
                if(f!=null){
                    count++;
                }
            }
            mNumber = count;
            Log.d("MainActivity","onBackStackChanged mNumber=" + mNumber);
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

        //*****************************************************************************

//        MyFragment todoListFragment = (MyFragment)getSupportFragmentManager().findFragmentById(R.id.listfragment); //리스트프레그먼트 화딱지나서 포기
//        todoListFragment.setToToast(this,myAdapter);
//        todoListFragment.setListAdapter(myAdapter);

        //*****************************************************************************

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.addOnBackStackChangedListener(mListner);
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        Log.d("MainActivity","onCreate fragment ="+fragment+",mNumber ="+mNumber);
//        if(savedInstanceState==null){//최초 프레그먼트 생성
//            Log.d("enterIf","savedInstanceState=null");
//            fragmentManager.beginTransaction()
//                    .add(R.id.fragment_container,MyFragment.getInstace(mNumber),FRAGMENT_TAG)
//                    .addToBackStack(null)
//                    .commit();
//        }

        LinearLayout tabLayout = (LinearLayout)findViewById(R.id.tabWidget);

        for(int i=0;i<maxDayOfMonth;i++){//해당월의 날짜 수 만큼 버튼 및 프레그먼트 생성

            Button btn = new Button(this);//버튼 생성
            btn.setText((i+1)+"일");
            btn.setId((thisMonth*100)+(i+1));
            btn.setOnClickListener(this);
            tabLayout.addView(btn);

            fragmentManager.beginTransaction()//프레그먼트 생성
                    .add(R.id.fragment_container,MyFragment.getInstace(i+1))
                    .addToBackStack(null)
                    .commit();
        }


        this.InitializeToDoData();
        MyAdapter myAdapter = new MyAdapter(this,todoDataList);

        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(myAdapter);//어댑터 연결

//        FragmentManager fragmentManager = getSupportFragmentManager();//프레그먼트 삭제
//        fragmentManager.popBackStack();




        //임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 결정
        //callFragment(FRAGMENT2);
    }

    //*****************************************************************************
    public void InitializeToDoData(){//샘플 리스트 데이터
        todoDataList = new ArrayList<SampleData>();

        todoDataList.add(new SampleData(Color.RED,"할일 1",false));
        todoDataList.add(new SampleData(Color.BLACK,"할일 1",false));
        todoDataList.add(new SampleData(Color.BLUE,"할일 1",false));
        todoDataList.add(new SampleData(Color.GREEN,"할일 1",false));
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

    //    public static void setListViewHeightBasedOnChildren(ListView listView) {//height 분할
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//
//        int totalHeight = 0;
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
//
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//        listView.requestLayout();
//    }
//    //*****************************************************************************
    //탭 클릭시
    @Override
    public void onClick(View v) {
        callFragment(v.getId());
    }

    private void callFragment(int fragment_no){//프래그먼트 전환
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Log.d("transactionFragment",""+getSupportFragmentManager().getFragments().get((fragment_no%100)));
        transaction.replace(R.id.fragment_container,getSupportFragmentManager().getFragments().get((fragment_no%100)));
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