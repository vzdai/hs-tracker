package com.mystrel.hstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mystrel.hstracker.AddGame.AddGameActivity;
import com.mystrel.hstracker.AddPack.AddPackActivity;
import com.mystrel.hstracker.AddQuest.AddQuestActivity;
import com.mystrel.hstracker.Options.OptionsActivity;
import com.mystrel.hstracker.ViewStats.ViewStatsActivity;

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

    public void viewOptions(View view) {
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

}
