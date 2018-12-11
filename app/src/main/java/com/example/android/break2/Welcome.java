package com.example.android.break2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;


public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        VideoView vid;
        // vid = (VideoView)findViewById(R.id.videoView);
        // MediaController m = new MediaController(this);
        //vid.setMediaController(m);
        String vidAddress = "android.resource://com.example.android.database/raw/video";
        Uri u = Uri.parse(vidAddress);
        // vid.setVideoURI(u);
        // vid.start();
        // vid.seekTo(1);

    }
    public void MoveToLogin(View v)
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void MoveToRegiester(View v)
    {
        Intent intent = new Intent(this,SignUp.class);
//starting the activity
        startActivity(intent);
    }
}