package com.mystrel.hstracker.AddGame;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by Vivian on 2016-07-15.
 */
public class Deck {

    private String deckClass;
    private String deckTitle;
    private boolean own;
    private Drawable icon;

    public Deck(String deckClass, String deckTitle, boolean own) {
        this.deckClass = deckClass;
        this.deckTitle = deckTitle;
        this.own = own;

        String iconName = "icon_" + deckClass.toLowerCase();

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

}
