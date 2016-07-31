package com.mystrel.hstracker.AddGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivian on 2016-07-10.
 */
public class AddGameActivity extends AppCompatActivity {

    private List<Deck> classes;
    private List<Deck> yourDecks;
    private OpponentDeckAdapter opponentDeckAdapter;
    private YourDeckAdapter yourDeckAdapter;
    private Boolean didYouWin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setClasses();
        setYourDecks();
        setAdapters();
    }

    private void setClasses() {
        classes = new ArrayList<>();

        String[] classStrings = getResources().getStringArray(R.array.classes);
        for(String string : classStrings) {
            classes.add(new Deck(this, string, string, false));
        }
    }

    private void setYourDecks() {
        yourDecks = new ArrayList<>();
        JSONObject data = Utils.loadData(getString(R.string.decks_file), this);

        if(data != null) {
            try {
                JSONArray decks = data.getJSONArray(getString(R.string.decks_file));

                for(int i = 0; i < decks.length(); i++) {
                    JSONObject deck = decks.getJSONObject(i);

                    String name = deck.getString(getString(R.string.deck_name_key));
                    String deckClass = deck.getString(getString(R.string.deck_class_key));

                    yourDecks.add(new Deck(this, deckClass, name, true));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setAdapters() {
        ListView yourDecksView = (ListView) findViewById(R.id.yourDecks);
        yourDecksView.setSelector(R.drawable.deck_choice_selector);
        yourDeckAdapter = new YourDeckAdapter(this, yourDecks);
        yourDecksView.setAdapter(yourDeckAdapter);

        addNewDeckButton(yourDecksView);

        ListView theirDecks = (ListView) findViewById(R.id.theirDecks);
        theirDecks.setSelector(R.drawable.deck_choice_selector);
        opponentDeckAdapter = new OpponentDeckAdapter(this, classes);
        theirDecks.setAdapter(opponentDeckAdapter);
    }

    private void addNewDeckButton(ListView listView) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.button_add_deck, null);
        Button addDeckButton = (Button) view.findViewById(R.id.addDeckButton);

        addDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGameActivity.this, AddDeckActivity.class);
                startActivity(intent);
            }
        });

        listView.addFooterView(addDeckButton);
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.yesCheckbox:
                if(checked) {
                    ((CheckBox) findViewById(R.id.noCheckbox)).setChecked(false);
                    didYouWin = true;
                } else {
                    didYouWin = null;
                }
                break;
            case R.id.noCheckbox:
                if(checked) {
                    ((CheckBox) findViewById(R.id.yesCheckbox)).setChecked(false);
                    didYouWin = false;
                } else {
                    didYouWin = null;
                }
                break;
        }
    }

}
