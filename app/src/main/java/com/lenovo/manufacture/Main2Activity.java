package com.lenovo.manufacture;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lenovo.manufacture.cy.fragment.CyFragment1Fragment;
import com.lenovo.manufacture.cy.fragment.CyFragment2Fragment;
import com.lenovo.manufacture.cy.fragment.CyFragment3Fragment;
import com.lenovo.manufacture.thl.Broadcast.BroadcastOne;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;
import com.lenovo.manufacture.thl.TangHaiLong_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    private String TAG = "Main2Activity";
    private TextView tvDay;
    private TextView tvDay2;
    private TabLayout tabTitle;
    private ViewPager thl2Vp;
    List<Fragment> list=new ArrayList<>();
    BroadcastOne broadcastOne;
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView thl = findViewById(R.id.thl);
        thl.setOnClickListener(this);

        //找到toolbar工具栏
        toolbar = findViewById(R.id.tl_head);
        //设置工具栏左边的导航图标
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setCollapseIcon(R.mipmap.ic_launcher);
        //设置工具栏的背景
        toolbar.setBackgroundResource(R.color.colorPrimary2);
        //使用toolbar替换系统自带的ActionBar
        setSupportActionBar(toolbar);
        //给toolbar设置当行图标的点击监听器
        //setNavigationOnClickListenner必须放到setSupportActionBar之后，不然不起作用
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//结束当前页面
            }
        });
        initView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this, TangHaiLong_01.class);
        startActivity(intent);
    }

    private void initView() {
        tabTitle = (TabLayout) findViewById(R.id.tab_title);
        thl2Vp = (ViewPager) findViewById(R.id.thl2_vp);
        list.clear();
        list.add(new CyFragment1Fragment());
        list.add(new CyFragment2Fragment());
        list.add(new CyFragment3Fragment());

        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        thl2Vp.setAdapter(adapter);

        thl2Vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //选中Tabtitle的指定标签
                tabTitle.getTabAt(position).select();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //给tab_title添加文字标签
        tabTitle.addTab(tabTitle.newTab().setText("商品"));
        tabTitle.addTab(tabTitle.newTab().setText("简介"));
        tabTitle.addTab(tabTitle.newTab().setText("购买"));
        //给tabTitle添加标签选中监听器
        tabTitle.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //选中时触发
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(Main2Activity.this, ""+tab.getPosition(), Toast.LENGTH_SHORT).show();
                thl2Vp.setCurrentItem(tab.getPosition());
            }
            //取消选中时触发
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //复选时触发
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        broadcastOne=new BroadcastOne();
        IntentFilter intentFilter=new IntentFilter();//定义一个intent过滤器
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastOne,intentFilter);

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("staticFilter");//只保留Action是staticFilterintent
        registerReceiver(myReceiver,intentFilter1);//注册接收者

        //向intent中添加广播意图 action相当于广播的类别名称可自己定义也可以使用系统的广播
        Intent intent=new Intent("staticFilter");
        sendBroadcast(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        broadcastOne=new BroadcastOne();
        IntentFilter intentFilter=new IntentFilter();//定义一个intent过滤器
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastOne,intentFilter);

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("staticFilter");
        registerReceiver(myReceiver,intentFilter1);//注册接收者
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(broadcastOne);
//        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastOne);
        unregisterReceiver(myReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_change_time:
                Toast.makeText(this, "改变时间", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_change_color:
                Toast.makeText(this, "改变颜色", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_change_bg:
                Toast.makeText(this, "改变背景", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
