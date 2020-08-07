package com.jadhao3726.tedxjnec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

public class welcomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);

        VideoView videoView = findViewById(R.id.videoView);

        String path = "android.resource://com.jadhao3726.tedxjnec/"+R.raw.finalwelcome;

        Uri u = Uri.parse(path);

        videoView.setVideoURI(u);

        videoView.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Write whatever to want to do after delay specified (1 sec)
                Intent intent = new Intent(welcomepage.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);




    }
}
