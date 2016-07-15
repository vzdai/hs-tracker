package com.mystrel.hstracker.ViewStats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mystrel.hstracker.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Vivian on 2016-07-10.
 */
public class ViewStatsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stats);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = (TextView) findViewById(R.id.text);
        loadPacksData(textView);

    }

    public void loadPacksData(TextView textView) {
        try {
            FileInputStream inputStream = openFileInput(getString(R.string.packs_file));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            textView.setText(stringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
