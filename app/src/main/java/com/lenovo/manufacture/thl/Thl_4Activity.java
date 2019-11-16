package com.lenovo.manufacture.thl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;

public class Thl_4Activity extends AppCompatActivity {
    private String TAG="Thl_4Activity========";
    Button button;
    TextView textView;
    Drawable drawable;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_4);
        button=findViewById(R.id.btn_xc);
        textView=findViewById(R.id.text_xc);
        drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());//图片大小一定要加
        button.setCompoundDrawables(drawable,null,null,null);
        textView.setCompoundDrawables(null,drawable,null,null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setCompoundDrawables(null,null,drawable,null);
                textView.setCompoundDrawables(null,null,null,drawable);
            }
        });
        Log.d(TAG, "onCreate");

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver,intentFilter1);//注册接收者

        //向intent中添加广播意图 action相当于广播的类别名称可自己定义也可以使用系统的广播
//        Intent intent=new Intent("staticFilter");
//        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
}
