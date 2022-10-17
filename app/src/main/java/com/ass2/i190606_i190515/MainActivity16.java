package com.ass2.i190606_i190515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity16 extends AppCompatActivity {
    TextView songT;
    int i;
    ImageButton back,next,middle;
    ArrayList<File> songslist=null;
    Thread seekaudio;
    MediaPlayer player;
    String TXTSONG;

SeekBar seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        songT=findViewById(R.id.songtitle);
        back=findViewById(R.id.back);
        next=findViewById(R.id.next);
        middle=findViewById(R.id.middle);
        seek=findViewById(R.id.seekbar);
       final TextView Time=findViewById(R.id.time);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        songslist=(ArrayList) bundle.getParcelableArrayList("Songlist");
        TXTSONG=intent.getStringExtra("currentSong");
        songT.setText(TXTSONG);
        i=intent.getIntExtra("position",0);

        Uri uri=Uri.parse(songslist.get(i).toString());
        player=MediaPlayer.create(this,uri);
        player.start();
        seek.setMax(player.getDuration());
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Time.setVisibility(View.VISIBLE);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Time.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            player.seekTo(seek.getProgress());
//            Time.setText(player.getDuration());
            }
        });

    seekaudio=new Thread(){
        @Override
        public void run() {
            super.run();
            int currposition = 0;
            try {
                while (currposition < player.getDuration()) {
                    currposition = player.getCurrentPosition();
                    seek.setProgress(currposition);

                    sleep(800);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
        seekaudio.start();
    }
};