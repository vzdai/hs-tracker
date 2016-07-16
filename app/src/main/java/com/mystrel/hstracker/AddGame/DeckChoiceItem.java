package com.mystrel.hstracker.AddGame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-14.
 */
public class DeckChoiceItem extends LinearLayout {

    private Context context;
    private ImageView imageView;
    private TextView textView;

    public DeckChoiceItem(Context context) {
        super(context);
        this.context = context;
    }

    public DeckChoiceItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.DeckChoiceItem);
        Drawable icon  = arr.getDrawable(R.styleable.DeckChoiceItem_deck_choice_icon);
        CharSequence text = arr.getString(R.styleable.DeckChoiceItem_deck_choice_text);

        String string = null;
        if(text != null) {
            string = text.toString();
        }

        setIcon(icon);
        setText(string);

        arr.recycle();
    }

    public void setIcon(int icon) {
        imageView.setImageResource(icon);
    }

    public void setIcon(Drawable icon) {
        imageView.setImageDrawable(icon);
    }

    public void setText(String text) {
        if(text != null) {
            textView.setText(text);
        }
    }

    public View inflate(ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.deck_choice, parent, false);

        imageView = (ImageView) view.findViewById(R.id.icon);
        textView = (TextView) view.findViewById(R.id.text);

        return view;
    }

}
