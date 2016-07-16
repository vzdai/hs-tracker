package com.mystrel.hstracker.AddGame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by Vivian on 2016-07-15.
 */
public class Deck {

    private Context context;
    private String deckClass;
    private String deckTitle;
    private boolean own;
    private int icon;

    public Deck(Context context, String deckClass, String deckTitle, boolean own) {
        this.deckClass = deckClass;
        this.deckTitle = deckTitle;
        this.own = own;

        String iconName = "icon_" + deckClass.toLowerCase();

        icon = context.getResources().getIdentifier(iconName, "drawable", context.getPackageName());
    }

    public void setDeckClass(String deckClass) {
        this.deckClass = deckClass;
    }

    public String getDeckClass() {
        return deckClass;
    }

    public void setDeckTitle(String title) {
        this.deckTitle = title;
    }

    public String getDeckTitle() {
        return deckTitle;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public boolean getOwn() {
        return own;
    }

    public int getIcon() {
        return icon;
    }

}
