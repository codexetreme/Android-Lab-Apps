package gears.com.lab_app_9_media;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton play_btn,pause_btn;
    MediaPlayer p = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_btn = findViewById(R.id.play_btn);
        pause_btn = findViewById(R.id.pause_btn);
        play_btn.setOnClickListener(this);
        pause_btn.setOnClickListener(this);
        p = MediaPlayer.create(this,R.raw.sound1);
        final boolean[] finished = {false};
        final SeekBar b = findViewById(R.id.seek);
        p.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finished[0] = true;
            }
        });

        final Handler mHandler = new Handler();
//Make sure you update Seekbar on UI thread
        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if(p != null){
                    int mCurrentPosition = p.getCurrentPosition() / 1000;
                    b.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.play_btn){
            if(!p.isPlaying())
                p.start();
        }
        if(view.getId()==R.id.pause_btn){
            if(p.isPlaying()){
                p.pause();
            }
        }
        if(view.getId()==R.id.forward_btn){
            int time = p.getCurrentPosition();
            time += 5000;
            if(time<=p.getDuration())
                p.seekTo(time);
        }
        if(view.getId()==R.id.back_btn){
            int time = p.getCurrentPosition();

            time -= 5000;
            if(time >=0)
                p.seekTo(time);
        }

        if(view.getId() == R.id.video){
            startActivity(new Intent(MainActivity.this,VideoPlayer.class));
        }
    }
}
