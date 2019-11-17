package com.lenovo.manufacture.thl.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.lenovo.manufacture.R;

public class MyService extends Service {
    public static MediaPlayer player;
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            player=MediaPlayer.create(getApplicationContext(),R.raw.jskw);
            player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.pause();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
