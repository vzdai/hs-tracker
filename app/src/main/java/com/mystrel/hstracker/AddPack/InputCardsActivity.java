package com.mystrel.hstracker.AddPack;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vivian on 2016-07-13.
 */
public class InputCardsActivity extends AppCompatActivity {

    private HashMap<String, Integer> cards;

    String packType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pack_input_cards);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cards = new HashMap<>();

        packType = getIntent().getStringExtra(getString(R.string.pack_type_key));
    }

    private void initializeCardsMap() {

        cards.put(getString(R.string.regular_common_key), 0);
        cards.put(getString(R.string.golden_common_key), 0);
        cards.put(getString(R.string.regular_rare_key), 0);
        cards.put(getString(R.string.golden_rare_key), 0);
        cards.put(getString(R.string.regular_epic_key), 0);
        cards.put(getString(R.string.golden_epic_key), 0);
        cards.put(getString(R.string.regular_legendary_key), 0);
        cards.put(getString(R.string.golden_legendary_key), 0);

    }

    public void addPack(View view) {
        InputCardsRow commonRow = (InputCardsRow) findViewById(R.id.commonRow);
        InputCardsRow rareRow = (InputCardsRow) findViewById(R.id.rareRow);
        InputCardsRow epicRow = (InputCardsRow) findViewById(R.id.epicRow);
        InputCardsRow legendaryRow = (InputCardsRow) findViewById(R.id.legendaryRow);

        int totalCards = 0;

        if(commonRow != null) {
            int regularCount = commonRow.getRegularCount();
            int goldenCount = commonRow.getGoldenCount();

            totalCards += regularCount + goldenCount;

            cards.put(getString(R.string.regular_common_key), regularCount);
            cards.put(getString(R.string.golden_common_key), goldenCount);
        }

        if(rareRow != null) {
            int regularCount = rareRow.getRegularCount();
            int goldenCount = rareRow.getGoldenCount();

            totalCards += regularCount + goldenCount;

            cards.put(getString(R.string.regular_rare_key), regularCount);
            cards.put(getString(R.string.regular_rare_key), goldenCount);
        }

        if(epicRow != null) {
            int regularCount = epicRow.getRegularCount();
            int goldenCount = epicRow.getGoldenCount();

            totalCards += regularCount + goldenCount;

            cards.put(getString(R.string.regular_epic_key), regularCount);
            cards.put(getString(R.string.golden_epic_key), goldenCount);
        }

        if(legendaryRow != null) {
            int regularCount = legendaryRow.getRegularCount();
            int goldenCount = legendaryRow.getGoldenCount();

            totalCards += regularCount + goldenCount;

            cards.put(getString(R.string.regular_legendary_key), regularCount);
            cards.put(getString(R.string.golden_legendary_key), goldenCount);
        }

        if(totalCards != 5) {
            Toast.makeText(this, getString(R.string.invalid_num_cards), Toast.LENGTH_SHORT).show();
        } else {
            updateData();
            setResult(100);
            finish();
        }
    }

    private void updateData() {
        JSONObject oldData = Utils.loadData(getString(R.string.packs_file), this);
        JSONObject newData = new JSONObject();

        Integer numPacks;
        JSONObject cardsPerType = new JSONObject();
        JSONObject packsSinceType = new JSONObject();

        try {
            if(oldData == null) { // putting in the first pack, old data doesn't exist yet
                numPacks = 1;

                for(Map.Entry<String, Integer> entry : cards.entrySet()) {

                    int numCardsPerType = entry.getValue() != 0 ? entry.getValue() : 0;
                    cardsPerType.put(entry.getKey(), numCardsPerType);

                    int numPacksSinceType = entry.getValue() == 0 ? 1 : 0;
                    packsSinceType.put(entry.getKey(), numPacksSinceType);

                }

            } else {
                numPacks = oldData.getInt(getString(R.string.num_packs_key));
                numPacks = numPacks + 1;

                JSONObject oldCardsPerType = oldData.getJSONObject(getString(R.string.cards_per_type_key));
                JSONObject oldPacksSinceType = oldData.getJSONObject(getString(R.string.packs_since_type_key));

                for(Map.Entry<String, Integer> entry : cards.entrySet()) {
                    Integer oldCardsPerTypeEntry = oldCardsPerType.getInt(entry.getKey());
                    oldCardsPerTypeEntry += entry.getValue();
                    cardsPerType.put(entry.getKey(), oldCardsPerTypeEntry);

                    Integer oldPacksSinceTypeEntry = oldPacksSinceType.getInt(entry.getKey());
                    int numPacksSinceType = entry.getValue() == 0 ? oldPacksSinceTypeEntry + 1 : 0;
                    packsSinceType.put(entry.getKey(), numPacksSinceType);
                }
            }

            newData.put(getString(R.string.num_packs_key), numPacks);
            newData.put(getString(R.string.cards_per_type_key), cardsPerType);
            newData.put(getString(R.string.packs_since_type_key), packsSinceType);

            Utils.saveData(getString(R.string.packs_file), newData, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject packToJSON() {
        JSONObject data = new JSONObject();
        try {
            for(Map.Entry<String, Integer> entry : cards.entrySet()) {
                data.put(entry.getKey(), entry.getValue());
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
