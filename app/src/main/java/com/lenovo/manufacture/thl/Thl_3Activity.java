package com.lenovo.manufacture.thl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;
import com.lenovo.manufacture.thl.adapter.ThlListItemAdapter;
import com.lenovo.manufacture.thl.data.List_gouzao;

import java.util.ArrayList;
import java.util.List;

public class Thl_3Activity extends Activity {

    private String TAG = "Thl_3Activity======";

    ProgressDialog progressDialog;
    Handler handler = new Handler();
    private Button btnTiao;
    private Button btnMenu;
    private TextView textMenu;
    private Button btnMenu2;
    private TextView textMenu2;
    List<List_gouzao> list_gouzaos;
    private ListView thl3List;
    ThlListItemAdapter thlListItemAdapter;
    private Spinner thl3Spinner;
    private Drawable drawable;//声明一个图形对象
    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_3);
        AlertDialog dialog = new AlertDialog.Builder(Thl_3Activity.this)
                .setTitle("11111111")
                .setMessage("asdffdsfadsf")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("afsdaf", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog = ProgressDialog.show(Thl_3Activity.this, "加载", "加载中");
                        handler.postDelayed(Dialog, 2000);
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("dsfsdaf", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel("one", "111", 4);
                        NotificationManager notificationManager = (NotificationManager) Thl_3Activity.this.getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.createNotificationChannel(notificationChannel);
                        Notification notification = new Notification.Builder(Thl_3Activity.this, "one")
                                .setContentText("1111111")
                                .setContentTitle("22222222")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .build();
                        notificationManager.notify(3, notification);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("dagfvasdf", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

        Button button = findViewById(R.id.btn_tiao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断当前是否为横屏,判断是否旋转
                if (Thl_3Activity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏
                } else if (Thl_3Activity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
                }
            }
        });
        Log.d(TAG, "onCreate");
        initView();
    }


    private void initView() {
        btnTiao = (Button) findViewById(R.id.btn_tiao);
        btnMenu = (Button) findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsMenu();
            }
        });
        textMenu = (TextView) findViewById(R.id.text_menu);
        btnMenu2 = (Button) findViewById(R.id.btn_menu2);
        btnMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });
        textMenu2 = (TextView) findViewById(R.id.text_menu2);
        thl3List = (ListView) findViewById(R.id.thl3_list);
        list_gouzaos = new ArrayList<>();
        list_gouzaos.add(new List_gouzao(R.mipmap.ic_launcher, "1111111", "11111111"));
        list_gouzaos.add(new List_gouzao(R.mipmap.ic_launcher, "2222222", "22222222"));
        list_gouzaos.add(new List_gouzao(R.mipmap.ic_launcher, "3333333", "333333333"));
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list_gouzaos.size();
            }

            @Override
            public Object getItem(int position) {
                return list_gouzaos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = View.inflate(Thl_3Activity.this, R.layout.thl_list_item, null);
                ImageView imageView = view.findViewById(R.id.list_image);
                TextView textView = view.findViewById(R.id.list_txt1);
                TextView textView1 = view.findViewById(R.id.list_txt2);
                imageView.setImageResource(list_gouzaos.get(position).getImage());
                textView.setText(list_gouzaos.get(position).getTxt1());
                textView1.setText(list_gouzaos.get(position).getTxt2());
                return view;
            }
        };
        thlListItemAdapter = new ThlListItemAdapter(Thl_3Activity.this, list_gouzaos);
