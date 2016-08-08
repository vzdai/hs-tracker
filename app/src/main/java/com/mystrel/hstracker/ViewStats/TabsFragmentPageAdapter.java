package com.mystrel.hstracker.ViewStats;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mystrel.hstracker.R;

/**
 * Created by Vivian on 2016-07-16.
 */
public class TabsFragmentPageAdapter extends FragmentPagerAdapter {
    private String tabTitles[];
    private Context context;

    public TabsFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabTitles = new String[] {
                context.getString(R.string.packs_tab),
                context.getString(R.string.games_tab)
                /*
                ,
                context.getString(R.string.quests_tab)
                */
        };
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) return PacksPageFragment.newInstance(position + 1, context);
        else if(position == 1) return GamesPageFragment.newInstance(position +1, context);
        else return QuestsPageFragment.newInstance(position + 1, context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
