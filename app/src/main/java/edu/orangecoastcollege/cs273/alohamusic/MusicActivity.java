package edu.orangecoastcollege.cs273.alohamusic;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MusicActivity extends AppCompatActivity {

    Button ukeleleButton;
    Button ipuButton;
    Button hulaButton;

    VideoView hulaVideoView;
    MediaPlayer ukeleleMediaplayer;
    MediaPlayer ipuMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        //associate the components
        ukeleleButton = (Button) findViewById(R.id.ukuleleButton);
        ipuButton = (Button) findViewById(R.id.ipuButton);
        hulaButton = (Button) findViewById(R.id.hulabutton);
        hulaVideoView = (VideoView) findViewById(R.id.hulaVideoView);

        //associate the mediaplayers
        ukeleleMediaplayer = MediaPlayer.create(this, R.raw.ukulele);
        ipuMediaPlayer = MediaPlayer.create(this, R.raw.ipu);

        //associate the videoview with its URI
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.hula;
        hulaVideoView.setVideoURI(Uri.parse(uri));

        //Create a MediaController for the VideoView
        //MediaController = controlls (play/pause/forward/ rewind)
        hulaVideoView.setMediaController(new MediaController(this));

    }

    //method will handle all 3 button clicks
    //use the button id to see which was clicked
    public void playMedia(View v)
    {
        switch(v.getId())
        {
            case R.id.ukuleleButton:
                if(ukeleleMediaplayer.isPlaying()) {
                    ukeleleMediaplayer.pause();
                    ukeleleButton.setText(R.string.ukulele_button_play_text);
                    ipuButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else {
                    ukeleleMediaplayer.start();
                    ukeleleButton.setText(R.string.ukulele_button_pause_text);
                    ipuButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.ipuButton:
                if(ipuMediaPlayer.isPlaying()) {
                    ipuMediaPlayer.pause();
                    ipuButton.setText(R.string.ipu_button_play_text);
                    ukeleleButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else {
                    ipuMediaPlayer.start();
                    ipuButton.setText(R.string.ipu_button_pause_text);
                    ukeleleButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.hulabutton:
                if(hulaVideoView.isPlaying())
                {
                    hulaVideoView.pause();
                    hulaButton.setText(R.string.hula_button_watch_text);
                    ukeleleButton.setVisibility(View.VISIBLE);
                    ipuButton.setVisibility(View.VISIBLE);
                }
                else {
                    hulaVideoView.start();
                    ukeleleButton.setVisibility(View.INVISIBLE);
                    ipuButton.setVisibility(View.INVISIBLE);
                }
                break;
        }

    }

    //override onStop() to release MediaPlayers to prevent memory leaks
    @Override
    protected void onStop() {
        super.onStop();
        ukeleleMediaplayer.release();
        ipuMediaPlayer.release();
    }
}
