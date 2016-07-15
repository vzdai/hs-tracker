package com.mystrel.hstracker.AddGame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
            classes.add(new Deck(string, string, false));
        }

        setAdapters();
    }

    private void setAdapters() {
        ListView yourDecks = (ListView) findViewById(R.id.yourDecks);
        yourDeckAdapter = new YourDeckAdapter();
        yourDecks.setAdapter(yourDeckAdapter);

        ListView theirDecks = (ListView) findViewById(R.id.theirDecks);
        opponentDeckAdapter = new OpponentDeckAdapter(this, classes);
        theirDecks.setAdapter(opponentDeckAdapter);
    }

}
