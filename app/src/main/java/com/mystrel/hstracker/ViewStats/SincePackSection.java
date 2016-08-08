package com.mystrel.hstracker.ViewStats;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-30.
 */
public class SincePackSection extends LinearLayout{
    public SincePackSection(Context context) {
        super(context);
        inflateView(context);
    }

    public SincePackSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);

        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.SincePackSection);
        boolean hidePercentages = arr.getBoolean(R.styleable.SincePackSection_hide_percentages, false);
        if (hidePercentages) hidePercentages();
        arr.recycle();
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stats_packs_since_pack_section, this);
    }

    private void hidePercentages() {
        LinearLayout container = (LinearLayout) getChildAt(0);
        for(int i = 0; i < container.getChildCount(); i++) {
            View view = container.getChildAt(i);
            if(view instanceof PacksStatsRow) {
                ((PacksStatsRow) view).hidePercents();
            }
        }
    }

    public void setTitle(String title) {
        TextView titleView = (TextView) findViewById(R.id.title);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 20, 0, 10); // llp.setMargins(left, top, right, bottom);
        titleView.setLayoutParams(lp);
        titleView.setTextSize(16);
        titleView.setText(title);
    }

    private void setNumsForRow(PacksStatsRow row, int num, float pct, int gNum, float gPct) {
        row.setRegularNumber(num);
        row.setRegularPercent(pct);
        row.setGoldenNumber(gNum);
        row.setGoldenPercent(gPct);
    }

    public void setCommonRow(int num, float pct, int gNum, float gPct) {
        PacksStatsRow row = (PacksStatsRow) findViewById(R.id.commonRow);
        setNumsForRow(row, num, pct, gNum, gPct);
    }

    public void setRareRow(int num, float pct, int gNum, float gPct) {
        PacksStatsRow row = (PacksStatsRow) findViewById(R.id.rareRow);
        setNumsForRow(row, num, pct, gNum, gPct);
    }

    public void setEpicRow(int num, float pct, int gNum, float gPct) {
        PacksStatsRow row = (PacksStatsRow) findViewById(R.id.epicRow);
        setNumsForRow(row, num, pct, gNum, gPct);
    }

    public void setLegendaryRow(int num, float pct, int gNum, float gPct) {
        PacksStatsRow row = (PacksStatsRow) findViewById(R.id.legendaryRow);
        setNumsForRow(row, num, pct, gNum, gPct);
    }
}
