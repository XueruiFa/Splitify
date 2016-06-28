package com.example.xuerui.splitify.;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xuerui.splitify.R;

import kaaes.spotify.webapi.android.SpotifyService;
public class Split extends AppCompatActivity implements SpotifyService, View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        OpenCon.clickConnect(bAddToPlaylist);

    }
}
