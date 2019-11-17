package com.lenovo.manufacture.thl.frament;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;


public class ThlFragment02Fragment extends Fragment implements View.OnClickListener {

    Drawable drawable;//声明一个图形对象
    TextView textView;
    MyReceiver myReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thl_fragment_02, null);
        textView=view.findViewById(R.id.txt_image1);//声明一个按钮对象
        drawable=getResources().getDrawable(R.mipmap.ic_launcher);//添加图形资源
        //设置图形大小，否则不会显示
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        Button button1=view.findViewById(R.id.btn_left);
        Button button2=view.findViewById(R.id.btn_right);
        Button button3=view.findViewById(R.id.btn_top);
        Button button4=view.findViewById(R.id.btn_bottem);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                textView.setCompoundDrawables(drawable,null,null,null);
                break;
            case R.id.btn_right:
                textView.setCompoundDrawables(null,null,drawable,null);
                break;
            case R.id.btn_top:
                textView.setCompoundDrawables(null,drawable,null,null);
                break;
            case R.id.btn_bottem:
                textView.setCompoundDrawables(null,null,null,drawable);
                break;
        }
    }
}
