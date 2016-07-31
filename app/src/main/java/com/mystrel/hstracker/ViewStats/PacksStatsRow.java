package com.mystrel.hstracker.ViewStats;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-30.
 */
public class PacksStatsRow extends TableLayout {
    public PacksStatsRow(Context context) {
        super(context);
        inflateView(context);
    }

    public PacksStatsRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.PacksStatsRow);
        CharSequence row_label = arr.getString(R.styleable.PacksStatsRow_packs_stats_row_label);
        if (row_label != null) {
            setRowLabel(row_label.toString());
        }
        arr.recycle();
    }

    public void setRowLabel(String label) {
        TextView textView = (TextView) findViewById(R.id.card_type);
        textView.setText(label);
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stats_packs_row, this);
    }

    public void hidePercents() {
        TextView regularPercent = (TextView) findViewById(R.id.regular_percent);
        regularPercent.setVisibility(GONE);

        TextView goldenPercent = (TextView) findViewById(R.id.golden_percent);
        goldenPercent.setVisibility(GONE);

        TableLayout container = (TableLayout) findViewById(R.id.container);
        container.setColumnStretchable(0, true);
        container.setColumnStretchable(1, true);
        container.setColumnStretchable(2, true);
    }

    public void setRegularNumber(int number) {
        TextView regularNumber = (TextView) findViewById(R.id.regular_number);
        regularNumber.setText(String.valueOf(number));
    }

    public void setGoldenNumber(int number) {
        TextView goldenNumber = (TextView) findViewById(R.id.golden_number);
        goldenNumber.setText(String.valueOf(number));
    }

    public void setRegularPercent(float percent) {
        TextView regularPercent = (TextView) findViewById(R.id.regular_percent);
        regularPercent.setText(String.format("%.2f%%", percent * 100));
    }

    public void setGoldenPercent(float percent) {
        TextView regularNumber = (TextView) findViewById(R.id.golden_percent);
        regularNumber.setText(String.format("%.2f%%", percent * 100));
    }

}
