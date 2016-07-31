package com.mystrel.hstracker.AddGame;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystrel.hstracker.R;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Vivian on 2016-07-14.
 */
public class OpponentDeckAdapter extends BaseAdapter {

    private Activity activity;
    private List<Deck> deckList;

    public OpponentDeckAdapter(Activity activity, List<Deck> deckList) {
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
        View itemView = convertView;

        if(convertView == null) {
            DeckChoiceItem item = new DeckChoiceItem(activity);
            itemView = item.inflate(parent);
        }

        ((ImageView) itemView.findViewById(R.id.icon)).setImageResource(deck.getIcon());
        ((TextView) itemView.findViewById(R.id.text)).setText(deck.getDeckTitle());

        return itemView;
    }

}
