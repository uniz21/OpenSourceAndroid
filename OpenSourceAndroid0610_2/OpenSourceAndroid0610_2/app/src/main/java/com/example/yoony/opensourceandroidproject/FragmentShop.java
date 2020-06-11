package com.example.yoony.opensourceandroidproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentShop extends Fragment {

    GridView gridView;
    EditText editText;
    EditText editText2;
    Button button;
    SingerAdapter singerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);
        editText = (EditText)view.findViewById(R.id.editText);
        editText2 = (EditText)view.findViewById(R.id.editText2);
        button = (Button)view.findViewById(R.id.button);

        singerAdapter = new SingerAdapter();
        singerAdapter.addItem(new SingerShopItem("하루쉬기","50", R.drawable.goals));
        singerAdapter.addItem(new SingerShopItem("치팅데이","80", R.drawable.goals));
        singerAdapter.addItem(new SingerShopItem("자유","10", R.drawable.goals));

        gridView.setAdapter(singerAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("구매하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity().getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity().getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();

                //Toast.makeText(getActivity().getApplicationContext(),"아이템명 : "+ singerAdapter.getItem(i).getName().toString() + "   POINT : "+singerAdapter.getItem(i).getCost().toString(),Toast.LENGTH_LONG).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString().trim();
                String cost = editText2.getText().toString().trim();
                singerAdapter.addItem(new SingerShopItem(name,cost, R.drawable.goals));
            }
        });

        return view;
    }

    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerShopItem> items = new ArrayList<SingerShopItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerShopItem singerItem){
            items.add(singerItem);
        }

        @Override
        public SingerShopItem getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            SingerViewer singerViewer = new SingerViewer(getActivity().getApplicationContext());
            singerViewer.setItem(items.get(i));
            return singerViewer;
        }
    }
}
