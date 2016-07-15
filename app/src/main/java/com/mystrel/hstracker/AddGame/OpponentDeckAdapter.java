package com.mystrel.hstracker.AddGame;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
        return deckList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public Deck getItem(int position) {
        return deckList.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            DeckChoiceItem item = new DeckChoiceItem(activity);
            item.inflateView(parent);
            convertView = item;

            holder = new ViewHolder();
            holder.deckChoiceItem = item;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }


    static class ViewHolder {
        public DeckChoiceItem deckChoiceItem;
    }
}
