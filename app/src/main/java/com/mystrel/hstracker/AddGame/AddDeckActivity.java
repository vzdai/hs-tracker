package com.mystrel.hstracker.AddGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-16.
 */
public class AddDeckActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_deck);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
