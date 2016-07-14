package com.mystrel.hstracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-13.
 */
public class InputCardsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pack_input_cards);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
