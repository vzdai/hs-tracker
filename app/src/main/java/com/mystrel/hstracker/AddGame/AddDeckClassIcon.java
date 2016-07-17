package com.mystrel.hstracker.AddGame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-16.
 */
public class AddDeckClassIcon extends LinearLayout {
    private String className;

    public AddDeckClassIcon(Context context) {
        super(context);
        inflateView(context);
    }

    public AddDeckClassIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.AddDeckClassIcon);
        CharSequence className = arr.getString(R.styleable.AddDeckClassIcon_class_name);

        if(className != null) {
            this.className = className.toString();
        }

        Drawable classIcon = arr.getDrawable(R.styleable.AddDeckClassIcon_class_icon);
        ImageView imageView = (ImageView) findViewById(R.id.iconImage);
        imageView.setImageDrawable(classIcon);

        arr.recycle();
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.add_deck_class_icon, this);
    }

    public String getClassName() {
        return className;
    }

}
