package com.example.yoony.opensourceandroidproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_tab1,bt_tab2;

    Calendar cal = Calendar.getInstance();
    int maxDayOfMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int thisMonth=cal.get(Calendar.MONTH);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_todo);

        //위젯에 대한 참조
        bt_tab1 = (Button)findViewById(R.id.tabBtnTest);
        bt_tab2 = (Button)findViewById(R.id.tabBtnTest6);

        //탭 버튼에 대한 리스너 연결
        bt_tab1.setOnClickListener(this);
        bt_tab2.setOnClickListener(this);

        LinearLayout tabLayout = (LinearLayout)findViewById(R.id.tabWidget);

        for(int i=0;i<maxDayOfMonth;i++){//해당월의 날짜 수 만큼 버튼 생성
            Button btn = new Button(this);
            btn.setText((i+1)+"일");
            btn.setId((thisMonth*100)+(i+1));
            tabLayout.addView(btn);
        }

        //임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 결정
        //callFragment(FRAGMENT2);
    }

    //탭 클릭시
    @Override
    public void onClick(View v) {
        callFragment(v.getId());
    }

    private void callFragment(int fragment_no){

        //프레그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment_no){
            //Fragment 호출
//            case 1:
//                Fragment1 fragment1 = new Fragment1();
//                transaction.replace(R.id.content,fragment1);
//                transaction.commit();
//                break;
//            case 2:
//                Fragment2 fragment2 = new Fragment2();
//                transaction.replace(R.id.content,fragment2);
//                transaction.commit();
//                break;
        }
    }
}





















//package com.example.yoony.opensourceandroidproject;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//
//public class MainActivity extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener {
//
////    ArrayList<SampleData> todoDataList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setTitle("당근과 채찍");
//
//        ImageButton drawer_open = (ImageButton) findViewById(R.id.drawer_open);
//
//        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });*/
//
//        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        drawer_open.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public  void onClick(View v){
//                drawer.openDrawer(navigationView);
//            }
//        });
//
////        //*****************************************************************************
////        this.InitializeToDoData();
////        ListView listView = (ListView)findViewById(R.id.listView);
////        final MyAdapter myAdapter = new MyAdapter(this,todoDataList);
////
////        listView.setAdapter(myAdapter);
////
////        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView parent, View view, int position, long id) {
////                Toast.makeText(getApplicationContext(),myAdapter.getItem(position).getJob(),Toast.LENGTH_LONG).show();
////            }
////        });
////        //*****************************************************************************
//    }
//
////    //*****************************************************************************
////    public void InitializeToDoData(){
////        todoDataList = new ArrayList<SampleData>();
////
////        todoDataList.add(new SampleData(Color.RED,"할일 1",false));
////        todoDataList.add(new SampleData(Color.BLACK,"할일 1",false));
////        todoDataList.add(new SampleData(Color.BLUE,"할일 1",false));
////        todoDataList.add(new SampleData(Color.GREEN,"할일 1",false));
////    }
////    public static void setListViewHeightBasedOnChildren(ListView listView) {//height 분할
////        ListAdapter listAdapter = listView.getAdapter();
////        if (listAdapter == null) {
////            // pre-condition
////            return;
////        }
////
////        int totalHeight = 0;
////        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
////
////        for (int i = 0; i < listAdapter.getCount(); i++) {
////            View listItem = listAdapter.getView(i, null, listView);
////            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
////            totalHeight += listItem.getMeasuredHeight();
////        }
////
////        ViewGroup.LayoutParams params = listView.getLayoutParams();
////        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
////        listView.setLayoutParams(params);
////        listView.requestLayout();
////    }
////    //*****************************************************************************
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_main) {
//            // Handle the main action
//        } else if (id == R.id.nav_todo) {
//
//        } else if (id == R.id.nav_goals) {
//
//        } else if (id == R.id.nav_community) {
//
//        } else if (id == R.id.nav_shop) {
//
//        } else if (id == R.id.nav_setting) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//}
