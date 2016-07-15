package com.mystrel.hstracker.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-14.
 */
public class DeckChoiceItem extends LinearLayout {
    public DeckChoiceItem(Context context) {
        super(context);
        inflateView(context);
    }

    public DeckChoiceItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.DeckChoiceItem);
        Drawable icon  = arr.getDrawable(R.styleable.DeckChoiceItem_deck_choice_icon);
        CharSequence text = arr.getString(R.styleable.DeckChoiceItem_deck_choice_text);

        if (icon != null) {
            ImageView imageView = (ImageView) findViewById(R.id.icon);
            imageView.setImageDrawable(icon);
        }

        if(text != null) {
            TextView textView = (TextView) findViewById(R.id.text);
            textView.setText(text);
        }

        arr.recycle();
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.deck_choice, this);
    }

}
