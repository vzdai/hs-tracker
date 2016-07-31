package com.mystrel.hstracker.ViewStats;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Vivian on 2016-07-26.
 */
public class GamesPageFragment extends Fragment{
    Context context;
    int page;
    JSONObject data;
    LinearLayout mainContainer;
    LinearLayout statsContainer;

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
        LinearLayout outerContainer = (LinearLayout) inflater.inflate(R.layout.stats_games, container, false);
        mainContainer = (LinearLayout) outerContainer.findViewById(R.id.innerContainer);
        statsContainer = (LinearLayout) mainContainer.findViewById(R.id.dynamicStats);
        data = Utils.loadData(getString(R.string.decks_file), getActivity());

        Spinner spinner = (Spinner) mainContainer.findViewById(R.id.spinner);
        setSpinnerValues(spinner);
        setSpinnerSelectedListener(spinner);

        return outerContainer;
    }

    private void setSpinnerValues(Spinner spinner) {
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add(getString(R.string.overall));

        try {
            JSONObject decks = data.getJSONObject(getString(R.string.decks_key));

            Iterator<String> iterator = decks.keys();
            while(iterator.hasNext()) {
                String deckName = iterator.next();
                spinnerArray.add(deckName);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerArray
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerSelectedListener(Spinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    JSONObject selectedData;
                    if(position == 0) {
                        String selected = getString(R.string.overall_games_key);
                        selectedData = data.getJSONObject(selected);
                    } else {
                        String selected = parent.getItemAtPosition(position).toString();
                        JSONObject decks = data.getJSONObject(getString(R.string.decks_key));
                        selectedData = decks.getJSONObject(selected);
                    }
                    initializeStats(selectedData);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }


    private void initializeStats(JSONObject data) {
        statsContainer.removeAllViews();

        WinsLossesSection overall = (WinsLossesSection) mainContainer.findViewById(R.id.overallWinLosses);

        try {
            int wins = data.getInt(getString(R.string.wins_key));
            int losses = data.getInt(getString(R.string.losses_key));
            overall.setStats(wins, losses, overall);

            JSONObject vsClasses = data.getJSONObject(getString(R.string.vs_classes_key));

            Iterator<String> iterator = vsClasses.keys();
            while(iterator.hasNext()) {
                String className = iterator.next();
                JSONObject classStats = vsClasses.getJSONObject(className);
                int classWins = classStats.getInt(getString(R.string.wins_key));
                int classLosses = classStats.getInt(getString(R.string.losses_key));

                WinsLossesSection section = new WinsLossesSection(
                        getActivity(), statsContainer, className, classWins, classLosses);

                statsContainer.addView(section);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
