package com.lenovo.manufacture.thl.Broadcast;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.service.MyService;

public class MyReceiver extends BroadcastReceiver {

    Intent intent1;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "asgsadfgfdsag", Toast.LENGTH_SHORT).show();
        @SuppressLint("WrongConstant")
        NotificationChannel notificationChannel=new NotificationChannel("one","111",4);
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        Notification notification=new Notification.Builder(context,"one")
                .setContentTitle("11111")
                .setSettingsText("222222")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notificationManager.notify(1,notification);

        int a=intent.getIntExtra("bf",0);
        Toast.makeText(context, "a"+a, Toast.LENGTH_SHORT).show();
        intent1=new Intent(context, MyService.class);
        if (a==1){
            context.startService(intent1);
        }
        if (a==2){
            context.stopService(intent1);
        }

    }
}
