package com.example.yoony.opensourceandroidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public MyAdapter(Context context,ArrayList<SampleData> data){
        mContext=context;
        sample=data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_data,null);//리스트 양식 샘플

        LinearLayout questcolor = (LinearLayout)view.findViewById(R.id.questColor);

        TextView todo_thing = (TextView)view.findViewById(R.id.todo_thing);

        CheckBox isDone = (CheckBox)view.findViewById(R.id.isDone);

        questcolor.setBackgroundColor(sample.get(position).getQuest());

        todo_thing.setText(sample.get(position).getJob());

        isDone.setActivated(sample.get(position).isChecked());

        return view;
    }
}
