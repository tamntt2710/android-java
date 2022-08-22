package com.example.music_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageButton btnPrev, btnStop, btnPlay, btnNext;
    MediaPlayer mediaPlayer;
    ArrayList<song> arraySong;
    int position = 0;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();
        addSong();
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
        txtTimeTotal.setText(String.valueOf(mediaPlayer.getDuration()));
        skSong.setMax(mediaPlayer.getDuration());
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mediaPlayer.getDuration()

                doStart();
            }
        });

        handler = new Handler(){
            public void handleMessage(Message message){
                super.handleMessage(message);
                txtTimeSong.setText(String.valueOf(message.arg1));
            }
        };

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                skSong.setVisibility(View.VISIBLE);
                int x = (int) Math.ceil(progress / 1000f);

                if (x == 0 && mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    clearMediaPlayer();
                    MainActivity.this.skSong.setProgress(0);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                }
            }
        });


        
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position >=0 && position <4 ){
                    skSong.setProgress(0);
                    clearMediaPlayer();
                    position ++;
                    txtTitle.setText(arraySong.get(position).getTitle());
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position >0 && position <=4 ){
                    skSong.setProgress(0);
                    position --;
                    txtTitle.setText(arraySong.get(position).getTitle());
                }
            }
        });
    }

    private void doStart() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnPlay.setBackgroundResource(R.drawable.ic_baseline_play_circle_outline_24);
        } else {
            mediaPlayer.setVolume(0.5f, 0.5f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
            btnPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int total = mediaPlayer.getDuration();

                while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
                    try {
                        Thread.sleep(1000);
                        currentPosition = mediaPlayer.getCurrentPosition();
                        Message msg = handler.obtainMessage();
                        msg.arg1 = currentPosition;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        return;
                    } catch (Exception e) {
                        return;
                    }

                    skSong.setProgress(currentPosition);

                }
            }
        });
        thread.start();
    }

    private void clearMediaPlayer() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void addSong() {

        arraySong = new ArrayList<>();
        arraySong.add(new song("Hai mươi hai", R.raw.hai_muoi_hai));
        arraySong.add(new song("Suy tư", R.raw.suy_tu));
        arraySong.add(new song("Tóc ngắn", R.raw.toc_ngan));
        arraySong.add(new song("Vào hạ", R.raw.vao_ha));
        arraySong.add(new song("Vì mẹ anh bắt chia tay", R.raw.vi_me_anh_bat_chia_tay));
    }

    private void onInit() {
        txtTitle = findViewById(R.id.txtTitle);
        txtTimeSong = findViewById(R.id.txtTimeSong);
        txtTimeTotal = findViewById(R.id.txtTimeTotal);
        skSong = findViewById(R.id.seekbar);
        btnPrev = findViewById(R.id.previousButton);
        btnPlay = findViewById(R.id.pauseButton);
        btnNext = findViewById(R.id.nextButton);
        btnStop = findViewById(R.id.stopButton);
    }
}