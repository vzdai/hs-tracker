package com.mystrel.hstracker.AddGame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivian on 2016-07-16.
 */
public class AddDeckActivity extends AddGameActivity {

    private List<AddDeckClassIcon> classIcons;
    private AddDeckClassIcon selectedItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_deck);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        classIcons = new ArrayList<>();

        LinearLayout row1 = (LinearLayout) findViewById(R.id.classRow1);
        LinearLayout row2 = (LinearLayout) findViewById(R.id.classRow2);

        populateClassChoices(row1);
        populateClassChoices(row2);

        setAddDeckButton();
    }

    private void populateClassChoices(LinearLayout layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if(view instanceof AddDeckClassIcon) {
                AddDeckClassIcon item = (AddDeckClassIcon) view;
                classIcons.add(item);

                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(selectedItem != null) {
                            selectedItem.setBackgroundColor(Color.TRANSPARENT);
                        }
                        v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.selector_selected));
                        selectedItem = (AddDeckClassIcon) v;
                    }
                });
            }
        }
    }

    private void setAddDeckButton() {
        Button button = (Button) findViewById(R.id.addDeckButton);
        if(button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText) findViewById(R.id.deckName);
                    if(editText != null) {
                        String deckName = editText.getText().toString();
                        if(deckName.length() == 0) {
                            Toast.makeText(AddDeckActivity.this, R.string.invalid_deck_name, Toast.LENGTH_SHORT).show();
                        } else if(selectedItem == null) {
                            Toast.makeText(AddDeckActivity.this, R.string.invalid_select_class, Toast.LENGTH_SHORT).show();
                        } else {
                            addDeckToJson(deckName, selectedItem.getClassName());
                            finish();
                        }
                    }
                }
            });
        }
    }

    private void addDeckToJson(String deckName, String className) {
        JSONObject decksBefore = Utils.loadData(getString(R.string.decks_file), this);

        JSONObject newDeck = new JSONObject();
        try {
            newDeck.put(getString(R.string.deck_class_key), className);
            newDeck.put(getString(R.string.wins_key), 0);
            newDeck.put(getString(R.string.losses_key), 0);

            JSONObject vsClasses = new JSONObject();

            String[] classStrings = getResources().getStringArray(R.array.classes);
            for(String string : classStrings) {
                JSONObject winLoss = new JSONObject();
                winLoss.put(getString(R.string.wins_key), 0);
                winLoss.put(getString(R.string.losses_key), 0);
                vsClasses.put(string, winLoss);
            }

            newDeck.put(getString(R.string.vs_classes_key), vsClasses);

            JSONObject decks;
            if(decksBefore == null) {
                decksBefore = new JSONObject();
                decks = new JSONObject();
            } else {
                decks = decksBefore.getJSONObject(getString(R.string.decks_key));
            }

            decks.put(deckName, newDeck);
            decksBefore.put(getString(R.string.decks_key), decks);
            Utils.saveData(getString(R.string.decks_file), decksBefore, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
