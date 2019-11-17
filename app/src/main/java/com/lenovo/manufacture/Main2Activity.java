package com.lenovo.manufacture;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.lenovo.manufacture.cy.fragment.CyFragment1Fragment;
import com.lenovo.manufacture.cy.fragment.CyFragment2Fragment;
import com.lenovo.manufacture.cy.fragment.CyFragment3Fragment;
import com.lenovo.manufacture.thl.Broadcast.BroadcastOne;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;
import com.lenovo.manufacture.thl.TangHaiLong_01;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    private String TAG = "Main2Activity";
    private TextView tvDay;
    private TextView tvDay2;
    private TabLayout tabTitle;
    private ViewPager thl2Vp;
    List<Fragment> list = new ArrayList<>();
    BroadcastOne broadcastOne;
    MyReceiver myReceiver;
    private Toolbar tlHead;
    private Button main2Btn1;
    private Button main2Btn2;
    private Button main2Btn3;
    private Button main2Btn4;
    private TextView thl;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView thl = findViewById(R.id.thl);

        thl.setOnClickListener(this);

        //找到toolbar工具栏
        toolbar = findViewById(R.id.tl_head);
        //设置工具栏左边的导航图标30
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
        switch (v.getId()){
            case R.id.thl:
                Intent intent = new Intent(Main2Activity.this, TangHaiLong_01.class);
                startActivity(intent);
                break;
            case R.id.main2_btn_1:
                Snackbar snackbar=Snackbar.make(v,"没什么意思，意思意思。",Snackbar.LENGTH_LONG);
                snackbar.show();
                break;
            case R.id.main2_btn_2:
                Snackbar snackbar2=Snackbar.make(v,"小意思，小意思",Snackbar.LENGTH_INDEFINITE);
                //LENGTH_LONG表示比较长的时间之后才关闭提示
                //LENGTH_SHORT表示短时间就关闭提示
                //LRNGTH_INDEFINITE表示不自动关闭提示
                snackbar2.show();
                break;
            case R.id.main2_btn_3:
                Snackbar snackbar3=Snackbar.make(v,"其实也没别的意思。",Snackbar.LENGTH_LONG);
                snackbar3.show();
                break;
            case R.id.main2_btn_4:
                Snackbar snackbar4=Snackbar.make(v,"是我不好意思。",Snackbar.LENGTH_SHORT);
                snackbar4.show();
                break;
        }
    }

    private void initView() {
        tabTitle = (TabLayout) findViewById(R.id.tab_title);
        thl2Vp = (ViewPager) findViewById(R.id.thl2_vp);
        list.clear();
        list.add(new CyFragment1Fragment());
        list.add(new CyFragment2Fragment());
        list.add(new CyFragment3Fragment());

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
                Toast.makeText(Main2Activity.this, "" + tab.getPosition(), Toast.LENGTH_SHORT).show();
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

        broadcastOne = new BroadcastOne();
        IntentFilter intentFilter = new IntentFilter();//定义一个intent过滤器
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastOne, intentFilter);

        myReceiver = new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1 = new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("start");//只保留Action是staticFilterintent
        registerReceiver(myReceiver, intentFilter1);//注册接收者

        Intent intent=new Intent("start");
        intent.putExtra("bf",1);
        sendBroadcast(intent);

        tlHead = (Toolbar) findViewById(R.id.tl_head);
        main2Btn1 = (Button) findViewById(R.id.main2_btn_1);
        main2Btn2 = (Button) findViewById(R.id.main2_btn_2);
        main2Btn3 = (Button) findViewById(R.id.main2_btn_3);
        main2Btn4 = (Button) findViewById(R.id.main2_btn_4);
        main2Btn1.setOnClickListener(this);
        main2Btn2.setOnClickListener(this);
        main2Btn3.setOnClickListener(this);
        main2Btn4.setOnClickListener(this);
        thl = (TextView) findViewById(R.id.thl);
        thl.setOnClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent=new Intent("start");
        intent.putExtra("bf",1);
        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent intent1=new Intent("start");
        intent1.putExtra("bf",2);
        sendBroadcast(intent1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastOne);
        unregisterReceiver(myReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
