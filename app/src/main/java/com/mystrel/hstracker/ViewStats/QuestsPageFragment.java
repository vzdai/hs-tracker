package com.mystrel.hstracker.ViewStats;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-16.
 */
public class QuestsPageFragment extends Fragment {

    Context context;
    int page;

    public static QuestsPageFragment newInstance(int page, Context context) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.fragment_page_key), page);
        QuestsPageFragment fragment = new QuestsPageFragment();
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
        View view = inflater.inflate(R.layout.stats_quests, container, false);
        return view;
    }

}
