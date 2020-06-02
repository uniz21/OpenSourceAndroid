package com.example.yoony.opensourceandroidproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Calendar;

public class FragmentTodo extends Fragment {
    Calendar cal = Calendar.getInstance();
    int maxDayOfMonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int thisDay=cal.get(Calendar.DAY_OF_MONTH)+1;
    int thisMonth=cal.get(Calendar.MONTH)+1;

    int fragmentPageNow=0;

    LinearLayout tabLayout,btnLayout;

    Button addListBtn,addBtn;

    private static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    private static final String KEY_NUMBER = "KEY_NUMBER";
    private  int mNumber=0;
    private FragmentManager.OnBackStackChangedListener mListner = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            FragmentManager fragmentManager = getFragmentManager();
            int count = 0;
            for(Fragment f:fragmentManager.getFragments()){
                if(f!=null){
                    count++;
                }
            }
            mNumber = count;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.today_todo,null);
        tabLayout=(LinearLayout)view.findViewById(R.id.tabLayout);
        btnLayout=(LinearLayout)view.findViewById(R.id.btnLayout);

        Log.d("maxDayOfMonth", ""+maxDayOfMonth);
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(mListner);
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        Log.d("MainActivity","onCreate fragment ="+fragment);
        if(savedInstanceState==null){//초기 프레그먼트 생성
            Log.d("enterIf","savedInstanceState=null");
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,MyFragment.getInstace(thisDay),FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit();
            fragmentPageNow=thisDay;
        }

        final LinearLayout tabWidgetLayout = (LinearLayout)view.findViewById(R.id.tabWidget);

        for(int i=0;i<maxDayOfMonth;i++){//해당월의 날짜 수 만큼 버튼 생성//for문 실행 이상함
            Log.d("for",""+i+1);
            Button btn = new Button(view.getContext());//버튼 생성
            btn.setText((i+1)+"일");
            btn.setId((thisMonth*100)+(i+1));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callFragment(v.getId());
                }
            });
            tabWidgetLayout.addView(btn);
        }

        addListBtn=(Button)view.findViewById(R.id.addListBtn);
        addListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//리스너 미작동
                Log.d("onclick","clicked");
                AddListFragment alFragment=new AddListFragment();
                alFragment.getInstance(fragmentPageNow,tabLayout,btnLayout);
                fragmentManager.beginTransaction().replace(R.id.fragment_container,alFragment).addToBackStack(null).commit();
                tabLayout.setVisibility(View.INVISIBLE);
                btnLayout.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }
    @Override
    public void onDestroy() {//종료시 백스택리스너 삭제
        super.onDestroy();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.removeOnBackStackChangedListener(mListner);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {//홈버튼 누를때 상태 저장
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER,mNumber);
    }

    private void callFragment(int fragment_no){//프래그먼트 전환
        int i=(fragment_no%100);
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,MyFragment.getInstace(i)).addToBackStack(null).commit();

    }
}

