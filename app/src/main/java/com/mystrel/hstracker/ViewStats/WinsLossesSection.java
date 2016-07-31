package com.mystrel.hstracker.ViewStats;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-31.
 */
public class WinsLossesSection extends TableLayout {
    public WinsLossesSection(Context context) {
        super(context);
        inflateView(context);
    }

    public WinsLossesSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public WinsLossesSection(Context context, ViewGroup container, String title, int wins, int losses) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.stats_games_win_loss, this);
        setTitle(title, view);
        setStats(wins, losses, view);
    }

    private void inflateView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.stats_games_win_loss, this);
    }

    public void setTitle(String title, View view) {
        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setVisibility(VISIBLE);
        titleView.setText(title);
    }

    public void setStats(int wins, int losses, View view) {
        TextView winsText = (TextView) view.findViewById(R.id.wins);
        winsText.setText(String.valueOf(wins));

        TextView lossesText = (TextView) view.findViewById(R.id.losses);
        lossesText.setText(String.valueOf(losses));

        TextView winPercentText = (TextView) view.findViewById(R.id.winPercent);

        float percent;
        if(wins + losses == 0) {
            percent = 0;
        } else {
            percent = (float) wins * 100 / (wins + losses);
        }

        winPercentText.setText(String.format("%.2f%%", percent));
    }
}