//        thl3List.setAdapter(baseAdapter);
        thl3List.setAdapter(thlListItemAdapter);
        thl3List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Thl_3Activity.this, "你点击了第" + (position + 1) + "个item", Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        @SuppressLint("WrongConstant")
                        NotificationChannel notificationChannel=new NotificationChannel("ones","1111",4);
                        NotificationManager notificationManager= (NotificationManager) Thl_3Activity.this.getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.createNotificationChannel(notificationChannel);
                        Notification notification=new Notification.Builder(Thl_3Activity.this,"ones")
                                .setContentTitle("11111111")
                                .setContentText("222222222")
                                .setSettingsText("33333333")
                                .setSubText("4444444444")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .build();
                        notificationManager.notify(1,notification);
                        break;
                    case 1:
                        AlertDialog alertDialog=new AlertDialog.Builder(Thl_3Activity.this)
                                .setTitle("11111111")
                                .setIcon(R.mipmap.ic_launcher)
                                .setMessage("3333333")
                                .setNegativeButton("11111",null)
                                .setNeutralButton("22222",null)
                                .setPositiveButton("33333",null)
                                .show();
                        break;
                    case 2:
                        Dialog dialog=new Dialog(Thl_3Activity.this);
                        dialog.show();
                        dialog.getWindow().setContentView(R.layout.dialog_item);
                        TextView textView=dialog.getWindow().findViewById(R.id.text_title);
                        textView.setText("111111111");
                        TextView textView1=dialog.getWindow().findViewById(R.id.text_car_id);
                        textView1.setText("2222222222");
                        Button button=dialog.getWindow().findViewById(R.id.btn_qx);
                        button.setText("去去去取消");
                        Button button1=dialog.getWindow().findViewById(R.id.btn_qr);
                        button1.setText("确确确确认");
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(Thl_3Activity.this,Thl_4Activity.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(Thl_3Activity.this,Thl_4Activity.class);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });
                        break;
                }
            }
        });
        thl3List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Thl_3Activity.this, "你长按了第" + (position + 1) + "个item", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        thl3Spinner = (Spinner) findViewById(R.id.thl3_spinner);
        thl3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int dividerHeight=5;//宽度
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                drawable=getResources().getDrawable(R.drawable.divider_red2);
                thl3List.setDivider(drawable);//设置listView的分隔线
                thl3List.setDividerHeight(dividerHeight);//设置listView的分隔线高度
                thl3List.setPadding(0,0,0,0);//设置list View四周空白
                thl3List.setBackgroundColor(Color.TRANSPARENT);//设置listView的背景颜色
                switch (position){
                    case 0://不显示分隔线（分割线高度为0）
                        thl3List.setDividerHeight(0);
                        break;
                    case 1://不显示分隔线（分隔线为null）
                        thl3List.setDivider(null);
                        thl3List.setDividerHeight(dividerHeight);
                        break;
                    case 2://只显示内部分割线（先设置分隔线高度）
                        thl3List.setDividerHeight(dividerHeight);
                        thl3List.setDivider(drawable);
                        break;
                    case 3://只显示内部分割线（后设置分隔线高度）
                        thl3List.setDivider(drawable);
                        thl3List.setDividerHeight(dividerHeight);
                        break;
                    case 4://显示底部分隔线（高度是wrap_content）
                        params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0,1);
                        thl3List.setFooterDividersEnabled(true);
                        break;
                    case 5://显示底部分隔线（高度是match_parent）
                        params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
                        thl3List.setFooterDividersEnabled(true);
                        break;
                    case 6://显示顶部分隔线（别折腾了，显示不了）
                        params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
                        thl3List.setHeaderDividersEnabled(true);
                        thl3List.setFooterDividersEnabled(true);
                        break;
                    case 7://显示全部分割线（使用Padding大法）注意：使用Padding时背景不能设置为透明
                        thl3List.setDivider(null);
                        thl3List.setDividerHeight(dividerHeight);
                        thl3List.setPadding(dividerHeight,dividerHeight,dividerHeight,dividerHeight);
                        thl3List.setBackgroundDrawable(drawable);
                        break;
                }
                thl3List.setLayoutParams(params);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("start");
        registerReceiver(myReceiver,intentFilter1);//注册接收者
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //从menu_item中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId(); // 获取菜单项的编号
        if (id == R.id.menu_change_time) { // 点击了菜单项“改变时间”
            textMenu2.setText("改变时间");
        } else if (id == R.id.menu_change_color) { // 点击了菜单项“改变颜色”
            textMenu2.setText("改变颜色");
        } else if (id == R.id.menu_change_bg) { // 点击了菜单项“改变背景”
            textMenu2.setText("改变背景");
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // 获取菜单项的编号
        if (id == R.id.menu_change_time) { // 点击了菜单项“改变时间”
            textMenu.setText("改变时间");
//            Intent intent=new Intent(this,Thl_4Activity.class);
//            startActivity(intent);
        } else if (id == R.id.menu_change_color) { // 点击了菜单项“改变颜色”
            textMenu.setText("改变颜色");
//            AlertDialog dialog = new AlertDialog.Builder(Thl_3Activity.this)
//                    .setTitle("1111111111")
//                    .setIcon(R.mipmap.ic_launcher)
//                    .setMessage("asdfsdafsda")
//                    .setNegativeButton("111111", null)
//                    .setNeutralButton("2222222", null)
//                    .setPositiveButton("333333", null)
//                    .show();
//            new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    dialog.dismiss();
//                }
//            }.start();
        } else if (id == R.id.menu_change_bg) { // 点击了菜单项“改变背景”
            textMenu.setText("改变背景");
        }
        return true;
    }


    public Runnable Dialog = new Runnable() {
        @Override
        public void run() {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        registerForContextMenu(textMenu2);
        unregisterReceiver(myReceiver);
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        unregisterForContextMenu(textMenu2);
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
