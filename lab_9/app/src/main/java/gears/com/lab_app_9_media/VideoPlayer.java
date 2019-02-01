package gears.com.lab_app_9_media;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity implements View.OnClickListener{

    VideoView vidoplayer;
    SurfaceHolder holder;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        vidoplayer = findViewById(R.id.videoPlayer);
        Uri p =
        Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.small);
        vidoplayer.setVideoURI(p);
    }


    public void onVideoControlClick(View view){

        switch (view.getId()){

            case R.id.vid_back_btn:
                int time = vidoplayer.getCurrentPosition();
                time -= 2000;
                if(time >=0)
                    vidoplayer.seekTo(time);
                break;
            case R.id.vid_forward_btn:
                time = vidoplayer.getCurrentPosition();
                time += 2000;
                if( time <= vidoplayer.getDuration())
                    vidoplayer.seekTo(time);
                break;
            case R.id.vid_play_btn:
                if(!vidoplayer.isPlaying())
                    vidoplayer.start();
                break;

            case R.id.vid_pause_btn:
                if(vidoplayer.isPlaying())
                    vidoplayer.pause();

                break;
            default:
                break;

        }
    }

    @Override
    public void onClick(View view) {

    }
}
