package com.example.yoony.opensourceandroidproject;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class MyFragment extends Fragment {
    ArrayList<SampleData> a=new ArrayList<SampleData>();//데이터베이스
    MyAdapter myAdapter;
    DBHelper dbHelper;

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
        dbHelper=new DBHelper(view.getContext(),"QuestApp.db",null,1);
        if(dbHelper.sortTodo(getArguments().getInt(ARG_NO,0))!="") {
            createList();
        }
        myAdapter = new MyAdapter(getActivity(),a);
        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(myAdapter);//어댑터 연결

        return view;
    }

    public void createList(){
        String temp[] = dbHelper.sortTodo(getArguments().getInt(ARG_NO,0)).split("\n");
        String data[][] = new String[5][temp.length];
        for (int i = 0; i < temp.length; i++) {
            for (int k = 0; k < 5; k++) {
                data[k][i] = temp[i].split("\\|")[k];
            }
            a.add(new SampleData(Integer.parseInt(data[0][i]), Color.RED, data[1][i], Integer.parseInt(data[4][i])));
        }
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
    public class MyAdapter extends BaseAdapter {

        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        ArrayList<SampleData> sample;

        public MyAdapter(Context context, ArrayList<SampleData> data) {
            mContext = context;
            sample = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return sample.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public SampleData getItem(int position) {
            return sample.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View view = mLayoutInflater.inflate(R.layout.list_data, null);//리스트 양식 샘플

            LinearLayout questcolor = (LinearLayout) view.findViewById(R.id.questColor);
            TextView todo_thing = (TextView) view.findViewById(R.id.todo_thing);
            CheckBox isDone = (CheckBox) view.findViewById(R.id.isDone);

            questcolor.setBackgroundColor(sample.get(position).getQuest());
            todo_thing.setText(sample.get(position).getJob());
            isDone.setChecked(sample.get(position).isChecked());

            isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        DBHelper dbHelper = new DBHelper(view.getContext(), "QuestApp.db", null, 1);
                        dbHelper.updateisDone(sample.get(position).getId(), 1);
                        sample.get(position).setIschecked(true);
                    } else {
                        DBHelper dbHelper = new DBHelper(view.getContext(), "QuestApp.db", null, 1);
                        dbHelper.updateisDone(sample.get(position).getId(), 0);
                        sample.get(position).setIschecked(false);
                    }
                    Collections.sort(sample, new Comparator<SampleData>() {
                            @Override
                            public int compare(SampleData o1, SampleData o2) {
                                if (o1.getisDone() < o2.getisDone()) {
                                    return -1;
                                } else if (o1.getisDone() > o2.getisDone()) {
                                    return 1;
                                }
                                return 0;
                            }
                    });
                    myAdapter.notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
