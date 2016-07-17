package com.mystrel.hstracker.AddGame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.mystrel.hstracker.R;

import java.util.List;

/**
 * Created by Vivian on 2016-07-14.
 */
public class YourDeckAdapter extends BaseAdapter {

    private Activity activity;
    private List<Deck> deckList;


    public YourDeckAdapter(Activity activity, List<Deck> deckList) {
        this.activity = activity;
        this.deckList = deckList;
    }

    public int getCount() {
        if(deckList == null) {
            return 0;
        } else {
            return deckList.size();
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public Deck getItem(int position) {
        if(deckList != null) {
            return deckList.get(position);
        } else {
            return null;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Deck deck = getItem(position);

        if(convertView == null) {
            DeckChoiceItem item = new DeckChoiceItem(activity);
            convertView = item.inflate(parent);
            item.setIcon(deck.getIcon());
            item.setText(deck.getDeckTitle());
        }

        return convertView;
    }



}
