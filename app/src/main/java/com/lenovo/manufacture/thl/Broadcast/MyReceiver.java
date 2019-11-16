package com.lenovo.manufacture.thl.Broadcast;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.lenovo.manufacture.R;

public class MyReceiver extends BroadcastReceiver {
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

        Dialog dialog=new Dialog(context);
        dialog.show();
        dialog.setContentView(R.layout.dialog_item);
        TextView textView=dialog.getWindow().findViewById(R.id.text_title);
        TextView textView1=dialog.getWindow().findViewById(R.id.text_car_id);
        Button button=dialog.getWindow().findViewById(R.id.btn_qx);
        Button button1=dialog.getWindow().findViewById(R.id.btn_qr);
        textView.setText("123654789");
        textView1.setText("987654321");
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
            }
        });
    }
}
