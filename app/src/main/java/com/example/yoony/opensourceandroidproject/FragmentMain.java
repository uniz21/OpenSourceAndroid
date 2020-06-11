package com.example.yoony.opensourceandroidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yoony.opensourceandroidproject.db.GoalDataTask;
import com.example.yoony.opensourceandroidproject.db.factory.GoalDAOFactory;
import com.example.yoony.opensourceandroidproject.db.model.Goal;
import com.tedpark.tedonactivityresult.rx2.TedRxOnActivityResult;

import java.util.ArrayList;
import java.util.List;

public class FragmentMain extends Fragment {
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ImageButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter() {
            @Override
            void setSelected(Goal goal) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("EXTRA_GOAL", goal);
                startActivity(intent);
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
        btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("onclick", "onClick: ");
                TedRxOnActivityResult.with(getContext())
                        .startActivityForResult(new Intent(getActivity(), AddActivity.class))
                        .subscribe(activityResult -> {
                            L.i("[TedRxOnActivityResult] " + activityResult);
                            if (activityResult.getResultCode() == Activity.RESULT_OK) {
                                //Intent data = activityResult.getData();
                                onGoalDataLoad();
                            }

                        }, Throwable::printStackTrace);
            }
        });

        onGoalDataLoad();

        return view;
    }


    private void onGoalDataLoad() {
        //쓰레드를 이용하여 데이터를 호출한다.. 데이터호출로직은 백그라운드에서 처리되어야한다.
        GoalDataTask task = new GoalDataTask.Builder().setFetcher(new GoalDataTask.DataFetcher() {
            @Override
            public List<Goal> getData() {
                try {
                    //getGoalList 함수를 통해 최종목표 리스트들을 호출한다.
                    return GoalDAOFactory.getGoalList(getActivity());
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