package com.mystrel.hstracker.ViewStats;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-26.
 */
public class GamesPageFragment extends Fragment{
    Context context;
    int page;

    public static GamesPageFragment newInstance(int page, Context context) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.fragment_page_key), page);
        GamesPageFragment fragment = new GamesPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("fragment_page");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_games, container, false);
        return view;
    }
}
