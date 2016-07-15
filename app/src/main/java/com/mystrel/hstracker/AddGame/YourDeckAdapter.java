package com.mystrel.hstracker.AddGame;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Vivian on 2016-07-14.
 */
public class YourDeckAdapter extends BaseAdapter {

    private List<Deck> deckList;


    public int getCount() {
        return deckList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public Deck getItem(int position) {
        return deckList.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
