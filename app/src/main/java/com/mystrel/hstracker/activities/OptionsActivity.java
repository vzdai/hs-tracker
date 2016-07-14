package com.mystrel.hstracker.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mystrel.hstracker.R;

import java.io.File;

/**
 * Created by Vivian on 2016-07-13.
 */
public class OptionsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void resetStats(View view) {
        File dir = getFilesDir();
        File file = new File(dir, getString(R.string.packs_file));
        boolean deleted = file.delete();

    }
}
