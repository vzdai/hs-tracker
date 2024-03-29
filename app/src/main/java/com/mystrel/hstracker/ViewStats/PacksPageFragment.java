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

        JSONObject data = Utils.loadData(getString(R.string.packs_file), getActivity());

        populateOverallData(view, data);
        populateClassicData(view, data);
        populateTgtData(view, data);
        populateWotogData(view, data);

        return view;
    }

    private void populateOverallData(View view, JSONObject data) {
        if(data != null) {
            try {
                JSONObject totalPacksObj = data.getJSONObject(getString(R.string.num_packs_key));
                int classicPacksNum = totalPacksObj.getInt(getString(R.string.classic_pack_key));
                int tgtPacksNum = totalPacksObj.getInt(getString(R.string.tgt_pack_key));
                int wotogPacksNum = totalPacksObj.getInt(getString(R.string.wotog_pack_key));

                JSONObject cardsPerType = data.getJSONObject(getString(R.string.cards_per_type_key));
                int common = cardsPerType.getInt(getString(R.string.regular_common_key));
                int rare = cardsPerType.getInt(getString(R.string.regular_rare_key));
                int epic = cardsPerType.getInt(getString(R.string.regular_epic_key));
                int legendary = cardsPerType.getInt(getString(R.string.regular_legendary_key));
                int gCommon = cardsPerType.getInt(getString(R.string.golden_common_key));
                int gRare = cardsPerType.getInt(getString(R.string.golden_rare_key));
                int gEpic = cardsPerType.getInt(getString(R.string.golden_epic_key));
                int gLegendary = cardsPerType.getInt(getString(R.string.golden_legendary_key));

                int totalDustNum = common * DUST_COMMON + gCommon * DUST_GOLDEN_COMMON +
                        rare * DUST_RARE + gRare * DUST_GOLDEN_RARE +
                        epic * DUST_EPIC + gEpic * DUST_GOLDEN_EPIC +
                        legendary * DUST_LEGENDARY + gLegendary * DUST_GOLDEN_LEGENDARY;

                TextView totalPacks = (TextView) view.findViewById(R.id.total_packs);
                String totalPacksString = getString(R.string.total_packs);
                totalPacks.setText(String.format(
                        totalPacksString, classicPacksNum, tgtPacksNum, wotogPacksNum));

                TextView totalDust = (TextView) view.findViewById(R.id.total_dust);
                String totalDustString = getString(R.string.total_dust);
                totalDust.setText(String.format(totalDustString, totalDustNum));

                TextView dustPerPack = (TextView) view.findViewById(R.id.dust_per_pack);
                String dustPerPackString = getString(R.string.dust_per_pack);
                dustPerPack.setText(String.format(dustPerPackString,
                        (float) totalDustNum / (classicPacksNum + tgtPacksNum + wotogPacksNum)));

                int numCards = (classicPacksNum + tgtPacksNum + wotogPacksNum) * 5;
                SincePackSection totalSection = (SincePackSection) view.findViewById(R.id.totalCardsSection);
                totalSection.setTitle(getString(R.string.total_card_type_stats));
                totalSection.setCommonRow(common, (float) common / numCards, gCommon, (float) gCommon / numCards);
                totalSection.setRareRow(rare, (float) rare / numCards, gRare, (float) gRare / numCards);
                totalSection.setEpicRow(epic, (float) epic / numCards, gEpic, (float) gEpic / numCards);
                totalSection.setLegendaryRow(legendary, (float) legendary / numCards, gLegendary, (float) gLegendary / numCards);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void populateClassicData(View view, JSONObject data) {
        try {
            JSONObject object = data.getJSONObject(getString(R.string.classic_packs_since_key));
            SincePackSection section = (SincePackSection) view.findViewById(R.id.classicSection);
            fillSection(object, section, getString(R.string.classic_pack_key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateTgtData(View view, JSONObject data) {
        try {
            JSONObject object = data.getJSONObject(getString(R.string.tgt_packs_since_key));
            SincePackSection section = (SincePackSection) view.findViewById(R.id.tgtSection);
            fillSection(object, section, getString(R.string.tgt_pack_key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateWotogData(View view, JSONObject data) {
        try {
            JSONObject object = data.getJSONObject(getString(R.string.wotog_packs_since_key));
            SincePackSection section = (SincePackSection) view.findViewById(R.id.wotogSection);
            fillSection(object, section, getString(R.string.wotog_pack_key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillSection(JSONObject sectionData, SincePackSection sectionView, String packName) {
        try {
            int sinceCommon = sectionData.getInt(getString(R.string.regular_common_key));
            int sinceRare = sectionData.getInt(getString(R.string.regular_rare_key));
            int sinceEpic = sectionData.getInt(getString(R.string.regular_epic_key));
            int sinceLegendary = sectionData.getInt(getString(R.string.regular_legendary_key));
            int sinceGCommon = sectionData.getInt(getString(R.string.golden_common_key));
            int sinceGRare = sectionData.getInt(getString(R.string.golden_rare_key));
            int sinceGEpic = sectionData.getInt(getString(R.string.golden_epic_key));
            int sinceGLegendary = sectionData.getInt(getString(R.string.golden_legendary_key));

            String unformattedTitle = getString(R.string.packs_since_last);
            sectionView.setTitle(String.format(unformattedTitle, packName));
            sectionView.setCommonRow(sinceCommon, 0, sinceGCommon, 0);
            sectionView.setRareRow(sinceRare, 0, sinceGRare, 0);
            sectionView.setEpicRow(sinceEpic, 0, sinceGEpic, 0);
            sectionView.setLegendaryRow(sinceLegendary, 0, sinceGLegendary, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
