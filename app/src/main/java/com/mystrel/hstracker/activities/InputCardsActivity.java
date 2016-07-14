package com.mystrel.hstracker.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.components.InputCardsRow;

import org.json.JSONObject;

import java.io.FileOutputStream;

/**
 * Created by Vivian on 2016-07-13.
 */
public class InputCardsActivity extends AppCompatActivity {

    int regularCommon, goldenCommon, regularRare, goldenRare,
            regularEpic, goldenEpic, regularLegendary, goldenLegendary;

    String packType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pack_input_cards);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        regularCommon = goldenCommon = regularRare = goldenRare =
                regularEpic = goldenEpic = regularLegendary = goldenLegendary = 0;

        packType = getIntent().getStringExtra(getString(R.string.pack_type_key));
    }

    public void addPack(View view) {
        InputCardsRow commonRow = (InputCardsRow) findViewById(R.id.commonRow);
        InputCardsRow rareRow = (InputCardsRow) findViewById(R.id.rareRow);
        InputCardsRow epicRow = (InputCardsRow) findViewById(R.id.epicRow);
        InputCardsRow legendaryRow = (InputCardsRow) findViewById(R.id.legendaryRow);

        if(commonRow != null) {
            regularCommon = commonRow.getRegularCount();
            goldenCommon = commonRow.getGoldenCount();
        }

        if(rareRow != null) {
            regularRare = rareRow.getRegularCount();
            goldenRare = rareRow.getGoldenCount();
        }

        if(epicRow != null) {
            regularEpic = epicRow.getRegularCount();
            goldenEpic = epicRow.getGoldenCount();
        }

        if(legendaryRow != null) {
            regularLegendary = legendaryRow.getRegularCount();
            goldenLegendary = legendaryRow.getGoldenCount();
        }

        if(regularCommon + goldenCommon + regularRare + goldenRare +
                regularEpic + goldenEpic + regularLegendary + goldenLegendary != 5) {
            Toast.makeText(this, getString(R.string.invalid_num_cards), Toast.LENGTH_SHORT).show();
        } else {
            saveData();
        }
    }

    private JSONObject packToJSON() {
        JSONObject data = new JSONObject();
        try {
            data.put(getString(R.string.pack_type_key), packType);
            data.put(getString(R.string.regular_common_key), regularCommon);
            data.put(getString(R.string.golden_common_key), goldenCommon);
            data.put(getString(R.string.regular_rare_key), regularRare);
            data.put(getString(R.string.golden_rare_key), goldenRare);
            data.put(getString(R.string.regular_epic_key), regularEpic);
            data.put(getString(R.string.golden_epic_key), goldenEpic);
            data.put(getString(R.string.regular_legendary_key), regularLegendary);
            data.put(getString(R.string.golden_legendary_key), goldenLegendary);

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveData() {
        try {
            FileOutputStream outputStream = openFileOutput(getString(R.string.packs_file), Context.MODE_APPEND);
            JSONObject data = packToJSON();
            if(data != null) {
                outputStream.write(data.toString().getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
