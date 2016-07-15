package com.mystrel.hstracker.AddPack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-10.
 */
public class AddPackActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addClassicPack(View view) {
        Intent intent = new Intent(this, InputCardsActivity.class);
        intent.putExtra(getString(R.string.pack_type_key), getString(R.string.classic_pack_key));
        startActivity(intent);
    }

    public void addTgtPack(View view) {
        Intent intent = new Intent(this, InputCardsActivity.class);
        intent.putExtra(getString(R.string.pack_type_key), getString(R.string.tgt_pack_key));
        startActivity(intent);
    }

    public void addWotogPack(View view) {
        Intent intent = new Intent(this, InputCardsActivity.class);
        intent.putExtra(getString(R.string.pack_type_key), getString(R.string.wotog_pack_key));
        startActivity(intent);
    }

}
