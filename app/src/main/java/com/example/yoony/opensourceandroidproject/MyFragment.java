package com.example.yoony.opensourceandroidproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyFragment extends Fragment {

    private static final String ARG_NO = "ARG_NO";

    public MyFragment() {
    }

    public static MyFragment getInstace(int no){
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NO,no);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfragment,null);
        ArrayList<SampleData> a=new ArrayList<SampleData>();//데이터베이스
//        for(int i=0;i<a.size();i++){
//            if (a.get(i).isChecked()){
//                a.add(a.get(i));
//                a.remove(i);
//            }
//        }
        a.add(new SampleData(Color.RED,"할일 1",false));
        a.add(new SampleData(Color.BLACK,"할일 1",false));
        a.add(new SampleData(Color.BLUE,"할일 1",false));
        a.add(new SampleData(Color.GREEN,"할일 1",false));

        MyAdapter myAdapter = new MyAdapter(getActivity(),a);

        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(myAdapter);//어댑터 연결

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int no = getArguments().getInt(ARG_NO,0);
        String text = "" + no + "번째 프래그먼트";
        Log.d("MyFragment","onCreate"+text);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView)view.findViewById(R.id.text);
        int no = getArguments().getInt(ARG_NO,0);
        String text = "" + no + "번째 프래그먼트";
        Log.d("MyFragment","onCreate"+text);
        textView.setText(text);//프레그먼트 전환 구분
    }
}
