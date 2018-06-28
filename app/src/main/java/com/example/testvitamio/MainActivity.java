package com.example.testvitamio;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vitamio.initialize(getApplicationContext());
        if (Vitamio.isInitialized(this)) {
            VideoView vitamio = (VideoView) findViewById(R.id.vitamio);
            vitamio.setVideoURI(Uri.parse(
                   "https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/从技术走向管理/001.从技术到管理_第1节_从技术到管理的内外部因素.mp4"));
            //默认的controller
            MediaController controller = new MediaController(this);
            vitamio.setMediaController(controller);
            vitamio.start();
            //缓冲监听
            vitamio.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
//percent 当前缓冲百分比
                }
            });
            vitamio.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    switch (what) {
                        //开始缓冲
                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:
//                            percentTv.setVisibility(View.VISIBLE);
//                            netSpeedTv.setVisibility(View.VISIBLE);
                            mp.pause();
                            return true;
                        //缓冲结束
                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:
//                            percentTv.setVisibility(View.GONE);
//                            netSpeedTv.setVisibility(View.GONE);
                            mp.start();
                            return true;

                    }
                    return false;
                }
            });
        }
    }
}
