package com.example.android.thebestmp3player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class AmazingMp3Service extends Service {

    public static final String MESSAGE_KEY = "Message";
    public static final String PLAY = "Play";
    public static final String PAUSE = "Pause";

    private MediaPlayer myPlayer;

    public AmazingMp3Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, getString(R.string.start_service), Toast.LENGTH_SHORT).show();

        MediaPlayer player = getMediaPlayer();

        String message = intent.getExtras().getString(MESSAGE_KEY);

        if(message != null) {
            switch (message) {
                case PLAY:
                    player.start();
                    break;
                case PAUSE:
                    player.pause();
                    break;
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, getString(R.string.stop_service), Toast.LENGTH_SHORT).show();
        getMediaPlayer().stop();
        super.onDestroy();
    }

    private MediaPlayer getMediaPlayer() {
        if (myPlayer == null) {
            myPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rick_roll);
        }
        return myPlayer;
    }
}
