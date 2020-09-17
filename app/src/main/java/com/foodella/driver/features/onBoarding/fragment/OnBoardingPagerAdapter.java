package com.foodella.driver.features.onBoarding.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OnBoardingPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<OnBoarding> onBoardingList;

    public OnBoardingPagerAdapter(FragmentManager manager, ArrayList<OnBoarding> onBoardingList) {
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.onBoardingList = onBoardingList;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return OnBoardingFragment.newInstance(onBoardingList.get(position));
    }

    @Override
    public int getCount() {
        return onBoardingList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}