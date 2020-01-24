package com.example.mysubmission3.adapter;

import android.content.Context;

import com.example.mysubmission3.R;
import com.example.mysubmission3.fragment.F_Movie;
import com.example.mysubmission3.fragment.F_Tvshow;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final Context ctx;
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ctx = context;

    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fm = null;
        switch (position){
            case 0 :
                fm = new F_Movie();

                break;
            case 1 :
                fm = new F_Tvshow();

                break;
        }
        return fm;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return ctx.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }


}
