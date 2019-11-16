package com.lenovo.manufacture.thl.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastOne extends BroadcastReceiver {
    private final String ACTION_WL="android.net.conn.CONNECTIVITY_CHANGE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "网络状态发生改变", Toast.LENGTH_SHORT).show();
    }
}
