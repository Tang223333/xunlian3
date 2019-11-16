package com.lenovo.manufacture.thl.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastTwo extends BroadcastReceiver {
    private final String ACTION_BOOT="android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "开机完毕", Toast.LENGTH_SHORT).show();
    }
}
