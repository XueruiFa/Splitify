package com.example.xuerui.splitify.;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.xuerui.splitify.R;

import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistSimple;

public class Split extends AppCompatActivity implements View.OnClickListener{


    int playlistNum;
    SpotifyApi api;
    SpotifyService spotify;
    int index, count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playlistNum = 1;
        setContentView(R.layout.activity_split);

        index = 0;
        api = new SpotifyApi();
        spotify = api.getService();
        Pager<PlaylistSimple> playlistsPager = getPlaylists(index);
        List<PlaylistSimple> myPlaylists = playlistsPager.items;
        ScrollView scroll = new ScrollView(this);
        for (PlaylistSimple p: myPlaylists) {
            setContentView(scroll);
            count++;
            TextView text = new TextView(this);
            text.setText(p.name);
            scroll.addView(text);
        }
    }

    private Pager<PlaylistSimple> getPlaylists(int index) {
        Map<String, Object> option = new Map<String, Object>;
        option.put("offset", index);
        return spotify.getMyPlaylists(option);
    }

    public void onClick(View view) {

    }
}
