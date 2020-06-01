package com.example.yoony.opensourceandroidproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View view = mLayoutInflater.inflate(R.layout.list_data,null);//리스트 양식 샘플

        LinearLayout questcolor = (LinearLayout)view.findViewById(R.id.questColor);

        TextView todo_thing = (TextView)view.findViewById(R.id.todo_thing);

        CheckBox isDone = (CheckBox)view.findViewById(R.id.isDone);

        questcolor.setBackgroundColor(sample.get(position).getQuest());

        todo_thing.setText(sample.get(position).getJob());

        isDone.setChecked(sample.get(position).isChecked());

        isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    DBHelper dbHelper=new DBHelper(view.getContext(),"QuestApp.db",null,1);
                    dbHelper.updateisDone(sample.get(position).getId(),1);
                    Log.e("check", "onCheckedChanged: "+dbHelper.selectTodo() );
                }
                else{
                    DBHelper dbHelper=new DBHelper(view.getContext(),"QuestApp.db",null,1);
                    dbHelper.updateisDone(sample.get(position).getId(),0);
                    Log.e("check", "onCheckedChanged: "+dbHelper.selectTodo() );
                }
                //myFragment의 OnCreaateView가 돌아야 한다.
            }
        });

        return view;
    }
}
