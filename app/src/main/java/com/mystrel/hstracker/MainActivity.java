package com.mystrel.hstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mystrel.hstracker.activities.AddGameActivity;
import com.mystrel.hstracker.activities.AddPackActivity;
import com.mystrel.hstracker.activities.AddQuestActivity;
import com.mystrel.hstracker.activities.ViewStatsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addGame(View view) {
        Intent i = new Intent(this, AddGameActivity.class);
        startActivity(i);
    }

    public void addPack(View view) {
        Intent i = new Intent(this, AddPackActivity.class);
        startActivity(i);
    }

    public void addQuest(View view) {
        Intent i = new Intent(this, AddQuestActivity.class);
        startActivity(i);
    }

    public void viewStats(View view) {
        Intent i = new Intent(this, ViewStatsActivity.class);
        startActivity(i);
    }

}
