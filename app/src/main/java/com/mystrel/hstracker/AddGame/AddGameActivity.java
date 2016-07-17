package com.mystrel.hstracker.AddGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.mystrel.hstracker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vivian on 2016-07-10.
 */
public class AddGameActivity extends AppCompatActivity {

    private List<Deck> classes;
    private OpponentDeckAdapter opponentDeckAdapter;
    private YourDeckAdapter yourDeckAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        classes = new ArrayList<>();

        String[] classStrings = getResources().getStringArray(R.array.classes);
        for(String string : classStrings) {
            classes.add(new Deck(this, string, string, false));
        }

        setAdapters();
    }

    private void setAdapters() {
        ListView yourDecks = (ListView) findViewById(R.id.yourDecks);
        yourDecks.setSelector(R.drawable.deck_choice_selector);
        yourDeckAdapter = new YourDeckAdapter(this, classes); // change to your decks
        yourDecks.setAdapter(yourDeckAdapter);

        addNewDeckButton(yourDecks);

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

}
