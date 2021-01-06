package com.ctis487.group8.project;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicPlayerService extends Service {
    public static MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.background);
        player.setLooping(true);
        player.setVolume(5,5);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }
}
