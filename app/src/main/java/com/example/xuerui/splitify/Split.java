package com.example.xuerui.splitify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistSimple;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Split extends AppCompatActivity implements View.OnClickListener {


    int playlistNum;
    SpotifyApi api;
    SpotifyService spotify;
    int index, count;
    Pager<PlaylistSimple> playlistsPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        playlistNum = 1;

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("No playlists found");
        dlgAlert.setTitle("Playlist Error");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

        index = 0;
        api = new SpotifyApi();
        spotify = api.getService();
        playlistsPager = spotify.getMyPlaylists();
        if (playlistsPager == null) {
            System.out.println("No playlists found error");
        }
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

    private void getPlaylists(int index) {
        Map<String, Object> option = new TreeMap<String, Object>();
        option.put("limit", 50);
        System.out.println("Reaches here");
        spotify.getMyPlaylists(option, new Callback<Pager<PlaylistSimple>>() {
            @Override
            public void success(Pager<PlaylistSimple> playlistSimplePager, Response response) {
                System.out.println("Reaches success");
                playlistsPager = playlistSimplePager;
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Reaches failure");
                Log.d("Playlist failure", error.toString());
            }
        });
    }

    public void onClick(View view) {

    }
}