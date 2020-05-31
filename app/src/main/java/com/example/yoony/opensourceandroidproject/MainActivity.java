package com.example.yoony.opensourceandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yoony.opensourceandroidproject.db.GoalDataTask;
import com.example.yoony.opensourceandroidproject.db.factory.GoalDAOFactory;
import com.example.yoony.opensourceandroidproject.db.model.Goal;
import com.tedpark.tedonactivityresult.rx2.TedRxOnActivityResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        drawer_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(navigationView);
            }
        });


        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter() {
            @Override
            void setSelected(Goal goal) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("EXTRA_GOAL", goal);
                startActivity(intent);
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,AddActivity.class);
//                startActivityForResult(intent,0);

                TedRxOnActivityResult.with(MainActivity.this)
                        .startActivityForResult(new Intent(getApplicationContext(), AddActivity.class))
                        .subscribe(activityResult -> {
                            L.i("[TedRxOnActivityResult] " + activityResult);
                            if (activityResult.getResultCode() == RESULT_OK) {
//                                Intent data = activityResult.getData();
                                onGoalDataLoad();
                            }

                        }, Throwable::printStackTrace);
            }
        });

        onGoalDataLoad();

    }

    private void onGoalDataLoad() {
        //쓰레드를 이용하여 데이터를 호출한다.. 데이터호출로직은 백그라운드에서 처리되어야한다.
        GoalDataTask task = new GoalDataTask.Builder().setFetcher(new GoalDataTask.DataFetcher() {
            @Override
            public List<com.example.yoony.opensourceandroidproject.db.model.Goal> getData() {
                try {
                    //getGoalList 함수를 통해 최종목표 리스트들을 호출한다.
                    return GoalDAOFactory.getGoalList(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }).setCallback(new GoalDataTask.TaskListener() {
            @Override
            public void onComplete(List<com.example.yoony.opensourceandroidproject.db.model.Goal> data) {
                L.i("::::[onGoalDataLoad] CallBack " + data);
                if (data != null) {
                    recyclerAdapter.updateItems(data);
                }

            }
        }).build();
        task.execute();
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

    /***************************************************************************************************목표세우기 추가*/
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==0){
//            String strMain=data.getStringExtra("main");
//
//            Goal goal=new Goal(strMain,0);
//            recyclerAdapter.addItem(goal);
//            recyclerAdapter.notifyDataSetChanged();
//        }
//    }

    abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
        abstract void setSelected(Goal goal);

        private List<Goal> listdata;

        public RecyclerAdapter() {

        }

        public void updateItems(List<Goal> items) {
            if (this.listdata == null) {
                listdata = new ArrayList<>();
            }
            this.listdata.clear();
            this.listdata.addAll(items);

            notifyDataSetChanged();
        }

        private AdapterView.OnItemClickListener mListener = null;

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {//recycler 빼기
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return new ItemViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return listdata == null ? 0 : listdata.size();
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
            Goal goal = listdata.get(i);

            itemViewHolder.maintext.setText(goal.getGoalTitle());

            //Main 목표의 각 Item 하나씩 클릭할떄마다 발생하는 클릭이벤트
            itemViewHolder.itemView.setOnClickListener(view -> setSelected(goal));

        }

        class ItemViewHolder extends RecyclerView.ViewHolder {

            private TextView maintext;

            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);

                maintext = itemView.findViewById(R.id.item_maintext);

            }
        }
    }
}

