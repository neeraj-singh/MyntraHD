package com.neerajsingh.myntrahd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.net.URI;

/**
 * Created by neeraj.singh on 16/04/16.
 */
public class VideoViewActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    YouTubePlayerView youTubePlayerView  ;
    VideoView videoView;
    private String videoCode ;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extra = getIntent().getExtras();
        if(extra!=null && extra.containsKey(SwipeActivity.VIDEO_CODE)){
            videoCode = extra.getString(SwipeActivity.VIDEO_CODE);
        }
        setContentView(R.layout.video_view_layout);
//        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
//        youTubePlayerView.initialize(Config.DEVELOPER_KEY, VideoViewActivity.this);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+getVideo(videoCode)));
        videoView.start();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            if(videoCode!=null && videoCode.length()>0) {
                player.loadVideo(videoCode);

                // Hiding player controls
                player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            }
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    private int getVideo(String videoCode) {
        if(videoCode.equalsIgnoreCase("a")){
            return R.raw.a;
        }

        if(videoCode.equalsIgnoreCase("b")){
            return R.raw.b;
        }
        if(videoCode.equalsIgnoreCase("c")){
            return R.raw.c;
        }
        if(videoCode.equalsIgnoreCase("d")){
            return R.raw.d;
        }if(videoCode.equalsIgnoreCase("e")){
            return R.raw.e;
        }
        if(videoCode.equalsIgnoreCase("f")){
            return R.raw.f;
        }
        if(videoCode.equalsIgnoreCase("g")){
            return R.raw.g;
        }
        if(videoCode.equalsIgnoreCase("h")){
            return R.raw.h;
        }
        if(videoCode.equalsIgnoreCase("i")){
            return R.raw.i;
        }
        if(videoCode.equalsIgnoreCase("j")){
            return R.raw.j;
        }
        return R.raw.a;
    }


}
