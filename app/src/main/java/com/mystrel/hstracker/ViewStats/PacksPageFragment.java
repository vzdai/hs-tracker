package com.mystrel.hstracker.ViewStats;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mystrel.hstracker.R;
import com.mystrel.hstracker.Utils;

import org.json.JSONObject;

/**
 * Created by Vivian on 2016-07-26.
 */
public class PacksPageFragment extends Fragment {
    Context context;
    int page;

    int DUST_COMMON = 5;
    int DUST_RARE = 20;
    int DUST_EPIC = 100;
    int DUST_LEGENDARY = 400;

    int DUST_GOLDEN_COMMON = 50;
    int DUST_GOLDEN_RARE = 100;
    int DUST_GOLDEN_EPIC = 400;
    int DUST_GOLDEN_LEGENDARY = 1600;

    public static PacksPageFragment newInstance(int page, Context context) {
        Bundle args = new Bundle();
        args.putInt(context.getString(R.string.fragment_page_key), page);
        PacksPageFragment fragment = new PacksPageFragment();
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
        View view = inflater.inflate(R.layout.stats_packs, container, false);

        populateData(view);

        return view;
    }

    private void populateData(View view) {
        JSONObject data = Utils.loadData(getString(R.string.packs_file), getActivity());

        if(data != null) {
            try {
                int totalPacksNum = data.getInt(getString(R.string.num_packs_key));

                JSONObject cardsPerType = data.getJSONObject(getString(R.string.cards_per_type_key));
                int common = cardsPerType.getInt(getString(R.string.regular_common_key));
                int rare = cardsPerType.getInt(getString(R.string.regular_rare_key));
                int epic = cardsPerType.getInt(getString(R.string.regular_epic_key));
                int legendary = cardsPerType.getInt(getString(R.string.regular_legendary_key));
                int gCommon = cardsPerType.getInt(getString(R.string.golden_common_key));
                int gRare = cardsPerType.getInt(getString(R.string.golden_rare_key));
                int gEpic = cardsPerType.getInt(getString(R.string.golden_epic_key));
                int gLegendary = cardsPerType.getInt(getString(R.string.golden_legendary_key));

                JSONObject packsSinceType = data.getJSONObject(getString(R.string.packs_since_type_key));
                int sinceCommon = packsSinceType.getInt(getString(R.string.regular_common_key));
                int sinceRare = packsSinceType.getInt(getString(R.string.regular_rare_key));
                int sinceEpic = packsSinceType.getInt(getString(R.string.regular_epic_key));
                int sinceLegendary = packsSinceType.getInt(getString(R.string.regular_legendary_key));
                int sinceGCommon = packsSinceType.getInt(getString(R.string.golden_common_key));
                int sinceGRare = packsSinceType.getInt(getString(R.string.golden_rare_key));
                int sinceGEpic = packsSinceType.getInt(getString(R.string.golden_epic_key));
                int sinceGLegendary = packsSinceType.getInt(getString(R.string.golden_legendary_key));

                int totalDustNum = common * DUST_COMMON + gCommon * DUST_GOLDEN_COMMON +
                        rare * DUST_RARE + gRare * DUST_GOLDEN_RARE +
                        epic * DUST_EPIC + gEpic * DUST_GOLDEN_EPIC +
                        legendary * DUST_LEGENDARY + gLegendary * DUST_GOLDEN_LEGENDARY;



                TextView totalPacks = (TextView) view.findViewById(R.id.total_packs);
                String totalPacksString = getString(R.string.total_packs);
                totalPacks.setText(String.format(totalPacksString, totalPacksNum));

                TextView totalDust = (TextView) view.findViewById(R.id.total_dust);
                String totalDustString = getString(R.string.total_dust);
                totalDust.setText(String.format(totalDustString, totalDustNum));

                TextView dustPerPack = (TextView) view.findViewById(R.id.dust_per_pack);
                String dustPerPackString = getString(R.string.dust_per_pack);
                dustPerPack.setText(String.format(dustPerPackString, (float) totalDustNum / totalPacksNum));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
