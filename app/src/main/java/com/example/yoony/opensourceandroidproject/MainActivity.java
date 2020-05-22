package com.example.yoony.opensourceandroidproject;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentMain fragmentmain = new FragmentMain();
        transaction.replace(R.id.content_fragment_layout, fragmentmain);
        transaction.commit();

        drawer_open.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                drawer.openDrawer(navigationView);
            }
        });
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
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentMain fragmentmain = new FragmentMain();
            transaction.replace(R.id.content_fragment_layout, fragmentmain);
            transaction.commit();

        } else if (id == R.id.nav_todo) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentTodo fragmenttodo = new FragmentTodo();
            transaction.replace(R.id.content_fragment_layout, fragmenttodo);
            transaction.commit();

        } else if (id == R.id.nav_goals) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentGoals fragmentgoals = new FragmentGoals();
            transaction.replace(R.id.content_fragment_layout, fragmentgoals);
            transaction.commit();

        } else if (id == R.id.nav_shop) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentShop fragmentshop = new FragmentShop();
            transaction.replace(R.id.content_fragment_layout, fragmentshop);
            transaction.commit();

        } else if (id == R.id.nav_setting) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            FragmentSetting fragmentsetting = new FragmentSetting();
            transaction.replace(R.id.content_fragment_layout, fragmentsetting);
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
