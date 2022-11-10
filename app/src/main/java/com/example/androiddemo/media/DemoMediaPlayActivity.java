package com.example.androiddemo.media;

import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class DemoMediaPlayActivity extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private final static int MAX_VOLUME = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo_media_play);

        audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        // 方式1
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.getDuration();
        mediaPlayer.getCurrentPosition();
        // 方式2
//        mediaPlayer = new MediaPlayer();
//        try {
//            File file = new File("/sdcard/music.mp3");
//            if(file.exists()){
//                mediaPlayer.setDataSource(this, Uri.parse(file.getAbsolutePath()));
//                mediaPlayer.prepareAsync();
//            } else {
//                Toast.makeText(this, file.getAbsolutePath()+"文件不存在", Toast.LENGTH_SHORT).show();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Button btn_mediaplay_start = findViewById(R.id.btn_mediaplay_start);
        Button btn_mediaplay_pause = findViewById(R.id.btn_mediaplay_pause);
        Button btn_mediaplay_stop = findViewById(R.id.btn_mediaplay_stop);

        btn_mediaplay_start.setOnClickListener(this);
        btn_mediaplay_pause.setOnClickListener(this);
        btn_mediaplay_stop.setOnClickListener(this);


        SeekBar seekbar_mediaplay_volume = findViewById(R.id.seekbar_mediaplay_volume);
        int streamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekbar_mediaplay_volume.setProgress(MAX_VOLUME*streamVolume/streamVolume);
        seekbar_mediaplay_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = (float) (1 - (Math.log(MAX_VOLUME - progress) / Math.log(MAX_VOLUME)));
                mediaPlayer.setVolume(volume,volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mediaplay_start:
                mediaPlayer.start();
                break;
            case R.id.btn_mediaplay_stop:
                mediaPlayer.stop();
                break;
            case R.id.btn_mediaplay_pause:
                mediaPlayer.pause();
                break;
        }
    }
}