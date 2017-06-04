package com.example.android.thebestmp3player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    MediaPlayer mPlayer;
    Button playBtn;
    Button stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.rick_roll);
        playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(this);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(getServiceIntent());
//        mPlayer.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playBtn:
                PlayService();
//                mPlayer.start();
                break;
            case R.id.stopBtn:
                PauseService();
//                mPlayer.pause();
                break;
            default:
                return;
        }
    }

    private Intent getServiceIntent() {
        return new Intent(getBaseContext(), AmazingMp3Service.class);
    }

    private void PauseService() {
        Intent i = getServiceIntent();
        Bundle b = new Bundle();
        b.putString(AmazingMp3Service.MESSAGE_KEY, AmazingMp3Service.PAUSE);
        i.putExtras(b);
        startService(i);
    }

    private void PlayService() {
        Intent i = new Intent(getBaseContext(), AmazingMp3Service.class);
        Bundle b = new Bundle();
        b.putString(AmazingMp3Service.MESSAGE_KEY, AmazingMp3Service.PLAY);
        i.putExtras(b);
        startService(i);
    }
}
