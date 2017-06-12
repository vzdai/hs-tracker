package com.mystrel.hstracker.AddGame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.Utils;
import com.mystrel.hstracker.ViewStats.PacksStatsRow;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vivian on 2016-07-10.
 */
public class AddGameActivity extends AppCompatActivity {

    private List<Deck> classes;
    private List<Deck> yourDecks;
    private OpponentDeckAdapter opponentDeckAdapter;
    private YourDeckAdapter yourDeckAdapter;

    private Deck yourDeck;
    private Deck theirDeck;
    private Boolean didYouWin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setClasses();
        setYourDecks();
        setLists();
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
                JSONObject decks = data.getJSONObject(getString(R.string.decks_key));

                Iterator<String> iterator = decks.keys();
                while(iterator.hasNext()) {
                    String deckName = iterator.next();
                    JSONObject deckDetails = decks.getJSONObject(deckName);
                    String deckClass = deckDetails.getString(getString(R.string.deck_class_key));
                    yourDecks.add(new Deck(this, deckClass, deckName, true));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setLists() {
        ListView yourDecksView = (ListView) findViewById(R.id.yourDecks);
        yourDecksView.setSelector(R.drawable.deck_choice_selector);
        yourDeckAdapter = new YourDeckAdapter(this, yourDecks);
        yourDecksView.setAdapter(yourDeckAdapter);

        addNewDeckButton(yourDecksView);

        ListView theirDecksView = (ListView) findViewById(R.id.theirDecks);
        theirDecksView.setSelector(R.drawable.deck_choice_selector);
        opponentDeckAdapter = new OpponentDeckAdapter(this, classes);
        theirDecksView.setAdapter(opponentDeckAdapter);

        yourDecksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yourDeck = (Deck) parent.getItemAtPosition(position);
            }
        });

        theirDecksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                theirDeck = (Deck) parent.getItemAtPosition(position);
            }
        });
    }

    private void addNewDeckButton(ListView listView) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.button_add_deck, null);
        Button addDeckButton = (Button) view.findViewById(R.id.addDeckButton);

        addDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGameActivity.this, AddDeckActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        listView.addFooterView(addDeckButton);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String deckClass = data.getStringExtra("deckClass");
                String deckName = data.getStringExtra("deckName");
                yourDecks.add(new Deck(this, deckClass, deckName, true));
                yourDeckAdapter.notifyDataSetChanged();
            }
        }
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

    public void addGame(View view) {
        if(yourDeck == null) {
            Toast.makeText(this, getString(R.string.invalid_your_deck), Toast.LENGTH_SHORT).show();
        } else if(theirDeck == null) {
            Toast.makeText(this, getString(R.string.invalid_their_deck), Toast.LENGTH_SHORT).show();
        } else if(didYouWin == null) {
            Toast.makeText(this, getString(R.string.invalid_win_loss), Toast.LENGTH_SHORT).show();
        } else {
            addGameToJson();
            Toast.makeText(this, "Game added!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void initializeOverallData(JSONObject data) {
        try {
            JSONObject overall = new JSONObject();
            overall.put(getString(R.string.wins_key), 0);
            overall.put(getString(R.string.losses_key), 0);

            JSONObject vsClasses = new JSONObject();

            String[] classStrings = getResources().getStringArray(R.array.classes);
            for(String string : classStrings) {
                JSONObject winLoss = new JSONObject();
                winLoss.put(getString(R.string.wins_key), 0);
                winLoss.put(getString(R.string.losses_key), 0);
                vsClasses.put(string, winLoss);
            }
            overall.put(getString(R.string.vs_classes_key), vsClasses);
            data.put(getString(R.string.overall_games_key), overall);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWinLoss(JSONObject object) {
        try {
            if(didYouWin) {
                String winsKey = getString(R.string.wins_key);
                int prevWins = object.getInt(winsKey);
                object.put(winsKey, prevWins + 1);
            } else {
                String lossesKey = getString(R.string.losses_key);
                int prevLosses = object.getInt(lossesKey);
                object.put(lossesKey, prevLosses + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGameToJson() {
        JSONObject data = Utils.loadData(getString(R.string.decks_file), this);

        try {
            if(!data.has(getString(R.string.overall_games_key))) {
                initializeOverallData(data);
            }

            JSONObject decks = data.getJSONObject(getString(R.string.decks_key));
            JSONObject yourDeckObj = decks.getJSONObject(yourDeck.getDeckTitle());
            updateWinLoss(yourDeckObj);

            JSONObject vsClasses = yourDeckObj.getJSONObject(getString(R.string.vs_classes_key));
            JSONObject classObj = vsClasses.getJSONObject(theirDeck.getDeckClass());
            updateWinLoss(classObj);

            JSONObject overallObj = data.getJSONObject(getString(R.string.overall_games_key));
            updateWinLoss(overallObj);

            JSONObject overallVsClasses = overallObj.getJSONObject(getString(R.string.vs_classes_key));
            JSONObject overallClassObj = overallVsClasses.getJSONObject(theirDeck.getDeckClass());
            updateWinLoss(overallClassObj);

            Utils.saveData(getString(R.string.decks_file), data, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
