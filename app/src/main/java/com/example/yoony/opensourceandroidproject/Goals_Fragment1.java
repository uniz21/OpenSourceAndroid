package com.example.yoony.opensourceandroidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yoony.opensourceandroidproject.db.GoalSubDataTask;
import com.example.yoony.opensourceandroidproject.db.factory.GoalSubDAOFactory;
import com.example.yoony.opensourceandroidproject.db.model.GoalSub;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Goals_Fragment1 extends Fragment {
    Long main;
    TextView sub_goal_1;
    LinearLayout sub_list;
    TextView sleep;
    TextView sleep2;
    TextView sleep3;
    int count=0;
    int percent =0;

    public Goals_Fragment1() {
        // Required empty public constructor
    }

    public void getInstance(String str) {
        this.main = Long.parseLong(str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goals_fragment1, container, false);
        View view2 = inflater.inflate(R.layout.goals_holder, container, false);

        LinearLayout linear=view2.findViewById(R.id.linear);

        DBHelper dbHelper = new DBHelper(view.getContext(), "QuestApp.db", null, 1);
        String[] str = dbHelper.SubQuest(main).split("\n");

        for (int a = 0; a < str.length; a++) {
            Log.e("sub", "" + str[a]);
        }

        ProgressBar progress = (ProgressBar) view.findViewById(R.id.progress) ;
        progress.setProgress(percent) ;

        for (int i=0;i<str.length;i++) {
            Goals_Sub goals_holder = new Goals_Sub(getContext());
            LinearLayout sub_list= (LinearLayout)view.findViewById(R.id.sub_list);
            sub_list.addView(goals_holder);
            TextView tv=goals_holder.findViewById(R.id.holder_text);
            CheckBox check=goals_holder.findViewById(R.id.checkBox);
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        count++;
                    } else count--;
                    percent=(int)(((double)count/(double)str.length)*100);
                    Log.e("progress", "percent: "+percent );
                    progress.setProgress(percent) ;
                }
            });
            Log.e("str", "onCreateView: "+str[i] );
            tv.setText(str[i]);
        }



        return view;
    }
}