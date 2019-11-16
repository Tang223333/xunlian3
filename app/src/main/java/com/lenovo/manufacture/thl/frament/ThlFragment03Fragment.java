package com.lenovo.manufacture.thl.frament;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.Broadcast.MyReceiver;

import static android.content.Context.NOTIFICATION_SERVICE;


public class ThlFragment03Fragment extends Fragment implements View.OnClickListener {


    private Button btn;
    int a = 1;
    private Drawable drawable;
    private Context context;
    Handler handler = new Handler();
    ProgressDialog progressDialog;
    private Switch switch01;
    MyReceiver myReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thl_fragment_03, null);

        myReceiver=new MyReceiver();//初始化一个BroadcastTeceiver对象
        IntentFilter intentFilter1=new IntentFilter();//定义一个intent过滤器
        intentFilter1.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getContext().registerReceiver(myReceiver,intentFilter1);//注册接收者

        //向intent中添加广播意图 action相当于广播的类别名称可自己定义也可以使用系统的广播
//        Intent intent=new Intent("staticFilter");
//        getContext().sendBroadcast(intent);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(myReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        context = getContext();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView(View view) {
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        //从布局文件中获取switch
        switch01 = (Switch) view.findViewById(R.id.switch_01);
    }

    private void dialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_wuliao);
        dialog.show();
        TextView textView = dialog.getWindow().findViewById(R.id.textview_title);
        TextView textView1 = dialog.getWindow().findViewById(R.id.textview_body);
        Button button = dialog.getWindow().findViewById(R.id.button_qx);
        Button button1 = dialog.getWindow().findViewById(R.id.button_qr);
        textView.setText("aaaaaaa" + a);
        textView1.setText("bbbbbbb" + a);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                progressDialog = ProgressDialog.show(context, "加载", "加载中");
                handler.postDelayed(Dialog, 2000);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notification() {
        @SuppressLint("WrongConstant")
        NotificationChannel notificationChannel = new NotificationChannel("" + a, "" + a, a);
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(notificationChannel);
        Notification notification = new Notification.Builder(context, "" + a)
                .setContentTitle("11111111" + a)
                .setContentText("222222222" + a)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        manager.notify(a, notification);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "" + a, Toast.LENGTH_SHORT).show();
        switch (a) {
            case 1:
                notification();
                dialog();
                a++;
                btn.setCompoundDrawables(drawable, null, null, null);
                break;
            case 2:
                notification();
                dialog();
                a++;
                btn.setCompoundDrawables(null, drawable, null, null);
                break;
            case 3:
                notification();
                dialog();
                a++;
                btn.setCompoundDrawables(null, null, drawable, null);
                break;
            case 4:
                notification();
                dialog();
                a = 1;
                btn.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }

    private Runnable Dialog = new Runnable() {
        @Override
        public void run() {
            if (progressDialog.isShowing()) {
                Toast.makeText(context, "加载完成", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }
    };
}
