package com.mystrel.hstracker.Options;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mystrel.hstracker.R;

import java.io.File;

/**
 * Created by Vivian on 2016-07-13.
 */
public class OptionsActivity extends AppCompatActivity {

    String[] ALL_FILES;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ALL_FILES = new String[] {getString(R.string.packs_file), getString(R.string.decks_file),
            getString(R.string.quests_file)};
    }

    public void resetStats(View view) {
        File dir = getFilesDir();

        for(String fileName : ALL_FILES) {
            File file = new File(dir, fileName);
            boolean deleted = file.delete();
        }
    }
}
