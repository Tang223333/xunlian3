package com.lenovo.manufacture.thl;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;
import com.lenovo.manufacture.thl.frament.ThlFragment01Fragment;
import com.lenovo.manufacture.thl.frament.ThlFragment02Fragment;
import com.lenovo.manufacture.thl.frament.ThlFragment03Fragment;

import java.util.ArrayList;
import java.util.List;

public class TangHaiLong_01 extends AppCompatActivity implements View.OnClickListener {

    private ViewPager thlViewpager;
    List<Fragment> list;
    private TextView One;
    private TextView Two;
    private TextView Three;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tang_hai_long_01);
        initView();
    }

    private void initView() {
        thlViewpager = (ViewPager) findViewById(R.id.thl_viewpager);
        list = new ArrayList<>();
        list.add(new ThlFragment01Fragment());
        list.add(new ThlFragment02Fragment());
        list.add(new ThlFragment03Fragment());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        thlViewpager.setAdapter(fragmentPagerAdapter);
        One = (TextView) findViewById(R.id.One);
        One.setOnClickListener(this);
        Two = (TextView) findViewById(R.id.Two);
        Two.setOnClickListener(this);
        Three = (TextView) findViewById(R.id.Three);
        Three.setOnClickListener(this);
        thlViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        csh();
                        One.setSelected(true);
                        thlViewpager.setCurrentItem(0);
                        break;
                    case 1:
                        csh();
                        Two.setSelected(true);
                        thlViewpager.setCurrentItem(1);
                        break;
                    case 2:
                        csh();
                        Three.setSelected(true);
                        thlViewpager.setCurrentItem(2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        csh();
        One.setSelected(true);

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("start");
        registerReceiver(myReceiver,intentFilter1);//注册接收者

    }

    @Override
    protected void onResume() {
        super.onResume();
        unregisterReceiver(myReceiver);
    }

    private void csh(){
        One.setSelected(false);
        Two.setSelected(false);
        Three.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.One:
                csh();
                One.setSelected(true);
                thlViewpager.setCurrentItem(0);
                break;
            case R.id.Two:
                csh();
                Two.setSelected(true);
                thlViewpager.setCurrentItem(1);
                break;
            case R.id.Three:
                csh();
                Three.setSelected(true);
                thlViewpager.setCurrentItem(2);
                break;
        }
    }
}