package com.example.yoony.opensourceandroidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    LinearLayout LL;
    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtText=findViewById(R.id.edtGoal);

        ImageButton button1 = findViewById(R.id.addSubBtn);
        LL = findViewById(R.id.LL);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText et = new EditText(getApplicationContext());
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                et.setLayoutParams(p);
                et.setHint("세부목표를 입력하세요.");
                LL.addView(et);

            }

        });

        findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str=edtText.getText().toString();

                if(str.length()>0){

                    Intent intent=new Intent();
                    intent.putExtra("main", str);
                    setResult(0, intent);
                    finish();
                }
            }
        });


        findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
